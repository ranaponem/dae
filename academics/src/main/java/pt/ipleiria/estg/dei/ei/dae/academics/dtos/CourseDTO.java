package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CourseDTO {
    private long code;
    private String name;

    public CourseDTO() {
    }

    public CourseDTO(long code, String name) {
        this.code = code;
        this.name = name;
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

    public static CourseDTO from(Course course) {
        return new CourseDTO(course.getCode(), course.getName());
    }

    public static List<CourseDTO> from(List<Course> courses) {
        return courses.stream().map(CourseDTO::from).collect(Collectors.toList());
    }
}
