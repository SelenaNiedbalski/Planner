package projecttests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
class WeeklyTimeBlockTest
{
    private WeeklyTimeBlock block1;
    private WeeklyTimeBlock block2;

    @BeforeEach
    public void setUp()
    {
        block1 = new WeeklyTimeBlock(
                List.of(DayOfWeek.MONDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0)
        );

        block2 = new WeeklyTimeBlock(
                List.of(DayOfWeek.MONDAY),
                LocalTime.of(10, 0),
                LocalTime.of(11, 0)
        );
    }

    @Test
    public void testNoConflict_DifferentTimes()
    {
        boolean conflict = block1.checkBlockConflict(block2);

        assertFalse(conflict);
    }

    @Test
    public void testConflict_OverlappingTimes()
    {
        WeeklyTimeBlock overlapping = new WeeklyTimeBlock(
                List.of(DayOfWeek.MONDAY),
                LocalTime.of(9, 30),
                LocalTime.of(10, 30)
        );

        boolean conflict = block1.checkBlockConflict(overlapping);

        assertTrue(conflict);
    }

    @Test
    public void testNoConflict_DifferentDays()
    {
        WeeklyTimeBlock differentDay = new WeeklyTimeBlock(
                List.of(DayOfWeek.TUESDAY),
                LocalTime.of(9, 0),
                LocalTime.of(10, 0)
        );

        boolean conflict = block1.checkBlockConflict(differentDay);

        assertFalse(conflict);
    }

    @Test
    public void testSetWeeklyTimeBlock_UpdatesValues()
    {
        block1.setWeeklyTimeBlock(
                List.of(DayOfWeek.FRIDAY),
                LocalTime.of(14, 0),
                LocalTime.of(15, 0)
        );

        assertEquals(List.of(DayOfWeek.FRIDAY), block1.getDaysOfTheWeek());
        assertEquals(LocalTime.of(14, 0), block1.getClassStartTime());
        assertEquals(LocalTime.of(15, 0), block1.getClassEndTime());
    }

    @Test
    public void testToString_NotNull()
    {
        String result = block1.toString();

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }
}
