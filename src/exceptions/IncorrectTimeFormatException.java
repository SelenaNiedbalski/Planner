
package exceptions;

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
 * Responsibilities of class: To represent an exception that is thrown when the time is in an incorrect format. The expected format is HH:MM:SS.
 * 
 * IncorrectTimeFormatException is-an Exception
 */
public class IncorrectTimeFormatException extends Exception
{
	/**
	 * Constructs an IncorrectTimeFormatException with a default error message.
	 */
	public IncorrectTimeFormatException()
	{
		super("Incorrect time format. Expected format: HH:MM:SS");
	}
}
