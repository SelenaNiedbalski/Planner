package mcvandfileservice;

import java.util.List;

import exceptions.MustBeOverOneException;

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
 * Responsibilities of class: To represent the information about the student that will be used to create top schedules for them which will include their major distinction
 * their number of courses they will input, and the courses they struggle with
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
	 * @throws MustBeOverOneException if the number of courses the student will input is not over 1
	 */
	public void setNumOfCoursesStudentWillInput(int newNumOfCoursesStudentWillInput) throws MustBeOverOneException
	{
		if (newNumOfCoursesStudentWillInput > 1)
		{
			numOfCoursesStudentWillInput = newNumOfCoursesStudentWillInput;
		}
		else
		{
			throw new MustBeOverOneException();
		}
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
