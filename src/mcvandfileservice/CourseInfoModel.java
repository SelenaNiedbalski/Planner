package mcvandfileservice;

import java.nio.file.Path;

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
	private String courseName;
	private boolean studentRequiredCourse;
	private final Path availableClassesRetrievalPath;
	private String courseType;
	
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
	 */
	public void setCourseName(String newCourseName)
	{
		courseName = newCourseName;
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
	 */
	public void setStudentRequiredCourse(boolean updatedStudentRequiredCourse)
	{
		studentRequiredCourse = updatedStudentRequiredCourse;
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
	 */
	public void setCourseType(String newCourseType)
	{
		courseType = newCourseType;
	}

}
