package mcvandfileservice;

import java.nio.file.Path;
import java.util.List;

import schedules.Schedule;

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

public class FileService
{
	// Instance Variables
	private ScheduleGeneratorRepository scheduleGeneratorRepository; // A file service has-a schedule generator repository
	private List<Schedule> topThreeSchedules; // A file service has-a list of the top three schedules
	private Path topThreeSchedulesDestinationPath; // A file service has-a path to the destination for the top three schedules file
	private CourseRepository courseRepository; // A file service has-a course repository
	
	/**
	 * Purpose: To construct a file service object with default values with a parameter course repository
	 * @param newCourseRepository The course repository to be set for the file service
	 * 
	 */
	public FileService(CourseRepository newCourseRepository)
	{
		scheduleGeneratorRepository = null;
		topThreeSchedules = null;
		topThreeSchedulesDestinationPath = null;
		courseRepository = newCourseRepository;
	}

	/**
	 * Purpose: To set the destination path for the top three schedules file
	 * @param newTopThreeSchedulesDestinationPath The destination path to be set for the top three schedules file
	 */
	public void setTopThreeSchedulesDestinationPath(Path newTopThreeSchedulesDestinationPath)
	{
		topThreeSchedulesDestinationPath = newTopThreeSchedulesDestinationPath;
	}
	
	/**
	 * Purpose: To set the schedule generator repository for the file service
	 * @param newScheduleGeneratorRepository The schedule generator repository to be set for the file service
	 */
	public void addScheduleGeneratorRepository(
			ScheduleGeneratorRepository newScheduleGeneratorRepository)
	{
		scheduleGeneratorRepository = newScheduleGeneratorRepository;
		
	}
	
	/**
	 * Purpose: To read the available classes file and save them to the course repository
	 * @param availableClassesFilePath The path to the available classes file
	 */
	public void readAvailableClassesFile(Path availableClassesFilePath)
	{
		// TODO: Code to read the available classes file and save them to the course repository
	}
}
