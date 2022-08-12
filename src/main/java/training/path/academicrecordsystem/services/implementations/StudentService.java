package training.path.academicrecordsystem.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import training.path.academicrecordsystem.exceptions.ResourceNotFoundException;
import training.path.academicrecordsystem.model.Student;
import training.path.academicrecordsystem.repositories.interfaces.StudentRepository;
import training.path.academicrecordsystem.services.interfaces.IStudentService;

import java.util.List;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void update(String id, Student student) throws ResourceNotFoundException {
        if (!studentRepository.exists(id)) throw new ResourceNotFoundException("Student " + id + " was not found");
        studentRepository.update(id, student);
    }

    @Override
    public void deleteById(String id) throws ResourceNotFoundException {
        if (!studentRepository.exists(id)) throw new ResourceNotFoundException("Student " + id + " was not found");
        studentRepository.deleteById(id);
    }

    @Override
    public Student findById(String id) throws ResourceNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student " + id + " was not found"));
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAll(int limit, int offset) {
        return studentRepository.findAll(limit, offset);
    }
}