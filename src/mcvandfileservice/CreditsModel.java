package mcvandfileservice;

import java.util.HashMap;
import exceptions.EndTypeBeforeStartTypeException;
import exceptions.MustBeOverOneException;

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
 *         Responsibilities of class: To represent the credits information for a
 *         schedule, including the minimum and maximum required credits, and to
 *         store the info from the app controller for the credits view
 * 
 */

public class CreditsModel
{
	// Instance Variables
	private int minRequiredCredits;
	private int maxRequiredCredits;

	// Constructors
	/**
	 * Purpose: To construct a CreditsModel with default values
	 * 
	 */
	public CreditsModel()
	{
		minRequiredCredits = 0;
		maxRequiredCredits = 0;
	}

	// Getters and Setters
	/**
	 * Purpose: To return the minimum required credits for a schedule
	 * 
	 * @return minRequiredCredits The minimum required credits
	 */
	public int getMinRequiredCredits()
	{
		return minRequiredCredits;
	}

	/**
	 * Purpose: To set the minimum required credits for a schedule
	 * 
	 * @param newMinRequiredCredits The new minimum required credits for a
	 *                              schedule
	 */
	public void setMinRequiredCredits(int newMinRequiredCredits)
	{
		minRequiredCredits = newMinRequiredCredits;
	}

	/**
	 * Purpose: To return the maximum required credits for a schedule
	 * 
	 * @return maxRequiredCredits The maximum required credits
	 */
	public int getMaxRequiredCredits()
	{
		return maxRequiredCredits;
	}

	/**
	 * Purpose: To set the maximum required credits for a schedule
	 * 
	 * @param newMaxRequiredCredits The new maximum required credits for a
	 *                              schedule
	 */
	public void setMaxRequiredCredits(int newMaxRequiredCredits)
	{
		maxRequiredCredits = newMaxRequiredCredits;
	}

	// Other Methods
	/**
	 * Purpose: To store the info from the app controller for the credits view
	 * 
	 * @param newMinRequiredCredits The minimum required credits for a schedule
	 * @param newMaxRequiredCredits The maximum required credits for a schedule
	 * @return creditsErrors A hashmap of error messages for the minimum and
	 *         maximum required credits inputs
	 */
	public HashMap<String, String> saveCredits(int newMinRequiredCredits,
			int newMaxRequiredCredits)
	{
		// Create a hashmap of error messages for the minimum and maximum
		// required credits inputs
		HashMap<String, String> creditsErrors = new HashMap<String, String>();

		// Check min required credits input
		if (newMinRequiredCredits <= 1)
		{
			MustBeOverOneException mustBeOverOneException = new MustBeOverOneException(
					"Minimum required credits value");
			creditsErrors.put("minRequiredCredits",
					mustBeOverOneException.getMessage());
		}

		// Check max required credits input
		if (newMaxRequiredCredits < newMinRequiredCredits)
		{
			EndTypeBeforeStartTypeException endTypeBeforeStartTypeException = new EndTypeBeforeStartTypeException(
					"Max credits", "min credits");
			creditsErrors.put("maxRequiredCredits",
					endTypeBeforeStartTypeException.getMessage());
		}

		// Add valid entries to the credits model
		if (creditsErrors.isEmpty())
		{
			minRequiredCredits = newMinRequiredCredits;
			maxRequiredCredits = newMaxRequiredCredits;
		}

		return creditsErrors;
	}

	/**
	 * Purpose: To clear the credits model of all information (for when the user
	 * hits back)
	 */
	public void clear()
	{
		minRequiredCredits = 0;
		maxRequiredCredits = 0;
	}

}
