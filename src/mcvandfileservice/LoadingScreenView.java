package mcvandfileservice;

import javax.swing.JFrame;

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
 * LoadingScreenView is-a JFrame 
 */
public class LoadingScreenView extends JFrame
{
	// Instance Variables
	private AppController appController; // A loading screen view has-an app controller
	
	
	
	
	
	
	
	
	// Getters and Setters
	/**
	 * Purpose: To return the app controller
	 * @return appController The app controller instance variable
	 */
	public AppController getAppController()
	{
		return appController;
	}
	
	/**
	 * Purpose: To set the app controller
	 * @param newAppController The app controller to set the app controller instance variable to
	 */	
	public void setAppController(AppController newAppController)
	{
		appController = newAppController;
	}
}
