package mcvandfileservice;

import java.util.List;

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

public class AboutYouModel
{
	// Instance Variables
	private String studentsMajorDistinction;
	private int numOfCoursesStudentWillInput;
	private List<String> struggleCourses;
	
	
	// Getters and Setters
	/**
	 * Purpose: To return the students major distinction
	 * @return studentsMajorDistinction The students major distinction
	 */
	public String getStudentsMajorDistinction()
	{
		return studentsMajorDistinction;
	}
	
	/**
	 * Purpose: To set the students major distinction
	 * @param newStudentsMajorDistinction The students major distinction
	 */
	public void setStudentsMajorDistinction(String newStudentsMajorDistinction)
	{
		studentsMajorDistinction = newStudentsMajorDistinction;
	}

	/**
	 * Purpose: To return the number of courses the student will input
	 * (the general course that dictates number of windows, not the classes available for it)
	 * @return numOfCoursesStudentWillInput The number of courses the student will input
	 */
	public int getNumOfCoursesStudentWillInput()
	{
		return numOfCoursesStudentWillInput;
	}
	
	/**
	 * Purpose: To set the number of courses the student will input
	 * @param newNumOfCoursesStudentWillInput The number of courses the student will input
	 */
	public void setNumOfCoursesStudentWillInput(int newNumOfCoursesStudentWillInput)
	{
		numOfCoursesStudentWillInput = newNumOfCoursesStudentWillInput;
	}

	/**
	 * Purpose: To return the courses the student struggles with
	 * @return struggleCourses The courses the student struggles with
	 */
	public List<String> getStruggleCourses()
	{
		return struggleCourses;
	}
	
	/**
	 * Purpose: To set the courses the student struggles with
	 * @param newStruggleCourses The courses the student struggles with
	 */
	public void setStruggleCourses(List<String> newStruggleCourses)
	{
		struggleCourses = newStruggleCourses;
	}

}
