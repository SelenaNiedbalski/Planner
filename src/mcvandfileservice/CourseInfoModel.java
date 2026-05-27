package mcvandfileservice;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import exceptions.EmptyTextboxException;
import exceptions.FileDoesNotExistException;
import exceptions.FilePathDoesNotExistException;
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
 * Responsibilities of class: 
 * 
 */

public class CourseInfoModel
{
	// Instance Variables
	private String courseName; // A course info model has-a course name
	private boolean studentRequiredCourse; // A course info model has-a boolean value for whether the course is a required course or not
	private Path availableClassesRetrievalPath; // A course info model has-a path for where the available classes will be retrieved from
	private String courseType; // A course info model has-a course type
	private int totalCourses; // A course info model has-a total number of courses the user will input
	private int currentCourseNum; // A course info model has-a current course number that the user is inputting (which will be used to determine how many courses the user has left to input)
	
	
	
	// Constructors
	/**
	 * Purpose: To construct a CourseInfoModel with default values
	 * 
	 */
	public CourseInfoModel()
	{
		courseName = null;
		studentRequiredCourse = false;
		availableClassesRetrievalPath = Path.of("");
		courseType = null;
		totalCourses = 0;
		currentCourseNum = 1;
	}
	
	/**
	 * Purpose: To return the course name that the student will input
	 * @return courseName The course name
	 */
	public String getCourseName()
	{
		return courseName;
	}
	
	/**
	 * Purpose: To set the course name that the student will input
	 * @param newCourseName The new course name
	 * @throws EmptyTextboxException if the new course name is null (which means the student did not input a course name in the textbox and left it empty)
	 */
	public void setCourseName(String newCourseName) throws EmptyTextboxException
	{
		if (newCourseName != null)
		{
		courseName = newCourseName;
		}
		else
		{
			throw new EmptyTextboxException("course name textbox");
		}
	}
	
	/**
	 * Purpose: To return whether the course the student will input is a required course or not
	 * @return studentRequiredCourse Whether the course is a required course or not
	 */
	public boolean isStudentRequiredCourse()
	{
		return studentRequiredCourse;
	}

	/**
	 * Purpose: To set whether the course the student will input is a required course or not
	 * @param updatedStudentRequiredCourse The updated value for whether the course is a required course or not
	 * @throws NoRadioButtonSelectedException if the updated value for whether the course is a required course or not is null (which means the student did not select either option for whether the course is a required course or not and left both radio buttons unselected)
	 */
	public void setStudentRequiredCourse(Boolean updatedStudentRequiredCourse) throws NoRadioButtonSelectedException
	{
		if (updatedStudentRequiredCourse != null)
		{
			if (updatedStudentRequiredCourse == Boolean.FALSE)
			{
				studentRequiredCourse = false;
			}
			else
			{
				studentRequiredCourse = true;
			}
		}
		else
		{
			throw new NoRadioButtonSelectedException("required course option");
		}
	}
	
	/**
	 * Purpose: To return the path where the available classes will be retrieved from
	 * @return availableClassesRetrievalPath The path where the available classes will be retrieved from
	 */
	public Path getAvailableClassesRetrievalPath()
	{
		return availableClassesRetrievalPath;
	}	
	
	/**
	 * Purpose: To return the course type that the student will input
	 * @return courseType The course type
	 */
	public String getCourseType()
	{
		return courseType;
	}
	
	/**
	 * Purpose: To set the course type that the student will input
	 * @param newCourseType The new course type
	 * @throws NoRadioButtonSelectedException if the new course type is null (which means the student did not select a course type and left all radio buttons for course type unselected)
	 */
	public void setCourseType(String newCourseType) throws NoRadioButtonSelectedException
	{
		if (courseType != null)
		{
			courseType = newCourseType;
		}
		 else
		{
			throw new NoRadioButtonSelectedException("course type option");
		}
	}

	
	
	
	
	/**
	 * Purpose: To save the info from the app controller for the course info view
	 * @param newTotalCourses The total number of courses the student will input (pull from userdatarepository)
	 * @param newCourseName The course name that the student will input
	 * @param updatedStudentRequiredCourse The value for whether the course is a required course or not that the student will input
	 * @param newCourseType The course type that the student will input
	 * @param newAvailableClassesRetrievalPath The path for where the available classes will be retrieved from that the student will input
	 * @param generalErrors A list of general error messages (just for filepath) to display in a pop up error message
	 * @return courseInfoErrors A hash map of any errors that occur when saving the info from the app controller for the course info view, where the keys are the names of the fields that have errors and the values are the error messages for those fields
	 */
	public HashMap<String, String> saveCourseInfo(int newTotalCourses, String newCourseName, Boolean updatedStudentRequiredCourse, String newCourseType, Path newAvailableClassesRetrievalPath, List<String> generalErrors)
	{
		// Create a hash map to store any errors that occur when saving the info from the app controller for the course info view, where the keys are the names of the fields that have errors and the values are the error messages for those fields
		HashMap<String, String> courseInfoErrors = new HashMap<String, String>();
		
		// If the generalErrors list is null, create it so errors can still be added
		if (generalErrors == null)
		{
			generalErrors = new ArrayList<String>();
		}
		
		// Check course name input
		if (newCourseName == null)
		{
			EmptyTextboxException emptyTextboxException = new EmptyTextboxException("course name textbox");
			courseInfoErrors.put("courseName", emptyTextboxException.getMessage());
		}
		else
		{
			courseName = newCourseName;
		}
		
		// Check student required course input
		if (updatedStudentRequiredCourse == null)
		{
			NoRadioButtonSelectedException noRadioButtonSelectedException = new NoRadioButtonSelectedException("required course option");
			courseInfoErrors.put("studentRequiredCourse", noRadioButtonSelectedException.getMessage());
		}
		else
		{
			if (updatedStudentRequiredCourse == Boolean.FALSE)
			{
				studentRequiredCourse = false;
			}
			else
			{
				studentRequiredCourse = true;
			}
		}
		
		// Check course type input
		if (newCourseType == null)
		{
			NoRadioButtonSelectedException noRadioButtonSelectedException = new NoRadioButtonSelectedException("course type option");
			courseInfoErrors.put("courseType", noRadioButtonSelectedException.getMessage());
		}
		else
		{
			courseType = newCourseType;
		}
		
		// Check available classes retrieval path input
		if (newAvailableClassesRetrievalPath == null || newAvailableClassesRetrievalPath.toString().trim().isEmpty())
		{
			EmptyTextboxException emptyTextboxException = new EmptyTextboxException("available classes textbox");
			generalErrors.add(emptyTextboxException.getMessage());
		}
		
		if (newAvailableClassesRetrievalPath != null && (!newAvailableClassesRetrievalPath.toFile().exists()
				|| !newAvailableClassesRetrievalPath.toFile().isFile()))
		{
			FileDoesNotExistException fileDoesNotExistException = new FileDoesNotExistException();
			generalErrors.add(fileDoesNotExistException.getMessage());
		}
		
		// Add valid entrieds to the course info model's instance variables
		if (courseInfoErrors.isEmpty() && generalErrors.isEmpty())
		{
			courseName = newCourseName;
			studentRequiredCourse = updatedStudentRequiredCourse;
			courseType = newCourseType;
			availableClassesRetrievalPath = newAvailableClassesRetrievalPath;
			totalCourses = newTotalCourses;
		}
		
		return courseInfoErrors;
	}
	
	
	/**
	 * To return the current course
	 * @return currentCourseNum The current course number the student is inputting
	 * 
	 */
	public int returnCurrentCourse()
	{
		return currentCourseNum;
	}
	
	/**
	 * Purpose: To update the number of which course the student is currently
	 * inputting information for (starts at 1 and increments by 1 each time
	 * a student clicks next)
	 * @return currentCourseNum The updated number of which course the student is currently inputting information for
	 */
	public int updateCurrentCourse()
	{
		currentCourseNum++;
		return currentCourseNum;
	}

	/**
	 * Purpose: To return the total number of courses the student will input
	 * 
	 */
	
	/**
	 * Purpose: To clear the course info model's instance variables (by setting them back to their default values)
	 */
	public void clear()
	{
		courseName = null;
		studentRequiredCourse = false;
		courseType = null;
		availableClassesRetrievalPath = Path.of("");
	}

	/**
	 * Purpose: To set the number of total courses the student will input (pull from userdatarepository)
	 * @param newTotalCourses The number of total courses the student will input
	 */
	public void setTotalCourses(int newTotalCourses)
	{
		totalCourses = newTotalCourses;
	}

	/**
	 * Purpose: To return the current course number the student is inputting
	 * @return currentCourseNum The current course number the student is inputting
	 */
	public int getCurrentCourse()
	{
		return currentCourseNum;
	}

}
