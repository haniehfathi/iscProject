package com.java.isc.services;

import com.java.isc.models.Professor;
import com.java.isc.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfessorService {

    public ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Iterable<Professor> getAllProfessor(){
        return professorRepository.findAll();
    }

    public Professor findOne(Long id) throws Exception {
        return Optional.ofNullable(professorRepository.findOne(id))
                .orElseThrow(()->new Exception("Professor not found"));
    }

    public Professor addProfessor(Professor professor) throws Exception {
        Professor professorIfExist=professorRepository.getByEmail(professor.getEmail());

        if(professorIfExist !=null )
            throw new Exception("Professor Existed");
        return professorRepository.save(professor);
    }


}
