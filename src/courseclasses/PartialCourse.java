package courseclasses;

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
 *         Responsibilities of class: To store information to create course
 *         objects when added with user input later
 * 
 */
public class PartialCourse
{
	// Instance Variables
	private String courseID; // A partial course has-a course ID
	private WeeklyTimeBlock timeBlock; // A partial course has-a weekly time
										// block representing the course
										// schedule
	private String instructor; // A partial course has-a instructor teaching the
								// course
	private double rmpScore; // A partial course has-a RateMyProfessor score
								// associated with the instructor
	private String campus; // A partial course has-a campus where the course is
							// offered
	private int credits; // A partial course has-a number of credits the course
							// is worth
	private String courseName; // A partial course has-a name

	// Constructor
	/**
	 * Purpose: To construct a PartialCourse with the given course information
	 * 
	 * @param newCourseID   The new course ID
	 * @param newTimeBlock  The new course weekly time block
	 * @param newInstructor The new course instructor
	 * @param newRMPScore   The new course RMP score
	 * @param newCampus     The new course campus
	 * @param newCredits    The new course credits
	 * @param newCourseName The new course name
	 */
	public PartialCourse(String newCourseID, WeeklyTimeBlock newTimeBlock,
			String newInstructor, double newRMPScore, String newCampus,
			int newCredits)
	{
		courseID = newCourseID;
		timeBlock = newTimeBlock;
		instructor = newInstructor;
		rmpScore = newRMPScore;
		campus = newCampus;
		credits = newCredits;
	}

	// Getters
	/**
	 * Purpose: To return the course ID for this partial course
	 * 
	 * @return courseID The course ID associated with this course
	 */
	public String getCourseID()
	{
		return courseID;
	}

	/**
	 * Purpose: To return the weekly time block for this course
	 * 
	 * @return timeBlock The weekly time block representing the course schedule
	 */
	public WeeklyTimeBlock getTimeBlock()
	{
		return timeBlock;
	}

	/**
	 * Purpose: To return the instructor's name for this course
	 * 
	 * @return instructor The name of the instructor teaching the course
	 */
	public String getInstructor()
	{
		return instructor;
	}

	/**
	 * Purpose: To return the instructor's RateMyProfessor score
	 * 
	 * @return rmpScore The RMP score associated with the instructor
	 */
	public double getRmpScore()
	{
		return rmpScore;
	}

	/**
	 * Purpose: To return the campus location for this course
	 * 
	 * @return campus The campus where the course is offered
	 */
	public String getCampus()
	{
		return campus;
	}

	/**
	 * Purpose: To return the course name for this course
	 * 
	 * @return courseName The name of the course
	 */
	public String getCourseName()
	{
		return courseName;
	}

	/**
	 * Purpose: To return the number of credits for this course
	 * 
	 * @return credits The number of credits the course is worth
	 */
	public int getCredits()
	{
		return credits;
	}
}