package schedules;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Duration;
import java.util.List;

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
 *         Version/date: 28 April 2026
 * 
 *         Responsibilities of class: To represent a block of time in a week for
 *         a class schedule
 * 
 */

public class WeeklyTimeBlock
{
	// Instance Variables
	private List<DayOfWeek> daysOfTheWeek; // A weekly time block has-many days
											// of the week
	private LocalTime classStartTime; // A weekly time block has-a class start
										// time
	private Duration classDuration; // A weekly time block has-a set duration of
									// time for a class
	private LocalTime classEndTime; // A weekly time block has-a class end time

	// Constructor
	/**
	 * Purpose: To create a WeeklyTimeBlock object with parameter values
	 * 
	 * @param newDaysOfTheWeek The new days of the week that the class meets on
	 * @param newStart         The new start time of the class
	 * @param newDuration      The new length of the class time
	 */
	public WeeklyTimeBlock(List<DayOfWeek> newDaysOfTheWeek,
			LocalTime newClassStartTime, Duration newClassDuration)
	{
		daysOfTheWeek = newDaysOfTheWeek;
		classStartTime = newClassStartTime;
		classDuration = newClassDuration;
		classEndTime = (classStartTime.plus(classDuration));
	}

	// Getters and Setters
	/**
	 * Purpose: To set the weekly time block
	 * 
	 * @param newDaysOfTheWeek The new days of the week that the class meets on
	 * @param newStart         The new start time of the class
	 * @param newDuration      The new length of the class time
	 */
	public void setWeeklyTimeBlock(List<DayOfWeek> newDaysOfTheWeek,
			LocalTime newClassStartTime, Duration newClassDuration)
	{
		daysOfTheWeek = newDaysOfTheWeek;
		classStartTime = newClassStartTime;
		classDuration = newClassDuration;
		classEndTime = (classStartTime.plus(classDuration));
	}

	/**
	 * Purpose: To return the days stored in the time block
	 * return daysOfTheWeek The days stored in the weekly time block when the
	 * class meets
	 */
	public List<DayOfWeek> getDaysOfTheWeek()
	{
		return daysOfTheWeek;
	}

	/**
	 * Purpose: To return the start time of the class in the weekly time block
	 * return classStartTime The start time of when the class meets
	 */
	public LocalTime getClassStartTime()
	{
		return classStartTime;
	}

	/**
	 * Purpose: To return the end time of the class in the weekly time block
	 * return classEndTime The end time of when the class concludes
	 */
	public LocalTime getClassEndTime()
	{
		return classEndTime;
	}

	/**
	 * Purpose: To return the length of time the class meets in the weekly time
	 * block
	 * return classDuration The duration of class
	 */
	public Duration getClassDuration()
	{
		return classDuration;
	}

	// Other Methods
	/**
	 * Purpose: To compare weekly time blocks and determine if they conflict
	 * with eachother
	 * 
	 * @param other The other WeeklyTimeBlock this one is being compared to
	 * @return timesOverlap if the schedule conflicts and false if not
	 */
	public boolean checkBlockConflict(WeeklyTimeBlock other)
	{
		// Check if they share at least one day
		boolean daysOverlap = this.daysOfTheWeek.stream()
				.anyMatch(other.daysOfTheWeek::contains);

		if (!daysOverlap)
		{
			return false;
		}

		// Check if time ranges overlap
		boolean timesOverlap = this.classStartTime.isBefore(other.classEndTime)
				&& other.classStartTime.isBefore(this.classEndTime);

		return timesOverlap;
	}

	/**
	 * Purpose: To return the WeeklyTimeblock info
	 * return String of WeeklyTimeBlock info
	 */
	public String toString()
	{
		return "Days=" + daysOfTheWeek + ", Start=" + classStartTime + ", End="
				+ classEndTime;
	}
}
