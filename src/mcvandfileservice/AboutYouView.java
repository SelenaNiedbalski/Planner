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
 * AboutYouView is-a JFrame
 */

public class AboutYouView extends JFrame
{
    // Instance Variables
	private AppController appController; // An about you view has-an app controller
	
	
	/**
	 * Purpose: To return the app controller
	 * @return appController The app controller
	 */
	public AppController getAppController()
	{
		return appController;
	}
	
	/**
	 * Purpose: To set the app controller to the given value
	 * @param newAppController The new app controller
	 */
	public void setAppController(AppController newAppController)
	{
		appController = newAppController;
	}

}
