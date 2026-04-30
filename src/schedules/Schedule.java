package schedules;
import java.util.List;

import courseclasses.Course;



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
 * Version/date: 28 April 2026
 * 
 * Responsibilities of class: To represent a schedule with many classes that a student can take
 * 
 */

public class Schedule
{
	// Instance Variables
	private List<Course> currentScheduleCourses; // A schedule has-many courses
	private List<WeeklyTimeBlock> currentScheduleTimes; // A schedule has-many weekly time blocks
	private double scheduleScore; // A schedule has-a score 
	private int totalCredits; // A schedule has-a total number of credits
	
	
	
	
	// Constructors
	public Schedule(List<Course> newCurrentScheduleCourses, List<WeeklyTimeBlock> newCurrentScheduleTimes, double newScheduleScore,
			int newTotalCredits)
	{
		currentScheduleCourses = newCurrentScheduleCourses;
		currentScheduleTimes = newCurrentScheduleTimes;
		scheduleScore = newScheduleScore;
		totalCredits = newTotalCredits;
	}
	
	
	
	
	// Getters and Setters
	/**
	 * Purpose: To return the current schedule of courses
	 * @return currentScheduleCourses The current schedule of courses
	 */
	public List<Course> getCurrentScheduleCourses()
	{
		return currentScheduleCourses;
	}
	
	/**
	 * Purpose: To set the current schedule of courses
	 * @param newCurrentScheduleCourses The new current schedule of courses
	 */
	public void setCurrentScheduleCourses(List<Course> newCurrentScheduleCourses)
	{
		currentScheduleCourses = newCurrentScheduleCourses;
	}
	
	/**
	 * Purpose: To return the current schedule of times
	 * @return currentScheduleTimes The current schedule of times
	 */
	public List<WeeklyTimeBlock> getCurrentScheduleTimes()
	{
		return currentScheduleTimes;
	}
	
	/**
	 * Purpose: To set the current schedule of times
	 * @param newCurrentScheduleTimes The new current schedule of times
	 */
	public void setCurrentScheduleTimes(List<WeeklyTimeBlock> newCurrentScheduleTimes)
	{
		currentScheduleTimes = newCurrentScheduleTimes;
	}
	
	/** 
	 * Purpose: To return the current score of the schedule
	 * @return scheduleScore The current score of the schedule
	 */
	public double getScheduleScore()
	{
		return scheduleScore;
	}
	
	/**
	 * Purpose: To set the current score of the schedule
	 * @param newScheduleScore The new score of the schedule
	 */
	public void setScheduleScore(double newScheduleScore)
	{
		scheduleScore = newScheduleScore;
	}
	
	/**
	 * Purpose: To return the total number of credits in the schedule
	 * @return totalCredits The total number of credits in the schedule
	 */
	public int getTotalCredits()
	{
		return totalCredits;
	}
	
	/**
	 * Purpose: To set the total number of credits in the schedule
	 * @param newTotalCredits The new total number of credits in the schedule
	 */
	public void setTotalCredits(int newTotalCredits)
	{
		totalCredits = newTotalCredits;
	}
	
}
