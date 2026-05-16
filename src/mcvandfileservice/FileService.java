package mcvandfileservice;

import java.awt.Desktop;
// Java premade imports
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import courseclasses.Course;

import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// My class imports
import schedules.WeeklyTimeBlock;
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
	private CourseRepository courseRepository; // A file service has-a course repository
	private Path topThreeSchedulesDestinationPath; // A file service has-a path to the destination for the top three schedules file
	
	/**
	 * Purpose: To construct a file service object with default values with a parameter course repository
	 * @param newScheduleGeneratorRepository The schedule generator repository to be set for the file service
	 * @param newCourseRepository The course repository to be set for the file service
	 * 
	 */
	public FileService(ScheduleGeneratorRepository newScheduleGeneratorRepository, CourseRepository newCourseRepository)
	{
		scheduleGeneratorRepository = newScheduleGeneratorRepository;
		topThreeSchedules = scheduleGeneratorRepository.getTopThreeSchedules();
		courseRepository = newCourseRepository;
		topThreeSchedulesDestinationPath = courseRepository.getTopThreeSchedulesDestinationPath();

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
	 * Purpose: To read the available classes file and store the information in the course repository
	 * @param availableClassesFilePath The path to the available classes file to be read
	 */
	public void readAvailableClassesFile(Path availableClassesFilePath)
	{
	    try
	    {
	        File availableClassesFile = new File(availableClassesFilePath.toString());
	        Scanner input = new Scanner(availableClassesFile);

	        if (input.hasNextLine())
	        {
	            String headerLine = input.nextLine().trim();
	            String[] headers = headerLine.split("\\s*,\\s*");

	            Map<String, Integer> colIndex = new HashMap<>();
	            for (int i = 0; i < headers.length; i++)
	            {
	                colIndex.put(headers[i].trim(), i);
	            }

	            while (input.hasNextLine())
	            {
	                String line = input.nextLine().trim();

	                if (!line.isEmpty())
	                {
	                    String[] values = line.split("\\s*,\\s*");

	                    String courseID = getValue(values, colIndex, "Course ID");
	                    String daysStr = getValue(values, colIndex, "Days");
	                    String timeStr = getValue(values, colIndex, "Time");
	                    String courseInstructor = getValue(values, colIndex, "Instructor");
	                    double courseRMPScore = parseDoubleSafe(getValue(values, colIndex, "RMP"));
	                    String courseCampus = getValue(values, colIndex, "Campus");
	                    int courseCredits = parseIntSafe(getValue(values, colIndex, "Credits"));

	                    WeeklyTimeBlock courseWeeklyTimeBlock = buildWeeklyTimeBlock(daysStr, timeStr);

	                    courseRepository.storeClassInfo(
	                        courseID,
	                        courseWeeklyTimeBlock,
	                        courseInstructor,
	                        courseRMPScore,
	                        courseCampus,
	                        courseCredits
	                    );
	                }
	            }
	        }
	        input.close();
	    }
	    catch (FileNotFoundException e)
	    {
	        // Handles the exception here instead of throwing
	        System.err.println("Error: The file could not be found at path: " + availableClassesFilePath);
	        e.printStackTrace();
	    }
	}

	/**
	 * Purpose: To get the value from the values array based on the column name and column index mapping
	 * (helper method for readAvailableClassesFile)
	 * @param values The array of values from the current line being read
	 * @param colIndex The mapping of column names to their respective indices in the values array
	 * @param colName The name of the column for which to retrieve the value
	 * @return The value from the values array corresponding to the given column name, or an empty string if the column name is not found or the index is out of bounds
	 */
	private static String getValue(String[] values, Map<String, Integer> colIndex, String colName)
	{
	    Integer idx = colIndex.get(colName);
	    if (idx == null || idx >= values.length) return "";
	    else return values[idx].trim();
	}

	/**
	 * Purpose: To safely parse a string to a double, returning 0.0 if parsing fails
	 * (helper method for readAvailableClassesFile)
	 * @param s The string to be parsed to a double
	 * @return The double value parsed from the string, or 0.0 if parsing fails
	 */
	private static double parseDoubleSafe(String s)
	{
	    try
	    {
	        return Double.parseDouble(s);
	    }
	    catch (Exception e)
	    {
	        return 0.0;
	    }
	}

	/**
	 * Purpose: To safely parse a string to an integer, returning 0 if parsing fails
	 * (helper method for readAvailableClassesFile)
	 * @param s The string to be parsed to an integer
	 * @return The integer value parsed from the string, or 0 if parsing fails
	 */
	private static int parseIntSafe(String s)
	{
	    try
	    {
	        return Integer.parseInt(s);
	    }
	    catch (Exception e)
	    {
	        return 0;
	    }
	}

	/**
	 * Purpose: To build a WeeklyTimeBlock object from the given days string and time string
	 * (helper method for readAvailableClassesFile)
	 * @param daysStr The string representing the days of the week for the time block
	 * @param timeStr The string representing the start and end times for the time block
	 * @return A WeeklyTimeBlock object constructed from the given days and time information
	 */
	private static WeeklyTimeBlock buildWeeklyTimeBlock(String daysStr, String timeStr)
	{
	    List<DayOfWeek> days = parseDays(daysStr);

	    LocalTime start = parseStartTime(timeStr);
	    Duration duration = parseDuration(timeStr);

	    return new WeeklyTimeBlock(days, start, duration);
	}

	/**
	 * Purpose: To parse the days string and extract the corresponding DayOfWeek values
	 * (helper method for buildWeeklyTimeBlock)
	 * @param daysStr The string representing the days of the week, which may contain various formats (MoWe, etc)
	 * @return A list of DayOfWeek values corresponding to the days represented in the input string
	 */
	private static List<DayOfWeek> parseDays(String daysStr)
	{
	    List<DayOfWeek> result = new ArrayList<>();
	    String d = daysStr.replaceAll("[^A-Za-z]", "");

	    for (int i = 0; i < d.length(); )
	    {
	        if (i + 2 <= d.length() && d.startsWith("Th", i))
	        {
	            result.add(DayOfWeek.THURSDAY);
	            i += 2;
	        }
	        else
	        {
	            String token = d.substring(i, Math.min(i + 2, d.length()));

	            if (token.equals("Mo")) result.add(DayOfWeek.MONDAY);
	            else if (token.equals("Tu")) result.add(DayOfWeek.TUESDAY);
	            else if (token.equals("We")) result.add(DayOfWeek.WEDNESDAY);
	            else if (token.equals("Fr")) result.add(DayOfWeek.FRIDAY);
	            else if (token.equals("Sa")) result.add(DayOfWeek.SATURDAY);
	            else if (token.equals("Su")) result.add(DayOfWeek.SUNDAY);

	            i += 2;
	        }
	    }
	    return result;
	}

	/**
	 * Purpose: To parse the time string and extract the start time as a LocalTime object
	 * (helper method for buildWeeklyTimeBlock)
	 * @param timeStr The string representing the start and end times, formatted as "HHmm-HHmm"
	 * @return A LocalTime object representing the start time extracted from the input string
	 */
	private static LocalTime parseStartTime(String timeStr)
	{
	    String start = timeStr.split("-")[0];
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
	    return LocalTime.parse(start, formatter);
	}

	/**
	 * Purpose: To parse the time string and calculate the duration between the start and end times
	 * (helper method for buildWeeklyTimeBlock)
	 * @param timeStr The string representing the start and end times, formatted as "HHmm-HHmm"
	 * @return A Duration object representing the duration between the start and end times extracted from the input string
	 */
	private static Duration parseDuration(String timeStr)
	{
	    String[] parts = timeStr.split("-");
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");

	    LocalTime start = LocalTime.parse(parts[0], formatter);
	    LocalTime end = LocalTime.parse(parts[1], formatter);

	    Duration duration = Duration.between(start, end);

	    if (duration.isNegative() || duration.isZero())
	    {
	        duration = duration.plusHours(24);
	    }
	    return duration;
	}




	
	
	/**
	 * Purpose: To write the top three schedules to a file at the destination path for the top three schedules file
	 * 
	 */
	public void topThreeToCSV()
	{
	    List<String> createdFilePaths = new ArrayList<>();

	    if (topThreeSchedules != null && topThreeSchedules.size() >= 3)
	    {
	        for (int i = 0; i < 3; i++)
	        {
	            Schedule schedule = topThreeSchedules.get(i);

	            String filePath = topThreeSchedulesDestinationPath.toString() + "/Schedule_" + (i + 1) + ".csv";
	            File outputFile = new File(filePath);

	            try (PrintWriter writer = new PrintWriter(outputFile))
	            {
	                writer.println("WeeklyTimeBlock,CourseName,CourseID,CourseInstructor,CourseRMPScore,CourseCampus,CourseCredits");

	                List<Course> courses = schedule.getCurrentScheduleCourses();

	                if (courses != null)
	                {
	                    for (Course course : courses)
	                    {
	                        String timeBlockStr = course.getCourseWeeklyTimeBlock().toString();

	                        writer.println(
	                                timeBlockStr + "," +
	                                course.getCourseName() + "," +
	                                course.getCourseID() + "," +
	                                course.getInstructorName() + "," +
	                                course.getInstructorRMPScore() + "," +
	                                course.getCourseCampusLocation() + "," +
	                                course.getCourseCredits()
	                        );
	                    }
	                }

	                // Add file paths to a lsit
	                createdFilePaths.add(filePath);

	                // Auto-open the top three schedules files
	                if (Desktop.isDesktopSupported())
	                {
	                    Desktop.getDesktop().open(outputFile);
	                }
	            }
	            catch (Exception e)
	            {
	                e.printStackTrace();
	            }
	        }
	    }
	    
	    // Save the file paths of the created top 3 schedule CSV files to the schedule generator repository
	    scheduleGeneratorRepository.saveTopThreeCSVFilePaths(createdFilePaths);
	}
	

}
