package mcvandfileservice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import courseclasses.Course;
import exceptions.IncorrectTimeFormatException;
import schedules.ScheduleGenerator;
import schedules.WeeklyTimeBlock;
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
 *         Responsibilities of class: To represent the controller of the app,
 *         which is responsible for handling user interactions from the views,
 *         handling exceptions, updating the models and repositories based on
 *         user input, and updating the views based on changes to the models and
 *         repositories. The AppController is the central hub that connects all
 *         parts of the app together and allows them to communicate with each
 *         other.
 * 
 *         AppController is-an ActionListerner
 */

public class AppController implements ActionListener
{
	// Instance Variables
	// IVs App views
	private AboutYouView aboutYouView; // An app controller has-an about you
										// view
	private CourseInfoView courseInfoView; // An app controller has-a course
											// info view
	private CreditsView creditsView; // An app controller has-a credits view
	private WishlistView wishlistView; // An app controller has-a wishlist view
	private StartScreenView startScreenView; // An app controller has-a start
												// screen view
	private LoadingScreenView loadingScreenView; // An app controller has-a
													// loading screen view

	// IVs App models
	private AboutYouModel aboutYouModel; // An app controller has-an about you
											// model
	private CourseInfoModel courseInfoModel; // An app controller has-a course
												// info model
	private CreditsModel creditsModel; // An app controller has-a credits model
	private ScheduleGenerator scheduleGenerator; // An app controller has-a
													// schedule generator model
	private WishlistModel wishlistModel; // An app controller has-a wishlist
											// model

	// IV Repositories
	private UserDataRepository userDataRepository; // An app controller has-a
													// user data repository
	private CourseRepository courseRepository; // An app controller has-a course
												// data repository
	private ScheduleGeneratorRepository scheduleGeneratorRepository; // An app
																		// controller
																		// has-a
																		// schedule
																		// generator
																		// repository
	// IV File Service
	private FileService fileService; // An app controller has-a file service

	// Sound IVs
	private SoundPlayer soundPlayer; // An app controller has-a sound player for
										// music

	// Constructor
	/**
	 * Purpose: To construct an AppController with the given app views and app
	 * models
	 * 
	 * @param newStartScreenView             The new start screen view
	 * @param newAboutYouView                The about you view
	 * @param newCourseInfoView              The course info view
	 * @param newCreditsView                 The credits view
	 * @param newWishlistView                The wishlist view
	 * @param newAboutYouModel               The about you model
	 * @param newCourseInfoModel             The course info model
	 * @param newCreditsModel                The credits model
	 * @param newScheduleGenerator           The schedule generator model
	 * @param newWishlistModel               The wishlist model
	 * @param newFileService                 The file service
	 * @param newUserDataRepository          The user data repository
	 * @param newCourseRepository            The course repository
	 * @param newScheduleGeneratorRepository The schedule generator repository
	 * @param newLoadingScreenView           The loading screen view
	 * @param newSoundPlayer                 The sound player for music
	 */
	public AppController(StartScreenView newStartScreenView,
			AboutYouView newAboutYouView, CourseInfoView newCourseInfoView,
			CreditsView newCreditsView, WishlistView newWishlistView,
			LoadingScreenView newLoadingScreenView,
			AboutYouModel newAboutYouModel, CourseInfoModel newCourseInfoModel,
			CreditsModel newCreditsModel,
			ScheduleGenerator newScheduleGenerator,
			WishlistModel newWishlistModel, FileService newFileService,
			UserDataRepository newUserDataRepository,
			CourseRepository newCourseRepository,
			ScheduleGeneratorRepository newScheduleGeneratorRepository,
			SoundPlayer newSoundPlayer)
	{
		super();

		// Set appviews
		startScreenView = newStartScreenView;
		aboutYouView = newAboutYouView;
		courseInfoView = newCourseInfoView;
		creditsView = newCreditsView;
		wishlistView = newWishlistView;
		loadingScreenView = newLoadingScreenView;

		// Set the app controller for each view
		startScreenView.setAppController(this);
		aboutYouView.setAppController(this);
		courseInfoView.setAppController(this);
		creditsView.setAppController(this);
		wishlistView.setAppController(this);
		loadingScreenView.setAppController(this);

		// Create new repositories, file service, and schedule generator
		userDataRepository = newUserDataRepository;
		courseRepository = newCourseRepository;
		scheduleGeneratorRepository = newScheduleGeneratorRepository;
		fileService = newFileService;
		scheduleGenerator = newScheduleGenerator;

		// Set the sound player and play music
		soundPlayer = newSoundPlayer;

		// Add action events to each JComponent within each view (Add e to comp
		// for)
		// Add e to comp for StartScreenView
		startScreenView.getStartButton().addActionListener(this);

		// Add e to comp for WishlistView
		wishlistView.getBackButton().addActionListener(this);
		wishlistView.getSaveButton().addActionListener(this);
		wishlistView.getContinueButton().addActionListener(this);

		// Add e to comp for CreditsView
		creditsView.getBackButton().addActionListener(this);
		creditsView.getSaveButton().addActionListener(this);
		creditsView.getContinueButton().addActionListener(this);

		// Add e to comp for AboutYouView
		aboutYouView.getBackButton().addActionListener(this);
		aboutYouView.getSaveButton().addActionListener(this);
		aboutYouView.getContinueButton().addActionListener(this);

		// Add e to comp for CourseInfoView
		courseInfoView.getBackButton().addActionListener(this);
		courseInfoView.getCSVButton().addActionListener(this);
		courseInfoView.getSaveButton().addActionListener(this);
		courseInfoView.getNextButton().addActionListener(this);
		courseInfoView.getBuildPlannerButton().addActionListener(this);

		// Set appmodels
		aboutYouModel = newAboutYouModel;
		courseInfoModel = newCourseInfoModel;
		creditsModel = newCreditsModel;
		scheduleGenerator = newScheduleGenerator;
		wishlistModel = newWishlistModel;

		// Update the count label for the course num in that view to match the
		// one in the model
	}

	// Methods
	/**
	 * Invoked when an action occurs
	 * 
	 * @param e the event to be processed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		// If the source is the start button from the STARTSCREENVIEW
		if (e.getSource() == startScreenView.getStartButton())
		{
			startApp();
		}
		// If the source is the back button from the WISHLISTVIEW
		else if (e.getSource() == wishlistView.getBackButton())
		{
			// Show warning message to user that if they go back, their wishlist
			// information will be lost and they will have to re-enter it if
			// they want to save it
			int result = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to go back? All previously entered information will be reset.",
					"Confirm", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.YES_OPTION)
			{
				// Dispose of the wishlist and reset all previous values
				wishlistView.dispose();
				clearToRestart();
			}
		}
		// If the source is the save button from the WISHLISTVIEW
		else if (e.getSource() == wishlistView.getSaveButton())
		{
			saveWishlist();
		}
		// If the source is the continue button from the WISHLISTVIEW
		else if (e.getSource() == wishlistView.getContinueButton())
		{
			continueWithWishlist();
		}
		else if (e.getSource() == creditsView.getBackButton())
		{
			// Dispose of the wishlist and set startscreen visible
			creditsView.dispose();
			wishlistView.setVisible(true);

		}
		else if (e.getSource() == creditsView.getSaveButton())
		{
			saveCredits();
		}
		else if (e.getSource() == creditsView.getContinueButton())
		{
			continueWithCredits();
		}
		else if (e.getSource() == aboutYouView.getBackButton())
		{
			// Dispose of the about you view and set wishlist view visible
			aboutYouView.dispose();
			creditsView.setVisible(true);
		}
		else if (e.getSource() == aboutYouView.getSaveButton())
		{
			saveAboutYou();
		}
		else if (e.getSource() == aboutYouView.getContinueButton())
		{
			continueWithAboutYou();
		}
		else if (e.getSource() == courseInfoView.getBackButton())
		{
			// Show warning message to user that if they go back, their course
			// info
			// information will be lost and they will have to re-enter it if
			// they want to save it
			int result = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to go back? All course information will be lost and you will have to re-enter it.",
					"Confirm", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.YES_OPTION)
			{
				// Clear the course info from respository and model, dispose
				// of the course info view and set about you view visible
				clearCourseInfo();
				courseInfoView.dispose();
				aboutYouView.setVisible(true);
			}
		}
		else if (e.getSource() == courseInfoView.getCSVButton())
		{
			fileService.copyTemplateCSVToTopThreeDestination();
		}
		else if (e.getSource() == courseInfoView.getSaveButton())
		{
			saveCourseInfo();
		}
		else if (e.getSource() == courseInfoView.getNextButton())
		{
			continueWithCourseInfo();
		}
		else if (e.getSource() == courseInfoView.getBuildPlannerButton())
		{
			buildSchedule();
		}

		else
		{
			// Handle other action events for the rest of the views here
		}
	}

	// Start Screen View
	/**
	 * Purpose: To start the app's process by closing out the start window and
	 * opening the
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

	// Wishlist View
	/**
	 * Purpose: To store all collected wishlist input data in one object
	 * (helper class for collectWishlistInput() method)
	 */
	private class WishlistInputData
	{
		Long minBreak;
		Long maxBreak;
		HashMap<DayOfWeek, WeeklyTimeBlock> times;
		HashMap<DayOfWeek, String> formatErrors;
		String campus;
		Path path;

		public WishlistInputData(Long minBreak, Long maxBreak,
				HashMap<DayOfWeek, WeeklyTimeBlock> times,
				HashMap<DayOfWeek, String> formatErrors, String campus,
				Path path)
		{
			this.minBreak = minBreak;
			this.maxBreak = maxBreak;
			this.times = times;
			this.formatErrors = formatErrors;
			this.campus = campus;
			this.path = path;
		}
	}

	/**
	 * Purpose: To collect all wishlist input data from the view, including time
	 * blocks and format errors
	 * (helper method for saveWishlist() and continueWithWishlist() methods)
	 * 
	 * @return A wrapper object containing all necessary wishlist data and
	 *         errors
	 */
	private WishlistInputData collectWishlistInput()
	{
		// Clear any previous error messages in the view
		wishlistView.setMonError("");
		wishlistView.setTuesError("");
		wishlistView.setWedError("");
		wishlistView.setThursError("");
		wishlistView.setFriError("");
		wishlistView.setSatError("");
		wishlistView.setSunError("");

		// Get the new values from the wishlist view
		Long newMinDesiredBreakTime = wishlistView.getMinBreakTime();
		Long newMaxDesiredBreakTime = wishlistView.getMaxBreakTime();

		// Store any time format errors from the view getters
		HashMap<DayOfWeek, String> inputFormatErrors = new HashMap<DayOfWeek, String>();

		// Create individual time blocks for each day of the week and add them
		// to a new hashmap
		HashMap<DayOfWeek, WeeklyTimeBlock> newDesiredStartAndEndTime = new HashMap<DayOfWeek, WeeklyTimeBlock>();

		// Monday time block
		LocalTime monStartTime = null;
		LocalTime monEndTime = null;
		try
		{
			monStartTime = wishlistView.getMonStartOfDay();
			monEndTime = wishlistView.getMonEndOfDay();
		}
		catch (IncorrectTimeFormatException incorrectTimeFormatException)
		{
			inputFormatErrors.put(DayOfWeek.MONDAY,
					incorrectTimeFormatException.getMessage());
			wishlistView.setMonError(incorrectTimeFormatException.getMessage());
		}

		WeeklyTimeBlock monTimeBlock;
		if (monStartTime == null && monEndTime == null)
		{
			monTimeBlock = null;
		}
		else if (inputFormatErrors.containsKey(DayOfWeek.MONDAY))
		{
			monTimeBlock = null;
		}
		else
		{
			List<DayOfWeek> monList = new ArrayList<DayOfWeek>();
			monList.add(DayOfWeek.MONDAY);
			monTimeBlock = new WeeklyTimeBlock(monList, monStartTime,
					monEndTime);
		}

		// Tuesday time block
		LocalTime tuesStartTime = null;
		LocalTime tuesEndTime = null;
		try
		{
			tuesStartTime = wishlistView.getTuesStartOfDay();
			tuesEndTime = wishlistView.getTuesEndOfDay();
		}
		catch (IncorrectTimeFormatException incorrectTimeFormatException)
		{
			inputFormatErrors.put(DayOfWeek.TUESDAY,
					incorrectTimeFormatException.getMessage());
			wishlistView
					.setTuesError(incorrectTimeFormatException.getMessage());
		}

		WeeklyTimeBlock tuesTimeBlock;
		if (tuesStartTime == null && tuesEndTime == null)
		{
			tuesTimeBlock = null;
		}
		else if (inputFormatErrors.containsKey(DayOfWeek.TUESDAY))
		{
			tuesTimeBlock = null;
		}
		else
		{
			List<DayOfWeek> tuesList = new ArrayList<DayOfWeek>();
			tuesList.add(DayOfWeek.TUESDAY);
			tuesTimeBlock = new WeeklyTimeBlock(tuesList, tuesStartTime,
					tuesEndTime);
		}

		// Wednesday time block
		LocalTime wedStartTime = null;
		LocalTime wedEndTime = null;
		try
		{
			wedStartTime = wishlistView.getWedStartOfDay();
			wedEndTime = wishlistView.getWedEndOfDay();
		}
		catch (IncorrectTimeFormatException incorrectTimeFormatException)
		{
			inputFormatErrors.put(DayOfWeek.WEDNESDAY,
					incorrectTimeFormatException.getMessage());
			wishlistView.setWedError(incorrectTimeFormatException.getMessage());
		}

		WeeklyTimeBlock wedTimeBlock;
		if (wedStartTime == null && wedEndTime == null)
		{
			wedTimeBlock = null;
		}
		else if (inputFormatErrors.containsKey(DayOfWeek.WEDNESDAY))
		{
			wedTimeBlock = null;
		}
		else
		{
			List<DayOfWeek> wedList = new ArrayList<DayOfWeek>();
			wedList.add(DayOfWeek.WEDNESDAY);
			wedTimeBlock = new WeeklyTimeBlock(wedList, wedStartTime,
					wedEndTime);
		}

		// Thursday time block
		LocalTime thursStartTime = null;
		LocalTime thursEndTime = null;
		try
		{
			thursStartTime = wishlistView.getThursStartOfDay();
			thursEndTime = wishlistView.getThursEndOfDay();
		}
		catch (IncorrectTimeFormatException incorrectTimeFormatException)
		{
			inputFormatErrors.put(DayOfWeek.THURSDAY,
					incorrectTimeFormatException.getMessage());
			wishlistView
					.setThursError(incorrectTimeFormatException.getMessage());
		}

		WeeklyTimeBlock thursTimeBlock;
		if (thursStartTime == null && thursEndTime == null)
		{
			thursTimeBlock = null;
		}
		else if (inputFormatErrors.containsKey(DayOfWeek.THURSDAY))
		{
			thursTimeBlock = null;
		}
		else
		{
			List<DayOfWeek> thursList = new ArrayList<DayOfWeek>();
			thursList.add(DayOfWeek.THURSDAY);
			thursTimeBlock = new WeeklyTimeBlock(thursList, thursStartTime,
					thursEndTime);
		}

		// Friday time block
		LocalTime friStartTime = null;
		LocalTime friEndTime = null;
		try
		{
			friStartTime = wishlistView.getFriStartOfDay();
			friEndTime = wishlistView.getFriEndOfDay();
		}
		catch (IncorrectTimeFormatException incorrectTimeFormatException)
		{
			inputFormatErrors.put(DayOfWeek.FRIDAY,
					incorrectTimeFormatException.getMessage());
			wishlistView.setFriError(incorrectTimeFormatException.getMessage());
		}

		WeeklyTimeBlock friTimeBlock;
		if (friStartTime == null && friEndTime == null)
		{
			friTimeBlock = null;
		}
		else if (inputFormatErrors.containsKey(DayOfWeek.FRIDAY))
		{
			friTimeBlock = null;
		}
		else
		{
			List<DayOfWeek> friList = new ArrayList<DayOfWeek>();
			friList.add(DayOfWeek.FRIDAY);
			friTimeBlock = new WeeklyTimeBlock(friList, friStartTime,
					friEndTime);
		}

		// Saturday time block
		LocalTime satStartTime = null;
		LocalTime satEndTime = null;
		try
		{
			satStartTime = wishlistView.getSatStartOfDay();
			satEndTime = wishlistView.getSatEndOfDay();
		}
		catch (IncorrectTimeFormatException incorrectTimeFormatException)
		{
			inputFormatErrors.put(DayOfWeek.SATURDAY,
					incorrectTimeFormatException.getMessage());
			wishlistView.setSatError(incorrectTimeFormatException.getMessage());
		}

		WeeklyTimeBlock satTimeBlock;
		if (satStartTime == null && satEndTime == null)
		{
			satTimeBlock = null;
		}
		else if (inputFormatErrors.containsKey(DayOfWeek.SATURDAY))
		{
			satTimeBlock = null;
		}
		else
		{
			List<DayOfWeek> satList = new ArrayList<DayOfWeek>();
			satList.add(DayOfWeek.SATURDAY);
			satTimeBlock = new WeeklyTimeBlock(satList, satStartTime,
					satEndTime);
		}

		// Sunday time block
		LocalTime sunStartTime = null;
		LocalTime sunEndTime = null;
		try
		{
			sunStartTime = wishlistView.getSunStartOfDay();
			sunEndTime = wishlistView.getSunEndOfDay();
		}
		catch (IncorrectTimeFormatException incorrectTimeFormatException)
		{
			inputFormatErrors.put(DayOfWeek.SUNDAY,
					incorrectTimeFormatException.getMessage());
			wishlistView.setSunError(incorrectTimeFormatException.getMessage());
		}

		WeeklyTimeBlock sunTimeBlock;
		if (sunStartTime == null && sunEndTime == null)
		{
			sunTimeBlock = null;
		}
		else if (inputFormatErrors.containsKey(DayOfWeek.SUNDAY))
		{
			sunTimeBlock = null;
		}
		else
		{
			List<DayOfWeek> sunList = new ArrayList<DayOfWeek>();
			sunList.add(DayOfWeek.SUNDAY);
			sunTimeBlock = new WeeklyTimeBlock(sunList, sunStartTime,
					sunEndTime);
		}

		// Add the time blocks to the hashmap
		newDesiredStartAndEndTime.put(DayOfWeek.MONDAY, monTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.TUESDAY, tuesTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.WEDNESDAY, wedTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.THURSDAY, thursTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.FRIDAY, friTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.SATURDAY, satTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.SUNDAY, sunTimeBlock);

		return new WishlistInputData(newMinDesiredBreakTime,
				newMaxDesiredBreakTime, newDesiredStartAndEndTime,
				inputFormatErrors, wishlistView.getCampusLocation(),
				wishlistView.getTopThreeSchedulesDestinationPath());
	}

	/**
	 * Purpose: To save the values in the wishlist view to the wishlist model
	 * and repository
	 * 
	 */
	public void saveWishlist()
	{
		// Collect all wishlist input data from the view
		WishlistInputData data = collectWishlistInput();

		try
		{
			// Purpose: To store general (non-time) errors for popup
			List<String> generalErrors = new ArrayList<String>();

			// Always validate through the model so general errors update
			// correctly in the view
			HashMap<DayOfWeek, String> timeErrors = wishlistModel.saveWishlist(
					data.minBreak, data.maxBreak, data.times, data.campus,
					data.path, generalErrors);

			// Add format errors on top (override if needed)
			timeErrors.putAll(data.formatErrors);

			// Update error labels next to desired start and end time fields
			updateWishlistStartAndEndTimeErrors(timeErrors);

			// Update the wishlist view to reflect any changes to error messages
			updateWishlistView();

			// Show general errors in a popup if any exist
			if (!generalErrors.isEmpty())
			{
				StringBuilder errorMessage = new StringBuilder();

				for (String errMsg : generalErrors)
				{
					errorMessage.append(errMsg).append("\n");
				}

				JOptionPane.showMessageDialog(null, errorMessage.toString(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}

			// If there are no errors, show success message and save
			if (timeErrors.isEmpty() && generalErrors.isEmpty())
			{
				userDataRepository.saveWishlist(data.minBreak, data.maxBreak,
						data.times, data.campus, data.path);

				JOptionPane.showMessageDialog(null,
						"\"Wishlist\" saved successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch (Exception e)
		{
			// Handle unexpected exceptions by showing an error message
			JOptionPane.showMessageDialog(null,
					"An unexpected error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Purpose: To continue from the wishlist view to the about you view.
	 * If the user did not change any previously-saved wishlist information, do
	 * not re-save it.
	 * If the user changed anything, save the updated values to the wishlist
	 * model and repository.
	 * If there are no errors and no exceptions, dispose of WishlistView and set
	 * CreditsView visible.
	 */
	private void continueWithWishlist()
	{
		// Purpose: To collect all wishlist input data from the view
		WishlistInputData data = collectWishlistInput();

		// Determine if anything was edited compared to what was previously
		// saved
		boolean wasEdited = false;

		try
		{
			// Compare against previously saved values (repository is the source
			// of truth)
			Long oldMinDesiredBreakTime = userDataRepository
					.getMinDesiredBreakTime();
			Long oldMaxDesiredBreakTime = userDataRepository
					.getMaxDesiredBreakTime();
			HashMap<DayOfWeek, WeeklyTimeBlock> oldDesiredStartAndEndTime = userDataRepository
					.getDesiredStartAndEndTime();
			String oldDesiredCampusLocation = userDataRepository
					.getDesiredCampusLocation();
			Path oldTopThreeSchedulesDestinationPath = userDataRepository
					.getTopThreeSchedulesDestinationPath();

			if ((oldMinDesiredBreakTime == null && data.minBreak != null)
					|| (oldMinDesiredBreakTime != null
							&& !oldMinDesiredBreakTime.equals(data.minBreak)))
			{
				wasEdited = true;
			}

			if ((oldMaxDesiredBreakTime == null && data.maxBreak != null)
					|| (oldMaxDesiredBreakTime != null
							&& !oldMaxDesiredBreakTime.equals(data.maxBreak)))
			{
				wasEdited = true;
			}

			if ((oldDesiredCampusLocation == null && data.campus != null)
					|| (oldDesiredCampusLocation != null
							&& !oldDesiredCampusLocation.equals(data.campus)))
			{
				wasEdited = true;
			}

			if ((oldTopThreeSchedulesDestinationPath == null
					&& data.path != null)
					|| (oldTopThreeSchedulesDestinationPath != null
							&& !oldTopThreeSchedulesDestinationPath
									.equals(data.path)))
			{
				wasEdited = true;
			}

			// Compare time blocks day-by-day
			if (oldDesiredStartAndEndTime == null)
			{
				wasEdited = true;
			}
			else
			{
				for (DayOfWeek day : DayOfWeek.values())
				{
					WeeklyTimeBlock oldBlock = oldDesiredStartAndEndTime
							.get(day);
					WeeklyTimeBlock newBlock = data.times.get(day);

					LocalTime oldStart = (oldBlock == null) ? null
							: oldBlock.getClassStartTime();
					LocalTime newStart = (newBlock == null) ? null
							: newBlock.getClassStartTime();

					LocalTime oldEnd = (oldBlock == null) ? null
							: oldBlock.getClassEndTime();
					LocalTime newEnd = (newBlock == null) ? null
							: newBlock.getClassEndTime();

					if ((oldStart == null && newStart != null)
							|| (oldStart != null && !oldStart.equals(newStart)))
					{
						wasEdited = true;
						break;
					}

					if ((oldEnd == null && newEnd != null)
							|| (oldEnd != null && !oldEnd.equals(newEnd)))
					{
						wasEdited = true;
						break;
					}
				}
			}
		}
		catch (Exception e)
		{
			// If repository getters fail, assume data was edited
			wasEdited = true;
		}

		try
		{
			// Store general (non-time) errors for popup
			List<String> generalErrors = new ArrayList<String>();

			// Validate through model
			HashMap<DayOfWeek, String> timeErrors = wishlistModel.saveWishlist(
					data.minBreak, data.maxBreak, data.times, data.campus,
					data.path, generalErrors);

			// Update error labels
			updateWishlistStartAndEndTimeErrors(timeErrors);
			updateWishlistView();

			// Show general errors in popup
			if (!generalErrors.isEmpty())
			{
				StringBuilder errorMessage = new StringBuilder();

				for (String errMsg : generalErrors)
				{
					errorMessage.append(errMsg).append("\n");
				}

				JOptionPane.showMessageDialog(null, errorMessage.toString(),
						"Error", JOptionPane.ERROR_MESSAGE);

				return;
			}

			// Proceed only if there are NO errors
			if (timeErrors.isEmpty() && generalErrors.isEmpty())
			{
				if (wasEdited)
				{
					userDataRepository.saveWishlist(data.minBreak,
							data.maxBreak, data.times, data.campus, data.path);
				}
				fileService.setTopThreeSchedulesDestinationPath(data.path);
				wishlistView.dispose();
				creditsView.setVisible(true);
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"An unexpected error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Purpose: To clear out all values saved in the wishlist model and user
	 * repository
	 * 
	 */
	private void clearWishlist()
	{
		wishlistModel.clear();
		wishlistView.resetFields();
		userDataRepository.clearWishlist();
	}

	/**
	 * Purpose: To update error labels next to desired start and end time fields
	 * 
	 * @param timeErrors A hashmap of error messages for the desired start and
	 *                   end time inputs, keyed by day of the week
	 */
	public void updateWishlistStartAndEndTimeErrors(
			HashMap<DayOfWeek, String> timeErrors)
	{
		wishlistView.setMonError(timeErrors.getOrDefault(DayOfWeek.MONDAY, ""));
		wishlistView
				.setTuesError(timeErrors.getOrDefault(DayOfWeek.TUESDAY, ""));
		wishlistView
				.setWedError(timeErrors.getOrDefault(DayOfWeek.WEDNESDAY, ""));
		wishlistView
				.setThursError(timeErrors.getOrDefault(DayOfWeek.THURSDAY, ""));
		wishlistView.setFriError(timeErrors.getOrDefault(DayOfWeek.FRIDAY, ""));
		wishlistView
				.setSatError(timeErrors.getOrDefault(DayOfWeek.SATURDAY, ""));
		wishlistView.setSunError(timeErrors.getOrDefault(DayOfWeek.SUNDAY, ""));
	}

	/**
	 * Purpose: To update the wishlistView UI
	 * 
	 */
	private void updateWishlistView()
	{
		wishlistView.revalidate();
		wishlistView.repaint();
	}

	// Credits View
	/**
	 * Purpose: To save the values in the credits view to the credits model and
	 * repository
	 * 
	 */
	public void saveCredits()
	{
		// Clear any previous error messages in the view
		creditsView.setMinCreditsError("");
		creditsView.setMaxCreditsError("");

		int minCreditsInput = creditsView.getMinCreditsInput();
		int maxCreditsInput = creditsView.getMaxCreditsInput();
		try
		{
			HashMap<String, String> creditsErrors = creditsModel
					.saveCredits(minCreditsInput, maxCreditsInput);
			updateCreditsErrors(creditsErrors);
			updateCreditsView();

			if (creditsErrors.isEmpty())
			{
				// Save to repository
				userDataRepository.saveCredits(minCreditsInput,
						maxCreditsInput);
				JOptionPane.showMessageDialog(null,
						"\"Credits\" saved successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"An unexpected error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Purpose: To save new/changed values in the credits view to the credits
	 * model and repository
	 * and preserve any stored values that have not changed
	 * 
	 */
	public void continueWithCredits()
	{
		// Clear any previous error messages in the view
		creditsView.setMinCreditsError("");
		creditsView.setMaxCreditsError("");

		int minCreditsInput = creditsView.getMinCreditsInput();
		int maxCreditsInput = creditsView.getMaxCreditsInput();

		try
		{
			HashMap<String, String> creditsErrors = creditsModel
					.saveCredits(minCreditsInput, maxCreditsInput);
			updateCreditsErrors(creditsErrors);
			updateCreditsView();

			if (creditsErrors.isEmpty())
			{
				int storedMinCredits = userDataRepository
						.getMinRequiredCredits();
				int storedMaxCredits = userDataRepository
						.getMaxRequiredCredits();

				boolean minChanged = (minCreditsInput != storedMinCredits);
				boolean maxChanged = (maxCreditsInput != storedMaxCredits);

				if (minChanged || maxChanged)
				{
					int minToSave = minChanged ? minCreditsInput
							: storedMinCredits;
					int maxToSave = maxChanged ? maxCreditsInput
							: storedMaxCredits;

					// Save to respository
					userDataRepository.saveCredits(minToSave, maxToSave);

					// Naviagation change
					creditsView.dispose();
					aboutYouView.setVisible(true);
				}
				else
				{
					// Navigation change
					creditsView.dispose();
					aboutYouView.setVisible(true);
				}
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"An unexpected error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Purpose: To clear out all values saved in the credits model and user
	 * repository
	 */
	private void clearCredits()
	{
		creditsModel.clear();
		creditsView.resetFields();
		userDataRepository.clearCredits();

	}

	/**
	 * Purpose: To update error labels for the credits view
	 * 
	 * @param creditsErrors A hashmap of error messages for the minimum and
	 *                      maximum required credits inputs
	 */
	public void updateCreditsErrors(HashMap<String, String> creditsErrors)
	{
		creditsView.setMinCreditsError(
				creditsErrors.getOrDefault("minRequiredCredits", ""));
		creditsView.setMaxCreditsError(
				creditsErrors.getOrDefault("maxRequiredCredits", ""));
	}

	/**
	 * Purpose: To update the creditsView UI
	 *
	 */
	private void updateCreditsView()
	{
		creditsView.revalidate();
		creditsView.repaint();
	}

	// About You View
	/**
	 * Purpose: To save the values in the about you view to the about you model
	 * and repository
	 * 
	 */
	public void saveAboutYou()
	{
		// Clear any previous error messages in the view
		aboutYouView.setMajorError("");
		aboutYouView.setNumCoursesError("");

		String majorInput = aboutYouView.getMajorInput();
		List<String> struggleCoursesInput = aboutYouView
				.getStruggleCoursesInput();
		int numCoursesInput = aboutYouView.getNumCoursesInput();

		try
		{
			HashMap<String, String> aboutYouErrors = aboutYouModel.saveAboutYou(
					majorInput, numCoursesInput, struggleCoursesInput);
			updateAboutYouErrors(aboutYouErrors);

			if (aboutYouErrors.isEmpty())
			{
				updateAboutYouView();

				// Save to repository
				userDataRepository.saveAboutYou(majorInput, numCoursesInput,
						struggleCoursesInput);
				JOptionPane.showMessageDialog(null,
						"\"About You\" saved successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"An unexpected error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Purpose: To continue from the about you view to the course info view
	 * 
	 */
	public void continueWithAboutYou()
	{
		// Clear any previous error messages in the view
		aboutYouView.setMajorError("");
		aboutYouView.setNumCoursesError("");

		String majorInput = aboutYouView.getMajorInput();
		List<String> struggleCoursesInput = aboutYouView
				.getStruggleCoursesInput();
		int numCoursesInput = aboutYouView.getNumCoursesInput();

		try
		{
			HashMap<String, String> aboutYouErrors = aboutYouModel.saveAboutYou(
					majorInput, numCoursesInput, struggleCoursesInput);
			updateAboutYouErrors(aboutYouErrors);

			if (aboutYouErrors.isEmpty())
			{
				updateAboutYouView();

				String storedMajor = userDataRepository.getMajor();
				int storedNumCourses = userDataRepository.getNumCourses();
				List<String> storedStruggleCourses = userDataRepository
						.getStruggleCourses();

				boolean majorChanged = (majorInput != null
						&& !majorInput.equals(storedMajor))
						|| (majorInput == null && storedMajor != null);
				boolean numCoursesChanged = (numCoursesInput != storedNumCourses);
				boolean struggleCoursesChanged = (struggleCoursesInput != null
						&& !struggleCoursesInput.equals(storedStruggleCourses))
						|| (struggleCoursesInput == null
								&& storedStruggleCourses != null);

				if (majorChanged || numCoursesChanged || struggleCoursesChanged)
				{
					String majorToSave = majorChanged ? majorInput
							: storedMajor;
					int numCoursesToSave = numCoursesChanged ? numCoursesInput
							: storedNumCourses;
					List<String> struggleCoursesToSave = struggleCoursesChanged
							? struggleCoursesInput
							: storedStruggleCourses;

					// Save to respository
					userDataRepository.saveAboutYou(majorToSave,
							numCoursesToSave, struggleCoursesToSave);

					// Update course info model with number of courses so that
					// it can validate the number of courses the student will
					// input in the course info view
					courseInfoModel.setTotalCourses(numCoursesToSave);
					courseInfoView.setTotalCourses(numCoursesToSave);
					int currentCourseNum = courseInfoModel.getCurrentCourse();

					// Update view to reflect num course
					courseInfoView.setCourseNumLabel(currentCourseNum);

					// Naviagation change
					aboutYouView.dispose();
					courseInfoView.setVisible(true);
				}
				else
				{
					// Update course info model with number of courses so that
					// it can validate the number of courses the student will
					// input in the course info view
					int newNumCourses = userDataRepository
							.getNumOfCoursesStudentWillInput();
					courseInfoModel.setTotalCourses(newNumCourses);
					int currentCourseNum = courseInfoModel.getCurrentCourse();

					// Update view to reflect num course
					courseInfoView.setTotalCourses(newNumCourses);
					courseInfoView.setCourseNumLabel(currentCourseNum);

					// Navigation change
					aboutYouView.dispose();
					courseInfoView.setVisible(true);
				}
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"An unexpected error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Purpose: To clear out all values saved in the about you model and user
	 * repository
	 * 
	 */
	public void clearAboutYou()
	{
		aboutYouModel.clear();
		aboutYouView.resetFields();
		userDataRepository.clearAboutYou();
	}

	/**
	 * Purpose: To update error labels for the about you view
	 * 
	 * @param aboutYouErrors A hashmap of error messages for the about you view
	 *                       inputs
	 */
	public void updateAboutYouErrors(HashMap<String, String> aboutYouErrors)
	{
		aboutYouView.setMajorError(
				aboutYouErrors.getOrDefault("studentsMajorDistinction", ""));
		aboutYouView.setNumCoursesError(aboutYouErrors
				.getOrDefault("numOfCoursesStudentWillInput", ""));
	}

	/**
	 * Purpose: To update the about you view UI
	 * 
	 */
	private void updateAboutYouView()
	{
		aboutYouView.revalidate();
		aboutYouView.repaint();
	}

	// Course Info View
	/**
	 * Purpose: To save the values in the course info view to the course info
	 * model and repository
	 * 
	 */
	public void saveCourseInfo()
	{
		// Clear any previous error messages in the view (same pattern as
		// saveAboutYou)
		courseInfoView.setCourseNameError("");
		courseInfoView.setStudentRequiredCourseError("");
		courseInfoView.setCourseTypeError("");

		// Collect inputs from the view
		String courseNameInput = courseInfoView.getCourseName();
		if (courseNameInput != null && courseNameInput.trim().isEmpty())
		{
			courseNameInput = null;
		}

		Boolean requiredInput = courseInfoView.getStudentRequiredCourse();
		String courseTypeInput = courseInfoView.getCourseType();
		Path retrievalPathInput = courseInfoView
				.getAvailableClassesRetrievalPath();

		// Pull total courses from repository (adjust getter name if yours
		// differs)
		int totalCoursesInput = userDataRepository
				.getNumOfCoursesStudentWillInput();

		try
		{
			// Collect general (popup) errors (model uses this for path errors)
			List<String> generalErrors = new ArrayList<String>();

			// Validate + save into model (but not repository yet)
			HashMap<String, String> courseInfoErrors = courseInfoModel
					.saveCourseInfo(totalCoursesInput, courseNameInput,
							requiredInput, courseTypeInput, retrievalPathInput,
							generalErrors);

			// Update field-level errors in the view
			updateCourseInfoErrors(courseInfoErrors);

			// Show general errors in a popup if any exist (same idea as
			// wishlist template)
			if (!generalErrors.isEmpty())
			{
				StringBuilder errorMessage = new StringBuilder();
				for (String errMsg : generalErrors)
				{
					errorMessage.append(errMsg).append("\n");
				}

				JOptionPane.showMessageDialog(null, errorMessage.toString(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}

			// If there are no errors, update view + save to repository +
			// success popup
			if (courseInfoErrors.isEmpty() && generalErrors.isEmpty())
			{
				updateCourseInfoView();

				// Save to repository
				userDataRepository.saveCourseInfo(courseNameInput,
						requiredInput, retrievalPathInput, courseTypeInput);

				JOptionPane.showMessageDialog(null,
						"\"Course Info\" saved successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"An unexpected error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Purpose: To save new/changed values in the course info view to the course
	 * info model and repository
	 * and preserve any stored values that have not changed, then continue to
	 * next course until the last one
	 * 
	 */
	public void continueWithCourseInfo()
	{
		// Clear any previous error messages in the view
		courseInfoView.setCourseNameError("");
		courseInfoView.setStudentRequiredCourseError("");
		courseInfoView.setCourseTypeError("");

		// Collect inputs from the view
		String courseNameInput = courseInfoView.getCourseName();
		if (courseNameInput != null && courseNameInput.trim().isEmpty())
		{
			courseNameInput = null;
		}

		Boolean requiredInput = courseInfoView.getStudentRequiredCourse();
		String courseTypeInput = courseInfoView.getCourseType();
		Path retrievalPathInput = courseInfoView
				.getAvailableClassesRetrievalPath();

		// Pull stored values from repository
		String storedCourseName = userDataRepository.getCourseName();
		Boolean storedRequired = userDataRepository.isStudentRequiredCourse();
		String storedCourseType = userDataRepository.getCourseType();
		Path storedPath = userDataRepository.getAvailableClassesRetrievalPath();

		try
		{
			// Collect general (popup) errors
			List<String> generalErrors = new ArrayList<String>();

			// Validate + save into model (not repository yet)
			HashMap<String, String> courseInfoErrors = courseInfoModel
					.saveCourseInfo(
							userDataRepository
									.getNumOfCoursesStudentWillInput(),
							courseNameInput, requiredInput, courseTypeInput,
							retrievalPathInput, generalErrors);

			// Update field-level errors in the view
			updateCourseInfoErrors(courseInfoErrors);

			// Show general errors in popup
			if (!generalErrors.isEmpty())
			{
				StringBuilder errorMessage = new StringBuilder();
				for (String errMsg : generalErrors)
				{
					errorMessage.append(errMsg).append("\n");
				}

				JOptionPane.showMessageDialog(null, errorMessage.toString(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}

			// If validation passes
			if (courseInfoErrors.isEmpty() && generalErrors.isEmpty())
			{
				updateCourseInfoView();

				// Determine what changed
				boolean courseNameChanged = (courseNameInput != null
						&& !courseNameInput.equals(storedCourseName))
						|| (courseNameInput == null
								&& storedCourseName != null);

				boolean requiredChanged = (requiredInput != null
						&& !requiredInput.equals(storedRequired))
						|| (requiredInput == null && storedRequired != null);

				boolean courseTypeChanged = (courseTypeInput != null
						&& !courseTypeInput.equals(storedCourseType))
						|| (courseTypeInput == null
								&& storedCourseType != null);

				boolean pathChanged = (retrievalPathInput != null
						&& !retrievalPathInput.equals(storedPath))
						|| (retrievalPathInput == null && storedPath != null);

				if (courseNameChanged || requiredChanged || courseTypeChanged
						|| pathChanged)
				{
					String courseNameToSave = courseNameChanged
							? courseNameInput
							: storedCourseName;
					Boolean requiredToSave = requiredChanged ? requiredInput
							: storedRequired;
					String courseTypeToSave = courseTypeChanged
							? courseTypeInput
							: storedCourseType;
					Path pathToSave = pathChanged ? retrievalPathInput
							: storedPath;

					// Save to repository
					userDataRepository.saveCourseInfo(courseNameToSave,
							requiredToSave, pathToSave, courseTypeToSave);
				}

				// File service reads class info and creates partial course
				// objects
				Path currentAvailClasses = userDataRepository
						.getAvailableClassesRetrievalPath();
				fileService.setCourseRepository(courseRepository);
				fileService.readAvailableClassesFile(currentAvailClasses);

				// Build final course objects based on partial course info and
				// user input and save to schedule generator

				// Store each course type as a difficulty and coursetypedefined
				// (to make it STEM, Liberal Arts, Professional, or Creative
				// Arts And Electives)
				double difficultyScore = 0.0;
				String courseTypeDefined = null;

				if (courseTypeInput.equals("Biology"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Chemistry"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Physics")
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Environmental Science"))
				{
					difficultyScore = 2.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Other Science"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Computer Science"))
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Information Technology"))
				{
					difficultyScore = 2.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Computer Information Systems"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput
						.equals("Cybersecurity and Information Assurance"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Data Science and Analytics"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput
						.equals("Software Engineering and Development"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Computer Engineering"))
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Other Tech"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Civil Engineering"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Mechanical Engineering"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Electrical Engineering"))
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Chemical Engineering"))
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Other Engineering"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Core Math"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Pure Math"))
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Applied Math"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("Other Math"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput.equals("History"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput.equals("Literature"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput.equals("Philosophy"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput.equals("Psychology"))
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput.equals("Sociology"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput.equals("Political Science"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput.equals("Justice Administration"))
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput.equals("Other Liberal Arts"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput.equals("Business"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput.equals("Healthcare"))
				{
					difficultyScore = 5.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput.equals("Law"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput.equals("Education"))
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput.equals("Vocational"))
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput.equals("Agriculture"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput.equals("Other Professional"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput.equals("Visual Arts and Design"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else if (courseTypeInput.equals("Foreign Language"))
				{
					difficultyScore = 4.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else if (courseTypeInput.equals("Culinary Arts"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else if (courseTypeInput.equals("Communication"))
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else if (courseTypeInput.equals("Performing Arts"))
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else if (courseTypeInput
						.equals("Physical Education and Nutrition"))
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Creative Arts and Electives";
				}

				List<Course> finalClassesBuiltForCourse = courseRepository
						.buildFinalCourses(courseNameInput, courseTypeDefined,
								requiredInput, difficultyScore);
				scheduleGenerator.addCompletedCoursesToCoursePool(
						finalClassesBuiltForCourse);

				// Move to next course OR next view
				int currentCourse = courseInfoModel.updateCurrentCourse();
				int totalCourses = userDataRepository
						.getNumOfCoursesStudentWillInput();

				if (currentCourse < totalCourses)
				{
					// Update the view for the next course
					courseInfoView.setCourseNumLabel(currentCourse);
					clearCourseInfo();
					updateCourseInfoView();
				}
				else
				{
					// Update view
					// Update the view for the next course
					courseInfoView.setCourseNumLabel(currentCourse);
					clearCourseInfo();
					courseInfoView.getNextButton().setVisible(false);
					courseInfoView.getBuildPlannerButton().setVisible(true);
					updateCourseInfoView();
				}
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"An unexpected error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Purpose: To build schedules and return top three
	 * 
	 */
	private void buildSchedule()
	{
		// Clear any previous error messages in the view
		courseInfoView.setCourseNameError("");
		courseInfoView.setStudentRequiredCourseError("");
		courseInfoView.setCourseTypeError("");

		// Collect inputs from the view
		String courseNameInput = courseInfoView.getCourseName();
		if (courseNameInput != null && courseNameInput.trim().isEmpty())
		{
			courseNameInput = null;
		}

		Boolean requiredInput = courseInfoView.getStudentRequiredCourse();
		String courseTypeInput = courseInfoView.getCourseType();
		Path retrievalPathInput = courseInfoView
				.getAvailableClassesRetrievalPath();

		// Pull stored values from repository
		String storedCourseName = userDataRepository.getCourseName();
		Boolean storedRequired = userDataRepository.isStudentRequiredCourse();
		String storedCourseType = userDataRepository.getCourseType();
		Path storedPath = userDataRepository.getAvailableClassesRetrievalPath();

		try
		{
			// Collect general (popup) errors
			List<String> generalErrors = new ArrayList<String>();

			// Validate + save into model (not repository yet)
			HashMap<String, String> courseInfoErrors = courseInfoModel
					.saveCourseInfo(
							userDataRepository
									.getNumOfCoursesStudentWillInput(),
							courseNameInput, requiredInput, courseTypeInput,
							retrievalPathInput, generalErrors);

			// Update field-level errors in the view
			updateCourseInfoErrors(courseInfoErrors);

			// Show general errors in popup
			if (!generalErrors.isEmpty())
			{
				StringBuilder errorMessage = new StringBuilder();
				for (String errMsg : generalErrors)
				{
					errorMessage.append(errMsg).append("\n");
				}

				JOptionPane.showMessageDialog(null, errorMessage.toString(),
						"Error", JOptionPane.ERROR_MESSAGE);
			}

			// If validation passes
			if (courseInfoErrors.isEmpty() && generalErrors.isEmpty())
			{
				updateCourseInfoView();

				// Determine what changed
				boolean courseNameChanged = (courseNameInput != null
						&& !courseNameInput.equals(storedCourseName))
						|| (courseNameInput == null
								&& storedCourseName != null);

				boolean requiredChanged = (requiredInput != null
						&& !requiredInput.equals(storedRequired))
						|| (requiredInput == null && storedRequired != null);

				boolean courseTypeChanged = (courseTypeInput != null
						&& !courseTypeInput.equals(storedCourseType))
						|| (courseTypeInput == null
								&& storedCourseType != null);

				boolean pathChanged = (retrievalPathInput != null
						&& !retrievalPathInput.equals(storedPath))
						|| (retrievalPathInput == null && storedPath != null);

				if (courseNameChanged || requiredChanged || courseTypeChanged
						|| pathChanged)
				{
					String courseNameToSave = courseNameChanged
							? courseNameInput
							: storedCourseName;
					Boolean requiredToSave = requiredChanged ? requiredInput
							: storedRequired;
					String courseTypeToSave = courseTypeChanged
							? courseTypeInput
							: storedCourseType;
					Path pathToSave = pathChanged ? retrievalPathInput
							: storedPath;

					// Save to repository
					userDataRepository.saveCourseInfo(courseNameToSave,
							requiredToSave, pathToSave, courseTypeToSave);
				}

				// File service reads class info and creates partial course
				// objects
				Path currentAvailClasses = userDataRepository
						.getAvailableClassesRetrievalPath();
				fileService.setCourseRepository(courseRepository);
				fileService.readAvailableClassesFile(currentAvailClasses);

				// Build final course objects based on partial course info and
				// user input and save to schedule generator

				// Store each course type as a difficulty and coursetypedefined
				// (to make it STEM, Liberal Arts, Professional, or Creative
				// Arts And Electives)
				double difficultyScore = 0.0;
				String courseTypeDefined = null;

				if (courseTypeInput == "Biology")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Chemistry")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Physics")
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Environmental Science")
				{
					difficultyScore = 2.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Other Science")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Computer Science")
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Information Technology")
				{
					difficultyScore = 2.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Computer Infomration Systems")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Cybersecurity and Information Assurance")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Data Science and Analytics")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Software Engineering and Development")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Computer Engineering")
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Other Tech")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Civil Engineering")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Mechanical Engineering")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Electrical Engineering")
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Chemical Engineering")
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Other Engineering")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Core Math")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Pure Math")
				{
					difficultyScore = 5.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Applied Math")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "Other Math")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "STEM";
				}
				else if (courseTypeInput == "History")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput == "Literature")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput == "Philosophy")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput == "Psychology")
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput == "Sociology")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput == "Political Science")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput == "Justice Administration")
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput == "Other Liberal Arts")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Liberal Arts";
				}
				else if (courseTypeInput == "Business")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput == "Healthcare")
				{
					difficultyScore = 5.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput == "Law")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput == "Education")
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput == "Vocational")
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput == "Agriculture")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput == "Other Professional")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Professional";
				}
				else if (courseTypeInput == "Visual Arts and Design")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else if (courseTypeInput == "Foreign Language")
				{
					difficultyScore = 4.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else if (courseTypeInput == "Culinary Arts")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else if (courseTypeInput == "Communication")
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else if (courseTypeInput == "Performing Arts")
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else if (courseTypeInput == "Physical Education and Nutrition")
				{
					difficultyScore = 2.0;
					courseTypeDefined = "Creative Arts and Electives";
				}
				else
				{
					difficultyScore = 3.0;
					courseTypeDefined = "Creative Arts and Electives";
				}

				List<Course> finalClassesBuiltForCourse = courseRepository
						.buildFinalCourses(courseNameInput, courseTypeDefined,
								requiredInput, difficultyScore);
				scheduleGenerator.addCompletedCoursesToCoursePool(
						finalClassesBuiltForCourse);

				// Add schedule generator repository to file service and setup
				// schedule generator
				fileService.setScheduleGeneratorRepository(
						scheduleGeneratorRepository);
				scheduleGenerator.setScheduleGeneratorReposAndFileService(
						userDataRepository, scheduleGeneratorRepository);

				// Close the courseInfoView and open the loading screen view
				courseInfoView.setVisible(false);
				loadingScreenView.setVisible(true);

				// Start playing loading music
				soundPlayer.playLoopingPlaylist();

				// New thread to stop music/loading screen code from playout of
				// sync with schedule generation
				new Thread(() -> {
					// Build schedules, note can go up to at least 100k but
					// takes longer. 25k is good for regular use (under 10
					// seconds)
					scheduleGenerator.generateTopThreeSchedules(25000);

					// Set top three schedules BEFORE switching back to UI
					// thread
					fileService.setTopThreeSchedules();

					// Switch back to UI thread for UI updates
					SwingUtilities.invokeLater(() -> {
						// Stop sound and close loading screen
						soundPlayer.stop();
						loadingScreenView.dispose();

						fileService.topThreeToCSV();

						// Reset for new schedule
						resetForNewSchedule();

						// Show success popup
						JOptionPane.showMessageDialog(null,
								"Schedules built successfully! Check your files for the top three schedules generated.",
								"Success", JOptionPane.INFORMATION_MESSAGE);
					});

				}).start();
			}
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,
					"An unexpected error occurred: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Purpose: To play music during schedule generation
	 * 
	 * @param filePath The file path of the sound to play
	 * @return The Clip object representing the playing sound, which can be
	 *         stopped later
	 */
	public Clip playLoopingSound(String filePath)
	{
		try
		{
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(
					getClass().getResource("sounds/Loading Music.wav"));

			Clip clip = AudioSystem.getClip();
			clip.open(audioStream);

			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();

			return clip;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Purpose: To clear out all values saved in the course info model and user
	 * repository
	 * 
	 */
	public void clearCourseInfo()
	{
		courseInfoModel.clear();
		courseInfoView.resetFields();
		userDataRepository.clearCourseInfo();
		courseRepository.clearCourses();
	}

	/**
	 * Purpose: To update error labels for the course info view
	 * 
	 * @param courseInfoErrors A hashmap of error messages for the course info
	 *                         view inputs
	 */
	public void updateCourseInfoErrors(HashMap<String, String> courseInfoErrors)
	{
		courseInfoView.setCourseNameError(
				courseInfoErrors.getOrDefault("courseName", ""));
		courseInfoView.setStudentRequiredCourseError(
				courseInfoErrors.getOrDefault("studentRequiredCourse", ""));
		courseInfoView.setCourseTypeError(
				courseInfoErrors.getOrDefault("courseType", ""));
	}

	/**
	 * Purpose: To update the course info view UI
	 * 
	 */
	private void updateCourseInfoView()
	{
		courseInfoView.revalidate();
		courseInfoView.repaint();
	}

	/**
	 * Purpose: To clear out all values when user returns to start screen from
	 * back button
	 * 
	 */
	public void clearToRestart()
	{
		clearCourseInfo();
		clearAboutYou();
		clearCredits();
		clearWishlist();
		startScreenView.setVisible(true);
	}

	/**
	 * Purpose: To reset information when the user returns to the start screen
	 * 
	 */
	public void resetForNewSchedule()
	{
		clearCourseInfo();
		courseInfoView.setCourseNumLabel(1);
		courseInfoView.getNextButton().setVisible(true);
		courseInfoView.getBuildPlannerButton().setVisible(false);
		updateCourseInfoView();
		clearAboutYou();
		clearCredits();
		clearWishlist();
		startScreenView.setVisible(true);
	}
}
