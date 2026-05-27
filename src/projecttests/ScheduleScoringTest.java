package projecttests;

import static org.junit.jupiter.api.Assertions.*;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import courseclasses.Course;
import schedules.Schedule;
import schedules.WeeklyTimeBlock;

/**
 * Lead Author(s):
 * @author Selena Niedbalski
 * 
 * 
 * References:
 *         Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented Problem Solving.
 *         Retrieved from
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * 
 * Version/date: 29 April 2026
 * 
 * Responsibilities of class: 
 * 
 */
class ScheduleScoringTest
{
    private Schedule schedule;

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
            super(newCourseName, newCourseID, newDaysOfTheWeek, newClassStartTime,
                    newClassEndTime, newCourseCampusLocation, newCourseCredits,
                    newCourseDifficulty, updatedCourseRequired, newInstructorName,
                    newInstructorRMPScore, newCourseType, updatedStruggleCourse);
        }
    }

    @BeforeEach
    public void setUp()
    {
        schedule = new Schedule();
    }

    private Course buildCourse(String name, String id, String type, boolean struggle)
    {
        return new TestCourse(
                name,
                id,
                List.of(DayOfWeek.MONDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "Main",
                3,
                2.0,
                true,
                "Professor",
                4.0,
                type,
                struggle
        );
    }

    @Test
    public void testSubtractPointsForSTEMCoursesOverThree()
    {
        schedule.addCourse(buildCourse("A","A1","STEM",false, 9));
        schedule.addCourse(buildCourse("B","B1","STEM",false, 10));
        schedule.addCourse(buildCourse("C","C1","STEM",false, 11));
        schedule.addCourse(buildCourse("D","D1","STEM",false, 12));

        schedule.setScheduleScore(100.0);

        int stemCount = (int) schedule.getCurrentScheduleCourses().stream()
                .filter(c -> c.getCourseType().equals("STEM"))
                .count();

        int extra = Math.max(0, stemCount - 3);
        double expected = 100.0 - (extra * 5);

        double result = schedule.subtractPointsForSTEMCoursesOverThree();

        assertEquals(expected, result);
    }

    @Test
    public void testSubtractPointsForStruggleCoursesOverOne()
    {
        schedule.addCourse(buildCourse("A","A1","STEM",true, 9));
        schedule.addCourse(buildCourse("B","B1","STEM",true, 10));

        schedule.setScheduleScore(100.0);

        int struggleCount = (int) schedule.getCurrentScheduleCourses().stream()
                .filter(c -> c.getStruggleCourse())
                .count();

        int extra = Math.max(0, struggleCount - 1);
        double expected = 100.0 - (extra * 5);

        double result = schedule.subtractPointsForStruggleCoursesOverOne();

        assertEquals(expected, result);
    }

    @Test
    public void testCalculateBreakTimeDeviation_NoPenalty()
    {
        schedule.addCourse(buildCourse("A","A1","STEM",false));
        schedule.setScheduleScore(100.0);

        double result = schedule.calculateBreakTimeDeviation(0L, 200L);

        assertTrue(result <= 100.0);
    }

    @Test
    public void testCalculateStartAndEndTimeDeviation_NoPreferences()
    {
        schedule.addCourse(buildCourse("A","A1","STEM",false));
        schedule.setScheduleScore(100.0);

        HashMap<DayOfWeek, WeeklyTimeBlock> map = new HashMap<>();

        double result = schedule.calculateStartAndEndTimeDeviation(map);

        assertTrue(result <= 100.0);
    }

    @Test
    public void testScheduleScoreUpdate()
    {
        schedule.setScheduleScore(50.0);

        assertEquals(50.0, schedule.getScheduleScore());
    }
    
    private Course buildCourse(String name, String id, String type, boolean struggle, int startHour)
    {
        return new TestCourse(
                name,
                id,
                List.of(DayOfWeek.MONDAY),
                LocalTime.of(startHour, 0),
                LocalTime.of(startHour + 1, 0),
                "Main",
                3,
                2.0,
                true,
                "Professor",
                4.0,
                type,
                struggle
        );
    }

}
