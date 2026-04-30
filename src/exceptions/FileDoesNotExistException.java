
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
 * Responsibilities of class: To represent an exception that is thrown when a specified file does not exist.
 * 
 * FileDoesNotExistException is-an Exception
 */
public class FileDoesNotExistException extends Exception
{
	/**
	 * Constructs a FileDoesNotExistException with the specified detail message.
	 *
	 * @param message the detail message
	 */
	public FileDoesNotExistException()
	{
		super("The specified file does not exist.");
	}
}
