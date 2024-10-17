package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.StudentBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;

import java.util.List;

@Path("student")
@Produces({MediaType.APPLICATION_JSON})

public class StudentService {
    @EJB
    private StudentBean studentBean;

    @GET
    @Path("/")
    public List<StudentDTO> getAllStudents() {
        return StudentDTO.from(studentBean.findAll());
    }

    @POST
    @Path("/")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response createStudent(StudentDTO DTO) {
        studentBean.create(DTO.getUsername(), DTO.getPassword(), DTO.getName(), DTO.getEmail(), DTO.getCourseCode());

        Student newStudent = studentBean.find(DTO.getUsername());

        return Response.status(Response.Status.CREATED).entity(StudentDTO.from(newStudent)).build();
    }
}
