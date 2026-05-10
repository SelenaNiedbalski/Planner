package mcvandfileservice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;

import schedules.ScheduleGenerator;
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
 * NOTE: Exceptions are all handled HERE
 * 
 */

public class AppController implements ActionListener
{
	// Instance Variables (daisy chains might edit)
	private int minRequiredCredits; // An app controller has-a minimum amount of required credits
	private int maxRequiredCredits; // An app controller has-a maximum amount of required credits
	private Duration minDesiredBreakTime; // An app controller has-a minimum desired break time
	private Duration maxDesiredBreakTime; // An app controller has-a maximum desired break time
	private WeeklyTimeBlock desiredStartAndEndTime; // An app controller has-a desired start and end time for each day of the week
	private String desiredCampusLocation; // An app controller has-a desired campus location
	private String studentsMajorDistinction; // An app controller has-a student's major distinction (STEM, non-STEM, Undecided)
	
//	minRequiredCredits = userDataRepository.getMinRequiredCredits();
//	maxRequiredCredits = userDataRepository.getMaxRequiredCredits();
//	minDesiredBreakTime = userDataRepository.getMinDesiredBreakTime();
//	maxDesiredBreakTime = userDataRepository.getMaxDesiredBreakTime();
//	desiredStartAndEndTime = userDataRepository.getDesiredStartAndEndTime();
//	desiredCampusLocation = userDataRepository.getDesiredCampusLocation();
//	studentsMajorDistinction = userDataRepository.getStudentsMajorDistinction();
	
	
	// IVs App views
	private AboutYouView aboutYouView; // An app controller has-an about you view
	private CourseInfoView courseInfoView; // An app controller has-a course info view
	private CreditsView creditsView; // An app controller has-a credits view
	private ScheduleGeneratorView scheduleGeneratorView; // An app controller has-a schedule generator view
	private WishlistView wishlistView; // An app controller has-a wishlist view
	
	// IVs App models
	private AboutYouModel aboutYouModel; // An app controller has-an about you model
	private CourseInfoModel courseInfoModel; // An app controller has-a course info model
	private CreditsModel creditsModel; // An app controller has-a credits model
	private ScheduleGenerator scheduleGenerator; // An app controller has-a schedule generator model
	private WishlistModel wishlistModel; // An app controller has-a wishlist model
	
	/**
	 * Purpose: To construct an AppController with the given app views and app models
	 * @param newAboutYouView The about you view 
	 * @param newCourseInfoView The course info view
	 * @param newCreditsView The credits view
	 * @param newScheduleGeneratorView The schedule generator view
	 * @param newWishlistView The wishlist view
	 * @param newAboutYouModel The about you model
	 * @param newCourseInfoModel The course info model
	 * @param newCreditsModel The credits model
	 * @param newScheduleGenerator The schedule generator model
	 * @param newWishlistModel The wishlist model
	 */
	public AppController(AboutYouView newAboutYouView, CourseInfoView newCourseInfoView, 
			CreditsView newCreditsView, ScheduleGeneratorView newScheduleGeneratorView, 
			WishlistView newWishlistView, AboutYouModel newAboutYouModel, 
			CourseInfoModel newCourseInfoModel, CreditsModel newCreditsModel, 
			ScheduleGenerator newScheduleGenerator, WishlistModel newWishlistModel)
	{
		super();
		
		// Set appviews
		aboutYouView = newAboutYouView;
		courseInfoView = newCourseInfoView;
		creditsView = newCreditsView;
		scheduleGeneratorView = newScheduleGeneratorView;
		wishlistView = newWishlistView;
		
		// Set appmodels
		aboutYouModel = newAboutYouModel;
		courseInfoModel = newCourseInfoModel;
		creditsModel = newCreditsModel;
		scheduleGenerator = newScheduleGenerator;
		wishlistModel = newWishlistModel;
		
		// Update the count label for the course num in that view to match the one in the model
	}

	// Methods
	/**
	 * Invoked when an action occurs
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Purpose: To save the information from the user's input into the correct repositories
	 * 
	 */
	
	
	
	
	
	
	
	// Getters and Setter that are getting changed
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
