package mcvandfileservice;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import courseclasses.Course;
import courseclasses.CreativeArtsAndElectivesCourse;
import courseclasses.LiberalArtsCourse;
import courseclasses.ProfessionalCourse;
import courseclasses.STEMCourse;
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

public class CourseRepository
{
	private List<PartialCourse> storedClasses; // A course repository has-many partial courses
	private Path topThreeSchedulesDestinationPath; // A course repository has-a destination path
	private UserDataRepository userDataRepository; // A course repository has-a user data repository
	
	/**
	 * Purpose: To construct a CourseRepository a default empty storedClasses list and the given userData repository to grab the destination schedule path
	 * @param userDataRepository The user data repository to grab the destination schedule path from
	 * 
	 */
	public CourseRepository(UserDataRepository newUserDataRepository)
	{
		storedClasses = null;
		topThreeSchedulesDestinationPath = null;
		userDataRepository = newUserDataRepository;
	}
	
	
	
	/**
	 * Purpose: To save the course information that the student will input to a file
	 * @param newCourseID The course ID
	 * @param newCourseWeeklyTimeBlock The course weekly time block
	 * @param newCourseInstructor The course instructor
	 * @param newCourseRMPScore The course RMP score
	 * @param newCourseCampus The course campus
	 * @param newCourseCredits The course credits
	 */
	public void storeClassInfo(String courseID,
			WeeklyTimeBlock courseWeeklyTimeBlock, String courseInstructor,
			double courseRMPScore, String courseCampus, int courseCredits)
	{
		PartialCourse newCourse = new PartialCourse(courseID,
				courseWeeklyTimeBlock, courseInstructor, courseRMPScore,
				courseCampus, courseCredits);

		storedClasses.add(newCourse);
	}
	
	
	/**
	 * Purpose: To create course objects based on partial info from partial course and input info from user input
	 * @param courseType The course type that the student will input
	 * @param required Whether the course is a required course or not that the student will input
	 * @param difficulty The course difficulty that the student will input
	 * @return finalCourses The list of course objects created based on the partial info from partial course and input info from user input
	 * 
	 */
	public List<Course> buildFinalCourses(String courseType, boolean required, double difficulty)
	{
	    List<Course> finalCourses = new ArrayList<>();

	    for (PartialCourse pc : storedClasses)
	    {
	        Course newCourse;

	        if (courseType.equals("STEM"))
	        {
	            newCourse = new STEMCourse();
	        }
	        else if (courseType.equals("LiberalArts"))
	        {
	            newCourse = new LiberalArtsCourse();
	        }
	        else if (courseType.equals("Professional"))
	        {
	            newCourse = new ProfessionalCourse();
	        }
	        else
	        {
	            newCourse = new CreativeArtsAndElectivesCourse();
	        }


			newCourse.setCourseID(pc.getCourseID());
			newCourse.setCourseTimeBlock(pc.getTimeBlock());
			newCourse.setInstructorName(pc.getInstructor());
			newCourse.setInstructorRMPScore(pc.getRmpScore());
			newCourse.setCourseCampusLocation(pc.getCampus());
			newCourse.setCourseCredits(pc.getCredits());

	        newCourse.setCourseDifficulty(difficulty);
	        newCourse.setCourseRequired(required);

	        finalCourses.add(newCourse);
	    }

	    // Clear out stored classes after they're used to build final courses
	    storedClasses.clear();

	    return finalCourses;
	}
	
	// Getters
	/**
	 * Purpose: To set the top three schedules destination path based on the user data repository
	 * @param userDataRepository The user data repository to grab the destination schedule path from
	 */
	public void setTopThreeSchedulesDestinationPath()
	{
		topThreeSchedulesDestinationPath = userDataRepository.getTopThreeSchedulesDestinationPath();
	}
	
	/**
	 * Purpose: To return the path where the top three schedules file will be saved to
	 * @return topThreeSchedulesDestinationPath The path where the top three schedules file will be saved to
	 */
	public Path getTopThreeSchedulesDestinationPath()
	{
		return topThreeSchedulesDestinationPath;
	}
	
}
