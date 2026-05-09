package mcvandfileservice;

import java.nio.file.Path;
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

public class WishlistModel
{
	// Instance Variables
	private Duration minDesiredBreakTime;
	private Duration maxDesiredBreakTime;
	private WeeklyTimeBlock desiredStartAndEndTime;
	private String desiredCampusLocation;
	private final Path topThreeSchedulesDestinationPath;
	
	// Constructors
	/**
	 * Purpose: To construct a WishlistModel with default values
	 */
	public WishlistModel()
	{
		minDesiredBreakTime = Duration.ZERO;
		maxDesiredBreakTime = Duration.ZERO;
		desiredStartAndEndTime = null;
		desiredCampusLocation = "";
		topThreeSchedulesDestinationPath = Path.of("");
	}

	/**
	 * Purpose: To return the minimum desired break time
	 * @return minDesiredBreakTime The minimum desired break time
	 */
	public Duration getMinDesiredBreakTime()
	{
		return minDesiredBreakTime;
	}
	
	/**
	 * Purpose: To set the minimum desired break time
	 * @param newMinDesiredBreakTime The new minimum desired break time
	 */
	public void setMinDesiredBreakTime(Duration newMinDesiredBreakTime)
	{
		minDesiredBreakTime = newMinDesiredBreakTime;
	}

	/**
	 * Purpose: To return the maximum desired break time
	 * @return maxDesiredBreakTime The maximum desired break time
	 */
	public Duration getMaxDesiredBreakTime()
	{
		return maxDesiredBreakTime;
	}
	
	/**
	 * Purpose: To set the maximum desired break time
	 * @param newMaxDesiredBreakTime The new maximum desired break time
	 */
	public void setMaxDesiredBreakTime(Duration newMaxDesiredBreakTime)
	{
		maxDesiredBreakTime = newMaxDesiredBreakTime;
	}

	/**
	 * Purpose: To return the desired start and end time
	 * @return desiredStartAndEndTime The desired start and end time
	 */
	public WeeklyTimeBlock getDesiredStartAndEndTime()
	{
		return desiredStartAndEndTime;
	}
	
	/**
	 * Purpose: To set the desired start and end time
	 * @param newDesiredStartAndEndTime The new desired start and end time
	 */
	public void setDesiredStartAndEndTime(WeeklyTimeBlock newDesiredStartAndEndTime)
	{
		desiredStartAndEndTime = newDesiredStartAndEndTime;
	}

	/**
	 * Purpose: To return the desired campus location
	 * @return desiredCampusLocation The desired campus location
	 */
	public String getDesiredCampusLocation()
	{
		return desiredCampusLocation;
	}
	
	/**
	 * Purpose: To set the desired campus location
	 * @param newDesiredCampusLocation The new desired campus location
	 */
	public void setDesiredCampusLocation(String newDesiredCampusLocation)
	{
		desiredCampusLocation = newDesiredCampusLocation;
	}

	/**
	 * Purpose: To return the destination path for the top three schedules
	 * @return topThreeSchedulesDestinationPath The destination path for the top three schedules
	 */
	public Path getTopThreeSchedulesDestinationPath()
	{
		return topThreeSchedulesDestinationPath;
	}

}
