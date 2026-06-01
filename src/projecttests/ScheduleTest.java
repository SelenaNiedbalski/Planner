package projecttests;

import static org.junit.jupiter.api.Assertions.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import courseclasses.Course;
import schedules.Schedule;

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
 *         Responsibilities of class: To test the Schedule class's addCourse and
 *         removeCourse methods, ensuring
 * 
 */
class ScheduleTest
{
	private Schedule schedule; // A schedule test has-a schedule

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

	@BeforeEach
	public void setUp()
	{
		schedule = new Schedule();
	}

	private Course buildCourse(String courseName, String courseID,
			List<DayOfWeek> days, LocalTime start, LocalTime end, int credits,
			String courseType)
	{
		return new TestCourse(courseName, courseID, days, start, end, "Main",
				credits, 2.0, true, "Professor Test", 4.0, courseType, false);
	}

	@Test
	public void testAddCourse_FirstCourseAdded()
	{
		Course course = buildCourse("Math 101", "MATH101",
				List.of(DayOfWeek.MONDAY), LocalTime.of(9, 0),
				LocalTime.of(10, 0), 3, "STEM");

		schedule.addCourse(course);

		assertEquals(1, schedule.getCurrentScheduleCourses().size());
		assertEquals(3, schedule.getTotalCredits());
	}

	@Test
	public void testAddCourse_NoConflictSecondCourseAdded()
	{
		Course course1 = buildCourse("Math 101", "MATH101",
				List.of(DayOfWeek.MONDAY), LocalTime.of(9, 0),
				LocalTime.of(10, 0), 3, "STEM");

		Course course2 = buildCourse("History 101", "HIST101",
				List.of(DayOfWeek.TUESDAY), LocalTime.of(11, 0),
				LocalTime.of(12, 0), 3, "Liberal Arts");

		schedule.addCourse(course1);
		schedule.addCourse(course2);

		assertEquals(2, schedule.getCurrentScheduleCourses().size());
		assertEquals(6, schedule.getTotalCredits());
	}

	@Test
	public void testAddCourse_DuplicateCourseNameRejected()
	{
		Course course1 = buildCourse("Math 101", "MATH101-A",
				List.of(DayOfWeek.MONDAY), LocalTime.of(9, 0),
				LocalTime.of(10, 0), 3, "STEM");

		Course course2 = buildCourse("Math 101", "MATH101-B",
				List.of(DayOfWeek.TUESDAY), LocalTime.of(11, 0),
				LocalTime.of(12, 0), 3, "STEM");

		schedule.addCourse(course1);
		schedule.addCourse(course2);

		assertEquals(1, schedule.getCurrentScheduleCourses().size());
	}

	@Test
	public void testAddCourse_ScheduleConflictRejected()
	{
		Course course1 = buildCourse("Math 101", "MATH101",
				List.of(DayOfWeek.MONDAY), LocalTime.of(9, 0),
				LocalTime.of(10, 30), 3, "STEM");

		Course course2 = buildCourse("History 101", "HIST101",
				List.of(DayOfWeek.MONDAY), LocalTime.of(10, 0),
				LocalTime.of(11, 0), 3, "Liberal Arts");

		schedule.addCourse(course1);
		schedule.addCourse(course2);

		assertEquals(1, schedule.getCurrentScheduleCourses().size());
		assertEquals(3, schedule.getTotalCredits());
	}

	@Test
	public void testRemoveCourse_RemovesCourseAndCredits()
	{
		Course course1 = buildCourse("Math 101", "MATH101",
				List.of(DayOfWeek.MONDAY), LocalTime.of(9, 0),
				LocalTime.of(10, 0), 3, "STEM");

		Course course2 = buildCourse("History 101", "HIST101",
				List.of(DayOfWeek.TUESDAY), LocalTime.of(11, 0),
				LocalTime.of(12, 0), 3, "Liberal Arts");

		schedule.addCourse(course1);
		schedule.addCourse(course2);

		schedule.removeCourse(course1);

		assertEquals(1, schedule.getCurrentScheduleCourses().size());
		assertEquals(3, schedule.getTotalCredits());
	}
}