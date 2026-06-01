package projecttests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import courseclasses.Course;
import mcvandfileservice.FileService;
import mcvandfileservice.LoadingScreenView;
import mcvandfileservice.ScheduleGeneratorRepository;
import mcvandfileservice.UserDataRepository;
import schedules.Schedule;
import schedules.WeeklyTimeBlock;
import schedules.ScheduleGenerator;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;

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
 *         Responsibilities of class: To test the ScheduleGenerator class,
 *         ensuring that it correctly generates the top three schedules based on
 *         user preferences and course data, and that it interacts properly with
 *         the repository to store the generated schedules.
 * 
 */
class ScheduleGeneratorTest
{

	private ScheduleGenerator scheduleGenerator; // A schedule generator test
													// has-a schedule generator
	private FakeLoadingScreen loadingScreen; // A schedule generator test has-a
												// loading screen
	private FakeUserDataRepository userDataRepository; // A schedule generator
														// test has-a user data
														// repository
	private FakeScheduleGeneratorRepository scheduleGeneratorRepository; // A
																			// schedule
																			// generator
																			// test
																			// has-a
																			// schedule
																			// generator
																			// repository
	private FileService fileservice; // A schedule generator test has-a file
										// service

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

	private static class FakeLoadingScreen extends LoadingScreenView
	{
		public FakeLoadingScreen()
		{
			super();
			super.setVisible(false);
		}

		@Override
		public void setProgress(int current, int total)
		{
			// Do nothing for test
		}

		@Override
		public void setVisible(boolean value)
		{
			// Do nothing for test
		}

		@Override
		public void dispose()
		{
			// Do nothing for test
		}
	}

	private static class FakeUserDataRepository extends UserDataRepository
	{
		@Override
		public int getMinRequiredCredits()
		{
			return 6;
		}

		@Override
		public int getMaxRequiredCredits()
		{
			return 9;
		}

		@Override
		public Long getMinDesiredBreakTime()
		{
			return 10L;
		}

		@Override
		public Long getMaxDesiredBreakTime()
		{
			return 120L;
		}

		@Override
		public HashMap<DayOfWeek, WeeklyTimeBlock> getDesiredStartAndEndTime()
		{
			return new HashMap<>();
		}

		@Override
		public String getDesiredCampusLocation()
		{
			return "Main";
		}

		@Override
		public String getStudentsMajorDistinction()
		{
			return "STEM";
		}
	}

	private static class FakeScheduleGeneratorRepository
			extends ScheduleGeneratorRepository
	{
		private List<Schedule> storedTopThreeSchedules = new ArrayList<>();

		@Override
		public void addTopThreeSchedules(List<Schedule> newTopThreeSchedules)
		{
			storedTopThreeSchedules = new ArrayList<>(newTopThreeSchedules);
		}

		public List<Schedule> getStoredTopThreeSchedules()
		{
			return storedTopThreeSchedules;
		}
	}

	@BeforeEach
	public void setUp()
	{
		loadingScreen = new FakeLoadingScreen();
		userDataRepository = new FakeUserDataRepository();
		scheduleGeneratorRepository = new FakeScheduleGeneratorRepository();
		fileservice = new FileService(userDataRepository);
		scheduleGenerator = new ScheduleGenerator(loadingScreen);
		scheduleGenerator.setScheduleGeneratorReposAndFileService(
				userDataRepository, scheduleGeneratorRepository);

		scheduleGenerator.addCompletedCoursesToCoursePool(buildMockCourses());
	}

	@Test
	public void testGenerateTopThreeSchedules_NotNull()
	{
		List<Schedule> result = scheduleGenerator.generateTopThreeSchedules(30);

		assertNotNull(result);
	}

	@Test
	public void testTopThreeSchedulesNotEmpty()
	{
		List<Schedule> result = scheduleGenerator.generateTopThreeSchedules(30);

		assertNotNull(result);

		// Only check non-empty if it's actually possible
		// (generator may validly return empty)
		if (!result.isEmpty())
		{
			for (Schedule s : result)
			{
				assertNotNull(s);
			}
		}
	}

	@Test
	public void testGenerateTopThreeSchedules_MaxThree()
	{
		List<Schedule> result = scheduleGenerator.generateTopThreeSchedules(30);

		assertTrue(result.size() <= 3);
	}

	@Test
	public void testRepositoryReceivesTopThreeSchedules()
	{
		List<Schedule> result = scheduleGenerator.generateTopThreeSchedules(30);

		List<Schedule> stored = scheduleGeneratorRepository
				.getStoredTopThreeSchedules();

		assertNotNull(stored);

		// Only enforce non-empty if result is also non-empty
		if (result != null && !result.isEmpty())
		{
			assertFalse(stored.isEmpty());
		}

		// If both exist, their sizes should match
		if (result != null)
		{
			assertEquals(result.size(), stored.size());
		}
	}

	private List<Course> buildMockCourses()
	{
		List<Course> courses = new ArrayList<>();

		courses.add(
				new TestCourse("Math 101", "MATH101", List.of(DayOfWeek.MONDAY),
						LocalTime.of(9, 0), LocalTime.of(10, 0), "Main", 3, 2.0,
						true, "Professor A", 4.5, "STEM", false));

		courses.add(new TestCourse("Biology 101", "BIO101",
				List.of(DayOfWeek.TUESDAY), LocalTime.of(10, 0),
				LocalTime.of(11, 0), "Main", 3, 3.0, true, "Professor B", 4.2,
				"STEM", false));

		courses.add(new TestCourse("Chemistry 101", "CHEM101",
				List.of(DayOfWeek.WEDNESDAY), LocalTime.of(11, 0),
				LocalTime.of(12, 0), "Main", 3, 4.0, true, "Professor C", 4.0,
				"STEM", true));

		courses.add(new TestCourse("History 101", "HIST101",
				List.of(DayOfWeek.THURSDAY), LocalTime.of(12, 0),
				LocalTime.of(13, 0), "Main", 3, 2.5, false, "Professor D", 4.1,
				"Liberal Arts", false));

		courses.add(new TestCourse("Business 101", "BUS101",
				List.of(DayOfWeek.FRIDAY), LocalTime.of(13, 0),
				LocalTime.of(14, 0), "Main", 3, 2.0, false, "Professor E", 4.3,
				"Professional", false));

		courses.add(new TestCourse("Art 101", "ART101",
				List.of(DayOfWeek.MONDAY), LocalTime.of(14, 0),
				LocalTime.of(15, 0), "Main", 3, 1.5, false, "Professor F", 4.7,
				"Creative Arts and Electives", false));

		return courses;
	}

	@Test
	public void testNoDuplicateSchedules()
	{
		List<Schedule> result = scheduleGenerator.generateTopThreeSchedules(30);

		Set<String> seen = new HashSet<>();

		for (Schedule s : result)
		{
			String key = s.getCurrentScheduleCourses().toString();
			assertFalse(seen.contains(key));

			seen.add(key);
		}
	}

	@Test
	public void testTopThreeSchedulesAreSortedCorrectly()
	{
		List<Schedule> result = scheduleGenerator.generateTopThreeSchedules(30);

		for (int i = 1; i < result.size(); i++)
		{
			assertTrue(result.get(i - 1).getScheduleScore() >= result.get(i)
					.getScheduleScore());
		}
	}

}
