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
 * Responsibilities of class: To represent an exception that is thrown when the value of minimum credits or the number of interested classes is less than or equal to 1
 * 
 * MustBeOverOneException is-an Exception
 */

public class MustBeOverOneException extends Exception
{
	/**
	 * Constructs a MustBeOverOneException with a default message.
	 * @param valueType The type of value that must be over one
	 */
	public MustBeOverOneException(String valueType)
	{
		super(valueType + " must be over one.");
	}
}
