package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.CourseDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.CourseBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Course;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;

import java.util.List;

@Path("course")
@Produces({MediaType.APPLICATION_JSON})
public class CourseService {
    @EJB
    private CourseBean courseBean;

    @GET
    @Path("/")
    public List<CourseDTO> getAllCourses(){
        return CourseDTO.from(courseBean.findAll());
    }

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createCourse(CourseDTO DTO) throws MyEntityExistsException, MyConstraintViolationException {
        courseBean.create(DTO.getCode(), DTO.getName());

        Course newCourse = courseBean.find(DTO.getCode());

        return Response.status(Response.Status.CREATED).entity(CourseDTO.from(newCourse)).build();    }
}
