package projecttests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import courseclasses.Course;
import mcvandfileservice.FileService;
import mcvandfileservice.UserDataRepository;
import schedules.Schedule;
import schedules.WeeklyTimeBlock;

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
 *         Version/date: 29 April 2026
 * 
 *         Responsibilities of class:
 * 
 */
class FileServiceTest
{
    private FileService fileService;
    private FakeUserDataRepository userDataRepository;

    private static class TestCourse extends Course
    {
        public TestCourse(String name, String id,
                List<DayOfWeek> days, LocalTime start, LocalTime end,
                String campus, int credits, double difficulty,
                boolean required, String instructor,
                double rmp, String type, boolean struggle)
        {
            super(name, id, days, start, end, campus, credits,
                  difficulty, required, instructor, rmp, type, struggle);
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
            return 12;
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

    @BeforeEach
    public void setUp()
    {
        userDataRepository = new FakeUserDataRepository();
        fileService = new FileService(userDataRepository);
    }

    private Schedule createSchedule()
    {
        Schedule schedule = new Schedule();

        Course c1 = new TestCourse(
                "Math 101",
                "MATH101",
                List.of(DayOfWeek.MONDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "Main",
                3,
                2.0,
                true,
                "Prof A",
                4.5,
                "STEM",
                false
        );

        Course c2 = new TestCourse(
                "History 101",
                "HIST101",
                List.of(DayOfWeek.TUESDAY),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0),
                "Main",
                3,
                1.5,
                false,
                "Prof B",
                4.2,
                "Liberal Arts",
                false
        );

        schedule.addCourse(c1);
        schedule.addCourse(c2);

        return schedule;
    }

    @Test
    public void testSetTopThreeSchedules_NotNull()
    {
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(createSchedule());

        fileService.setTopThreeSchedules(schedules);

        assertNotNull(fileService.getTopThreeSchedules());
    }

    @Test
    public void testSetTopThreeSchedules_NotEmpty()
    {
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(createSchedule());

        fileService.setTopThreeSchedules(schedules);

        assertFalse(fileService.getTopThreeSchedules().isEmpty());
    }

    @Test
    public void testTopThreeToCSV_WithSchedules()
    {
        List<Schedule> schedules = new ArrayList<>();
        schedules.add(createSchedule());
        schedules.add(createSchedule());
        schedules.add(createSchedule());

        fileService.setTopThreeSchedules(schedules);

        // Should run without throwing exception
        fileService.topThreeToCSV();

        assertTrue(fileService.getTopThreeSchedules().size() == 3);
    }

    @Test
    public void testTopThreeToCSV_EmptyListHandled()
    {
        List<Schedule> schedules = new ArrayList<>();

        fileService.setTopThreeSchedules(schedules);

        // Should not crash even if empty
        fileService.topThreeToCSV();

        assertTrue(fileService.getTopThreeSchedules().isEmpty());
    }
}
