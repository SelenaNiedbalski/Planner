package schedules;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import courseclasses.Course;
import mcvandfileservice.FileService;
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
	private Duration minBreakTime; // A schedule generator model has-a minimum break time
	private Duration maxBreakTime; // A schedule generator model has-a maximum break time
	private WeeklyTimeBlock desiredStartAndEndTime; // A schedule generator model has-a desired start and end time for eachday of the week
	private String desiredCampusLocation; // A schedule generator model has-a desired campus location
	private String studentsMajorDistinction; // A schedule generator model has-a student's major distinction (STEM, non-STEM, undecided)
	private ScheduleGeneratorRepository scheduleGeneratorRepository; // A schedule generator model has-a schedule generator repository
	private FileService fileService; // A schedule generator model has-a file service
	
	
	
	// Constructors
	/**
	 * Purpose: To create a schedule generator object that has parameter values
	 * @param userDataRepository The user data repository 
	 * @param newScheduleGeneratorRepository The schedule generator repository
	 * @param newFileService The file service
	 */
	public ScheduleGenerator(UserDataRepository userDataRepository, ScheduleGeneratorRepository newScheduleGeneratorRepository, FileService newFileService)
	{
		allCourses = null;
		topThreeSchedules = null;
		randomizer = null;
		minCredits = userDataRepository.getMinRequiredCredits();
		maxCredits = userDataRepository.getMaxRequiredCredits();
		minBreakTime = userDataRepository.getMinDesiredBreakTime();
		maxBreakTime = userDataRepository.getMaxDesiredBreakTime();
		desiredStartAndEndTime = userDataRepository.getDesiredStartAndEndTime();
		desiredCampusLocation = userDataRepository.getDesiredCampusLocation();
		studentsMajorDistinction = userDataRepository.getStudentsMajorDistinction();
		scheduleGeneratorRepository = newScheduleGeneratorRepository;
		fileService = newFileService;
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
	 * @param topSchedulesTest5 The list of 3 schedules to set as the top 3 schedules for testing purposes
	 */
	public void setTopThreeSchedules(List<Schedule> topSchedulesTest5)
	{
		topThreeSchedules = (List<Schedule>) topSchedulesTest5;
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
			schedule.setScheduleScore(0.0);
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
		if ((schedule.getTotalCredits()) >= minCredits || schedule.getTotalCredits() <= maxCredits)
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
	    // Safety checks
	    if (allCourses == null || allCourses.isEmpty())
	    {
	        return;
	    }

	    if (randomizer == null)
	    {
	        randomizer = new Random();
	    }

	    // Shuffle for randomness
	    List<Course> shuffledCourses = new ArrayList<>(allCourses);
	    Collections.shuffle(shuffledCourses, randomizer);

	    // Loop through all shuffled courses and add them until max number is reached
	    for (Course course : shuffledCourses)
	    {
	        if (schedule.getTotalCredits() < maxCredits)
	        {
	            // Let addCourse() handle all validation and updates
	            schedule.addCourse(course);
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
	    // List to store all generated schedules
	    List<Schedule> possibleSchedules = new ArrayList<>();

	    // Keep generating until we reach the desired number of schedules
	    while (possibleSchedules.size() < maxSchedules)
	    {
	        // Create a new empty schedule
	        Schedule schedule = new Schedule();

	        // Randomly add courses to schedule
	        this.addRandomCoursesToSchedule(schedule);

	        // Only keep schedules that meet minimum credit requirement
	        if (schedule.getTotalCredits() >= minCredits)
	        {
	            possibleSchedules.add(schedule);
	        }
	        // If it doesn't meet minCredits, it is discarded and loop continues
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
	
}
