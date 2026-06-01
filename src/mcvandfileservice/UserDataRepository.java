package mcvandfileservice;

import java.nio.file.Path;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import schedules.WeeklyTimeBlock;

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
 *         Responsibilities of class: To represent a repository for the user
 *         data that is inputted by the user in the different views of the app
 *         (wishlist view, credits view, about you view, and course info view).
 *         This class will have instance variables for all of the different
 *         pieces of user data that are inputted by the user in the different
 *         views of the app, as well as getter and setter methods for each piece
 *         of user data. This class will also have methods to clear the user
 *         data for each view (for when the user wants to reset their info by
 *         selecting back).
 */

public class UserDataRepository
{
	// Instance Variables
	private int minRequiredCredits; // A user data repository has-a minimum
									// amount of required credits
	private int maxRequiredCredits; // A user data repository has-a maximum
									// amount of required credits
	private Long minDesiredBreakTime; // A user data repository has-a minimum
										// desired break time
	private Long maxDesiredBreakTime; // A user data repository has-a maximum
										// desired break time
	private HashMap<DayOfWeek, WeeklyTimeBlock> desiredStartAndEndTime; // A
																		// user
																		// data
																		// repository
																		// has-many
																		// desired
																		// start
																		// and
																		// end
																		// time
	private String desiredCampusLocation; // A user data repository has-a
											// desired campus location
	private String studentsMajorDistinction; // A user data repository has-a
												// student's major distinction
	private final Path topThreeSchedulesDestinationPath; // A user data
															// repository has-a
															// destination path
															// for the top three
															// schedules
	private int numOfCoursesStudentWillInput; // A user data repository has-a
												// number of courses the student
												// will input
	private String courseName; // A user data repository has-a course name
	private boolean studentRequiredCourse = true; // A user data repository
													// has-a boolean for whether
													// the course is a required
													// course for the student
	private List<String> struggleCourses; // A user data repository has-many
											// struggle courses
	private Path availableClassesRetrievalPath; // A user data repository has-an
												// available classes retrieval
												// path
	private String courseType; // A user data repository has-a course type

	// Constructors
	/**
	 * Purpose: To construct a UserDataRepository with default values
	 * (all values will be updated as they are saved inside methods for the
	 * models)
	 * 
	 */
	public UserDataRepository()
	{
		minRequiredCredits = 0;
		maxRequiredCredits = 0;
		minDesiredBreakTime = null;
		maxDesiredBreakTime = null;
		desiredStartAndEndTime = null;
		desiredCampusLocation = null;
		studentsMajorDistinction = null;
		topThreeSchedulesDestinationPath = null;
		numOfCoursesStudentWillInput = 0;
		courseName = null;
		studentRequiredCourse = false;
		struggleCourses = null;
		availableClassesRetrievalPath = null;
		courseType = null;
	}

	// Getters and Setters
	/**
	 * Purpose: To return the minimum required credits for the user based on the
	 * user's input in the CreditsModel
	 * 
	 * @return minRequiredCredits The minimum required credits for the user
	 */
	public int getMinRequiredCredits()
	{
		return minRequiredCredits;
	}

	/**
	 * Purpose: To set the minimum required credits for the user based on the
	 * user's input in the CreditsModel
	 * 
	 * @param newMinRequiredCredits The new minimum required credits for the
	 *                              user
	 */
	public void setMinRequiredCredits(int newMinRequiredCredits)
	{
		minRequiredCredits = newMinRequiredCredits;
	}

	/**
	 * Purpose: To return the maximum required credits for the user based on the
	 * user's input in the CreditsModel
	 * 
	 * @return maxRequiredCredits The maximum required credits for the user
	 */
	public int getMaxRequiredCredits()
	{
		return maxRequiredCredits;
	}

	/**
	 * Purpose: To set the maximum required credits for the user based on the
	 * user's input in the CreditsModel
	 * 
	 * @param newMaxRequiredCredits The new maximum required credits for the
	 *                              user
	 */
	public void setMaxRequiredCredits(int newMaxRequiredCredits)
	{
		maxRequiredCredits = newMaxRequiredCredits;
	}

	/**
	 * Purpose: To return the minimum desired break time for the user based on
	 * the user's input in the AboutYouModel
	 * 
	 * @return minDesiredBreakTime The minimum desired break time for the user
	 */
	public Long getMinDesiredBreakTime()
	{
		return minDesiredBreakTime;
	}

	/**
	 * Purpose: To set the minimum desired break time
	 * 
	 * @param newMinDesiredBreakTime The new minimum desired break time
	 */
	public void setMinDesiredBreakTime(Long newMinDesiredBreakTime)
	{
		minDesiredBreakTime = newMinDesiredBreakTime;

	}

	/**
	 * Purpose: To return the maximum desired break time for the user based on
	 * the user's input in the AboutYouModel
	 * 
	 * @return maxDesiredBreakTime The maximum desired break time for the user
	 */
	public Long getMaxDesiredBreakTime()
	{
		return maxDesiredBreakTime;
	}

	/**
	 * Purpose: To set the maximum desired break time
	 * 
	 * @param newMaxDesiredBreakTime The new maximum desired break time
	 */
	public void setMaxDesiredBreakTime(Long newMaxDesiredBreakTime)
	{
		maxDesiredBreakTime = newMaxDesiredBreakTime;
	}

	/**
	 * Purpose: To return the desired start and end time for the user based on
	 * the user's input in the AboutYouModel
	 * 
	 * @return desiredStartAndEndTime The desired start and end time for the
	 *         user
	 */
	public HashMap<DayOfWeek, WeeklyTimeBlock> getDesiredStartAndEndTime()
	{
		return desiredStartAndEndTime;
	}

	/**
	 * Purpose: To set the desired start and end time
	 * 
	 * @param newDesiredStartAndEndTime The new desired start and end time
	 */
	public void setDesiredStartAndEndTime(
			HashMap<DayOfWeek, WeeklyTimeBlock> newDesiredStartAndEndTime)
	{
		desiredStartAndEndTime = newDesiredStartAndEndTime;
	}

	/**
	 * Purpose: To return the desired campus location for the user based on the
	 * user's input in the AboutYouModel
	 * 
	 * @return desiredCampusLocation The desired campus location for the user
	 */
	public String getDesiredCampusLocation()
	{
		return desiredCampusLocation;
	}

	/**
	 * Purpose: To set the desired campus location for the user based on the
	 * user's input in the AboutYouModel
	 * 
	 * @param newDesiredCampusLocation The new desired campus location for the
	 *                                 user
	 */
	public void setDesiredCampusLocation(String newDesiredCampusLocation)
	{
		desiredCampusLocation = newDesiredCampusLocation;
	}

	/**
	 * Purpose: To return the student's major distinction for the user based on
	 * the user's input in the AboutYouModel
	 * 
	 * @return studentsMajorDistinction The student's major distinction for the
	 *         user
	 */
	public String getStudentsMajorDistinction()
	{
		return studentsMajorDistinction;
	}

	/**
	 * Purpose: To set the student's major distinction for the user based on the
	 * user's input in the AboutYouModel
	 * 
	 * @param newStudentsMajorDistinction The new student's major distinction
	 *                                    for the user
	 */
	public void setStudentsMajorDistinction(String newStudentsMajorDistinction)
	{
		studentsMajorDistinction = newStudentsMajorDistinction;
	}

	/**
	 * Purpose: To return the destination path for the top three schedules based
	 * on the user's input in the WishlistModel
	 * 
	 * @return topThreeSchedulesDestinationPath The destination path for the top
	 *         three schedules
	 */
	public Path getTopThreeSchedulesDestinationPath()
	{
		return topThreeSchedulesDestinationPath;
	}

	/**
	 * Purpose: To return the number of courses the student will input based on
	 * the user's input in the AboutYouModel
	 * 
	 * @return numOfCoursesStudentWillInput The number of courses the student
	 *         will input
	 */
	public int getNumOfCoursesStudentWillInput()
	{
		return numOfCoursesStudentWillInput;
	}

	/**
	 * Purpose: To set the number of courses the student will input based on the
	 * user's input in the AboutYouModel
	 * 
	 * @param newNumOfCoursesStudentWillInput The new number of courses the
	 *                                        student will input
	 */
	public void setNumOfCoursesStudentWillInput(
			int newNumOfCoursesStudentWillInput)
	{
		numOfCoursesStudentWillInput = newNumOfCoursesStudentWillInput;
	}

	/**
	 * Purpose: To return the course name based on the user's input in the
	 * CourseInfoModel
	 * 
	 * @return courseName The course name
	 */
	public String getCourseName()
	{
		return courseName;
	}

	/**
	 * Purpose: To set the course name based on the user's input in the
	 * CourseInfoModel
	 * 
	 * @param newCourseName The new course name
	 */
	public void setCourseName(String newCourseName)
	{
		courseName = newCourseName;
	}

	/**
	 * Purpose: To return whether the course is a required course for the
	 * student based on the user's input in the CourseInfoModel
	 * 
	 * @return studentRequiredCourse Whether the course is a required course for
	 *         the student
	 */
	public boolean isStudentRequiredCourse()
	{
		return studentRequiredCourse;
	}

	/**
	 * Purpose: To set whether the course is a required course for the student
	 * based on the user's input in the CourseInfoModel
	 * 
	 * @param updatedStudentRequiredCourse The new value for whether the course
	 *                                     is a required course for the student
	 */
	public void setStudentRequiredCourse(boolean updatedStudentRequiredCourse)
	{
		studentRequiredCourse = updatedStudentRequiredCourse;
	}

	/**
	 * Purpose: To return the struggle courses based on the user's input in the
	 * AboutYouModel
	 * 
	 * @return struggleCourses The struggle courses
	 */
	public List<String> getStruggleCourses()
	{
		return struggleCourses;
	}

	/**
	 * Purpose: To set the struggle courses based on the user's input in the
	 * AboutYouModel
	 * 
	 * @param newStruggleCourses The new struggle courses
	 */
	public void setStruggleCourses(List<String> newStruggleCourses)
	{
		struggleCourses = newStruggleCourses;
	}

	/**
	 * Purpose: To return the available classes retrieval path based on the
	 * user's input in the CourseInfoModel
	 * 
	 * @return availableClassesRetrievalPath The available classes retrieval
	 *         path
	 */
	public Path getAvailableClassesRetrievalPath()
	{
		return availableClassesRetrievalPath;
	}

	/**
	 * Purpose: To set the available classes retrieval path based on the user's
	 * input in the CourseInfoModel
	 * 
	 * @param newAvailableClassesRetrievalPath The new available classes
	 *                                         retrieval path
	 */
	public void setAvailableClassesRetrievalPath(
			Path newAvailableClassesRetrievalPath)
	{
		availableClassesRetrievalPath = newAvailableClassesRetrievalPath;
	}

	/**
	 * Purpose: To return the course type based on the user's input in the
	 * CourseInfoModel
	 * 
	 * @return courseType The course type
	 */
	public String getCourseType()
	{
		return courseType;
	}

	/**
	 * Purpose: To set the course type based on the user's input in the
	 * CourseInfoModel
	 * 
	 * @param newCourseType The new course type
	 */
	public void setCourseType(String newCourseType)
	{
		courseType = newCourseType;
	}

	/**
	 * Purpose: To return the student's major distinction for the user based on
	 * the user's input in the AboutYouModel
	 * 
	 * @return studentsMajorDistinction The student's major distinction for the
	 *         user
	 */
	public String getMajor()
	{
		return studentsMajorDistinction;
	}

	/**
	 * Purpose: To return the number of courses the student will input based on
	 * the user's input in the AboutYouModel
	 * 
	 * @return numOfCoursesStudentWillInput The number of courses the student
	 *         will input
	 */
	public int getNumCourses()
	{
		return numOfCoursesStudentWillInput;
	}

	// Other methods
	/**
	 * Purpose: To store the info from the app controller for the wishlist view
	 * 
	 * @param newMinDesired                       break time for the user
	 *                                            * @param
	 *                                            newMinDesiredBreakTime The
	 *                                            minimum desired break time for
	 *                                            the user
	 * @param newDesiredStartAndEndTime           The desired start and end time
	 *                                            for the user
	 * @param newDesiredCampusLocation            The desired campus location
	 *                                            for the user
	 * @param newTopThreeSchedulesDestinationPath The destination path for the
	 *                                            top three schedules for the
	 *                                            user
	 */
	public void saveWishlist(Long newMinDesiredBreakTime,
			Long newMaxDesiredBreakTime,
			HashMap<DayOfWeek, WeeklyTimeBlock> newDesiredStartAndEndTime,
			String newDesiredCampusLocation,
			Path newTopThreeSchedulesDestinationPath)
	{
		minDesiredBreakTime = newMinDesiredBreakTime;
		maxDesiredBreakTime = newMaxDesiredBreakTime;
		desiredStartAndEndTime = newDesiredStartAndEndTime;
		desiredCampusLocation = newDesiredCampusLocation;
	}

	/**
	 * Purpose: To store the info from the app controller for the credits view
	 * (for min and max required credits)
	 * 
	 * @param newMinRequiredCredits The minimum required credits for the user
	 * @param newMaxRequiredCredits The maximum required credits for the user
	 */
	public void saveCredits(int newMinRequiredCredits,
			int newMaxRequiredCredits)
	{
		minRequiredCredits = newMinRequiredCredits;
		maxRequiredCredits = newMaxRequiredCredits;
	}

	/**
	 * Purpose: To store the info from the app controller for the about you view
	 * (for the students major distinction,
	 * the number of courses the student will input, and the courses the student
	 * struggles with)
	 * 
	 * @param newStudentsMajorDistinction     The student's major distinction
	 * @param newNumOfCoursesStudentWillInput The number of courses the student
	 *                                        will input
	 * @param newStruggleCourses              The courses the student struggles
	 *                                        with
	 */
	public void saveAboutYou(String newStudentsMajorDistinction,
			int newNumOfCoursesStudentWillInput,
			List<String> newStruggleCourses)
	{
		studentsMajorDistinction = newStudentsMajorDistinction;
		numOfCoursesStudentWillInput = newNumOfCoursesStudentWillInput;
		struggleCourses = newStruggleCourses;
	}

	/**
	 * Purpose: To store the info from the app controller for the course info
	 * view (for the course name,
	 * whether the course is required for the student, availableclasses
	 * retrieval path, and the course type)
	 * 
	 * @param newCourseName                    The course name
	 * @param updatedStudentRequiredCourse     Whether the course is required
	 *                                         for the student
	 * @param newAvailableClassesRetrievalPath The available classes retrieval
	 *                                         path
	 * @param newCourseType                    The course type
	 */
	public void saveCourseInfo(String newCourseName,
			boolean updatedStudentRequiredCourse,
			Path newAvailableClassesRetrievalPath, String newCourseType)
	{
		courseName = newCourseName;
		studentRequiredCourse = updatedStudentRequiredCourse;
		availableClassesRetrievalPath = newAvailableClassesRetrievalPath;
		courseType = newCourseType;
	}

	/**
	 * Purpose: To clear the wishlist info stored in the UserDataRepository (for
	 * when the user wants to reset their wishlist by selecting back)
	 */
	public void clearWishlist()
	{
		minDesiredBreakTime = null;
		maxDesiredBreakTime = null;
		desiredStartAndEndTime = null;
		desiredCampusLocation = null;
	}

	/**
	 * Purpose: To clear the credits info stored in the UserDataRepository (for
	 * when the user wants to reset their credits info by selecting back)
	 */
	public void clearCredits()
	{
		minRequiredCredits = 0;
		maxRequiredCredits = 0;
	}

	/**
	 * Purpose: To clear the about you info stored in the UserDataRepository
	 * (for when the user wants to reset their about you info by selecting back)
	 */
	public void clearAboutYou()
	{
		studentsMajorDistinction = null;
		numOfCoursesStudentWillInput = 0;
		struggleCourses = null;
	}

	/**
	 * Purpose: To clear the course info stored in the UserDataRepository (for
	 * when the user wants to reset their course info by selecting back)
	 */
	public void clearCourseInfo()
	{
		courseName = null;
		studentRequiredCourse = false;
		availableClassesRetrievalPath = null;
		courseType = null;
	}

}
