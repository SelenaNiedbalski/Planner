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

public class AppController
{
	// Instance Variables
	private int minRequiredCredits; // An app controller has-a minimum amount of required credits
	private int maxRequiredCredits; // An app controller has-a maximum amount of required credits
	private Duration minDesiredBreakTime; // An app controller has-a minimum desired break time
	private Duration maxDesiredBreakTime; // An app controller has-a maximum desired break time
	private WeeklyTimeBlock desiredStartAndEndTime; // An app controller has-a desired start and end time for each day of the week
	private String desiredCampusLocation; // An app controller has-a desired campus location
	private String studentsMajorDistinction; // An app controller has-a student's major distinction (STEM, non-STEM, Undecided)
	
	
	/**
	 * Purpose: To create an app controller object with default values (pulled from userData)
	 * @param userDataRepository The user data that the app controller will pull data from to set its instance variables
	 * 
	 */
	public AppController(UserDataRepository userDataRepository)
	{
		minRequiredCredits = userDataRepository.getMinRequiredCredits();
		maxRequiredCredits = userDataRepository.getMaxRequiredCredits();
		minDesiredBreakTime = userDataRepository.getMinDesiredBreakTime();
		maxDesiredBreakTime = userDataRepository.getMaxDesiredBreakTime();
		desiredStartAndEndTime = userDataRepository.getDesiredStartAndEndTime();
		desiredCampusLocation = userDataRepository.getDesiredCampusLocation();
		studentsMajorDistinction = userDataRepository.getStudentsMajorDistinction();
	}

	/**
	 * Purpose: To return the minimum amount of required credits for a schedule
	 * @return minRequiredCredits The minimum amount of required credits for a schedule
	 */
	public int getMinRequiredCredits()
	{
		return minRequiredCredits;
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
	 * Purpose: To return the minimum desired break time for a schedule
	 * @return minDesiredBreakTime The minimum desired break time for a schedule
	 */
	public Duration getMinDesiredBreakTime()
	{
		return minDesiredBreakTime;
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
	 * Purpose: To return the desired start and end time for each day of the week for a schedule
	 * @return desiredStartAndEndTime The desired start and end time for each day of the week for a schedule
	 */
	public WeeklyTimeBlock getDesiredStartAndEndTime()
	{
		return desiredStartAndEndTime;
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
	 * Purpose: To return the student's major distinction (STEM, non-STEM, Undecided)
	 * @return studentsMajorDistinction The student's major distinction (STEM, non-STEM, Undecided)
	 */
	public String getStudentsMajorDistinction()
	{
		return studentsMajorDistinction;
	}
	
	
}
