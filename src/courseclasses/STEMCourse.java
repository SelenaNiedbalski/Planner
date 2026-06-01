package courseclasses;

import java.time.DayOfWeek;
import java.time.LocalTime;
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
 *         Version/date: 31 May 2026
 * 
 *         Responsibilities of class: To represent a STEM course, which is a
 *         type of course. It inherits from the Course class and does not add
 *         any additional attributes or methods.
 * 
 *         STEMCourse is-a course
 *         STEMCourse is-a major-specific course
 */

public class STEMCourse extends Course implements MajorSpecific
{
	// Instance Variables
	private double courseScore; // A Liberal Arts course has-a course score
								// (specifically to update course score due to
								// implemented method from MajorSpecific
								// interface)

	// Constructors
	/**
	 * Purpose: To create a STEMCourse object with all default values
	 */
	public STEMCourse()
	{
		super("STEM");
	}

	/**
	 * Purpose: To create a STEMCourse object with parameter values for course
	 * name,
	 * the course ID, the course's weekly time block(with the constructor params
	 * for WeeklyTimeBlock), the course's campus location,
	 * the amount of credits the course is worth, the number of the course
	 * difficulty rating,
	 * if the course is required or not, the instructor's name for the course,
	 * the instructor's RMP score, and if the course is a struggle course or not
	 * (course score will still be default value of 0.0, and courseType will
	 * remain as "Course")
	 * 
	 * @param newCourseName           The new course name
	 * @param newCourseID             The new course ID
	 * @param newDaysOfTheWeek        The new days the class meets during
	 * @param newClassStartTime       The new time the class starts at
	 * @param newClassEndTime         The new end of time the class meets
	 * @param newcourseCampusLocation The new campus location for the course
	 * @param newCourseCredits        The new amount of credits the course is
	 *                                worth
	 * @param newCourseDifficulty     The new number for the difficulty rating
	 *                                of the course
	 * @param updatedCourseRequired   The updated value for if the course is
	 *                                required or not
	 * @param newInstructorName       The new name of the course's instructor
	 * @param newInstructorRMPScore   The new RMP score for the instructor
	 * @param updatedStruggleCourse   The updated value for if the course is a
	 *                                struggle-course of not
	 */
	public STEMCourse(String newCourseName, String newCourseID,
			List<DayOfWeek> newDaysOfTheWeek, LocalTime newClassStartTime,
			LocalTime newClassEndTime, String newCourseCampusLocation,
			int newCourseCredits, double newCourseDifficulty,
			boolean updatedCourseRequired, String newInstructorName,
			double newInstructorRMPScore, String newCourseType,
			boolean updatedStruggleCourse)
	{
		super(newCourseName, newCourseID, newDaysOfTheWeek, newClassStartTime,
				newClassEndTime, newCourseCampusLocation, newCourseCredits,
				newCourseDifficulty, updatedCourseRequired, newInstructorName,
				newInstructorRMPScore, "STEM", updatedStruggleCourse);
	}

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
	@Override
	public double addPointsForMajorSpecificCourse(
			String studentsMajorDistinction)
	{
		if (this.getCourseType() == studentsMajorDistinction)
		{
			courseScore += 5;
		}
		return courseScore;
	}

	/**
	 * Purpose: To calculate overall course score (runs helper methods from
	 * course
	 * and a helper method from major-specific interface)
	 * 
	 * @param desiredCampusLocation    The student's desired campus location
	 * @param studentsMajorDistinction The student's major distinction
	 */
	@Override
	public double calculateCourseScore(String desiredCampusLocation,
			String studentsMajorDistinction)
	{
		this.addPointsForRMPScore();
		this.addPointsForCourseRequired();
		this.addPointsForMajorSpecificCourse(studentsMajorDistinction);
		this.subtractPointsForWrongCampusLocation(desiredCampusLocation);
		this.subtractPointsForCourseDifficulty();
		return courseScore;
	}
}
