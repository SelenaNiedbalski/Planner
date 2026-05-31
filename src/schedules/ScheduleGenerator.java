package schedules;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;

import courseclasses.Course;
import mcvandfileservice.LoadingScreenView;
import mcvandfileservice.ScheduleGeneratorRepository;
import mcvandfileservice.UserDataRepository;
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
 * Version/date: 9 May 2026
 * 
 * Responsibilities of class: To represent the data model for the schedule generator which handles final calculations and creations of randomized schedules
 * 
 */

public class ScheduleGenerator
{
	// Instance Variables
	private List<Course> allCourses; // A schedule generator model has-many courses
	private List<Schedule> topThreeSchedules; // A schedule generator model has-many top schedules
	private Random randomizer; // A schedule generator model has-a randomizer
	private int minCredits; // A schedule generator model has-a minimum amount of credits
	private int maxCredits; // A schedule generator model has-a maximum amount of credits
	private Long minBreakTime; // A schedule generator model has-a minimum break time
	private Long maxBreakTime; // A schedule generator model has-a maximum break time
	private HashMap<DayOfWeek, WeeklyTimeBlock> desiredStartAndEndTime; // A schedule generator model has-a desired start and end time for eachday of the week
	private String desiredCampusLocation; // A schedule generator model has-a desired campus location
	private String studentsMajorDistinction; // A schedule generator model has-a student's major distinction (STEM, non-STEM, undecided)
	private ScheduleGeneratorRepository scheduleGeneratorRepository; // A schedule generator model has-a schedule generator repository
	private LoadingScreenView loadingScreen; // A schedule generator model has-a loading screen
	
	
	// Constructors
	/**
	 * Purpose: To create a schedule generator object that has parameter values
	 * @param newLoadingScreen The loading screen to set the schedule generator's loading screen instance variable to
	 */
	public ScheduleGenerator(LoadingScreenView newLoadingScreen)
	{
		allCourses = null;
		topThreeSchedules = null;
		randomizer = null;
		loadingScreen = newLoadingScreen;
	}
	
	/**
	 * Purpose: To set the info for the min and max credits, break times, desired start and end times, desired campus location, and student's major distinction from the user data repository
	 * @param newUserDataRepository The user data repository to grab the info from
	 * @param newScheduleGeneratorRepository The schedule generator repository to set the schedule generator repository instance variable to
	 */
	public void setScheduleGeneratorReposAndFileService(UserDataRepository newUserDataRepository, ScheduleGeneratorRepository newScheduleGeneratorRepository)
	{
		minCredits = newUserDataRepository.getMinRequiredCredits();
		maxCredits = newUserDataRepository.getMaxRequiredCredits();
		minBreakTime = newUserDataRepository.getMinDesiredBreakTime();
		maxBreakTime = newUserDataRepository.getMaxDesiredBreakTime();
		desiredStartAndEndTime = newUserDataRepository.getDesiredStartAndEndTime();
		desiredCampusLocation = newUserDataRepository.getDesiredCampusLocation();
		studentsMajorDistinction = newUserDataRepository.getStudentsMajorDistinction();
		scheduleGeneratorRepository = newScheduleGeneratorRepository;
	}
	
	/**
	 * Purpose: To add completed courses to the schedule generator's course pool
	 * @param completedCourses The list of completed courses to add to the schedule generator's course pool
	 */
	public void addCompletedCoursesToCoursePool(List<Course> completedCourses)
	{
		if (allCourses == null)
		{
			allCourses = new ArrayList<>();
		}
		
		for (Course course : completedCourses)
		{
			allCourses.add(course);
		}
	}
	
	// Getters and Setters
	/**
	 * Purpose: To return the list of all available courses
	 * return allCourses The available course pool
	 */
	public List<Course> getAllCourses()
	{
		return allCourses;
	}
	
	/**
	 * Purpose: To set the list of all available courses
	 * @param newAllCourses The new pool of available courses
	 */
	public void setAllCourses(List<Course> newAllCourses)
	{
		allCourses = newAllCourses;
	}
	
	/**
	 * Purpose: To return the top 3 scoring schedules *for test ONLY*
	 * return topThreeSchedules The top 3 highest scoring schedules
	 */
	public List<Schedule> getTopThreeSchedules()
	{
		return topThreeSchedules;
	}
	
	/**
	 * Purpose: To return the minimum credits the schedule must have
	 * @return minCredits The minimum credits for a schedule
	 */
	public int getMinCredits()
	{
		return minCredits;
	}
	
	/**
	 * Purpose: To return the maximum credits the schedule must have
	 * @return maxCredits The maximum credits for a schedule
	 */
	public int getMaxCredits()
	{
		return maxCredits;
	}
	
	/**
	 * Purpose: To set the top 3 schedules for test purposes ONLY
	 * @param newopSchedules The list of 3 schedules to set as the top 3 schedules for testing purposes
	 */
	public void setTopThreeSchedules(List<Schedule> newTopSchedules)
	{
		topThreeSchedules = newTopSchedules;
	}
	
	
	
	
	// Helper Methods
	/**
	 * Purpose: To calculate the schedule's score
	 * (helper method for generateSchedules)
	 * @param schedule The schedule being scored
	 * @return scheduleScore The score of the schedule
	 */
	private double calculateScheduleScore(Schedule schedule)
	{		
		// Drop schedule score to zero if it's outside of required credits
		if (this.withinCredits(schedule) == false)
		{
			schedule.setScheduleScore(-300.0);
		}
		else
		{
			schedule.addCourseScores(desiredCampusLocation, studentsMajorDistinction);
			schedule.calculateBreakTimeDeviation(minBreakTime, maxBreakTime);
			schedule.calculateStartAndEndTimeDeviation(desiredStartAndEndTime);
			schedule.subtractPointsForSTEMCoursesOverThree();		
			schedule.subtractPointsForStruggleCoursesOverOne();
		}
		
		// This is only to return the score, this is not the actual value holder
		return schedule.getScheduleScore();
			
	}
	
	/**
	 * Purpose: To check if the schedule's total credits are within the credit range
	 * (helper method for calculateScheduleScore)
	 * @return true if it's within the range and false if it's not
	 */
	private boolean withinCredits(Schedule schedule)
	{
		if ((schedule.getTotalCredits()) >= minCredits && schedule.getTotalCredits() <= maxCredits)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Purpose: To add a schedule to the top 3 schedules list and remove
	 * the lowest scoring one if there are already 3 in there
	 * (helper method for generateSchedules)
	 * @param possibleSchedule The schedule being considered to be added to the top 3 schedules list
	 */
	private void addToTopThreeSchedules(Schedule possibleSchedule)
	{
		// If fewer than 3 schedules, just add it
	    if (topThreeSchedules.size() < 3) 
	    {
	        topThreeSchedules.add(possibleSchedule);
	    } 
	    else 
	    {
	        // Find the lowest-scoring schedule using compareTo
	        Schedule lowestSchedule = topThreeSchedules.stream()
	                .reduce((s1, s2) -> s1.compareTo(s2) == s1 ? s2 : s1)
	                .orElse(null);

	        // If potentialSchedule is better than the lowest, replace it
	        if (lowestSchedule != null &&
	            possibleSchedule.compareTo(lowestSchedule) == possibleSchedule) 
	        {
	            topThreeSchedules.removeIf(schedule -> schedule == lowestSchedule);
	            topThreeSchedules.add(possibleSchedule);
	        }
	    }
	    
	    // Sort the top three schedules highest to lowest
	    topThreeSchedules.sort((a, b) -> a.compareTo(b) == a ? -1 : 1);
	}
	
	/**
	 * Purpose: To randomize the courses that get added to a schedule object
	 * (helper method for createPossibleSchedules)
	 * @param schedule The schedule being generated that will have courses added to it
	 */
	private void addRandomCoursesToSchedule(Schedule schedule)
	{
	    // Return if there are no available courses
	    if (allCourses == null || allCourses.isEmpty())
	    {
	        return;
	    }

	    // Initialize randomizer if needed
	    if (randomizer == null)
	    {
	        randomizer = new Random();
	    }

	    
	    // shuffle all courses
	    List<Course> shuffledCourses = new ArrayList<>(allCourses);
	    Collections.shuffle(shuffledCourses, randomizer);

	    // Group all sections by course name
	    HashMap<String, List<Course>> courseSectionsByName = new HashMap<>();
	    
	    for (Course course : shuffledCourses)
	    {
	        String courseName = course.getCourseName();

	        if (courseSectionsByName.containsKey(courseName) == false)
	        {
	            courseSectionsByName.put(courseName, new ArrayList<>());
	        }

	        courseSectionsByName.get(courseName).add(course);
	    }

	    // Pick one random section for each course name
	    List<Course> chosenSections = new ArrayList<>();

	    for (String courseName : courseSectionsByName.keySet())
	    {
	        List<Course> sectionOptions = courseSectionsByName.get(courseName);

	        if (sectionOptions != null && sectionOptions.isEmpty() == false)
	        {
	            int randomIndex = randomizer.nextInt(sectionOptions.size());
	            Course chosenSection = sectionOptions.get(randomIndex);
	            chosenSections.add(chosenSection);
	        }
	    }

	    // Shuffle chosen sections so schedule generation order is random
	    Collections.shuffle(chosenSections, randomizer);
	    
	 
	    
	    
	  
	    
	    // Try to add chosen sections until minimum credits are reached or adding more would exceed max credits
	    for (Course course : chosenSections)
	    {
	        WeeklyTimeBlock courseBlock = course.getCourseWeeklyTimeBlock();

	        boolean allowedByUser = true;

	        if (courseBlock != null)
	        {
	            List<DayOfWeek> days = courseBlock.getDaysOfTheWeek();

	            for (DayOfWeek day : days)
	            {
	                // If user left that day blank → do NOT allow this course
	                if (desiredStartAndEndTime.get(day) == null)
	                {
	                    allowedByUser = false;
	                }
	            }
	        }

	        // Skip courses on days the user does not want
	        if (!allowedByUser)
	        {
	            continue;
	        }

	        if (schedule.getTotalCredits() < minCredits)
	        {
	            if (schedule.getTotalCredits() + course.getCourseCredits() <= maxCredits)
	            {
	                schedule.addCourse(course);
	            }
	        }
	    }
	}

	
	
	
	
	/**
	 * Purpose: To cap the amount of schedules generated at the specified parameter amount
	 * (helper method for generatePossibleSchedule)
	 * @param maxSchedules The max amount of schedules generated
	 */
	private List<Schedule> createPossibleSchedules(int maxSchedules)
	{
	    List<Schedule> possibleSchedules = new ArrayList<>();
	    
	    int attempts = 0;
	    int maxAttempts = maxSchedules * 20;

	    // Track start time
	    long startTime = System.currentTimeMillis();

	    while (possibleSchedules.size() < maxSchedules && attempts < maxAttempts)
	    {
	        attempts++;

	        Schedule schedule = new Schedule();
	        this.addRandomCoursesToSchedule(schedule);

	        boolean enoughCredits = schedule.getTotalCredits() >= minCredits;
	        boolean duplicate = scheduleExists(possibleSchedules, schedule);

	        if (enoughCredits && !duplicate)
	        {
	            possibleSchedules.add(schedule);
	        }

	        // Progress based on attempts
	        int attemptsCopy = attempts;
	        int maxAttemptsCopy = maxAttempts;

	        long elapsedTime = System.currentTimeMillis() - startTime;

	        // Progress percent
	        double progress = attemptsCopy / (double) maxAttemptsCopy;

	        // Estimate total time
	        long estimatedTotalTime = (long) (elapsedTime / Math.max(progress, 0.0001));

	        // Remaining time
	        long remainingTime = estimatedTotalTime - elapsedTime;

	        SwingUtilities.invokeLater(() ->
	        {
	            loadingScreen.setProgress(attemptsCopy, maxAttemptsCopy);
	            loadingScreen.setTimeRemaining(remainingTime);
	        });
	    }

	    return possibleSchedules;
	}
	
	
	
	
	
	
	// Other Methods
	/**
	 * Purpose: To create many possible schedule objects to compare to each other
	 * and add to the top 3 schedules list if they are high scoring enough
	 * and return the top 3 schedules at the end
	 * @param maxSchedules The max amount of schedules generated
	 * @return topThreeSchedules The 3 highest scoring schedules generated
	 */
	public List<Schedule> generateTopThreeSchedules(int maxSchedules)
	{
	    // Generate all possible schedules
	    List<Schedule> possibleSchedules = this.createPossibleSchedules(maxSchedules);

	    // Ensure topThreeSchedules is initialized
	    if (topThreeSchedules == null)
	    {
	        topThreeSchedules = new ArrayList<>();
	    }

	    // Loop through each schedule
	    for (Schedule schedule : possibleSchedules)
	    {
	        // Calculate its score
	        this.calculateScheduleScore(schedule);

	        // Let helper method handle ranking
	        this.addToTopThreeSchedules(schedule);
	    }
	    
	    // Add 3 top schedules to the schedule generator repository
	    scheduleGeneratorRepository.addTopThreeSchedules(topThreeSchedules);
	    
	    return topThreeSchedules;
	}

	/**
	 * Purpose: To clear the course pool if the user selects back button on course info view
	 * 
	 */
	public void clearCoursePool()
	{
		if (allCourses != null)
		{
			allCourses.clear();
		}		
	}
	
	/**
	 * Purpose: To check if a schedule with the same courses already exists in the list of schedules
	 * @param list The list of schedules to check against
	 * @param newSchedule The new schedule being considered for addition to the list
	 * @return true if a schedule with the same courses already exists, false otherwise
	 */
	private boolean scheduleExists(List<Schedule> list, Schedule newSchedule)
	{
	    for (Schedule existing : list)
	    {
	        if (existing.getCurrentScheduleCourses()
	                .equals(newSchedule.getCurrentScheduleCourses()))
	        {
	            return true;
	        }
	    }
	    return false;
	}
	
}
