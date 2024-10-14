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
    @PostConstruct
    public void populateDB(){
        studentBean.create("marau", "123", "Rafael", "123");
        studentBean.create("zexxx", "123", "Jose", "123");
        studentBean.create("lol", "123", "Manel", "123");
        studentBean.create("archwiki", "123", "Arch", "123");
    }
}
