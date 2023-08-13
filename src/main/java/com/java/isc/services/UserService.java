package com.java.isc.services;

import com.java.isc.models.Course;
import com.java.isc.models.Student;
import com.java.isc.models.User;
import com.java.isc.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User addUser(User u) {
        try {
            return userRepository.save(u);
        }
        catch(Exception e)
        {
            System.out.println("exception in AddUser with exception type: "+e.toString());
            return null;
        }
    }

}
