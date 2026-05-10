package projecttests;


import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import courseclasses.Course;
import mcvandfileservice.AboutYouModel;
import mcvandfileservice.AppController;
import mcvandfileservice.CourseInfoModel;
import mcvandfileservice.CourseRepository;
import mcvandfileservice.CreditsModel;
import mcvandfileservice.ScheduleGeneratorRepository;
import mcvandfileservice.UserDataRepository;
import mcvandfileservice.WishlistModel;
import schedules.Schedule;
import schedules.ScheduleGeneratorModel;

/**
 * Lead Author(s): Selena Niedbalski
 * Version/date: 8 May 2026
 * Responsibilities of class:
 * Tests MVC structure, verifies getters return correct values,
 * and setters correctly update instance variables.
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestMVCStructure
{

    // ======================
    // ABOUT YOU MODEL
    // ======================

	@Order(1)
	@Test
	void testAboutYouModel()
	{
	    AboutYouModel aboutModelTest1 = new AboutYouModel();

	    assertNotNull(aboutModelTest1);

	    assertNull(aboutModelTest1.getStudentsMajorDistinction());
	    assertEquals(0, aboutModelTest1.getNumOfCoursesStudentWillInput());
	    assertNull(aboutModelTest1.getStruggleCourses());

	    aboutModelTest1.setStudentsMajorDistinction("STEM");
	    aboutModelTest1.setNumOfCoursesStudentWillInput(4);

	    List<String> strugglesTest1 = new ArrayList<>();
	    strugglesTest1.add("Math");

	    aboutModelTest1.setStruggleCourses(strugglesTest1);

	    assertEquals("STEM", aboutModelTest1.getStudentsMajorDistinction());
	    assertEquals(4, aboutModelTest1.getNumOfCoursesStudentWillInput());
	    assertEquals(strugglesTest1, aboutModelTest1.getStruggleCourses());
	}

	@Order(2)
	@Test
	void testAppController()
	{
	    AboutYouModel aboutModelTest2 = new AboutYouModel();
	    aboutModelTest2.setStudentsMajorDistinction("STEM");
	    aboutModelTest2.setNumOfCoursesStudentWillInput(4);

	    List<String> strugglesTest2 = new ArrayList<>();
	    strugglesTest2.add("Math");
	    aboutModelTest2.setStruggleCourses(strugglesTest2);

	    CreditsModel creditsTest2 = new CreditsModel();
	    creditsTest2.setMinRequiredCredits(12);
	    creditsTest2.setMaxRequiredCredits(18);

	    WishlistModel wishlistTest2 = new WishlistModel();

	    CourseInfoModel courseTest2 = new CourseInfoModel();
	    courseTest2.setCourseName("CISC191");
	    courseTest2.setStudentRequiredCourse(true);
	    courseTest2.setCourseType("Core");

	    UserDataRepository repoTest2 =
	        new UserDataRepository(wishlistTest2, creditsTest2, aboutModelTest2, courseTest2);

	    AppController controllerTest2 = new AppController(repoTest2);

	    assertNotNull(controllerTest2);

	    assertEquals(12, controllerTest2.getMinRequiredCredits());
	    assertEquals(18, controllerTest2.getMaxRequiredCredits());
	    assertEquals("STEM", controllerTest2.getStudentsMajorDistinction());
	}

	@Order(3)
	@Test
	void testCourseInfoModel()
	{
	    CourseInfoModel courseModelTest3 = new CourseInfoModel();

	    assertNotNull(courseModelTest3);

	    assertNull(courseModelTest3.getCourseName());
	    assertFalse(courseModelTest3.isStudentRequiredCourse());

	    courseModelTest3.setCourseName("CISC191");
	    courseModelTest3.setStudentRequiredCourse(true);
	    courseModelTest3.setCourseType("Core");

	    assertEquals("CISC191", courseModelTest3.getCourseName());
	    assertTrue(courseModelTest3.isStudentRequiredCourse());
	    assertEquals("Core", courseModelTest3.getCourseType());
	}

	@Order(4)
	@Test
	void testCourseRepository()
	{
	    WishlistModel wishlistTest4 = new WishlistModel();

	    CreditsModel creditsTest4 = new CreditsModel();
	    creditsTest4.setMinRequiredCredits(12);
	    creditsTest4.setMaxRequiredCredits(18);

	    AboutYouModel aboutModelTest4 = new AboutYouModel();

	    CourseInfoModel courseModelTest4 = new CourseInfoModel();
	    courseModelTest4.setCourseName("Math101");
	    courseModelTest4.setStudentRequiredCourse(true);

	    UserDataRepository repoTest4 =
	        new UserDataRepository(wishlistTest4, creditsTest4, aboutModelTest4, courseModelTest4);

	    CourseRepository courseRepoTest4 = new CourseRepository(repoTest4);

	    assertNotNull(courseRepoTest4);

	    assertEquals("Math101", courseRepoTest4.getCourseName());
	    assertTrue(courseRepoTest4.isStudentRequiredCourse());

	    courseRepoTest4.setCourseName("Math101");
	    courseRepoTest4.setStudentRequiredCourse(false);
	    courseRepoTest4.setCourseType("Elective");

	    assertEquals("Math101", courseRepoTest4.getCourseName());
	    assertFalse(courseRepoTest4.isStudentRequiredCourse());
	    assertEquals("Elective", courseRepoTest4.getCourseType());
	}

	@Order(5)
	@Test
	void testCreditsModel()
	{
	    CreditsModel creditsModelTest5 = new CreditsModel();

	    assertNotNull(creditsModelTest5);

	    assertEquals(0, creditsModelTest5.getMinRequiredCredits());
	    assertEquals(0, creditsModelTest5.getMaxRequiredCredits());

	    creditsModelTest5.setMinRequiredCredits(12);
	    creditsModelTest5.setMaxRequiredCredits(15);

	    assertEquals(12, creditsModelTest5.getMinRequiredCredits());
	    assertEquals(15, creditsModelTest5.getMaxRequiredCredits());
	}

	@Order(6)
	@Test
	void testScheduleGeneratorModelMethods()
	{
	    // ======================
	    // BUILD REQUIRED CHAIN (no nulls)
	    // ======================

	    WishlistModel wishlistTest6 = new WishlistModel();

	    CreditsModel creditsTest6 = new CreditsModel();
	    creditsTest6.setMinRequiredCredits(12);
	    creditsTest6.setMaxRequiredCredits(18);

	    AboutYouModel aboutTest6 = new AboutYouModel();

	    CourseInfoModel courseTest6 = new CourseInfoModel();

	    UserDataRepository userDataTest6 =
	        new UserDataRepository(wishlistTest6, creditsTest6, aboutTest6, courseTest6);

	    AppController controllerTest6 =
	        new AppController(userDataTest6);

	    ScheduleGeneratorRepository repoTest6 =
	        new ScheduleGeneratorRepository(controllerTest6);

	    // ======================
	    // CONSTRUCT MODEL
	    // ======================

	    ScheduleGeneratorModel generatorTest6 =
	        new ScheduleGeneratorModel(repoTest6);

	    assertNotNull(generatorTest6);

	    // ======================
	    // TEST ONLY MODEL-OWNED METHODS
	    // ======================

	    List<Course> allCoursesTest6 = List.of();
	    List<Schedule> topSchedulesTest6 = List.of();

	    generatorTest6.setAllCourses(allCoursesTest6);
	    generatorTest6.setTopThreeSchedules(topSchedulesTest6);

	    assertEquals(allCoursesTest6, generatorTest6.getAllCourses());
	    assertEquals(topSchedulesTest6, generatorTest6.getTopThreeSchedules());
	}
}
