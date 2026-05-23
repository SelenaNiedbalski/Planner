package schedules;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import exceptions.MustBeOverOneException;
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
	
	private HashMap<DayOfWeek, List<LocalTime[]>> timesPerDay; // A weekly time block has-a hashmap that maps days of the week to lists of time ranges (start and end times)
	
	// Constructor
	/**
	 * Purpose: To create a WeeklyTimeBlock object with parameter values
	 * 
	 * @param newDaysOfTheWeek The new days of the week that the class meets on
	 * @param newStart         The new start time of the class
	 * @param newDuration      The new length of the class time
	 */
	public WeeklyTimeBlock(List<DayOfWeek> newDaysOfTheWeek, LocalTime newClassStartTime, LocalTime newClassEndTime)
	{
		daysOfTheWeek = newDaysOfTheWeek;
		classStartTime = newClassStartTime;
		classEndTime = newClassEndTime;

		if (classStartTime != null && classEndTime != null)
		{
			classDuration = Duration.between(classStartTime, classEndTime);
		}
		else
		{
			classDuration = null;
		}
	}

	// Getters and Setters
	/**
	 * Purpose: To set the weekly time block
	 * 
	 * @param newDaysOfTheWeek The new days of the week that the class meets on
	 * @param newStart         The new start time of the class
	 * @param newDuration      The new length of the class time
	 */
	public void setWeeklyTimeBlock(List<DayOfWeek> newDaysOfTheWeek, LocalTime newClassStartTime, LocalTime newClassEndTime)
	{
	    daysOfTheWeek = newDaysOfTheWeek;
	    classStartTime = newClassStartTime;
	    classEndTime = newClassEndTime;

	    if (classStartTime != null && classEndTime != null)
	    {
	        classDuration = Duration.between(classStartTime, classEndTime);
	    }
	    else
	    {
	        classDuration = null;
	    }
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
	 * Purpose: To check if this time block contains a specific day
	 * @return containsDay if the time block contains the day and !containDay if not
	 */
	public boolean containsDay(DayOfWeek day)
	{
		boolean containsDay = true;
		
		// Loop through the days in the timeblock
		for (DayOfWeek currentDay : daysOfTheWeek)
		{
			// If the current day matches the day being checked return true
			if (currentDay.equals(day))
			{
				return containsDay;
			}
		}
		return !containsDay;
	}
	
	/**
	 * Purpose: To find the EARLIEST start time for a specific day in the time block
	 * @param day The day of the week being checked for a start time
	 * @return startTime The start time for the day in the time block 
	 * (if the day exists, return null if not)
	 */
	public LocalTime getStartTimeForDay(DayOfWeek day)

{		
    // If the day is not in the hashmap, return null
    if (timesPerDay.containsKey(day) == false)
    {
        return null;
    }
    
    List<LocalTime[]> timeRangesForDay = timesPerDay.get(day);
    
    // If the list is empty, return null
    if (timeRangesForDay == null || timeRangesForDay.size() == 0)
    {
        return null;
    }
    
    LocalTime earliestStartTime = null;
    
    // Loop through all time ranges for that day
    for (LocalTime[] timeRange : timeRangesForDay)
    {
        LocalTime startTime = timeRange[0];
        
        if (earliestStartTime == null || startTime.isBefore(earliestStartTime))
        {
            earliestStartTime = startTime;
        }
    }
    
    return earliestStartTime;
}

	/**
	 * Purpose: To find the LATEST end time for a specific day in the time block
	 * @param day The day of the week being checked for an end time
	 * @return the latest end time for that day, or null if none exist
	 */
	public LocalTime getEndTimeForDay(DayOfWeek day)
	{		
	    if (timesPerDay.containsKey(day) == false)
	    {
	        return null;
	    }
	    
	    List<LocalTime[]> timeRangesForDay = timesPerDay.get(day);
	    
	    if (timeRangesForDay == null || timeRangesForDay.size() == 0)
	    {
	        return null;
	    }
	    
	    LocalTime latestEndTime = null;
	    
	    for (LocalTime[] timeRange : timeRangesForDay)
	    {
	        LocalTime endTime = timeRange[1];
	        
	        if (latestEndTime == null || endTime.isAfter(latestEndTime))
	        {
	            latestEndTime = endTime;
	        }
	    }
	    
	    return latestEndTime;
	}
	
	
	
	/**
	 * Purpose: To compare weekly time blocks and determine if they conflict
	 * with eachother
	 * @param other The other WeeklyTimeBlock this one is being compared to
	 * @return true if there is a time conflict between weeklytimeblocks and false if not
	 */
	public boolean checkBlockConflict(WeeklyTimeBlock other)
	{
		// The daysOverlap if the time blocks share any days of the week
		boolean daysOverlap = this.daysOfTheWeek.stream()
				.anyMatch(other.daysOfTheWeek::contains);
		
		// If the time blocks don't share any days return false
		if (!daysOverlap)
		{
			return false;
		}

		// The times of the time blocks overlap if the original class start-time
		// is before the end-time of the other course and if the other course's
		// start time is before the original class' end time
		boolean timesOverlap = this.classStartTime.isBefore(other.classEndTime)
				&& other.classStartTime.isBefore(this.classEndTime);
		
		// If there's no time overlap return false, otherwise return true
		if(!timesOverlap)
		{
			return false;
		}
		else
		{
		return true;
		}
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

	/**
	 * Purpose: To set the start time of the class in the weekly time block
	 * @param newStart The new start time of the class
	 */
	public void setClassStartTime(LocalTime newStart)
	{
		classStartTime = newStart;
	}
}
