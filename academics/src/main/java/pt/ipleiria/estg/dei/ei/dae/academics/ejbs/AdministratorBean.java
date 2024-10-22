package pt.ipleiria.estg.dei.ei.dae.academics.ejbs;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Administrator;

@Stateless
public class AdministratorBean {

    @PersistenceContext
    private EntityManager em;

    public void create(String username, String password, String name, String email) {
        Administrator a = new Administrator(username, password, name, email);
        em.persist(a);
    }
}
