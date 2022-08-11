package training.path.academicrecordsystem.repositories.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import training.path.academicrecordsystem.model.Course;
import training.path.academicrecordsystem.model.CourseClass;
import training.path.academicrecordsystem.repositories.implementations.rowmappers.ClassesByCourseRowMapper;
import training.path.academicrecordsystem.repositories.interfaces.CourseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JdbcCourseRepository implements CourseRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCourseRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Course> findById(String id) {
        String query = "SELECT * FROM courses WHERE id = ?";
        try {
            Course course = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Course.class), UUID.fromString(id));
            return Optional.ofNullable(course);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Course> findByName(String name) {
        String query = "SELECT * FROM courses WHERE name ILIKE ?";
        try {
            Course course = jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Course.class), name);
            return Optional.ofNullable(course);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Course> findAll() {
        String query = "SELECT * FROM courses ORDER BY name";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Course.class));
    }

    @Override
    public List<Course> findAll(int limit, int offset) {
        String query = "SELECT * FROM courses ORDER BY name LIMIT ? OFFSET ?";
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Course.class), limit, offset);
    }

    @Override
    public int save(Course course) {
        String query = "INSERT INTO courses (id, name, credits) VALUES (?, ?, ?)";
        return jdbcTemplate.update(query, UUID.fromString(course.getId()), course.getName(), course.getCredits());
    }

    @Override
    public int update(String id, Course course) {
        String query = "UPDATE courses SET name = ?, credits = ? WHERE id = ?";
        return jdbcTemplate.update(query, course.getName(), course.getCredits(), UUID.fromString(id));
    }

    @Override
    public int deleteById(String id) {
        String query = "DELETE FROM courses WHERE id = ?";
        return jdbcTemplate.update(query, UUID.fromString(id));
    }

    @Override
    public boolean exists(String id) {
        String query = "SELECT * FROM courses WHERE id = ?";
        try {
            jdbcTemplate.queryForObject(query, new BeanPropertyRowMapper<>(Course.class), UUID.fromString(id));
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Override
    public Optional<List<CourseClass>> getClassesByCourse(String courseId) {
        String query = """
                SELECT co.id AS course_id, co.name AS course_name, credits, cl.id AS class_id, available, cl.professor_id AS prof_id, u.name AS prof_name
                FROM courses co INNER JOIN classes cl ON cl.course_id = co.id
                INNER JOIN professors p ON cl.professor_id = p.id
                INNER JOIN users u ON u.id = p.id
                WHERE co.id = ?""";
        try {
            List<CourseClass> classes = jdbcTemplate.query(query, new ClassesByCourseRowMapper(), UUID.fromString(courseId));
            return Optional.of(classes);
        } catch (DataAccessException e) {
            return Optional.empty();
        }
    }

}
