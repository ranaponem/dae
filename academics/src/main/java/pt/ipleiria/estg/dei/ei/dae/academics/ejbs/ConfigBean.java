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

    @PostConstruct
    public void populateDB(){
        courseBean.create(1, "EI");
        courseBean.create(2, "Jogos");
        studentBean.create("marau", "123", "Rafael", "123@gmail.com",1);
        studentBean.create("zexxx", "123", "Jose", "456@gmail.com",2);
        studentBean.create("lol", "123", "Manel", "123@hotmail.com",1);
        studentBean.create("archwiki", "123", "Arch", "456@hotmail.com",2);
    }
}
