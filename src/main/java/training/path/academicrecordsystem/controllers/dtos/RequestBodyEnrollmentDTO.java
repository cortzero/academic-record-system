package training.path.academicrecordsystem.controllers.dtos;

import lombok.Data;

import java.util.List;

@Data
public class RequestBodyEnrollmentDTO {

    private String id;
    private String studentId;
    private List<String> courseClassIds;
    private int semester;

}
