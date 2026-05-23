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
 * Responsibilities of class: To represent an exception that is thrown when the value of minimum credits, maxiumum credits, or the number of interested classes is less than 1
 * 
 * MaxBreakLessThanMinException is-an Exception
 */

public class EndTimeBeforeStartTimeException extends Exception
{
	/**
	 * Constructs a MustBeOverOneException with a default message that takes parameters for time type
	 * @startTimeType The type of start time
	 * @endTimeType The type of end time
	 */
	public EndTimeBeforeStartTimeException(String startTimeType, String endTimeType)
	{
		super("" + startTimeType + "  must be greater than " + endTimeType + ".");
	}
}
