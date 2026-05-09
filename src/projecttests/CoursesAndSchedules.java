
package projecttests;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import courseclasses.Course;
import courseclasses.STEMCourse;
import mcvandfileservice.ScheduleGeneratorRepository;
import schedules.Schedule;
import schedules.ScheduleGeneratorModel;
import schedules.WeeklyTimeBlock;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TestCoursesAndSchedules
{

    // ======================
    // STEM COURSE (METHOD TEST)
    // ======================
    @Order(1)
    @Test
    void testSTEMCourseMethods()
    {
        STEMCourse stemTest1 = new STEMCourse();

        assertNotNull(stemTest1);

        // Test setter + getter
        stemTest1.setCourseScore(3.5);
        assertEquals(3.5, stemTest1.getCourseScore());

        // Update again to ensure method works repeatedly
        stemTest1.setCourseScore(4.2);
        assertEquals(4.2, stemTest1.getCourseScore());
    }

    // ======================
    // WEEKLY TIME BLOCK (METHOD TEST)
    // ======================
    @Order(2)
    @Test
    void testWeeklyTimeBlockMethods()
    {
        List<DayOfWeek> daysTest2 = new ArrayList<>();
        daysTest2.add(DayOfWeek.MONDAY);

        LocalTime startTest2 = LocalTime.of(9, 0);
        Duration durationTest2 = Duration.ofMinutes(60);

        WeeklyTimeBlock blockTest2 =
            new WeeklyTimeBlock(daysTest2, startTest2, durationTest2);

        assertNotNull(blockTest2);

        // Test getters
        assertEquals(daysTest2, blockTest2.getDaysOfTheWeek());
        assertEquals(startTest2, blockTest2.getClassStartTime());
        assertEquals(durationTest2, blockTest2.getClassDuration());

        // Update using setters if available
        LocalTime newStart = LocalTime.of(10, 0);
        blockTest2.setClassStartTime(newStart);

        assertEquals(newStart, blockTest2.getClassStartTime());
    }

    // ======================
    // SCHEDULE (METHOD TEST)
    // ======================
    @Order(3)
    @Test
    void testScheduleMethods()
    {
        List<Course> coursesTest3 = new ArrayList<>();
        List<WeeklyTimeBlock> timesTest3 = new ArrayList<>();

        Schedule scheduleTest3 =
            new Schedule(coursesTest3, timesTest3, 0);

        assertNotNull(scheduleTest3);

        // Verify getters
        assertEquals(coursesTest3, scheduleTest3.getCurrentScheduleCourses());
        assertEquals(timesTest3, scheduleTest3.getCurrentScheduleTimes());

        // Test setters
        scheduleTest3.setTotalCredits(12);
        scheduleTest3.setScheduleScore(6.5);

        assertEquals(12, scheduleTest3.getTotalCredits());
        assertEquals(6.5, scheduleTest3.getScheduleScore());

        // Add to lists and verify behavior
        coursesTest3.add(null);
        timesTest3.add(new WeeklyTimeBlock(
            new ArrayList<>(),
            LocalTime.of(8, 0),
            Duration.ofMinutes(50)));

        assertEquals(1, scheduleTest3.getCurrentScheduleCourses().size());
        assertEquals(1, scheduleTest3.getCurrentScheduleTimes().size());
    }

    // ======================
    // SCHEDULE COUNTS (METHOD TEST)
    // ======================
    @Order(4)
    @Test
    void testScheduleCounts()
    {
        Schedule scheduleTest4 =
            new Schedule(new ArrayList<>(), new ArrayList<>(), 0);

        // Set counts
        scheduleTest4.setNumSTEMCourses(3);
        scheduleTest4.setNumStruggleCourses(2);

        // Verify getters
        assertEquals(3, scheduleTest4.getNumSTEMCourses());
        assertEquals(2, scheduleTest4.getNumStruggleCourses());
    }

    // ======================
    // SCHEDULE GENERATOR MODEL (METHOD TEST)
    // ======================
    @Order(5)
    @Test
    void testScheduleGeneratorModelMethods()
    {
        ScheduleGeneratorRepository repoTest5 =
            new ScheduleGeneratorRepository(null);

        ScheduleGeneratorModel generatorTest5 =
            new ScheduleGeneratorModel(repoTest5);

        assertNotNull(generatorTest5);

        // Test setters for lists
        List<Course> allCoursesTest5 = List.of();
        List<Schedule> topSchedulesTest5 = List.of();

        generatorTest5.setAllCourses(allCoursesTest5);
        generatorTest5.setTopThreeSchedules(topSchedulesTest5);

    	}
   }
