package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintViolationException;
import org.hibernate.Hibernate;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

import java.util.List;

@Stateless
public class StudentBean {
    @PersistenceContext
    private EntityManager em;

    @EJB
    private CourseBean cb;

    @EJB
    private SubjectBean sb;

    public List<Student> findAll() {
        return em.createNamedQuery("getAllStudents", Student.class).getResultList();
    }

    public Student find(String username) {
        return em.find(Student.class, username);
    }

    public boolean exists(String username) {
        Query query = em.createQuery(
                "SELECT COUNT(s.username) FROM Student s WHERE s.username = :username",
                Long.class
        );
        query.setParameter("username", username);
        return (Long)query.getSingleResult() > 0L;
    }

    public void create(String username, String password, String name, String email, long courseCode) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        if(exists(username))
            throw new MyEntityExistsException("Student with username " + username + " already exists");

        Course course = cb.find(courseCode);

        if(course == null)
            throw new MyEntityNotFoundException("Course with code " + courseCode + " not found");
        try{
            Student s = new Student(username, password, name, email, course);
            course.addStudent(s);
            em.persist(s);
            em.flush();
        }
        catch(ConstraintViolationException e){
            throw new MyConstraintViolationException(e);
        }
    }

    public void enrollStudentInSubject(String username, long subjectCode){
        Student stu = find(username);
        Subject sub = sb.find(subjectCode);
        if(sub.getStudents().contains(stu) || stu.getSubjects().contains(sub))
            return;

        if(sub.getCourse().equals(stu.getCourse())){
            stu.addSubject(sub);
            sub.addStudent(stu);
        }
    }

    public Student findWithSubjects(String username){
        var student = this.find(username);
        Hibernate.initialize(student.getSubjects());
        return student;
    }

    public void update(String username, String password, String name, String email, long courseCode) {
        Student student = find(username);
        if (student == null) {
            System.err.println("ERROR_STUDENT_NOT_FOUND: " + username);
            return;
        }

        em.lock(student, LockModeType.OPTIMISTIC);

        student.setPassword(password);
        student.setName(name);
        student.setEmail(email);

        if (student.getCourse().getCode() != courseCode) {
            Course course = cb.find(courseCode);
            if (course == null) {
                System.err.println("ERROR_COURSE_NOT_FOUND: " + courseCode);
                return;
            }

            student.setCourse(course);
        }
    }
}
