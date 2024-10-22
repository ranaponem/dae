package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Subject;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class SubjectDTO implements Serializable {
    private long code;
    private String name;
    private String schoolYear;
    private String courseName;
    private int courseYear;

    public SubjectDTO() {
    }

    public SubjectDTO(long code, String name, String schoolYear, String courseName, int courseYear) {
        this.code = code;
        this.name = name;
        this.schoolYear = schoolYear;
        this.courseName = courseName;
        this.courseYear = courseYear;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(int courseYear) {
        this.courseYear = courseYear;
    }

    public static SubjectDTO from(Subject subject) {
        return new SubjectDTO(subject.getCode(), subject.getName(), subject.getSchoolYear(), subject.getCourse().getName(), subject.getCourseYear());
    }

    public static List<SubjectDTO> from(List<Subject> subjects) {
        return subjects.stream().map(SubjectDTO::from).collect(Collectors.toList());
    }
}
