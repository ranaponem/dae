package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentDTO implements Serializable {
    private String username;
    private String password, name, email, courseName;
    private long courseCode;
    private List<SubjectDTO> subjects;

    public StudentDTO() {
    }

    public StudentDTO(String username, String password, String name, String email, String courseName, long courseCode) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.courseName = courseName;
        this.courseCode = courseCode;
        subjects = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public long getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(long courseCode) {
        this.courseCode = courseCode;
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

    public List<SubjectDTO> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SubjectDTO> subjects) {
        this.subjects = subjects;
    }

    public static StudentDTO from(Student student) {
        return new StudentDTO(student.getUsername(), student.getPassword(), student.getName(), student.getEmail(), student.getCourse().getName(), student.getCourse().getCode());
    }

    public static List<StudentDTO> from(List<Student> students) {
        return students.stream().map(StudentDTO::from).collect(Collectors.toList());
    }
}
