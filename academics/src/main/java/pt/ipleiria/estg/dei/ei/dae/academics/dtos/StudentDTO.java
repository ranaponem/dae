package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDTO implements Serializable {
    private String username;
    private String password, name, email;

    public StudentDTO() {
    }

    public StudentDTO(String username, String password, String name, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public static StudentDTO from(Student student) {
        return new StudentDTO(student.getUsername(), student.getPassword(), student.getName(), student.getEmail());
    }

    public static List<StudentDTO> from(List<Student> students) {
        return students.stream().map(StudentDTO::from).collect(Collectors.toList());
    }
}
