package com.java.isc;

import com.java.isc.models.Course;
import com.java.isc.models.Professor;
import com.java.isc.repositories.CourseRepository;
import com.java.isc.repositories.ProfessorRepository;
import com.java.isc.services.CourseService;
import com.java.isc.services.ProfessorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.java.isc.models.Professor;

@RunWith(MockitoJUnitRunner.class)
public class ProfessorServiceTest {
    @Mock
    private ProfessorRepository professorRepository;

    @InjectMocks
    private ProfessorService professorService;

    private Professor professor;

    @Before
    public void init(){

        professor=new Professor(10L,"ALI","FATHI", "ali@gmail.com", "0912", "tehran");
    }

    @Test
    public void findOneAndProfessorExist() throws Exception {
        Mockito.when(professorRepository.findOne(10L)).thenReturn(professor);

        Professor professor=professorService.findOne(10L);

        Assert.assertNotNull(professor);
        Mockito.verify(professorRepository,Mockito.times(1)).findOne(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(professorRepository);
    }

    @Test(expected = Exception.class)
    public void findOneAndCourseIsNull() throws Exception {
        Mockito.when(professorRepository.findOne(10L)).thenReturn(null);

        Professor professor=professorService.findOne(10L);

        Assert.assertNull(professor);
        Mockito.verify(professorRepository,Mockito.times(1)).findOne(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(professorRepository);
    }

    @Test(expected = Exception.class)
    public void addProfessorAndProfessorIsAlreadyExist() throws Exception {
        Mockito.when(professorRepository.getByEmail("ali@gmail.com")).thenReturn(professor);

        Professor c=professorService.addProfessor(professor);

        Assert.assertNull(c);
        Mockito.verify(professorRepository,Mockito.times(1)).getByEmail(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(professorRepository);
    }
}