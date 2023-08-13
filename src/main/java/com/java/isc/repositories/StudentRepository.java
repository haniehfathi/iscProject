package com.java.isc.repositories;

import com.java.isc.models.Student;
import com.java.isc.models.User;
import com.java.isc.models.Course;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student,Long> {

    @Query(value = "select u from Student u where u.username=:username")
    Student findByUsernameQuery(String username);

    @Modifying
    @Transactional
    @Query(value = "insert into units (st_id, course_id) VALUES (?, ?)", nativeQuery = true)
    void  courseAssignment(Student st_id,Course course_id);


    @Query(value = "select u from Student u where u.id=:id")
    Student findOne(Long id);

    Student findByEmail(String s);
}
