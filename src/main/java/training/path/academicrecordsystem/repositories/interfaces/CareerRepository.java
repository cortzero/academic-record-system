package training.path.academicrecordsystem.repositories.interfaces;

import training.path.academicrecordsystem.model.Career;

import java.util.List;
import java.util.Optional;

public interface CareerRepository {

    Optional<Career> findById(String id);
    Optional<Career> findByName(String name);
    List<Career> findAll();
    int save(Career career);
    int update(String id, Career career);
    int deleteById(String id);
    boolean exists(String id);
    
}