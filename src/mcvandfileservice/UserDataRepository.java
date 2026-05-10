package mcvandfileservice;

import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import schedules.WeeklyTimeBlock;

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

public class UserDataRepository
{
	// Instance Variables
	private int minRequiredCredits;
	private int maxRequiredCredits;
	private Duration minDesiredBreakTime;
	private Duration maxDesiredBreakTime;
	private WeeklyTimeBlock desiredStartAndEndTime;
	private String desiredCampusLocation;
	private String studentsMajorDistinction;
	private final Path topThreeSchedulesDestinationPath;
	private int numOfCoursesStudentWillInput;
	private String courseName;
	private boolean studentRequiredCourse = true;
	private List<String> struggleCourses;
	private Path availableClassesRetrievalPath;
	private String courseType;

	
	// Constructors
	/**
	 * Purpose: To construct a UserDataRepository with default values
	 * 
	 */
	public UserDataRepository()
	{
		minRequiredCredits = 0;
		maxRequiredCredits = 0;
		minDesiredBreakTime = Duration.ZERO;
		maxDesiredBreakTime = Duration.ZERO;
		desiredStartAndEndTime = null;
		desiredCampusLocation = null;
		studentsMajorDistinction = null;
		topThreeSchedulesDestinationPath = null;
		numOfCoursesStudentWillInput = 0;
		courseName = null;
		studentRequiredCourse = true;
		struggleCourses = null;
		availableClassesRetrievalPath = null;
		courseType = null;
		
	}
	
	
	/**
	 * Purpose: To store the info from the app controller for the credits view (for min and max required credits)
	 * @param newMinRequiredCredits The minimum required credits for the user
	 * @param newMaxRequiredCredits The maximum required credits for the user
	 */
	public void saveCredits(int newMinRequiredCredits, int newMaxRequiredCredits)
	{
		minRequiredCredits = newMinRequiredCredits;
		maxRequiredCredits = newMaxRequiredCredits;
	}
	
	/**
	 * Purpose: To store the info from the app controller for the wishlist view (for the min and max desired break time,
	 * the desired start and end time, the desired campus location, and the top three schedules destination path)
	 * @param newMinDesiredBreakTime The minimum desired break time for the user
	 * @param newMaxDesiredBreakTime The maximum desired break time for the user
	 * @param newDesiredStartAndEndTime The desired start and end time for the user
	 * @param newDesiredCampusLocation The desired campus location for the user
	 * @param newTopThreeSchedulesDestinationPath The destination path for the top three schedules for the user
	 */
	 public void saveWishlist(Duration newMinDesiredBreakTime, Duration newMaxDesiredBreakTime, WeeklyTimeBlock newDesiredStartAndEndTime, 
			 String newDesiredCampusLocation, Path newTopThreeSchedulesDestinationPath)
	 {
		 minDesiredBreakTime = newMinDesiredBreakTime;
		 maxDesiredBreakTime = newMaxDesiredBreakTime;
		 desiredStartAndEndTime = newDesiredStartAndEndTime;
		 desiredCampusLocation = newDesiredCampusLocation;
	 }
	 
	 /**
	  * Purpose: To store the info from the app controller for the about you view (for the students major distinction, 
	  * the number of courses the student will input, and the courses the student struggles with)
	  * @param newStudentsMajorDistinction The student's major distinction
	  * @param newNumOfCoursesStudentWillInput The number of courses the student will input
	  * @param newStruggleCourses The courses the student struggles with
	  */
	public void saveAboutYou(String newStudentsMajorDistinction, int newNumOfCoursesStudentWillInput, List<String> newStruggleCourses)
	{
		studentsMajorDistinction = newStudentsMajorDistinction;
		numOfCoursesStudentWillInput = newNumOfCoursesStudentWillInput;
		struggleCourses = newStruggleCourses;
	}
	
	/**
	 * Purpose: To store the info from the app controller for the course info view (for the course name, 
	 * whether the course is required for the student, availableclasses retrieval path, and the course type)
	 * @param newCourseName The course name
	 * @param updatedStudentRequiredCourse Whether the course is required for the student
	 * @param newAvailableClassesRetrievalPath The available classes retrieval path
	 * @param newCourseType The course type
	 */
	public void saveCourseInfo(String newCourseName, boolean updatedStudentRequiredCourse, Path newAvailableClassesRetrievalPath, String newCourseType)
	{
		courseName = newCourseName;
		studentRequiredCourse = updatedStudentRequiredCourse;
		availableClassesRetrievalPath = newAvailableClassesRetrievalPath;
		courseType = newCourseType;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Purpose: To return the minimum required credits for the user based on the user's input in the CreditsModel
	 * @return minRequiredCredits The minimum required credits for the user
	 */
	public int getMinRequiredCredits()
	{
		return minRequiredCredits;
	}
	
	/**
	 * Purpose: To set the minimum required credits for the user based on the user's input in the CreditsModel
	 * @param newMinRequiredCredits The new minimum required credits for the user
	 */
	public void setMinRequiredCredits(int newMinRequiredCredits)
	{
		minRequiredCredits = newMinRequiredCredits;
	}

	/**
	 * Purpose: To return the maximum required credits for the user based on the user's input in the CreditsModel
	 * @return maxRequiredCredits The maximum required credits for the user
	 */
	public int getMaxRequiredCredits()
	{
		return maxRequiredCredits;
	}
	
	/**
	 * Purpose: To set the maximum required credits for the user based on the user's input in the CreditsModel
	 * @param newMaxRequiredCredits The new maximum required credits for the user
	 */
	public void setMaxRequiredCredits(int newMaxRequiredCredits)
	{
		maxRequiredCredits = newMaxRequiredCredits;
	}

	/**
	 * Purpose: To return the minimum desired break time for the user based on the user's input in the AboutYouModel	
	 * @return minDesiredBreakTime The minimum desired break time for the user
	 */
	public Duration getMinDesiredBreakTime()
	{
		return minDesiredBreakTime;
	}
	
	/**
	 * Purpose: To set the minimum desired break time for the user based on the user's input in the AboutYouModel
	 * @param newMinDesiredBreakTime The new minimum desired break time for the user
	 */
	public void setMinDesiredBreakTime(Duration newMinDesiredBreakTime)
	{
		minDesiredBreakTime = newMinDesiredBreakTime;
	}

	/**
	 * Purpose: To return the maximum desired break time for the user based on the user's input in the AboutYouModel
	 * @return maxDesiredBreakTime The maximum desired break time for the user
	 */
	public Duration getMaxDesiredBreakTime()
	{
		return maxDesiredBreakTime;
	}
	
	/**
	 * Purpose: To set the maximum desired break time for the user based on the user's input in the AboutYouModel
	 * @param newMaxDesiredBreakTime The new maximum desired break time for the user
	 */
	public void setMaxDesiredBreakTime(Duration newMaxDesiredBreakTime)
	{
		maxDesiredBreakTime = newMaxDesiredBreakTime;
	}

	/**
	 * Purpose: To return the desired start and end time for the user based on the user's input in the AboutYouModel
	 * @return desiredStartAndEndTime The desired start and end time for the user
	 */
	public WeeklyTimeBlock getDesiredStartAndEndTime()
	{
		return desiredStartAndEndTime;
	}

	/**
	 * Purpose: To set the desired start and end time for the user based on the user's input in the AboutYouModel
	 * @param newDesiredStartAndEndTime The new desired start and end time for the user
	 */
	public void setDesiredStartAndEndTime(WeeklyTimeBlock newDesiredStartAndEndTime)
	{
		desiredStartAndEndTime = newDesiredStartAndEndTime;
	}

	/**
	 * Purpose: To return the desired campus location for the user based on the user's input in the AboutYouModel
	 * @return desiredCampusLocation The desired campus location for the user
	 */
	public String getDesiredCampusLocation()
	{
		return desiredCampusLocation;
	}

	/**
	 * Purpose: To set the desired campus location for the user based on the user's input in the AboutYouModel
	 * @param newDesiredCampusLocation The new desired campus location for the user
	 */
	public void setDesiredCampusLocation(String newDesiredCampusLocation)
	{
		desiredCampusLocation = newDesiredCampusLocation;
	}

	/**
	 * Purpose: To return the student's major distinction for the user based on the user's input in the AboutYouModel
	 * @return studentsMajorDistinction The student's major distinction for the user
	 */
	public String getStudentsMajorDistinction()
	{
		return studentsMajorDistinction;
	}
	
	/**
	 * Purpose: To set the student's major distinction for the user based on the user's input in the AboutYouModel
	 * @param newStudentsMajorDistinction The new student's major distinction for the user
	 */
	public void setStudentsMajorDistinction(String newStudentsMajorDistinction)
	{
		studentsMajorDistinction = newStudentsMajorDistinction;
	}

	/**
	 * Purpose: To return the destination path for the top three schedules based on the user's input in the WishlistModel
	 * @return topThreeSchedulesDestinationPath The destination path for the top three schedules
	 */
	public Path getTopThreeSchedulesDestinationPath()
	{
		return topThreeSchedulesDestinationPath;
	}
	
	/**
	 * Purpose: To return the number of courses the student will input based on the user's input in the AboutYouModel
	 * @return numOfCoursesStudentWillInput The number of courses the student will input
	 */
	public int getNumOfCoursesStudentWillInput()
	{
		return numOfCoursesStudentWillInput;
	}
	
	/**
	 * Purpose: To set the number of courses the student will input based on the user's input in the AboutYouModel
	 * @param newNumOfCoursesStudentWillInput The new number of courses the student will input
	 */
	public void setNumOfCoursesStudentWillInput(int newNumOfCoursesStudentWillInput)
	{
		numOfCoursesStudentWillInput = newNumOfCoursesStudentWillInput;
	}
	
	/**
	 * Purpose: To return the course name based on the user's input in the CourseInfoModel
	 * @return courseName The course name
	 */
	public String getCourseName()
	{
		return courseName;
	}
	
	/**
	 * Purpose: To set the course name based on the user's input in the CourseInfoModel
	 * @param newCourseName The new course name
	 */
	public void setCourseName(String newCourseName)
	{
		courseName = newCourseName;
	}
	
	/**
	 * Purpose: To return whether the course is a required course for the student based on the user's input in the CourseInfoModel
	 * @return studentRequiredCourse Whether the course is a required course for the student
	 */
	public boolean isStudentRequiredCourse()
	{
		return studentRequiredCourse;
	}
	
	/**
	 * Purpose: To set whether the course is a required course for the student based on the user's input in the CourseInfoModel
	 * @param updatedStudentRequiredCourse The new value for whether the course is a required course for the student
	 */
	public void setStudentRequiredCourse(boolean updatedStudentRequiredCourse)
	{
		studentRequiredCourse = updatedStudentRequiredCourse;
	}
	
	/**
	 * Purpose: To return the struggle courses based on the user's input in the AboutYouModel
	 * @return struggleCourses The struggle courses
	 */
	public List<String> getStruggleCourses()
	{
		return struggleCourses;
	}
	
	/**
	 * Purpose: To set the struggle courses based on the user's input in the AboutYouModel
	 * @param newStruggleCourses The new struggle courses
	 */
	public void setStruggleCourses(List<String> newStruggleCourses)
	{
		struggleCourses = newStruggleCourses;
	}
	
	/**
	 * Purpose: To return the available classes retrieval path based on the user's input in the CourseInfoModel
	 * @return availableClassesRetrievalPath The available classes retrieval path
	 */
	public Path getAvailableClassesRetrievalPath()
	{
		return availableClassesRetrievalPath;
	}
	
	/**
	 * Purpose: To set the available classes retrieval path based on the user's input in the CourseInfoModel
	 * @param newAvailableClassesRetrievalPath The new available classes retrieval path
	 */
	public void setAvailableClassesRetrievalPath(Path newAvailableClassesRetrievalPath)
	{
		availableClassesRetrievalPath = newAvailableClassesRetrievalPath;
	}
	
	/**
	 * Purpose: To return the course type based on the user's input in the CourseInfoModel
	 * @return courseType The course type
	 */
	public String getCourseType()
	{
		return courseType;
	}
	
	/**
	 * Purpose: To set the course type based on the user's input in the CourseInfoModel
	 * @param newCourseType The new course type
	 */
	public void setCourseType(String newCourseType)
	{
		courseType = newCourseType;
	}
	
}
