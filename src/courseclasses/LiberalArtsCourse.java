package courseclasses;

import java.time.DayOfWeek;
import java.time.Duration;
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
 *         Responsibilities of class: To represent a liberal arts course, which
 *         is a type of Course. This is also a type of course that inherits
 *         methods from major-specific as it represents a category of classes
 *         within a major distinction
 * 
 *         LiberalArtsCourse is-a course
 *         LiberalArtsCourse is-a major-specific course
 */

public class LiberalArtsCourse extends Course implements MajorSpecific
{
	// Instance Variables
	private double courseScore; // A Liberal Arts course has-a course score
								// (specifically to update course score due to
								// implemented method from MajorSpecific
								// interface)

	// Constructors
	/**
	 * Purpose: To create a LiberalArtsCourse object with all default values
	 */
	public LiberalArtsCourse()
	{
		super("Liberal Arts");
	}

	/**
	 * Purpose: To create a LiberalArtsCourse object with parameter values for
	 * course name,
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
	public LiberalArtsCourse(String newCourseName, String newCourseID,
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
				newInstructorRMPScore, "Liberal Arts", updatedStruggleCourse);
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
	 * and a helper method from major-specific interface
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
		this.addPointsForMajorSpecificCourse(studentsMajorDistinction); // Variation
																		// for
																		// polymorphism
		this.subtractPointsForWrongCampusLocation(desiredCampusLocation);
		this.subtractPointsForCourseDifficulty();
		return courseScore;
	}
}
