package com.java.isc.repositories;

import com.java.isc.models.Professor;
import com.java.isc.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor,Long> {
    public Professor getByEmail(String email);

    @Query(value = "select p from Professor p where p.id=:id")
    Professor findOne(Long id);
}
