package courseclasses;

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
 *         Responsibilities of class: To represent courses that are STEM
 *         designated or Liberal Arts designated
 * 
 * 
 */

public interface MajorSpecific
{
	// Methods
	/**
	 * Purpose: To add points to the course score if the course is a
	 * major-specific course
	 * STEM courses are major-specific if the student's major distinction is a
	 * STEM major
	 * Liberal Arts courses are major-specific if the student's major
	 * distinction is a Liberal Arts major
	 * 
	 * @param studentsMajorDistinction The student's major distinction
	 * 
	 */
	public double addPointsForMajorSpecificCourse(
			String studentsMajorDistinction);
}
