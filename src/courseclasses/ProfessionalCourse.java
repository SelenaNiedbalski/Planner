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
 *         Responsibilities of class: To represent a professional course, which
 *         is a type of course. It inherits from the Course class and does not
 *         add any additional attributes or methods.
 * 
 *         ProfessionalCourse is-a course
 */

public class ProfessionalCourse extends Course
{
	// Constructors
	/**
	 * Purpose: To create a ProfessionalCourse object with all default values
	 */
	public ProfessionalCourse()
	{
		super("Professional");
	}

	/**
	 * Purpose: To create a ProfessionalCourse object with parameter values for
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
	 * @param newClassEndTime         The new lndength of time the class meets
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
	public ProfessionalCourse(String newCourseName, String newCourseID,
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
				newInstructorRMPScore, "Professional", updatedStruggleCourse);
	}
}
