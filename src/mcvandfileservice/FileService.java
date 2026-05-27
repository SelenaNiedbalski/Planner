package mcvandfileservice;

import java.awt.Desktop;
// Java premade imports
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;

import java.io.IOException;
import java.io.InputStream;


import courseclasses.Course;

import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalTime;
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
     * Purpose: To construct a file service object with default values
     * 
     */
    public FileService(UserDataRepository newUserDataRepository)
    {
        scheduleGeneratorRepository = null;
        topThreeSchedules = null;
        courseRepository = null;
        topThreeSchedulesDestinationPath = null;
    }

    /**
     * Purpose: To set the schedule generator repository for the file service
     * @param newScheduleGeneratorRepository The schedule generator repository to be set for the file service
     */
    public void setScheduleGeneratorRepository(ScheduleGeneratorRepository newScheduleGeneratorRepository)
    {
        scheduleGeneratorRepository = newScheduleGeneratorRepository;
    }
    
    /**
     * Purpose: To set the list of the top three schedules for the file service
     * @param newTopThreeSchedules The list of the top three schedules to be set for the file service
     */
    public void setTopThreeSchedules()
    {
        topThreeSchedules = scheduleGeneratorRepository.getTopThreeSchedules();
    }
    
    /**
     * Purpose: To set the top three schedules for the file service
     * @param newTopThreeSchedules The list of the top three schedules to be set for the file service
     */
    public void setTopThreeSchedules(List<Schedule> newTopThreeSchedules)
    {
		topThreeSchedules = newTopThreeSchedules;
	}
    
    /**
     * Purpose: To return the top three schedules for the file service
     * @return The list of the top three schedules for the file service
     */
    public List<Schedule> getTopThreeSchedules()
    {
		return topThreeSchedules;
	}
    
    
    /**
     * Purpose: To set the course repository for the file service
     * @param newCourseRepository The course repository to be set for the file service
     */
    public void setCourseRepository(CourseRepository newCourseRepository)
    {
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

                        WeeklyTimeBlock courseWeeklyTimeBlock = null;

                        // Validate days and time before building the weekly time block
                        if (daysStr != null && !daysStr.trim().isEmpty() &&
                            timeStr != null && !timeStr.trim().isEmpty())
                        {
                            try
                            {
                                courseWeeklyTimeBlock = buildWeeklyTimeBlock(daysStr, timeStr);
                            }
                            catch (Exception e)
                            {
                                // Report invalid schedule data without stopping the file read
                                System.out.println("Invalid time format, row skipped: " + line);
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            // Report rows that do not include complete schedule information to debug
                           // System.out.println("Row missing days/time, skipped: " + line);
                        }

                        // Store the class info only when a valid weekly time block exists
                        if (courseWeeklyTimeBlock != null)
                        {
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
            }

            input.close();
        }
        catch (FileNotFoundException e)
        {
            // Report an invalid file path when the input file cannot be found
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
        if (idx == null || idx >= values.length)
        {
            return "";
        }
        else
        {
            return values[idx].trim();
        }
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
     * Purpose: To build a WeeklyTimeBlock object from the given days and time strings
     * @param daysStr The string representing the days (e.g., MoWe, TuTh)
     * @param timeStr The string representing the time range (e.g., 1420-1625)
     * @return A valid WeeklyTimeBlock if parsing succeeds, otherwise null
     */
    public WeeklyTimeBlock buildWeeklyTimeBlock(String daysStr, String timeStr)
    {
        WeeklyTimeBlock weeklyBlock = null;

        // Check that both day text and time text contain values before parsing
        if (daysStr != null && !daysStr.trim().isEmpty() &&
            timeStr != null && !timeStr.trim().isEmpty())
        {
            List<DayOfWeek> parsedDays = parseDays(daysStr);

            // Require at least one valid day before creating the weekly time block
            if (parsedDays != null && !parsedDays.isEmpty())
            {
                String[] parts = timeStr.split("-");

                // Split the time range into one start time and one end time
                if (parts.length == 2)
                {
                    String start = parts[0].trim();
                    String end = parts[1].trim();

                    LocalTime startTime = parseMilitaryTime(start);
                    LocalTime endTime = parseMilitaryTime(end);

                    // Create the weekly time block only when both times parse successfully
                    if (startTime != null && endTime != null)
                    {
                        weeklyBlock = new WeeklyTimeBlock(parsedDays, startTime, endTime);
                    }
                    else
                    {
                        System.out.println("Invalid start or end time. WeeklyTimeBlock not created.");
                    }
                }
                else
                {
                    System.out.println("Unexpected time format: " + timeStr);
                }
            }
            else
            {
                System.out.println("Invalid days value: " + daysStr);
            }
        }

        return weeklyBlock;
    }

    /**
     * Purpose: To convert a military time string (e.g., 1830 -> 18:30) into a LocalTime object
     * @param time The time string to be parsed
     * @return A LocalTime object if parsing is successful, otherwise null
     */
    private static LocalTime parseMilitaryTime(String time)
    {
        LocalTime parsedTime = null;

        // Accept only 4-digit military time strings before formatting them
        if (time != null && time.trim().length() == 4)
        {
            try
            {
                String formattedTime = time.substring(0, 2) + ":" + time.substring(2);
                parsedTime = LocalTime.parse(formattedTime);
            }
            catch (Exception e)
            {
                System.out.println("Invalid time value: " + time);
            }
        }
        else
        {
            System.out.println("Unexpected time format: " + time);
        }

        return parsedTime;
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
     * Purpose: To write the top schedules to CSV files in the destination folder
     */
    public void topThreeToCSV()
    {
        List<String> createdFilePaths = new ArrayList<>();

        System.out.println("Entered topThreeToCSV");

        // Ensure schedules exist before proceeding
        if (topThreeSchedules == null || topThreeSchedules.isEmpty())
        {
            System.out.println("No schedules available to write.");
            return;
        }

        // Ensure destination path exists
        if (topThreeSchedulesDestinationPath == null)
        {
            System.out.println("Destination path is null.");
            return;
        }

        File dir = topThreeSchedulesDestinationPath.toFile();

        if (!dir.exists())
        {
            System.out.println("Destination folder does not exist. Creating...");
            dir.mkdirs();
        }

        // Determine how many schedules to write (up to 3)
        int numSchedulesToWrite = Math.min(3, topThreeSchedules.size());

        for (int i = 0; i < numSchedulesToWrite; i++)
        {
            System.out.println("Creating schedule #" + (i + 1));

            Schedule schedule = topThreeSchedules.get(i);

            String filePath = topThreeSchedulesDestinationPath.toString()
                    + "/Schedule_" + (i + 1) + ".csv";

            File outputFile = new File(filePath);

            System.out.println("Writing file to: " + outputFile.getAbsolutePath());

            try (PrintWriter writer = new PrintWriter(outputFile))
            {
            	writer.println("Days,Start Time,End Time,Course Name,Course ID,Instructor,RMP,Campus,Credits");

                List<Course> courses = schedule.getCurrentScheduleCourses();

                if (courses != null && !courses.isEmpty())
                {
                	for (Course course : courses)
                	{
                	    WeeklyTimeBlock block = course.getCourseWeeklyTimeBlock();

                	    List<DayOfWeek> days = block.getDaysOfTheWeek();
                	    LocalTime start = block.getClassStartTime();
                	    LocalTime end = block.getClassEndTime();


                	    // Combine all days into one string
                	    String combinedDays = buildDaysString(days);

                	    writer.println(
                	            combinedDays + "," +
                	            start + "," +
                	            end + "," +
                	            course.getCourseName() + "," +
                	            course.getCourseID() + "," +
                	            course.getInstructorName() + "," +
                	            course.getInstructorRMPScore() + "," +
                	            course.getCourseCampusLocation() + "," +
                	            course.getCourseCredits()
                	    );
                	}
                	
                }
                else
                {
                    System.out.println("Schedule has no courses.");
                }

                createdFilePaths.add(filePath);

                // Open file automatically after writing
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

        // Save file paths if repository exists
        if (scheduleGeneratorRepository != null)
        {
            scheduleGeneratorRepository.saveTopThreeCSVFilePaths(createdFilePaths);
        }
        else
        {
            System.out.println("ScheduleGeneratorRepository is null.");
        }
    }
    
    /**
     * Purpose: To save the CSV template file to the top three schedules destination path
     * (when the user clicks the "Save CSV Template" button in the course info view)
     */
    public void copyTemplateCSVToTopThreeDestination()
    {
        try
        {
            InputStream input = getClass().getResourceAsStream("/files/CSV Template.csv");

            // Stop the download when the CSV template resource cannot be found
            if (input == null)
            {
                JOptionPane.showMessageDialog(null, "CSV Template resource could not be found.");
                return;
            }

            // Copy the template directly into the selected destination folder
            Path target = topThreeSchedulesDestinationPath.resolve("CSV Template.csv");
            Files.copy(input, target, StandardCopyOption.REPLACE_EXISTING);

            // Show a success message after the template is copied
            JOptionPane.showMessageDialog(null, "Template successfully downloaded to: \n" + target.toString());

            // Open the downloaded template automatically after saving it
            if (Desktop.isDesktopSupported())
            {
                Desktop.getDesktop().open(target.toFile());
            }

            input.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Download failed: " + e.getMessage());
        }
    }
    
    /**
     * Purpose: To format a DayOfWeek value into a more user-friendly string (e.g., MONDAY -> Monday)
     * @param day The DayOfWeek value to be formatted
     * @return A string representing the formatted day of the week
     */
    private String formatDay(DayOfWeek day)
    {
        String lower = day.toString().toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
    
    private String buildDaysString(List<DayOfWeek> days)
    {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < days.size(); i++)
        {
            String formattedDay = formatDay(days.get(i));
            result.append(formattedDay);

            if (i < days.size() - 1)
            {
                result.append("|"); // separator (you can use comma too, but "|" is cleaner)
            }
        }

        return result.toString();
    }
    
}