package pt.ipleiria.estg.dei.ei.dae.academics.ws;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.StudentDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.dtos.SubjectDTO;
import pt.ipleiria.estg.dei.ei.dae.academics.ejbs.StudentBean;
import pt.ipleiria.estg.dei.ei.dae.academics.entities.Student;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.academics.exceptions.MyEntityNotFoundException;

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
    public Response create(StudentDTO studentDTO) throws MyEntityExistsException, MyEntityNotFoundException {
        studentBean.create(
                studentDTO.getUsername(),
                studentDTO.getPassword(),
                studentDTO.getName(),
                studentDTO.getEmail(),
                studentDTO.getCourseCode()
        );
        Student student = studentBean.find(studentDTO.getUsername());
        return Response.status(Response.Status.CREATED).entity(StudentDTO.from
                (student)).build();
    }
    @GET
    @Path("{username}")
    public Response getStudent(@PathParam("username") String username) {
        var student = studentBean.findWithSubjects(username);
        var studentDTO = StudentDTO.from(student);
        studentDTO.setSubjects(SubjectDTO.from(student.getSubjects()));
        return Response.ok(studentDTO).build();
    }

    @GET
    @Path("{username}/subjects")
    public Response getStudentSubjects(@PathParam("username") String username) {
        var student = studentBean.findWithSubjects(username);
        return Response.ok(SubjectDTO.from(student.getSubjects())).build();
    }
}
