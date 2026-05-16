package courseclasses;

import schedules.WeeklyTimeBlock;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.Duration;
import java.util.List;

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
 *         Version/date: 29 April 2026
 * 
 *         Responsibilities of class: To represent a course that a student can
 *         take. This class is abstract
 * 
 */

public abstract class Course
{
	// Instance Variables
	private String courseName; // A course has-a course name
	private String courseID; // A course has-a course ID
	private WeeklyTimeBlock courseWeeklyTimeBlock; // A course has-a weekly time
													// block (weekly schedule)
	private String courseCampusLocation; // A course has-a campus location
	private int courseCredits; // A course has-a number of credits it is worth
	private double courseDifficulty; // A course has-a number that represents
										// its difficulty level
	private boolean courseRequired = true; // A course has-a value indicating if it is
									// a required course or not
	private double courseScore; // A course has-a course score
	private String instructorName; // A course has-an instructor's name
	private double instructorRMPScore; // A course has-an instructor's
										// RateMyProfessor score listed
	private String courseType; // A course has-a course type (STEM, Liberal
								// Arts, Professional, or Creative Arts and
								// Electives)
	private boolean struggleCourse = true; // A course has-a value indicating if it is
									// a struggle course or not

	// Constructors
	/**
	 * Purpose: To create a course object with all default values except
	 * courseType
	 * 
	 */
	public Course(String newCourseType)
	{
		courseName = "No course name listed";
		courseID = "No Course ID listed";
		courseWeeklyTimeBlock = null;
		courseCampusLocation = "No campus location listed";
		courseCredits = 0;
		courseDifficulty = 0.0;
		courseRequired = !courseRequired;
		courseScore = 0.0;
		instructorName = "Instructor TBD";
		instructorRMPScore = 0.0;
		courseType = newCourseType;
		struggleCourse = !struggleCourse;
	}

	/**
	 * Purpose: To create a Course object with parameter values for course name,
	 * the course ID, the course's weekly time block(with the constructor params
	 * for WeeklyTimeBlock), the course's campus location,
	 * the amount of credits the course is worth, the number of the course
	 * difficulty rating,
	 * if the course is required or not, the instructor's name for the course,
	 * and the instructor's RMP score (course score will still be default value
	 * of 0.0, and courseType will remain as "Course")
	 * 
	 * @param newCourseName           The new course name
	 * @param newCourseID             The new course ID
	 * @param newDaysOfTheWeek        The new days the class meets during
	 * @param newClassStartTime       The new time the class starts at
	 * @param newClassDuration        The new length of time the class meets
	 * @param newcourseCampusLocation The new campus location for the course
	 * @param newCourseCredits        The new amount of credits the course is
	 *                                worth
	 * @param newCourseDifficulty     The new number for the difficulty rating
	 *                                of the course
	 * @param updatedCourseRequired   The updated value for if the course is
	 *                                required or not
	 * @param newInstructorName       The new name of the course's instructor
	 * @param newInstructorRMPScore   The new RMP score for the instructor
	 * @param newCourseType           The updated type of the course
	 * @param updatedStruggleCourse   The updated value for if the course is a
	 *                                struggle-course of not
	 */
	public Course(String newCourseName, String newCourseID,
			List<DayOfWeek> newDaysOfTheWeek, LocalTime newClassStartTime,
			Duration newClassDuration, String newCourseCampusLocation,
			int newCourseCredits, double newCourseDifficulty,
			boolean updatedCourseRequired, String newInstructorName,
			double newInstructorRMPScore, String newCourseType,
			boolean updatedStruggleCourse)
	{
		courseName = newCourseName;
		courseID = newCourseID;
		courseWeeklyTimeBlock = new WeeklyTimeBlock(newDaysOfTheWeek,
				newClassStartTime, newClassDuration);
		courseCampusLocation = newCourseCampusLocation;
		courseCredits = newCourseCredits;
		courseDifficulty = newCourseDifficulty;
		courseRequired = updatedCourseRequired;
		courseScore = 0.0;
		instructorName = newInstructorName;
		instructorRMPScore = newInstructorRMPScore;
		courseType = newCourseType;
		struggleCourse = updatedStruggleCourse;
	}

	// Getters and Setters
	/**
	 * Purpose: To return the course name
	 * return courseName The name of the course
	 */
	public String getCourseName()
	{
		return courseName;
	}

	/**
	 * Purpose: To set the course name
	 * 
	 * @param newCourseName The new name of the course
	 */
	public void setCourseName(String newCourseName)
	{
		courseName = newCourseName;
	}

	/**
	 * Purpose: To return the course ID
	 * return courseID The ID string for the course
	 */
	public String getCourseID()
	{
		return courseID;
	}

	/**
	 * Purpose: To set the course ID
	 * 
	 * @param newCourseID The new ID for the course
	 */
	public void setCourseID(String newCourseID)
	{
		courseID = newCourseID;
	}

	/**
	 * Purpose: To return the weekly time block for the course
	 * return weeklyTimeBlock The weekly course meeting times
	 */
	public WeeklyTimeBlock getCourseWeeklyTimeBlock()
	{
		return courseWeeklyTimeBlock;
	}

	/**
	 * Purpose: To set the weekly time block for the course using raw data values (not a completed time block)
	 * 
	 * @param newDaysOfTheWeek  The new days the class meets during
	 * @param newClassStartTime The new time the class starts at
	 * @param newClassDuration  The new length of time the class meets
	 */
	public void setWeeklyTimeBlock(List<DayOfWeek> newDaysOfTheWeek,
			LocalTime newClassStartTime, Duration newClassDuration)
	{
		courseWeeklyTimeBlock.setWeeklyTimeBlock(newDaysOfTheWeek,
				newClassStartTime, newClassDuration);
	}

	/**
	 * Purpose: To return the campus location for the course
	 * return courseCampusLocation The campus location for the course
	 */
	public String getCourseCampusLocation()
	{
		return courseCampusLocation;
	}

	/**
	 * Purpose: To set the campus location for the course
	 * 
	 * @param newCourseCampusLocation The new campus location for the course
	 */
	public void setCourseCampusLocation(String newCourseCampusLocation)
	{
		courseCampusLocation = newCourseCampusLocation;
	}

	/**
	 * Purpose: To return the amount of credits the course is worth
	 * return courseCredits The number of credits the course is worth
	 */
	public int getCourseCredits()
	{
		return courseCredits;
	}

	/**
	 * Purpose: To set the amount of credits the course is worth
	 * 
	 * @param newCourseCredits The new number of credits the course is worth
	 */
	public void setCourseCredits(int newCourseCredits)
	{
		courseCredits = newCourseCredits;
	}

	/**
	 * Purpose: To return the course difficulty number
	 * return courseDifficulty The number of the course's difficulty rating
	 */
	public double getCourseDifficulty()
	{
		return courseDifficulty;
	}

	/**
	 * Purpose: To set the course difficulty rating
	 * 
	 * @param newCourseDifficulty The new number of the course's difficulty
	 *                            rating
	 */
	public void setCourseDifficulty(double newCourseDifficulty)
	{
		courseDifficulty = newCourseDifficulty;
	}

	/**
	 * Purpose: To return the boolean value of if a course is required or not
	 * return courseRequired The boolean value of if a course is required or not
	 */
	public boolean getCourseRequired()
	{
		return courseRequired;
	}

	/**
	 * Purpose: To set the boolean value of if a course is required or not
	 * 
	 * @param updatedCourseRequired The updated value of if the course is
	 *                              required or not
	 */
	public void setCourseRequired(boolean updatedCourseRequired)
	{
		courseRequired = updatedCourseRequired;
	}

	/**
	 * Purpose: To return the current score of the course
	 * return courseScore The overall score of the course
	 */
	public double getCourseScore()
	{
		return courseScore;
	}

	/**
	 * Purpose: To set the current score of the course
	 * 
	 * @param newCourseScore The new score of the course
	 */
	public void setCourseScore(double newCourseScore)
	{
		courseScore = newCourseScore;
	}

	/**
	 * Purpose: To return the name of the instructor for the course
	 * return instructorName The name of the course's instructor
	 */
	public String getInstructorName()
	{
		return instructorName;
	}

	/**
	 * Purpose: To set the name of the instructor for the course
	 * 
	 * @param newInstructorName The new name of the course's instructor
	 */
	public void setInstructorName(String newInstructorName)
	{
		instructorName = newInstructorName;
	}

	/**
	 * Purpose: To return the instructor's RMP score for the course
	 * 
	 * @return instructorRMPScore The instructor's RMP score
	 */
	public double getInstructorRMPScore()
	{
		return instructorRMPScore;
	}

	/**
	 * Purpose: To set the instructor's RMP score for the course
	 * 
	 * @param newInstructorRMPScore The instructor's new RMP score
	 */
	public void setInstructorRMPScore(double newInstructorRMPScore)
	{
		instructorRMPScore = newInstructorRMPScore;
	}

	/**
	 * Purpose: To return the type of the course (STEM, Liberal Arts,
	 * Professional, or Creative Arts and Electives)
	 * return courseType The type of course
	 */
	public String getCourseType()
	{
		return courseType;
	}

	/**
	 * Purpose: To return the boolean value for if the course is a struggle
	 * course or not
	 * 
	 * @return struggleCourse The boolean value for struggleCourse
	 */
	public boolean getStruggleCourse()
	{
		return struggleCourse;
	}

	/**
	 * Purpose: To set the boolean value for if the course is a struggle course
	 * or not
	 * 
	 * @param updatedStruggleCourse The updated boolean value for struggleCourse
	 */
	public void setStruggleCourse(boolean updatedStruggleCourse)
	{
		struggleCourse = updatedStruggleCourse;
	}

	// Other Methods
	/**
	 * Purpose: To add points to the course score for the amount of the RMP
	 * score of the course's instructor
	 * return courseScore The updated course score
	 */
	public double addPointsForRMPScore()
	{
		courseScore += instructorRMPScore;
		return courseScore;
	}

	/**
	 * Purpose: To add points to the course score if the course is required
	 * return courseScore The updated course score
	 */
	public double addPointsForCourseRequired()
	{
		if (courseRequired)
		{
			courseScore += 5;
		}
		return courseScore;
	}

	/**
	 * Purpose: To subtract points from the course score if the campus location
	 * is not the one the user wanted
	 * 
	 * @param desiredCampusLocation The campus location the student wants their
	 *                              courses to be at
	 *                              return courseScore The updated course score
	 */
	public double subtractPointsForWrongCampusLocation(
			String desiredCampusLocation)
	{
		if (courseCampusLocation != desiredCampusLocation)
		{
			courseScore -= 5;
		}
		return courseScore;
	}

	/**
	 * Purpose: To subtract points from the course score for the amount of the
	 * course difficulty score
	 * return courseScore The updated course score
	 */
	public double subtractPointsForCourseDifficulty()
	{
		courseScore -= courseDifficulty;
		return courseScore;
	}

	/**
	 * Purpose: To calculate overall course score (runs helper-calculation
	 * methods)
	 * 
	 * @param desiredCampusLocation    The student's desired campus location
	 * @param studentsMajorDistinction The student's major type
	 */
	public double calculateCourseScore(String desiredCampusLocation,
			String studentsMajorDistinction)
	{
		this.addPointsForRMPScore();
		this.addPointsForCourseRequired();
		this.subtractPointsForWrongCampusLocation(desiredCampusLocation);
		this.subtractPointsForCourseDifficulty();
		this.nonMajorSpecificCourseCalculationMessage(studentsMajorDistinction);
		return courseScore;
	}

	/**
	 * Purpose: To return that the student's major is undesignatede, so
	 * major-specific method is not run
	 * 
	 * @param studentsMajorDistinction The student's major type
	 *                                 return string A message indicating the
	 *                                 calculateCourseScore without the
	 *                                 major-specific method is run
	 */
	public String nonMajorSpecificCourseCalculationMessage(
			String studentsMajorDistinction)
	{
		return "The student indicated their major was "
				+ studentsMajorDistinction
				+ ", so this calculation does not give "
				+ "additional points for courses within STEM or Liberal Arts distinctions.";
	}

	/**
	 * Purpose: To return the course info as a string
	 * return toString The course info
	 */
	@Override
	public String toString()
	{
		return "Weekly Time Block: " + courseWeeklyTimeBlock.toString()
				+ " Course: " + courseName +" Course ID " + courseID
				+ " Instructor: " + instructorName
				+ " RMP Score: " + instructorRMPScore
				+ "Campus Location: " + courseCampusLocation
				+ " Credits: " + courseCredits;
				
				
	}

	/**
	 * Purpose: To set the course's weekly time block from a completed WeeklyTimeBlock object
	 * @param newWeeklyTimeBlock The new weekly time block for the course
	 */
	public void setCourseTimeBlock(WeeklyTimeBlock newWeeklyTimeBlock)
	{
		courseWeeklyTimeBlock = newWeeklyTimeBlock;
	}
}
