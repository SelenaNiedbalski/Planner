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
	// Instance Variables
	// IVs App views
	private AboutYouView aboutYouView; // An app controller has-an about you view
	private CourseInfoView courseInfoView; // An app controller has-a course info view
	private CreditsView creditsView; // An app controller has-a credits view
	private ScheduleGeneratorView scheduleGeneratorView; // An app controller has-a schedule generator view
	private WishlistView wishlistView; // An app controller has-a wishlist view
	private StartScreenView startScreenView; // An app controller has-a start screen view
	private LoadingScreenView loadingScreenView; // An app controller has-a loading screen view
	
	// IVs App models
	private AboutYouModel aboutYouModel; // An app controller has-an about you model
	private CourseInfoModel courseInfoModel; // An app controller has-a course info model
	private CreditsModel creditsModel; // An app controller has-a credits model
	private ScheduleGenerator scheduleGenerator; // An app controller has-a schedule generator model
	private WishlistModel wishlistModel; // An app controller has-a wishlist model
	
	/**
	 * Purpose: To construct an AppController with the given app views and app models
	 * @param newStartScreenView The new start screen view
	 * @param newAboutYouView The about you view 
	 * @param newCourseInfoView The course info view
	 * @param newCreditsView The credits view
	 * @param newScheduleGeneratorView The schedule generator view
	 * @param newWishlistView The wishlist view
	 * @param newLoadingScreenView The new loading screen view
	 * @param newAboutYouModel The about you model
	 * @param newCourseInfoModel The course info model
	 * @param newCreditsModel The credits model
	 * @param newScheduleGenerator The schedule generator model
	 * @param newWishlistModel The wishlist model
	 */
	public AppController(StartScreenView newStartScreenView, AboutYouView newAboutYouView, CourseInfoView newCourseInfoView, 
			CreditsView newCreditsView, ScheduleGeneratorView newScheduleGeneratorView, WishlistView newWishlistView, 
			LoadingScreenView newLoadingScreenView,AboutYouModel newAboutYouModel, CourseInfoModel newCourseInfoModel, 
			CreditsModel newCreditsModel, ScheduleGenerator newScheduleGenerator, WishlistModel newWishlistModel)
	{
		super();
		
		// Set appviews
		startScreenView = newStartScreenView;
		aboutYouView = newAboutYouView;
		courseInfoView = newCourseInfoView;
		creditsView = newCreditsView;
		scheduleGeneratorView = newScheduleGeneratorView;
		wishlistView = newWishlistView;
		loadingScreenView = newLoadingScreenView;
		
		// Set the app controller for each view
		startScreenView.setAppController(this);
		aboutYouView.setAppController(this);
		courseInfoView.setAppController(this);
		creditsView.setAppController(this);
		scheduleGeneratorView.setAppController(this);
		wishlistView.setAppController(this);
		loadingScreenView.setAppController(this);
		
		// Add action events to each JComponent within each view (Add e to comp for)
		// Add e to comp for StartScreenView
		startScreenView.getStartButton().addActionListener(this);
		
		
		
		// Add e to comp for AboutYouView
		
		// Add e to comp for CourseInfoView
		
		// Add e to comp for CreditsView
		
		// Add e to comp for ScheduleGeneratorView
		
		// Add e to comp for WishlistView
		
		// Add e to comp for LoadingScreenView
		
		
		
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
		// If the source is the start button from the start screen view
		if(e.getSource() == startScreenView.getStartButton())
		{
			startApp();
		}
		
	}
		
	
	// Getters and Setter that are getting changed


	
	// Other Methods
	/**
	 * Purpose: To start the app's process by closing out the start window and opening the 
	 * wishlist window
	 * 
	 */
	public void startApp()
	{
		// Close the start screen
		startScreenView.dispose();
		
		// Open a new wishlist window
		wishlistView.setVisible(true);
	}
	
}
