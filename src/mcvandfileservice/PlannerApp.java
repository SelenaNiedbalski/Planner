package mcvandfileservice;

import schedules.ScheduleGenerator;
import sounds.SoundPlayer;

/**
 * Lead Author(s):
 * 
 * @author Selena Niedbalski
 * 
 * 
 *         References:
 *         Morelli, R., & Walde, R. (2016). Java, Java, Java: Object-Oriented
 *         Problem Solving.
 *         Retrieved from
 *         https://open.umn.edu/opentextbooks/textbooks/java-java-java-object-oriented-problem-solving
 * 
 * 
 *         Version/date: 31 May 2026
 * 
 *         Responsibilities of class: To represent the main entry point of the
 *         application, initializing all views, models, repositories, and the
 *         controller to set up the app's functionality.
 * 
 */

public class PlannerApp
{
	// Main method to run the app
	public static void main(String[] args)
	{
		// Initialize app views
		StartScreenView startScreenView = new StartScreenView();
		WishlistView wishlistView = new WishlistView();
		CreditsView creditsView = new CreditsView();
		AboutYouView aboutYouView = new AboutYouView();
		CourseInfoView courseInfoView = new CourseInfoView();
		LoadingScreenView loadingScreenView = new LoadingScreenView();

		// Initialize repositories and File Service
		UserDataRepository userDataRepository = new UserDataRepository();
		CourseRepository courseRepository = new CourseRepository(
				userDataRepository);
		ScheduleGeneratorRepository scheduleGeneratorRepository = new ScheduleGeneratorRepository();
		FileService fileService = new FileService(userDataRepository);

		// Initialize app models
		WishlistModel wishlistModel = new WishlistModel();
		CreditsModel creditsModel = new CreditsModel();
		AboutYouModel aboutYouModel = new AboutYouModel();
		CourseInfoModel courseInfoModel = new CourseInfoModel();
		ScheduleGenerator scheduleGenerator = new ScheduleGenerator(
				loadingScreenView);

		// Initialize sound player for music
		SoundPlayer soundPlayer = new SoundPlayer();

		// Initialize app controller
		AppController appController = new AppController(startScreenView,
				aboutYouView, courseInfoView, creditsView, wishlistView,
				loadingScreenView, aboutYouModel, courseInfoModel, creditsModel,
				scheduleGenerator, wishlistModel, fileService,
				userDataRepository, courseRepository,
				scheduleGeneratorRepository, soundPlayer);
	}
}
