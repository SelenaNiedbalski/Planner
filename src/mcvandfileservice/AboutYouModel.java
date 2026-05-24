package mcvandfileservice;

import java.util.HashMap;
import java.util.List;

import exceptions.MustBeOverOneException;
import exceptions.NoRadioButtonSelectedException;

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
	 * @throws NoRadioButtonSelectedException if the students major distinction is null (if no radio button is selected)
	 */
	public void setStudentsMajorDistinction(String newStudentsMajorDistinction) throws NoRadioButtonSelectedException 
	{
		if (newStudentsMajorDistinction != null)
		{
			studentsMajorDistinction = newStudentsMajorDistinction;
		}
		else
		{
			throw new NoRadioButtonSelectedException("major distinction");
		}
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
			throw new MustBeOverOneException("Number of courses");	
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
		if (newStruggleCourses != null)
		{
			struggleCourses = newStruggleCourses;
		}
		else
		{
			struggleCourses = null;
		}
	}
	
	/**
	 * Purpose: To save the info from the app controller for the about you view
	 * @param newStudentsMajorDistinction The students major distinction
	 * @param newNumOfCoursesStudentWillInput The number of courses the student will input
	 * @param newStruggleCourses The courses the student struggles with
	 * @return aboutYouErrors A hashmap of error messages for the students major distinction and the number of courses the student will input
	 */
	public HashMap<String, String> saveAboutYou(String newStudentsMajorDistinction, int newNumOfCoursesStudentWillInput, List<String> newStruggleCourses)
	{
		// Create a hashmap of error messages for the students major distinction and the number of courses the student will input
		HashMap<String, String> aboutYouErrors = new HashMap<String, String>();
		
		// Check students major distinction input
		if ( newStudentsMajorDistinction == null || newStudentsMajorDistinction.isEmpty())
		{
			NoRadioButtonSelectedException noRadioButtonSelectedException = new NoRadioButtonSelectedException("major distinction");
			aboutYouErrors.put("studentsMajorDistinction", noRadioButtonSelectedException.getMessage());
			studentsMajorDistinction = null;
		}
		else
		{
			studentsMajorDistinction = newStudentsMajorDistinction;
		}
		
		// Check number of courses student will input
		if (newNumOfCoursesStudentWillInput <= 1)
		{
			MustBeOverOneException mustBeOverOneException = new MustBeOverOneException("Number of courses");
			aboutYouErrors.put("numOfCoursesStudentWillInput", mustBeOverOneException.getMessage());
		}
		else
		{
			
			numOfCoursesStudentWillInput = newNumOfCoursesStudentWillInput;
		
		}
		
		// Check struggle courses input
		if (newStruggleCourses != null)
		{
			struggleCourses = newStruggleCourses;
		}
		else
		{
			struggleCourses = null;
		}
		
		return aboutYouErrors;
	}

	/**
	 * Purpose: To clear the about you model of all information (for when the user hits back)
	 */
	public void clear()
	{
		studentsMajorDistinction = null;
		numOfCoursesStudentWillInput = 0;
		struggleCourses = null;
		
	}

}
