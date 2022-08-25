package training.path.academicrecordsystem.repositories.interfaces;

import training.path.academicrecordsystem.model.CourseClass;
import training.path.academicrecordsystem.model.Professor;

import java.util.List;
import java.util.Optional;

public interface IProfessorRepository {

    int save(Professor professor);

    int update(String id, Professor professor);

    int updateBasicInfo(String id, Professor professor);

    int deleteById(String id);

    Optional<Professor> findById(String id);

    Optional<Professor> findByUserName(String userName);

    Optional<Professor> findByEmail(String email);

    List<Professor> findAll();

    List<Professor> findAll(int limit, int offset);

    List<CourseClass> findClasses(String id);

    boolean exists(String id);

}
