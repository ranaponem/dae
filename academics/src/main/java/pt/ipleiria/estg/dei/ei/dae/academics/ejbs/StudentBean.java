package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.util.List;

@Stateless
public class StudentBean {
    @PersistenceContext
    private EntityManager em;

    @EJB
    private CourseBean cb;

    public List<Student> findAll() {
        return em.createNamedQuery("getAllStudents", Student.class).getResultList();
    }

    public Student find(String username){
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
        em.persist(course);
    }
}
