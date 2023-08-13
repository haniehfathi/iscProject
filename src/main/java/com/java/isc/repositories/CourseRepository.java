package com.java.isc.repositories;

import com.java.isc.models.Course;
import com.java.isc.models.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends CrudRepository<Course,Long> {

    List<Course> findCoursesByStudentId(Student student);

    @Query(value = "select c from Course c where c.id=:id")
    Course findOne(Long id);

    Course findByTitle(String t);
}
