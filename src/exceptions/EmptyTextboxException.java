
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
 * Responsibilities of class: To represent an exception that is thrown when a textbox is left empty.
 * 
 * EmptyTextboxException is-an Exception
 */
public class EmptyTextboxException extends Exception
{
	/**
	 * Constructs an EmptyTextboxException with a default error message.
	 */
	public EmptyTextboxException()
	{
		super("The textbox cannot be empty.");
	}
}
