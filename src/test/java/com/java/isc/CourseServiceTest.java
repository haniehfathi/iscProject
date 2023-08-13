
package com.java.isc;

        import com.java.isc.models.Course;
        import com.java.isc.models.Student;
        import com.java.isc.repositories.CourseRepository;
        import com.java.isc.repositories.ProfessorRepository;
        import com.java.isc.repositories.StudentRepository;
        import com.java.isc.services.CourseService;
        import com.java.isc.services.ProfessorService;
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
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course;

    @Before
    public void init(){

        course=new Course(10L,"computer", (short) 3, null);
    }

    @Test
    public void findOneAndCourseExist() throws Exception {
        Mockito.when(courseRepository.findOne(10L)).thenReturn(course);

        Course std=courseService.findOne(10L);

        Assert.assertNotNull(std);
        Mockito.verify(courseRepository,Mockito.times(1)).findOne(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(courseRepository);
    }

    @Test(expected = Exception.class)
    public void findOneAndCourseIsNull() throws Exception {
        Mockito.when(courseRepository.findOne(10L)).thenReturn(null);

        Course std=courseService.findOne(10L);

        Assert.assertNull(std);
        Mockito.verify(courseRepository,Mockito.times(1)).findOne(Mockito.anyLong());
        Mockito.verifyNoMoreInteractions(courseRepository);
    }

    @Test(expected = Exception.class)
    public void addCourseAndCourseIsAlreadyExist() throws Exception {
        Mockito.when(courseRepository.findByTitle("computer")).thenReturn(course);

        Course c=courseService.addCourse(course);

        Assert.assertNull(c);
        Mockito.verify(courseRepository,Mockito.times(1)).findByTitle(Mockito.anyString());
        Mockito.verifyNoMoreInteractions(courseRepository);
    }
}