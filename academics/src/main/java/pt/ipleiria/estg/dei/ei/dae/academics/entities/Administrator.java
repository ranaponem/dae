package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class Administrator extends User {
    public Administrator() {
    }

    public Administrator(String username, String password, String name, String email) {
        super(username, password, name, email);
    }
}
