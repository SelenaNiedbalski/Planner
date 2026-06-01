package projecttests;

import static org.junit.jupiter.api.Assertions.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import courseclasses.Course;

/**
 * Lead Author(s):
 * 
 * @author Selena Niedbalski
 * 
 * 
 *         References:
 *         Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented
 *         Problem Solving.
 *         Retrieved from
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * 
 *         Version/date: 31 May 2026
 * 
 *         Responsibilities of class: To test the Course class, which represents
 *         a university course with various attributes and methods to calculate
 *         a course score based on factors such as RMP score, course difficulty,
 *         and campus location. The tests will verify the correctness of the
 *         methods in the Course class and ensure that they behave as expected
 *         under different scenarios.
 * 
 */
class CourseTest
{
	private static class TestCourse extends Course
	{
		public TestCourse(String newCourseName, String newCourseID,
				List<DayOfWeek> newDaysOfTheWeek, LocalTime newClassStartTime,
				LocalTime newClassEndTime, String newCourseCampusLocation,
				int newCourseCredits, double newCourseDifficulty,
				boolean updatedCourseRequired, String newInstructorName,
				double newInstructorRMPScore, String newCourseType,
				boolean updatedStruggleCourse)
		{
			super(newCourseName, newCourseID, newDaysOfTheWeek,
					newClassStartTime, newClassEndTime, newCourseCampusLocation,
					newCourseCredits, newCourseDifficulty,
					updatedCourseRequired, newInstructorName,
					newInstructorRMPScore, newCourseType,
					updatedStruggleCourse);
		}
	}

	private Course buildCourse(String name, String id, double difficulty,
			double rmp, boolean required, String type)
	{
		return new TestCourse(name, id, List.of(DayOfWeek.MONDAY),
				LocalTime.of(9, 0), LocalTime.of(10, 0), "Main", 3, difficulty,
				required, "Professor Test", rmp, type, false);
	}

	@Test
	public void testAddPointsForRMPScore()
	{
		Course course = buildCourse("Math", "M1", 2.0, 4.5, true, "STEM");

		double result = course.addPointsForRMPScore();

		assertEquals(4.5, result);
	}

	@Test
	public void testAddPointsForCourseRequired()
	{
		Course course = buildCourse("Math", "M1", 2.0, 0.0, true, "STEM");

		double result = course.addPointsForCourseRequired();

		assertEquals(5.0, result);
	}

	@Test
	public void testSubtractPointsForWrongCampus()
	{
		Course course = buildCourse("Math", "M1", 2.0, 0.0, true, "STEM");

		double result = course.subtractPointsForWrongCampusLocation("Downtown");

		assertEquals(-5.0, result);
	}

	@Test
	public void testSubtractPointsForCourseDifficulty()
	{
		Course course = buildCourse("Math", "M1", 3.0, 0.0, true, "STEM");

		double result = course.subtractPointsForCourseDifficulty();

		assertEquals(-3.0, result);
	}

	@Test
	public void testCalculateCourseScore_CombinedEffects()
	{
		Course course = buildCourse("Math", "M1", 2.0, 4.0, true, "STEM");

		double result = course.calculateCourseScore("Main", "STEM");

		// expected: +4 (RMP) +5 (required) -2 (difficulty) = 7
		assertEquals(7.0, result);
	}

	@Test
	public void testCalculateCourseScore_WrongCampusPenalty()
	{
		Course course = buildCourse("Math", "M1", 2.0, 4.0, true, "STEM");

		double result = course.calculateCourseScore("Downtown", "STEM");

		// expected: +4 +5 -5 -2 = 2
		assertEquals(2.0, result);
	}

	@Test
	public void testToString_NotNull()
	{
		Course course = buildCourse("Math", "M1", 2.0, 4.0, true, "STEM");

		String result = course.toString();

		assertNotNull(result);
		assertFalse(result.isEmpty());
	}
}
