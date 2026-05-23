
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
 * Responsibilities of class: To represent an exception that is thrown when a file path does not exist
 * 
 * FilePathDoesNotExistException is-an Exception
 */
public class FilePathDoesNotExistException extends Exception
{
	/**
	 * Constructs a FilePathDoesNotExistException with the specified detail message.
	 *
	 * @param message the detail message
	 */
	public FilePathDoesNotExistException()
	{
		super(" The file path for the top three schedules destination does not exist. Navigate to folder, right click the folder, and select \"Copy as Path\" to get the correct path.");
	}
}
