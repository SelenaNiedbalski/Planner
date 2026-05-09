package mcvandfileservice;

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
	 * @return minRequiredCredits The minimum required credits
	 */
	public int getMinRequiredCredits()
	{
		return minRequiredCredits;
	}

	/**
	 * Purpose: To set the minimum required credits for a schedule
	 * @param newMinRequiredCredits The new minimum required credits for a schedule
	 */
	public void setMinRequiredCredits(int newMinRequiredCredits)
	{
		minRequiredCredits = newMinRequiredCredits;
	}
	
	/**
	 * Purpose: To return the maximum required credits for a schedule
	 * @return maxRequiredCredits The maximum required credits
	 */
	public int getMaxRequiredCredits()
	{
		return maxRequiredCredits;
	}



	/**
	 * Purpose: To set the maximum required credits for a schedule
	 * @param newMaxRequiredCredits The new maximum required credits for a schedule
	 */
	public void setMaxRequiredCredits(int newMaxRequiredCredits)
	{
		maxRequiredCredits = newMaxRequiredCredits;
	}
	

}
