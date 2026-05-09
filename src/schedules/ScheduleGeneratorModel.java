package schedules;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

import courseclasses.Course;
import mcvandfileservice.ScheduleGeneratorRepository;
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
 * Responsibilities of class: To represent the data model for the schedule generator which handles final calculations and creations of randomized schedules
 * 
 */

public class ScheduleGeneratorModel
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
	
	
	
	// Constructors
	/**
	 * Purpose: To create a schedule generator object that has parameter values
	 * @param scheduleGeneratorRepository The schedule generator repository that the schedule generator model will pull data from to set its instance variables
	 */
	public ScheduleGeneratorModel(ScheduleGeneratorRepository scheduleGeneratorRepository)
	{
		allCourses = null;
		topThreeSchedules = null;
		randomizer = null;
		minCredits = scheduleGeneratorRepository.getMinRequiredCredits();
		maxCredits = scheduleGeneratorRepository.getMaxRequiredCredits();
		minBreakTime = scheduleGeneratorRepository.getMinDesiredBreakTime();
		maxBreakTime = scheduleGeneratorRepository.getMaxDesiredBreakTime();
		desiredStartAndEndTime = scheduleGeneratorRepository.getDesiredStartAndEndTime();
		desiredCampusLocation = scheduleGeneratorRepository.getDesiredCampusLocation();
		studentsMajorDistinction = scheduleGeneratorRepository.getStudentsMajorDistinction();
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
	
	
	
	
	
	// Other Methods
	/**
	 * Purpose: To create many possible schedule objects to compare to eachother
	 */
	public List<Schedule> generatePossibleSchedule()
	{
		// TODO: Code that generates possibles schedules
		// cap at create possible schedules amount (method)
		// randomize with randomizer (method)
	}
	
	/**
	 * Purpose: To generate the top 3 schedules
	 * @return topThreeSchedules The 3 highest scoring schedules
	 */
	public List<Schedule> generateTopThreeSchedules()
	{
		// TODO: Code that does calculations and returns top 3
		// includes print out
		return topThreeSchedules;
	}
	
	/**
	 * Purpose: To calculate the schedule's score
	 * @return scheduleScore The score of the schedule
	 */
	public double calculateScheduleScore(Schedule schedule)
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
	 */
	public void addToTopThreeSchedules(Schedule possibleSchedule)
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
	 * Purpose: To randomize the adding of courses to the schedules
	 * 
	 */
	public void randomizeSchedule(Schedule schedule)
	{
		// TODO: Code that uses the randomizer to randomize which courses get added to the generated schedules
		// (helper method)
	}
	
	/**
	 * Purpose: To cap the amount of schedules generated at the specified parameter amount
	 * @param maxSchedules The max amount of schedules generated
	 */
	public List<Schedule> createPossibleSchedules(int maxSchedules)
	{
		// TODO: Code that actually creates schedule objects after checking is done in shell calculation (this is the final helper)
	}


	/**
	 * Purpose: To set the top 3 schedules for test purposes ONLY
	 * @param topSchedulesTest5 The list of 3 schedules to set as the top 3 schedules for testing purposes
	 */
	public void setTopThreeSchedules(List<Schedule> topSchedulesTest5)
	{
		topThreeSchedules = (List<Schedule>) topSchedulesTest5;
	}
	
	/**
	 * Purpose: To calculate the scores for each schedule created and compare to eachother
	 */
	
	
	
	
}
