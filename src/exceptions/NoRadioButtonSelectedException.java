package exceptions;

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
 *         Responsibilities of class: To represent an exception that is thrown
 *         when no radio button is selected in the GUI when one is required to
 *         be selected.
 * 
 *         NoRadioButtonSelectedException is-an Exception
 */
public class NoRadioButtonSelectedException extends Exception
{
	/**
	 * Constructs an NoRadioButtonSelectedException with a default error message
	 * with a parameter for the name of the text field that is empty
	 * 
	 * @param textfieldValue The name of the text field that is empty
	 */
	public NoRadioButtonSelectedException(String textfieldValue)
	{
		super("A " + textfieldValue + " must be selected.");
	}
}
