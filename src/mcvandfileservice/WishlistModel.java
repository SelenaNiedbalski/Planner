package mcvandfileservice;

import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exceptions.EmptyTextboxException;
import exceptions.EndTimeBeforeStartTimeException;
import exceptions.FilePathDoesNotExistException;
import exceptions.IncorrectTimeFormatException;
import exceptions.MustBeOverOneException;
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
	private Long minDesiredBreakTime;
	private Long maxDesiredBreakTime;
	private HashMap<DayOfWeek, WeeklyTimeBlock> desiredStartAndEndTime;
	private String desiredCampusLocation;
	private Path topThreeSchedulesDestinationPath;
	
	// Constructor
	/**
	 * Purpose: To construct a WishlistModel with default values
	 */
	public WishlistModel()
	{
		minDesiredBreakTime = null;
		maxDesiredBreakTime = null;
		desiredStartAndEndTime = null;
		desiredCampusLocation = null;
		topThreeSchedulesDestinationPath = null;
	}

	
	
	// Getters and Setters
	/**
	 * Purpose: To return the minimum desired break time
	 * @return minDesiredBreakTime The minimum desired break time
	 */
	public Long getMinDesiredBreakTime()
	{
		return minDesiredBreakTime;
	}
	
	/**
	 * Purpose: To set the minimum desired break time
	 * @param newMinDesiredBreakTime The new minimum desired break time
	 * @throws MustBeOverOneException if the new minimum desired break time is less than 1 minute 
	 */
	public void setMinDesiredBreakTime(Long newMinDesiredBreakTime) throws MustBeOverOneException 
	{
	    // If the new minimum desired break time is null or is greater than or equal to 1 minute
	    if (newMinDesiredBreakTime == null || newMinDesiredBreakTime > 1L) 
	    {
	        minDesiredBreakTime = newMinDesiredBreakTime;
	    } 
	    else 
	    {
	        throw new MustBeOverOneException();
	    }
	}

	/**
	 * Purpose: To return the maximum desired break time
	 * @return maxDesiredBreakTime The maximum desired break time
	 */
	public Long getMaxDesiredBreakTime()
	{
		return maxDesiredBreakTime;
	}
	
	/**
	 * Purpose: To set the maximum desired break time
	 * @param newMaxDesiredBreakTime The new maximum desired break time
	 * @throws GreaterThanPreviousTimeException if the new maximum desired break time is less than the minimum desired break time 
	 */
	public void setMaxDesiredBreakTime(Long newMaxDesiredBreakTime) throws EndTimeBeforeStartTimeException
	{
	    if (newMaxDesiredBreakTime == null || minDesiredBreakTime == null
	        || newMaxDesiredBreakTime >= minDesiredBreakTime)
	    {
	        maxDesiredBreakTime = newMaxDesiredBreakTime;
	    }
	    else
	    {
	        throw new EndTimeBeforeStartTimeException("MaxDesiredBreakTime", "MinDesiredBreakTime");
	    }
	}

	/**
	 * Purpose: To return the desired start and end time
	 * @return desiredStartAndEndTime The desired start and end time
	 */
	public HashMap<DayOfWeek, WeeklyTimeBlock> getDesiredStartAndEndTime()
	{
		return desiredStartAndEndTime;
	}
	
	/**
	 * Purpose: To set the desired start and end time
	 * @param newDesiredStartAndEndTime The new desired start and end time
	 * @param generalErrors A list of general error messages (non-time errors) to display in a popup
	 */
	public void setDesiredStartAndEndTime(HashMap<DayOfWeek, WeeklyTimeBlock> newDesiredStartAndEndTime, List<String> generalErrors)
	{
	    // Create list array of error messages to store (for general popup)
	    List<String> error = new ArrayList<String>();
	    
	    // If the generalErrors list is null, create it so errors can still be added
	    if (generalErrors == null)
	    {
	        generalErrors = new ArrayList<String>();
	    }
	    
	    // Check desired start and end times
	    if (newDesiredStartAndEndTime != null)
	    {
	        for (DayOfWeek day : newDesiredStartAndEndTime.keySet())
	        {
	            WeeklyTimeBlock timeBlock = newDesiredStartAndEndTime.get(day);

	            if (timeBlock != null)
	            {
	                LocalTime start = timeBlock.getClassStartTime();
	                LocalTime end = timeBlock.getClassEndTime();

	                if (start != null && end != null && end.isBefore(start))
	                {
	                    EndTimeBeforeStartTimeException endTimeBeforeStartTimeException = new EndTimeBeforeStartTimeException("EndTime", "StartTime");
	                    error.add("For " + day.toString() + ": " + endTimeBeforeStartTimeException.getMessage());
	                }
	            }
	        }
	    }
	    
	    // Add error messages to generalErrors list instead of throwing exception
	    for (String errMsg : error)
	    {
	        generalErrors.add(errMsg);
	    }
	    
	    // Add entries to the wishlist model
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

	
	// Other Methods
	/**
	 * Purpose: To store the info from the app controller for the wishlist view (for the min and * Purpose: To store the info from the app controller for the wishlist view (for the min and max desired break time,
	 * @param newDesiredCampusLocation The desired campus location for the user
	 * @param newTopThreeSchedulesDestinationPath The destination path for the top three schedules for the user
	 * @param generalErrors A list of general error messages (non-time errors) to display in a popup
	 * @return timeErrors A hashmaps of error messages for the desired start and end times 
	 * (if any end times are before start times they're added to hashmap for that day)
	 */
	public HashMap<DayOfWeek, String> saveWishlist(Long newMinDesiredBreakTime,
	        Long newMaxDesiredBreakTime,
	        HashMap<DayOfWeek, WeeklyTimeBlock> newDesiredStartAndEndTime,
	        String newDesiredCampusLocation,
	        Path newTopThreeSchedulesDestinationPath,
	        List<String> generalErrors)
	{
	    // Create hashmap of error messages for the desired start and end times
	    HashMap<DayOfWeek, String> timeErrors = new HashMap<>();

	    // If the generalErrors list is null, create it so errors can still be added
	    if (generalErrors == null)
	    {
	        generalErrors = new ArrayList<String>();
	    }

	    // Check min desired break time
	    if (newMinDesiredBreakTime != null && newMinDesiredBreakTime <= 1L)
	    {
	        MustBeOverOneException mustBeOverOneException = new MustBeOverOneException("MinBreakTime value");
	        generalErrors.add(mustBeOverOneException.getMessage());
	    }

	    // Check max desired break time
	    if (newMaxDesiredBreakTime != null && newMinDesiredBreakTime != null
	            && newMaxDesiredBreakTime < newMinDesiredBreakTime)
	    {
	        EndTimeBeforeStartTimeException endTimeBeforeStartTimeException = new EndTimeBeforeStartTimeException("MaxBreakTime", "MinBreakTime");
	        generalErrors.add(endTimeBeforeStartTimeException.getMessage());
	    }

	    // Check desired start and end times
	    if (newDesiredStartAndEndTime != null)
	    {
	        for (DayOfWeek day : newDesiredStartAndEndTime.keySet())
	        {
	            WeeklyTimeBlock timeBlock = newDesiredStartAndEndTime.get(day);

	            if (timeBlock != null)
	            {
	                LocalTime start = timeBlock.getClassStartTime();
	                LocalTime end = timeBlock.getClassEndTime();

	                if ((start == null && end != null) || (start != null && end == null))
	                {
	                    timeErrors.put(day, "Both boxes must be blanked out if one is.");
	                }
	                else if (start != null && end != null)
	                {
	                    if (!end.isAfter(start))
	                    {
	                        EndTimeBeforeStartTimeException endTimeBeforeStartTimeException = new EndTimeBeforeStartTimeException("EndTime", "StartTime");
	                        timeErrors.put(day, endTimeBeforeStartTimeException.getMessage());
	                    }
	                }
	            }
	        }
	    }

	    // Check top three schedules destination path
	    if (newTopThreeSchedulesDestinationPath == null)
	    {
	        EmptyTextboxException emptyTextBoxException = new EmptyTextboxException("top three schedules destination path");
	        generalErrors.add(emptyTextBoxException.getMessage());
	    }

	    if (newTopThreeSchedulesDestinationPath != null
	            && (!newTopThreeSchedulesDestinationPath.toFile().exists()
	            || !newTopThreeSchedulesDestinationPath.toFile().isDirectory()))
	    {
	        FilePathDoesNotExistException filePathDoesNotExistException = new FilePathDoesNotExistException();
	        generalErrors.add(filePathDoesNotExistException.getMessage());
	    }

	    // Add valid entries to the wishlist model
	    if (timeErrors.isEmpty() && generalErrors.isEmpty())
	    {
	        minDesiredBreakTime = newMinDesiredBreakTime;
	        maxDesiredBreakTime = newMaxDesiredBreakTime;
	        desiredStartAndEndTime = newDesiredStartAndEndTime;
	        desiredCampusLocation = newDesiredCampusLocation;
	        topThreeSchedulesDestinationPath = newTopThreeSchedulesDestinationPath;
	    }

	    return timeErrors;
	}
	
	
	/**
	 * Purpose: To clear the wishlist model of all information (for when the user wants to reset their wishlist)
	 */
	public void clear()
	{
		minDesiredBreakTime = null;
		maxDesiredBreakTime = null;
		desiredStartAndEndTime = null;
		desiredCampusLocation = null;
	}
}
