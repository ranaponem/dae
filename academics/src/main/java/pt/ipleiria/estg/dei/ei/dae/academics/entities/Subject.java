package pt.ipleiria.estg.dei.ei.dae.academics.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllSubjects",
                query = "SELECT s FROM Subject s ORDER BY s.course.name, s.schoolYear DESC, s.courseYear, s.name"
        )
})
@Table(name = "subjects",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"name", "course_code", "school_year"})
        })
public class Subject implements Serializable {
    @Id
    @NotNull
    private long code;
    @NotBlank
    private String name;
    @Column(name = "school_year")
    @NotBlank
    private String schoolYear;
    @Column(name = "course_year")
    @NotNull
    private int courseYear;
    @ManyToOne
    @NotNull
    private Course course;
    @ManyToMany
    @JoinTable(
            name = "subject_student",
            joinColumns = @JoinColumn(
                    name = "subject_code",
                    referencedColumnName = "code"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_username",
                    referencedColumnName = "username"
            )
    )
    private List<Student> students;

    @ManyToMany
    @JoinTable(
            name = "subject_teacher",
            joinColumns = @JoinColumn(
                    name = "subject_code",
                    referencedColumnName = "code"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "teacher_username",
                    referencedColumnName = "username"
            )
    )
    private List<Teacher> teachers;

    public Subject() {
    }

    public Subject(long code, String name, String schoolYear, int courseYear, Course course) {
        this.code = code;
        this.name = name;
        this.schoolYear = schoolYear;
        this.courseYear = courseYear;
        this.course = course;
        students = new ArrayList<>();
        teachers = new ArrayList<>();
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(String schoolYear) {
        this.schoolYear = schoolYear;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void addStudent(Student s){
        students.add(s);
    }

    public void removeStudent(Student s){
        students.remove(s);
    }
}
