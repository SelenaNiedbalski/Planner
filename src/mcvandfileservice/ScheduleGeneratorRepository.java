package mcvandfileservice;

import java.time.Duration;

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

public class ScheduleGeneratorRepository
{
	// Instance Variables
	private int minRequiredCredits; // A schedule generator repository has-a minimum amount of required credits
	private int maxRequiredCredits; // A schedule generator repository has-a maximum amount of required credits
	private Duration minDesiredBreakTime; // A schedule generator repository has-a minimum desired break time
	private Duration maxDesiredBreakTime; // A schedule generator repository has-a maximum desired break time
	private WeeklyTimeBlock desiredStartAndEndTime; // A schedule generator repository has-a desired start and end time for each day of the week
	private String desiredCampusLocation; // A schedule generator repository has-a desired campus location
	private String studentsMajorDistinction; // A schedule generator repository has-a student's major distinction (STEM, non-STEM, Undecided)
	
	// Constructor
	/**
	 * Purpose: To create a schedule generator repository object with default values (pulled from appController, which pulls from userData)
	 * @param appController The app controller that the schedule generator repository will pull data from to set its instance variables
	 * 
	 */
	public ScheduleGeneratorRepository(AppController appController)
	{
		minRequiredCredits = appController.getMinRequiredCredits();
		maxRequiredCredits = appController.getMaxRequiredCredits();
		minDesiredBreakTime = appController.getMinDesiredBreakTime();
		maxDesiredBreakTime = appController.getMaxDesiredBreakTime();
		desiredStartAndEndTime = appController.getDesiredStartAndEndTime();
		desiredCampusLocation = appController.getDesiredCampusLocation();
		studentsMajorDistinction = appController.getStudentsMajorDistinction();
	}
	
	// Getters and Setters
	/**
	 * Purpose: To return the minimum amount of required credits for a schedule
	 * @return minRequiredCredits The minimum amount of required credits for a schedule
	 */
	public int getMinRequiredCredits()
	{
		return minRequiredCredits;
	}

	/**
	 * Purpose: To set the minimum amount of required credits for a schedule
	 * @param newMinRequiredCredits The new minimum amount of required credits for a schedule
	 */
	public void setMinRequiredCredits(int newMinRequiredCredits)
	{
		minRequiredCredits = newMinRequiredCredits;
	}
	
	/**
	 * Purpose: To return the maximum amount of required credits for a schedule
	 * @return maxRequiredCredits The maximum amount of required credits for a schedule
	 */
	public int getMaxRequiredCredits()
	{
		return maxRequiredCredits;
	}

	
	/**
	 * Purpose: To set the maximum amount of required credits for a schedule
	 * @param newMaxRequiredCredits The new maximum amount of required credits for a schedule
	 */
	public void setMaxRequiredCredits(int newMaxRequiredCredits)
	{
		maxRequiredCredits = newMaxRequiredCredits;
	}
	
	/**
	 * Purpose: To return the minimum desired break time for a schedule
	 * @return minDesiredBreakTime The minimum desired break time for a schedule
	 */
	public Duration getMinDesiredBreakTime()
	{
		return minDesiredBreakTime;
	}
	
	/**
	 * Purpose: To set the minimum desired break time for a schedule
	 * @param newMinDesiredBreakTime The new minimum desired break time for a schedule
	 */
	public void setMinDesiredBreakTime(Duration newMinDesiredBreakTime)
	{
		minDesiredBreakTime = newMinDesiredBreakTime;
	}
	
	/**
	 * Purpose: To return the maximum desired break time for a schedule
	 * @return maxDesiredBreakTime The maximum desired break time for a schedule
	 */
	public Duration getMaxDesiredBreakTime()
	{
		return maxDesiredBreakTime;
	}
	
	/**
	 * Purpose: To set the maximum desired break time for a schedule
	 * @param newMaxDesiredBreakTime The new maximum desired break time for a schedule
	 */
	public void setMaxDesiredBreakTime(Duration newMaxDesiredBreakTime)
	{
		maxDesiredBreakTime = newMaxDesiredBreakTime;
	}

	/**
	 * Purpose: To return the desired start and end time for each day of the week for a schedule
	 * @return desiredStartAndEndTime The desired start and end time for each day of the week for a schedule
	 */
	public WeeklyTimeBlock getDesiredStartAndEndTime()
	{
		return desiredStartAndEndTime;
	}
	
	/**
	 * Purpose: To set the desired start and end time for each day of the week for a schedule
	 * @param newDesiredStartAndEndTime The new desired start and end time for each day of the week for a schedule
	 */
	public void setDesiredStartAndEndTime(WeeklyTimeBlock newDesiredStartAndEndTime)
	{
		desiredStartAndEndTime = newDesiredStartAndEndTime;
	}

	/**
	 * Purpose: To return the desired campus location for a schedule
	 * @return desiredCampusLocation The desired campus location for a schedule
	 */
	public String getDesiredCampusLocation()
	{
		return desiredCampusLocation;
	}
	
	/**
	 * Purpose: To set the desired campus location for a schedule
	 * @param newDesiredCampusLocation The new desired campus location for a schedule
	 */
	public void setDesiredCampusLocation(String newDesiredCampusLocation)
	{
		desiredCampusLocation = newDesiredCampusLocation;
	}

	/**
	 * Purpose: To return the student's major distinction (STEM, non-STEM, Undecided) for a schedule
	 * @return studentsMajorDistinction The student's major distinction (STEM, non-STEM, Undecided) for a schedule
	 */
	public String getStudentsMajorDistinction()
	{
		return studentsMajorDistinction;
	}
	
	/**
	 * Purpose: To set the student's major distinction (STEM, non-STEM, Undecided) for a schedule
	 * @param newStudentsMajorDistinction The new student's major distinction (STEM, non-STEM, Undecided) for a schedule
	 */
	public void setStudentsMajorDistinction(String newStudentsMajorDistinction)
	{
		studentsMajorDistinction = newStudentsMajorDistinction;
	}

}
