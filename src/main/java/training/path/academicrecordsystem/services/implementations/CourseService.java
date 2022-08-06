package training.path.academicrecordsystem.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.path.academicrecordsystem.exceptions.NotFoundResourceException;
import training.path.academicrecordsystem.model.Course;
import training.path.academicrecordsystem.repositories.implementations.JdbcCourseRepository;
import training.path.academicrecordsystem.services.interfaces.ICourseService;

import java.util.List;

@Service
public class CourseService implements ICourseService {

    private final JdbcCourseRepository jdbcCourseRepository;

    @Autowired
    public CourseService(JdbcCourseRepository jdbcCourseRepository) {
        this.jdbcCourseRepository = jdbcCourseRepository;
    }

    @Override
    public void save(Course course) {
        jdbcCourseRepository.save(course);
    }

    @Override
    public void update(Course course) throws NotFoundResourceException {
        if (!jdbcCourseRepository.exists(course.getId())) throw new NotFoundResourceException("Course " + course.getName() + " was not found");
        jdbcCourseRepository.update(course.getId(), course);
    }

    @Override
    public void deleteById(String id) throws NotFoundResourceException {
        if (!jdbcCourseRepository.exists(id)) throw new NotFoundResourceException("Course " + id + " was not found");
        jdbcCourseRepository.deleteById(id);
    }

    @Override
    public Course findById(String id) throws NotFoundResourceException {
        return jdbcCourseRepository.findById(id).orElseThrow(() -> new NotFoundResourceException("Course " + id + " was not found"));
    }

    @Override
    public Course findByName(String name) throws NotFoundResourceException {
        return jdbcCourseRepository.findByName(name).orElseThrow(() -> new NotFoundResourceException("Course " + name + " was not found"));
    }

    @Override
    public List<Course> findAll() {
        return jdbcCourseRepository.findAll();
    }

}