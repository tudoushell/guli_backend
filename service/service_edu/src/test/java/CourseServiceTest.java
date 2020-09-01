import com.elliot.eduservice.EduApplication;
import com.elliot.eduservice.service.EduCourseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest(classes = EduApplication.class)
@RunWith(SpringRunner.class)
public class CourseServiceTest {
  @Resource
  private EduCourseService eduCourseService;

  @Test
  public void test() {
    System.out.println(eduCourseService.listCourseByTeacherId("85da726c7d398e36c82f8a3300ffe31a"));
  }
}
