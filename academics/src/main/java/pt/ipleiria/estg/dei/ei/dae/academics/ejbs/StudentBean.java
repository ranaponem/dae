package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

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

    public Student find(String username) throws RuntimeException {
        Student s = em.find(Student.class, username);
        if(s == null)
            throw new RuntimeException("Student with username " + username + " not found");

        return s;
    }

    public void create(String username, String password, String name, String email, long courseCode) {
        Course course = cb.find(courseCode);
        Student s = new Student(username, password, name, email, course);
        course.addStudent(s);
        em.persist(s);
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
}
