package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

@Singleton
@Startup
public class ConfigBean {
    @EJB
    private StudentBean studentBean;

    @EJB
    private CourseBean courseBean;

    @EJB
    private SubjectBean subjectBean;

    @EJB
    private TeacherBean teacherBean;

    @EJB
    private AdministratorBean administratorBean;

    @PostConstruct
    public void populateDB(){
        courseBean.create(1, "EI");
        courseBean.create(2, "Jogos");
        subjectBean.create(1, "DAE", "2024/25", 3, 1);
        subjectBean.create(2, "DAD", "2024/25", 3, 1);
        studentBean.create("marau", "123", "Rafael", "123@gmail.com",1);
        studentBean.create("zexxx", "123", "Jose", "456@gmail.com",2);
        studentBean.create("lol", "123", "Manel", "123@hotmail.com",1);
        studentBean.create("archwiki", "123", "Arch", "456@hotmail.com",2);
        studentBean.enrollStudentInSubject("marau", 1);
        studentBean.enrollStudentInSubject("marau", 2);
        teacherBean.create("patriciomoment", "123", "Patricio", "pat@ricio.pt", "GA1.16");
        administratorBean.create("admin", "admin", "Gilberto o destroidor", "gilberto@hotmail.com");
    }
}
