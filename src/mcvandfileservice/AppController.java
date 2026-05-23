package mcvandfileservice;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

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
	
	// IV Repositories
	private UserDataRepository userDataRepository; // An app controller has-a user data repository
	private CourseRepository courseRepository; // An app controller has-a course data repository
	
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
		
		// Create new repositories
		userDataRepository = new UserDataRepository();
		courseRepository = new CourseRepository(userDataRepository);
		
		// Add action events to each JComponent within each view (Add e to comp for)
		// Add e to comp for StartScreenView
		startScreenView.getStartButton().addActionListener(this);
		
		
		// Add e to comp for WishlistView
		wishlistView.getBackButton().addActionListener(this);
		wishlistView.getSaveButton().addActionListener(this);
		wishlistView.getContinueButton().addActionListener(this);
	
		// Add e to comp for AboutYouView

		
		// Add e to comp for CourseInfoView
		
		// Add e to comp for CreditsView
		
		// Add e to comp for ScheduleGeneratorView
		
		
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
		// If the source is the start button from the STARTSCREENVIEW
		if(e.getSource() == startScreenView.getStartButton())
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
					"Are you sure you want to go back? Your wishlist information will be lost and you will have to re-enter it if you want to save it.",
					"Confirm", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (result == JOptionPane.YES_OPTION)
			{
				// Clear the wishlist info from respository and model, dispose
				// of the wishlist and set startscreen visible
				clearWishlist();
				wishlistView.dispose();
				startScreenView.setVisible(true);
			}
		}
		// If the source is the save button from the WISHLISTVIEW
		else if(e.getSource() == wishlistView.getSaveButton())
		{
			saveWishlist();
		}
		// If the source is the continue button from the WISHLISTVIEW
		else if(e.getSource() == wishlistView.getContinueButton())
		{
			continueWithWishlist();
		}
		else
		{
			// Handle other action events for other views here as you implement them
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
	
	/**
	 * Purpose: To save the values in the wishlist view to the wishlist model and repository
	 */
	public void saveWishlist()
	{		
		// Get the new values from the wishlist view
		Long newMinDesiredBreakTime = wishlistView.getMinBreakTime();
		Long newMaxDesiredBreakTime = wishlistView.getMaxBreakTime();
		
		// Create individual time blocks for each day of the week and add them to a new hashmap
		HashMap<DayOfWeek, WeeklyTimeBlock> newDesiredStartAndEndTime = new HashMap<DayOfWeek, WeeklyTimeBlock>();		
		// Monday time block
		LocalTime monStartTime = wishlistView.getMonStartOfDay();
		LocalTime monEndTime = wishlistView.getMonEndOfDay();
		List<DayOfWeek> monList = new ArrayList<DayOfWeek>();
		monList.add(DayOfWeek.MONDAY);
		WeeklyTimeBlock monTimeBlock;
		if (monStartTime != null || monEndTime != null)
		{
		    monTimeBlock = new WeeklyTimeBlock(monList, monStartTime, monEndTime);
		}
		else
		{
		    monTimeBlock = null;
		}

		// Tuesday time block
		LocalTime tuesStartTime = wishlistView.getTuesStartOfDay();
		LocalTime tuesEndTime = wishlistView.getTuesEndOfDay();
		List<DayOfWeek> tuesList = new ArrayList<DayOfWeek>();
		tuesList.add(DayOfWeek.TUESDAY);
		WeeklyTimeBlock tuesTimeBlock;
		if (tuesStartTime != null || tuesEndTime != null)
		{
		    tuesTimeBlock = new WeeklyTimeBlock(tuesList, tuesStartTime, tuesEndTime);
		}
		else
		{
		    tuesTimeBlock = null;
		}

		// Wednesday time block
		LocalTime wedStartTime = wishlistView.getWedStartOfDay();
		LocalTime wedEndTime = wishlistView.getWedEndOfDay();
		List<DayOfWeek> wedList = new ArrayList<DayOfWeek>();
		wedList.add(DayOfWeek.WEDNESDAY);
		WeeklyTimeBlock wedTimeBlock;
		if (wedStartTime != null || wedEndTime != null)
		{
		    wedTimeBlock = new WeeklyTimeBlock(wedList, wedStartTime, wedEndTime);
		}
		else
		{
		    wedTimeBlock = null;
		}

		// Thursday time block
		LocalTime thursStartTime = wishlistView.getThursStartOfDay();
		LocalTime thursEndTime = wishlistView.getThursEndOfDay();
		List<DayOfWeek> thursList = new ArrayList<DayOfWeek>();
		thursList.add(DayOfWeek.THURSDAY);
		WeeklyTimeBlock thursTimeBlock;
		if (thursStartTime != null || thursEndTime != null)
		{
		    thursTimeBlock = new WeeklyTimeBlock(thursList, thursStartTime, thursEndTime);
		}
		else
		{
		    thursTimeBlock = null;
		}

		// Friday time block
		LocalTime friStartTime = wishlistView.getFriStartOfDay();
		LocalTime friEndTime = wishlistView.getFriEndOfDay();
		List<DayOfWeek> friList = new ArrayList<DayOfWeek>();
		friList.add(DayOfWeek.FRIDAY);
		WeeklyTimeBlock friTimeBlock;
		if (friStartTime != null || friEndTime != null)
		{
		    friTimeBlock = new WeeklyTimeBlock(friList, friStartTime, friEndTime);
		}
		else
		{
		    friTimeBlock = null;
		}

		// Saturday time block
		LocalTime satStartTime = wishlistView.getSatStartOfDay();
		LocalTime satEndTime = wishlistView.getSatEndOfDay();
		List<DayOfWeek> satList = new ArrayList<DayOfWeek>();
		satList.add(DayOfWeek.SATURDAY);
		WeeklyTimeBlock satTimeBlock;
		if (satStartTime != null || satEndTime != null)
		{
		    satTimeBlock = new WeeklyTimeBlock(satList, satStartTime, satEndTime);
		}
		else
		{
		    satTimeBlock = null;
		}

		// Sunday time block
		LocalTime sunStartTime = wishlistView.getSunStartOfDay();
		LocalTime sunEndTime = wishlistView.getSunEndOfDay();
		List<DayOfWeek> sunList = new ArrayList<DayOfWeek>();
		sunList.add(DayOfWeek.SUNDAY);
		WeeklyTimeBlock sunTimeBlock;
		if (sunStartTime != null || sunEndTime != null)
		{
		    sunTimeBlock = new WeeklyTimeBlock(sunList, sunStartTime, sunEndTime);
		}
		else
		{
		    sunTimeBlock = null;
		}

		// Add the time blocks to the hashmap
		newDesiredStartAndEndTime.put(DayOfWeek.MONDAY, monTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.TUESDAY, tuesTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.WEDNESDAY, wedTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.THURSDAY, thursTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.FRIDAY, friTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.SATURDAY, satTimeBlock);
		newDesiredStartAndEndTime.put(DayOfWeek.SUNDAY, sunTimeBlock);


		
		// Get the desired campus location and top three schedules destination path from the wishlist view
		String newDesiredcampusLocation = wishlistView.getCampusLocation();
		Path newTopThreeSchedulesDestinationPath = wishlistView.getTopThreeSchedulesDestinationPath();
		
		try
		{
		// Input the values into the wishlistModel to verify
		HashMap<DayOfWeek, String> timeErrors = wishlistModel.saveWishlist(newMinDesiredBreakTime, newMaxDesiredBreakTime, newDesiredStartAndEndTime, newDesiredcampusLocation, newTopThreeSchedulesDestinationPath);
		
		// Update error labels next to desired start and end time fields
		updateWishlistStartAndEndTimeErrors(timeErrors);
		
		// Update the wishlist view to reflect any changes to error messages
		updateWishlistView();
		
			// If there are no time errors, show a success message
			if (timeErrors.isEmpty())
			{
				// Save the values to the user data repository
				userDataRepository.saveWishlist(newMinDesiredBreakTime, newMaxDesiredBreakTime, newDesiredStartAndEndTime, newDesiredcampusLocation, newTopThreeSchedulesDestinationPath);
				
				// Show success message
				JOptionPane.showMessageDialog(null, "Wishlist saved successfully!", "Success",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		catch (Exception e)

		{
			// If there is an error during saving, show an error message
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Purpose: To continue from the wishlist view to the about you view.
	 * If the user did not change any previously-saved wishlist information, do not re-save it.
	 * If the user changed anything, save the updated values to the wishlist model and repository.
	 * If there are no errors and no exceptions, dispose of WishlistView and set AboutYouView visible.
	 */
	private void continueWithWishlist()
	{
	        // Get the new values from the wishlist view
	        Long newMinDesiredBreakTime = wishlistView.getMinBreakTime();
	        Long newMaxDesiredBreakTime = wishlistView.getMaxBreakTime();
	        
			// Create individual time blocks for each day of the week and add them to a new hashmap
			HashMap<DayOfWeek, WeeklyTimeBlock> newDesiredStartAndEndTime = new HashMap<DayOfWeek, WeeklyTimeBlock>();		
			// Monday time block
			LocalTime monStartTime = wishlistView.getMonStartOfDay();
			LocalTime monEndTime = wishlistView.getMonEndOfDay();
			List<DayOfWeek> monList = new ArrayList<DayOfWeek>();
			monList.add(DayOfWeek.MONDAY);
			WeeklyTimeBlock monTimeBlock;
			if (monStartTime != null || monEndTime != null)
			{
			    monTimeBlock = new WeeklyTimeBlock(monList, monStartTime, monEndTime);
			}
			else
			{
			    monTimeBlock = null;
			}

			// Tuesday time block
			LocalTime tuesStartTime = wishlistView.getTuesStartOfDay();
			LocalTime tuesEndTime = wishlistView.getTuesEndOfDay();
			List<DayOfWeek> tuesList = new ArrayList<DayOfWeek>();
			tuesList.add(DayOfWeek.TUESDAY);
			WeeklyTimeBlock tuesTimeBlock;
			if (tuesStartTime != null || tuesEndTime != null)
			{
			    tuesTimeBlock = new WeeklyTimeBlock(tuesList, tuesStartTime, tuesEndTime);
			}
			else
			{
			    tuesTimeBlock = null;
			}

			// Wednesday time block
			LocalTime wedStartTime = wishlistView.getWedStartOfDay();
			LocalTime wedEndTime = wishlistView.getWedEndOfDay();
			List<DayOfWeek> wedList = new ArrayList<DayOfWeek>();
			wedList.add(DayOfWeek.WEDNESDAY);
			WeeklyTimeBlock wedTimeBlock;
			if (wedStartTime != null || wedEndTime != null)
			{
			    wedTimeBlock = new WeeklyTimeBlock(wedList, wedStartTime, wedEndTime);
			}
			else
			{
			    wedTimeBlock = null;
			}

			// Thursday time block
			LocalTime thursStartTime = wishlistView.getThursStartOfDay();
			LocalTime thursEndTime = wishlistView.getThursEndOfDay();
			List<DayOfWeek> thursList = new ArrayList<DayOfWeek>();
			thursList.add(DayOfWeek.THURSDAY);
			WeeklyTimeBlock thursTimeBlock;
			if (thursStartTime != null || thursEndTime != null)
			{
			    thursTimeBlock = new WeeklyTimeBlock(thursList, thursStartTime, thursEndTime);
			}
			else
			{
			    thursTimeBlock = null;
			}

			// Friday time block
			LocalTime friStartTime = wishlistView.getFriStartOfDay();
			LocalTime friEndTime = wishlistView.getFriEndOfDay();
			List<DayOfWeek> friList = new ArrayList<DayOfWeek>();
			friList.add(DayOfWeek.FRIDAY);
			WeeklyTimeBlock friTimeBlock;
			if (friStartTime != null || friEndTime != null)
			{
			    friTimeBlock = new WeeklyTimeBlock(friList, friStartTime, friEndTime);
			}
			else
			{
			    friTimeBlock = null;
			}

			// Saturday time block
			LocalTime satStartTime = wishlistView.getSatStartOfDay();
			LocalTime satEndTime = wishlistView.getSatEndOfDay();
			List<DayOfWeek> satList = new ArrayList<DayOfWeek>();
			satList.add(DayOfWeek.SATURDAY);
			WeeklyTimeBlock satTimeBlock;
			if (satStartTime != null || satEndTime != null)
			{
			    satTimeBlock = new WeeklyTimeBlock(satList, satStartTime, satEndTime);
			}
			else
			{
			    satTimeBlock = null;
			}

			// Sunday time block
			LocalTime sunStartTime = wishlistView.getSunStartOfDay();
			LocalTime sunEndTime = wishlistView.getSunEndOfDay();
			List<DayOfWeek> sunList = new ArrayList<DayOfWeek>();
			sunList.add(DayOfWeek.SUNDAY);
			WeeklyTimeBlock sunTimeBlock;
			if (sunStartTime != null || sunEndTime != null)
			{
			    sunTimeBlock = new WeeklyTimeBlock(sunList, sunStartTime, sunEndTime);
			}
			else
			{
			    sunTimeBlock = null;
			}

			// Add the time blocks to the hashmap
			newDesiredStartAndEndTime.put(DayOfWeek.MONDAY, monTimeBlock);
			newDesiredStartAndEndTime.put(DayOfWeek.TUESDAY, tuesTimeBlock);
			newDesiredStartAndEndTime.put(DayOfWeek.WEDNESDAY, wedTimeBlock);
			newDesiredStartAndEndTime.put(DayOfWeek.THURSDAY, thursTimeBlock);
			newDesiredStartAndEndTime.put(DayOfWeek.FRIDAY, friTimeBlock);
			newDesiredStartAndEndTime.put(DayOfWeek.SATURDAY, satTimeBlock);
			newDesiredStartAndEndTime.put(DayOfWeek.SUNDAY, sunTimeBlock);
	        // Get the desired campus location and top three schedules destination path from the wishlist view
	        String newDesiredcampusLocation = wishlistView.getCampusLocation();
	        Path newTopThreeSchedulesDestinationPath = wishlistView.getTopThreeSchedulesDestinationPath();

	        // Determine if anything was edited compared to what was previously saved
	        boolean wasEdited = false;

	        try
	        {
	            // Compare against previously saved values (repository is the source of truth)
	            // NOTE: Update these getter method names to match your repository if they differ.
	            Long oldMinDesiredBreakTime = userDataRepository.getMinDesiredBreakTime();
	            Long oldMaxDesiredBreakTime = userDataRepository.getMaxDesiredBreakTime();
	            HashMap<DayOfWeek, WeeklyTimeBlock> oldDesiredStartAndEndTime = userDataRepository.getDesiredStartAndEndTime();
	            String oldDesiredCampusLocation = userDataRepository.getDesiredCampusLocation();
	            Path oldTopThreeSchedulesDestinationPath = userDataRepository.getTopThreeSchedulesDestinationPath();

	            if ((oldMinDesiredBreakTime == null && newMinDesiredBreakTime != null)
	                    || (oldMinDesiredBreakTime != null && !oldMinDesiredBreakTime.equals(newMinDesiredBreakTime)))
	            {
	                wasEdited = true;
	            }

	            if ((oldMaxDesiredBreakTime == null && newMaxDesiredBreakTime != null)
	                    || (oldMaxDesiredBreakTime != null && !oldMaxDesiredBreakTime.equals(newMaxDesiredBreakTime)))
	            {
	                wasEdited = true;
	            }

	            if ((oldDesiredCampusLocation == null && newDesiredcampusLocation != null)
	                    || (oldDesiredCampusLocation != null && !oldDesiredCampusLocation.equals(newDesiredcampusLocation)))
	            {
	                wasEdited = true;
	            }

	            if ((oldTopThreeSchedulesDestinationPath == null && newTopThreeSchedulesDestinationPath != null)
	                    || (oldTopThreeSchedulesDestinationPath != null && !oldTopThreeSchedulesDestinationPath.equals(newTopThreeSchedulesDestinationPath)))
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
	                    WeeklyTimeBlock oldBlock = oldDesiredStartAndEndTime.get(day);
	                    WeeklyTimeBlock newBlock = newDesiredStartAndEndTime.get(day);

	                    LocalTime oldStart = (oldBlock == null) ? null : oldBlock.getClassStartTime();
	                    LocalTime newStart = (newBlock == null) ? null : newBlock.getClassStartTime();

	                    LocalTime oldEnd = (oldBlock == null) ? null : oldBlock.getClassEndTime();
	                    LocalTime newEnd = (newBlock == null) ? null : newBlock.getClassEndTime();

	                    if ((oldStart == null && newStart != null) || (oldStart != null && !oldStart.equals(newStart)))
	                    {
	                        wasEdited = true;
	                        break;
	                    }

	                    if ((oldEnd == null && newEnd != null) || (oldEnd != null && !oldEnd.equals(newEnd)))
	                    {
	                        wasEdited = true;
	                        break;
	                    }
	                }
	            }
	        }
	        catch (Exception e)
	        {
	            // If repository getters aren't available yet or something unexpected happens, treat as edited
	            wasEdited = true;
	        }

	        try
	        {
	            // Always validate through the model so timeErrors update correctly in the view
	            HashMap<DayOfWeek, String> timeErrors = wishlistModel.saveWishlist(newMinDesiredBreakTime,
	                    newMaxDesiredBreakTime,
	                    newDesiredStartAndEndTime,
	                    newDesiredcampusLocation,
	                    newTopThreeSchedulesDestinationPath);

	            // Update error labels next to desired start and end time fields
	            updateWishlistStartAndEndTimeErrors(timeErrors);
	            updateWishlistView();
	            
	            // If there are no time errors, proceed to the next view
	            if (timeErrors.isEmpty())
	            {
	                // Save to the user data repository
	                if (wasEdited)
	                {
	                    userDataRepository.saveWishlist(newMinDesiredBreakTime,
	                            newMaxDesiredBreakTime,
	                            newDesiredStartAndEndTime,
	                            newDesiredcampusLocation,
	                            newTopThreeSchedulesDestinationPath);
	                }
	                
	                // Dispose of the wishlist view and show the about you view
	                wishlistView.dispose();
	                aboutYouView.setVisible(true);
	            }
	        }
	        catch (Exception e)
	        {
	            JOptionPane.showMessageDialog(null, e.getMessage(), "Error",
	                    JOptionPane.ERROR_MESSAGE);
	        }
	    }
	/**
	 * Purpose: To clear out all values saved in the wishlist model and user repository
	 * 
	 */
	private void clearWishlist()
	{
		wishlistModel.clear();
		userDataRepository.clearWishlist();
	}
	
	/**
	 * Purpose: To update the error messages for the desired start and end times in the wishlist view 
	 * (helper method for saveWishlist() to call after it gets the time errors from the wishlist model and user repository and saves them to the app controller)
	 */
	private void updateWishlistStartAndEndTimeErrors(HashMap<DayOfWeek, String> timeErrors)
	{
	// Clear all day error JLabels first
	wishlistView.setMonError("");
	wishlistView.setTuesError("");
	wishlistView.setWedError("");
	wishlistView.setThursError("");
	wishlistView.setFriError("");
	wishlistView.setSatError("");
	wishlistView.setSunError("");

	// Now apply the new errors from timeErrors
	for (DayOfWeek day : timeErrors.keySet())
	{
	    String message = timeErrors.get(day);

	    switch (day)
	    {
	        case MONDAY:
	            wishlistView.setMonError(message);
	            break;
	        case TUESDAY:
	            wishlistView.setTuesError(message);
	            break;
	        case WEDNESDAY:
	            wishlistView.setWedError(message);
	            break;
	        case THURSDAY:
	            wishlistView.setThursError(message);
	            break;
	        case FRIDAY:
	            wishlistView.setFriError(message);
	            break;
	        case SATURDAY:
	            wishlistView.setSatError(message);
	            break;
	        case SUNDAY:
	            wishlistView.setSunError(message);
	            break;
	    }
	}
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
}
