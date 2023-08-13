package com.java.isc.repositories;

import com.java.isc.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);

    @Query(value = "select u from User u where u.username=:username")
    User findByUsernameQuery(String username);
}
