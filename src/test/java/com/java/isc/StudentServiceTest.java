package com.java.isc;

import com.java.isc.models.Student;
import com.java.isc.repositories.StudentRepository;
import com.java.isc.services.StudentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @Before
    public void init(){

        student=new Student(10L,"hani","ff","h@gmail.com","0914",
                "tehran","user3","145", (short) 0);
    }

@Test
    public void findOneAndStudentExist() throws Exception {
    Mockito.when(studentRepository.findOne(10L)).thenReturn(student);

    Student std=studentService.findOne(10L);

    Assert.assertNotNull(std);
    Mockito.verify(studentRepository,Mockito.times(1)).findOne(Mockito.anyLong());
    Mockito.verifyNoMoreInteractions(studentRepository);
}

    @Test(expected = Exception.class)
    public void findOneAndStudentIsNull() throws Exception {
    Mockito.when(studentRepository.findOne(10L)).thenReturn(null);

    Student std=studentService.findOne(10L);

    Assert.assertNull(std);
    Mockito.verify(studentRepository,Mockito.times(1)).findOne(Mockito.anyLong());
    Mockito.verifyNoMoreInteractions(studentRepository);
}

    @Test
    public void addStudentAndStudentIsAlreadyExist() throws Exception {
    Mockito.when(studentRepository.findByEmail("h@gmail.com")).thenReturn(student);

    Student std=studentService.addStudent(student);

    Assert.assertNull(std);
    Mockito.verify(studentRepository,Mockito.times(1)).findByEmail(Mockito.anyString());
    Mockito.verifyNoMoreInteractions(studentRepository);
}

}