package training.path.academicrecordsystem.repositories.rowmappers;

import org.springframework.jdbc.core.RowMapper;
import training.path.academicrecordsystem.model.Course;
import training.path.academicrecordsystem.model.CourseClass;
import training.path.academicrecordsystem.model.Enrollment;
import training.path.academicrecordsystem.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EnrollmentsInformationRowMapper implements RowMapper<Enrollment> {

    @Override
    public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(rs.getObject("enroll_id", UUID.class).toString());
        enrollment.setSemester(rs.getInt("semester"));

        Student student = new Student();
        student.setId(rs.getObject("student_id", UUID.class).toString());
        student.setName(rs.getString("student_name"));
        student.setEmail(rs.getString("student_email"));
        enrollment.setStudent(student);

        Course course = new Course();
        course.setId(rs.getObject("course_id", UUID.class).toString());
        course.setName(rs.getString("course_name"));

        CourseClass courseClass = new CourseClass();
        courseClass.setId(rs.getObject("class_id", UUID.class).toString());
        courseClass.setCourse(course);
        enrollment.setCourseClass(courseClass);

        return enrollment;
    }

}