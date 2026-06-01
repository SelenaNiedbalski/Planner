package schedules;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import courseclasses.Course;

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
 *         Responsibilities of class: To represent a schedule with many classes
 *         that a student can take
 * 
 */
public class Schedule
{
	// Instance Variables
	private List<Course> currentScheduleCourses; // A schedule has-many courses
	private List<WeeklyTimeBlock> currentScheduleTimes; // A schedule has-many
														// weekly time blocks
	private double scheduleScore; // A schedule has-a score
	private int totalCredits; // A schedule has-a total number of credits
	private double numSTEMCourses; // A schedule has-a number of STEM courses it
									// contains
	private double numStruggleCourses; // A schedule has-a number of struggle
										// courses

	// Constructors
	/**
	 * Purpose: To create a schedule object with default values
	 * 
	 */
	public Schedule()
	{
		currentScheduleCourses = new ArrayList<>();
		currentScheduleTimes = new ArrayList<>();
		scheduleScore = 0.0;
		totalCredits = 0;
		numSTEMCourses = 0;
		numStruggleCourses = 0;
	}

	// Getters and Setters
	/**
	 * Purpose: To return the current schedule of courses
	 * 
	 * @return currentScheduleCourses The current schedule of courses
	 */
	public List<Course> getCurrentScheduleCourses()
	{
		return currentScheduleCourses;
	}

	/**
	 * Purpose: To set the current schedule of courses
	 * 
	 * @param newCurrentScheduleCourses The new current schedule of courses
	 */
	public void setCurrentScheduleCourses(
			List<Course> newCurrentScheduleCourses)
	{
		currentScheduleCourses = newCurrentScheduleCourses;
	}

	/**
	 * Purpose: To return the current schedule of times
	 * 
	 * @return currentScheduleTimes The current schedule of times
	 */
	public List<WeeklyTimeBlock> getCurrentScheduleTimes()
	{
		return currentScheduleTimes;
	}

	/**
	 * Purpose: To set the current schedule of times
	 * 
	 * @param newCurrentScheduleTimes The new current schedule of times
	 */
	public void setCurrentScheduleTimes(
			List<WeeklyTimeBlock> newCurrentScheduleTimes)
	{
		currentScheduleTimes = newCurrentScheduleTimes;
	}

	/**
	 * Purpose: To return the current score of the schedule
	 * 
	 * @return scheduleScore The current score of the schedule
	 */
	public double getScheduleScore()
	{
		return scheduleScore;
	}

	/**
	 * Purpose: To set the current score of the schedule
	 * 
	 * @param newScheduleScore The new score of the schedule
	 */
	public void setScheduleScore(double newScheduleScore)
	{
		scheduleScore = newScheduleScore;
	}

	/**
	 * Purpose: To return the total number of credits in the schedule
	 * 
	 * @return totalCredits The total number of credits in the schedule
	 */
	public int getTotalCredits()
	{
		return totalCredits;
	}

	/**
	 * Purpose: To set the total number of credits in the schedule
	 * 
	 * @param newTotalCredits The new total number of credits in the schedule
	 */
	public void setTotalCredits(int newTotalCredits)
	{
		totalCredits = newTotalCredits;
	}

	/**
	 * Purpose: To return the number of STEM courses in the schedule
	 * 
	 * @return numSTEMCourses The number of STEM courses in the schedule
	 */
	public double getNumSTEMCourses()
	{
		return numSTEMCourses;
	}

	/**
	 * Purpose: To return the number of struggle courses in the schedule
	 * 
	 * @return numStruggleCourses The number of struggle courses in the schedule
	 */
	public double getNumStruggleCourses()
	{
		return numStruggleCourses;
	}

	// Helper Methods
	/**
	 * Purpose: To check if the potential schedule course has a schedule
	 * conflict with the
	 * course in the schedule (helper method inside canAddCourse method)
	 * 
	 * @param potentialCourse  The course that could potentially be added to the
	 *                         schedule
	 * @param courseInSchedule The course that is currently in the schedule
	 * @return hasScheduleConflict if they do overlap, and !hasScheduleConflict
	 *         if not
	 */
	private boolean hasScheduleConflict(Course potentialCourse,
			Course courseInSchedule)
	{
		// Initializing hasScheduleConflict
		boolean hasScheduleConflict = true;

		// Get the actual time blocks of both courses and save it to run
		// checkBlockCOnflict on
		WeeklyTimeBlock weeklyTimeBlock1 = potentialCourse
				.getCourseWeeklyTimeBlock();
		WeeklyTimeBlock weeklyTimeBlock2 = courseInSchedule
				.getCourseWeeklyTimeBlock();

		// Run method to check time block conflicts on both blocks and
		// if it returns true then return hasScheduleConflict, otherwise return
		// !hasScheduleConflict
		if ((weeklyTimeBlock1.checkBlockConflict(weeklyTimeBlock2)) == true)
		{
			return hasScheduleConflict;
		}
		else
		{
			return !hasScheduleConflict;
		}
	}

	/**
	 * Purpose: To check if a course can be added to the schedule (helper method
	 * inside addCourse method)
	 * 
	 * @param potentialCourse The course that could potentially be added to the
	 *                        schedule
	 * @return canAddCourse if the course can be added and !canAddCourse if not
	 */
	private boolean canAddCourse(Course potentialCourse)

	{
		// Initializing canAddCourse
		boolean canAddCourse = true;

		// If there are no courses yet, you can add the first one
		if (currentScheduleCourses == null
				|| currentScheduleCourses.size() == 0)
		{
			return canAddCourse;
		}

		// Check for duplicate course names and schedule conflicts
		for (Course courseInSchedule : currentScheduleCourses)
		{
			// Can not add course if there is already a course of the same
			// course name
			if (potentialCourse.getCourseName()
					.equals(courseInSchedule.getCourseName()))
			{
				return !canAddCourse;
			}

			// Check schedule conflicts with each course currently in the array
			if (this.hasScheduleConflict(potentialCourse,
					courseInSchedule) == true)
			{
				return !canAddCourse;
			}
		}

		// If no duplicates and no conflicts, then return canAddCourse
		return canAddCourse;
	}

	/**
	 * Purpose: To compare local time for each day in a time block and store the
	 * earliest time for each day in a hashmap
	 * (Helper method for calculateStartAndEndTimeDeviation)
	 * 
	 * @return earliestStartTimeForEachDay The earliest start time for each day
	 *         in the schedule
	 */
	private HashMap<DayOfWeek, LocalTime> getEarliestStartTimeForEachDay()
	{
		// Create the hashmap
		HashMap<DayOfWeek, LocalTime> earliestStartTimeForEachDay = new HashMap<>();

		// Loop through each time block in the schedule
		for (WeeklyTimeBlock block : currentScheduleTimes)
		{
			if (block != null)
			{
				// Loop through each day inside that block
				for (DayOfWeek day : block.getDaysOfTheWeek())
				{
					LocalTime currentStartTime = block.getStartTimeForDay(day);

					if (currentStartTime != null)
					{
						// If the day is not already in the table, add it
						if (earliestStartTimeForEachDay
								.containsKey(day) == false)
						{
							earliestStartTimeForEachDay.put(day,
									currentStartTime);
						}
						else
						{
							// Compare with existing earliest time
							LocalTime storedTime = earliestStartTimeForEachDay
									.get(day);

							if (currentStartTime.isBefore(storedTime))
							{
								earliestStartTimeForEachDay.put(day,
										currentStartTime);
							}
						}
					}
				}
			}
		}

		return earliestStartTimeForEachDay;
	}

	/**
	 * Purpose: To compare local time for each day in a time block and store the
	 * latest time for each day in a hashmap
	 * (Helper method for calculateStartAndEndTimeDeviation)
	 * 
	 * @return latestEndTimeForEachDay The latest end time for each day in the
	 *         schedule
	 */
	private HashMap<DayOfWeek, LocalTime> getLatestEndTimeForEachDay()
	{
		// Create the hashmap
		HashMap<DayOfWeek, LocalTime> latestEndTimeForEachDay = new HashMap<>();

		// Loop through each time block in the schedule
		for (WeeklyTimeBlock block : currentScheduleTimes)
		{
			if (block != null)
			{
				// Loop through each day inside that block
				for (DayOfWeek day : block.getDaysOfTheWeek())
				{
					LocalTime currentEndTime = block.getEndTimeForDay(day);

					if (currentEndTime != null)
					{
						// If the day is not already in the table, add it
						if (latestEndTimeForEachDay.containsKey(day) == false)
						{
							latestEndTimeForEachDay.put(day, currentEndTime);
						}
						else
						{
							// Compare with existing latest time
							LocalTime storedTime = latestEndTimeForEachDay
									.get(day);

							if (currentEndTime.isAfter(storedTime))
							{
								latestEndTimeForEachDay.put(day,
										currentEndTime);
							}
						}
					}
				}
			}
		}

		return latestEndTimeForEachDay;
	}

	/**
	 * Purpose: To check if the schedule contains STEM courses and to store the
	 * numberof them
	 * (Helper method for subtractPointsForSTEMCoursesOverThree)
	 * 
	 * @return numSTEMCourses The new number of STEM courses in the schedule
	 */
	private double calculateNumSTEMCourses()
	{
		// Reset count before recalculating
		numSTEMCourses = 0.0;

		// Loop through currentScheduleCourses
		for (Course course : currentScheduleCourses)
		{
			// Check if the course is STEM
			if (course.getCourseType().equals("STEM"))
			{
				numSTEMCourses++;
			}
		}

		return numSTEMCourses;
	}

	/**
	 * Purpose: To check if the schedule contains struggle courses and to store
	 * the number of them
	 * (Helper method for subtractPointsForStruggleCoursesOverOne)
	 * 
	 * @return numStruggleCourses The new number of struggle courses in the
	 *         schedule
	 */
	private double calculateNumStruggleCourses()
	{
		// Reset count before recalculating
		numStruggleCourses = 0.0;

		// Loop through currentScheduleCourses
		for (Course course : currentScheduleCourses)
		{
			// Check if the course is STEM
			if (course.getStruggleCourse() == true)
			{
				numStruggleCourses++;
			}
		}

		return numStruggleCourses;
	}

	// Other Methods
	/**
	 * Purpose: To add a course to the schedule and sort the schedule courses
	 * and times in order from earliest to latest time (local time)
	 * 
	 * @param potentialCourse The course that could potentially be added to the
	 *                        schedule
	 * @return courseAdded if it was added successfully and !courseAdded if it
	 *         could not be added
	 */
	public void addCourse(Course potentialCourse)
	{
		if (this.canAddCourse(potentialCourse) == true)
		{

			currentScheduleCourses.add(potentialCourse);

			// Keep the time blocks list in sync with courses
			WeeklyTimeBlock blockToAdd = potentialCourse
					.getCourseWeeklyTimeBlock();
			if (blockToAdd != null)
			{
				currentScheduleTimes.add(blockToAdd);
			}

			// Sort courses and time blocks in the schedule so that courses are
			// in local time order (earliest to latest)
			// Must keep lists connected so they both have to be sorted at the
			// same time based on the time blocks

			// Create a temporary list of pairs (Course + WeeklyTimeBlock)
			List<Object[]> pairedList = new ArrayList<>();

			for (int i = 0; i < currentScheduleCourses.size(); i++)
			{
				pairedList.add(new Object[] { currentScheduleCourses.get(i),
						currentScheduleTimes.get(i) });
			}

			// Sort by start time (earliest to latest)
			pairedList.sort((a, b) -> {
				WeeklyTimeBlock blockA = (WeeklyTimeBlock) a[1];
				WeeklyTimeBlock blockB = (WeeklyTimeBlock) b[1];

				LocalTime startA = blockA.getClassStartTime();
				LocalTime startB = blockB.getClassStartTime();

				if (startA == null && startB == null)
				{
					return 0;
				}
				else if (startA == null)
				{
					return 1;
				}
				else if (startB == null)
				{
					return -1;
				}

				return startA.compareTo(startB);
			});

			// Clear original lists
			currentScheduleCourses.clear();
			currentScheduleTimes.clear();

			// Re-add sorted values
			for (Object[] pair : pairedList)
			{
				currentScheduleCourses.add((Course) pair[0]);
				currentScheduleTimes.add((WeeklyTimeBlock) pair[1]);
			}

			// Update total credits
			int creditsToAdd = potentialCourse.getCourseCredits();
			totalCredits += creditsToAdd;
		}
	}

	/**
	 * Purpose: To remove a course from the schedule
	 * 
	 */
	public void removeCourse(Course course)
	{
		currentScheduleCourses.remove(course);

		// Keep the time blocks list in sync with courses
		WeeklyTimeBlock blockToRemove = course.getCourseWeeklyTimeBlock();

		if (blockToRemove != null)
		{
			currentScheduleTimes.removeIf(block -> block.equals(blockToRemove));
		}

		// Update total credits
		int creditsToRemove = course.getCourseCredits();
		totalCredits -= creditsToRemove;
	}

	/**
	 * Purpose: To compare schedule scores to eachother
	 * 
	 * @return the schedule with the higher score (and this schedule if they're
	 *         equal)
	 */
	public Schedule compareTo(Schedule other)
	{
		if (this.getScheduleScore() >= other.getScheduleScore())
		{
			return this;
		}
		else
		{
			return other;
		}
	}

	/**
	 * Purpose: To calculate the break times in between courses on the schedule
	 * and subtract points from the scheduleScore for each break time that is
	 * outside of the desired break time range
	 * 
	 * @param minDesiredBreakTime The minimum desired break time between courses
	 * @param maxDesiredBreakTime The maximum desired break time between courses
	 * @return scheduleScore The new scheduleScore
	 */
	public double calculateBreakTimeDeviation(long minDesiredBreakMinutes,
			long maxDesiredBreakMinutes)
	{
		for (DayOfWeek day : DayOfWeek.values())
		{

			List<LocalTime[]> timesForDay = new ArrayList<>();

			for (WeeklyTimeBlock block : currentScheduleTimes)
			{
				if (block.containsDay(day))
				{
					timesForDay.add(new LocalTime[] { block.getClassStartTime(),
							block.getClassEndTime() });
				}
			}

			if (timesForDay.size() >= 2)
			{
				timesForDay.sort((a, b) -> a[0].compareTo(b[0]));

				for (int i = 0; i < timesForDay.size() - 1; i++)
				{
					LocalTime endCurrent = timesForDay.get(i)[1];
					LocalTime startNext = timesForDay.get(i + 1)[0];

					long breakMinutes = java.time.temporal.ChronoUnit.MINUTES
							.between(endCurrent, startNext);

					// Only process valid breaks (no overlap, no zero)
					if (breakMinutes > 0)
					{
						if (breakMinutes < minDesiredBreakMinutes)
						{
							long diff = minDesiredBreakMinutes - breakMinutes;
							scheduleScore -= diff / 5.0;
						}
						else if (breakMinutes > maxDesiredBreakMinutes)
						{
							long diff = breakMinutes - maxDesiredBreakMinutes;
							scheduleScore -= diff / 5.0;
						}
					}
				}
			}
		}
		return scheduleScore;
	}

	/**
	 * Purpose: To calculate the difference between the schedule's times
	 * and the student's desired time ranges and subtract points from the
	 * scheduleScore
	 * 
	 * @param desiredStartAndEndTimes The student's desired start and end times
	 *                                for each day of the week
	 *                                (a hashmap containing a weekly time block
	 *                                for each day)
	 * @return scheduleScore The new scheduleScore
	 */
	public double calculateStartAndEndTimeDeviation(
			HashMap<DayOfWeek, WeeklyTimeBlock> desiredTimesPerDay)
	{
		HashMap<DayOfWeek, LocalTime> earliestTimes = this
				.getEarliestStartTimeForEachDay();
		HashMap<DayOfWeek, LocalTime> latestTimes = this
				.getLatestEndTimeForEachDay();

		for (DayOfWeek day : earliestTimes.keySet())
		{
			LocalTime scheduleEarliest = earliestTimes.get(day);
			LocalTime scheduleLatest = latestTimes.get(day);

			// Get that day's specific time block
			WeeklyTimeBlock desiredBlock = desiredTimesPerDay.get(day);

			// If day start and end are blank and nulll values were created in a
			// block
			if (desiredBlock == null)
			{
				if (scheduleEarliest != null && scheduleLatest != null)
				{
					scheduleScore -= 300;
				}
			}
			else
			{
				LocalTime desiredStart = desiredBlock.getClassStartTime();
				LocalTime desiredEnd = desiredBlock.getClassEndTime();

				// Days are blank and block was created with null times
				if (desiredStart == null && desiredEnd == null)
				{
					if (scheduleEarliest != null && scheduleLatest != null)
					{
						scheduleScore -= 300;
					}
				}

				// Compare times
				else if (desiredStart != null && desiredEnd != null)
				{
					if (scheduleEarliest != null
							&& scheduleEarliest.isBefore(desiredStart))
					{
						Duration difference = Duration.between(scheduleEarliest,
								desiredStart);
						scheduleScore -= (difference.toMinutes() / 2.0);
					}

					if (scheduleLatest != null
							&& scheduleLatest.isAfter(desiredEnd))
					{
						Duration difference = Duration.between(desiredEnd,
								scheduleLatest);
						scheduleScore -= (difference.toMinutes() / 2.0);
					}
				}
			}
		}
		return scheduleScore;
	}

	/**
	 * Purpose: To subtract points from the scheduleScore for each numSTEMCourse
	 * count over 3
	 * 
	 * @return scheduleScore The new scheduleScore
	 */
	public double subtractPointsForSTEMCoursesOverThree()
	{
		this.calculateNumSTEMCourses();
		if (numSTEMCourses > 3)
		{
			double numSTEMCoursesOverThree = numSTEMCourses - 3.0;

			// Calculation to subtract from schedule score
			double pointsToSubtract = numSTEMCoursesOverThree * 7.0;
			scheduleScore -= pointsToSubtract;
		}

		return scheduleScore;
	}

	/**
	 * Purpose: To subtract points from the scheduleScore for each numSTEMCourse
	 * count over 3
	 * 
	 * @return scheduleScore The new scheduleScore
	 */
	public double subtractPointsForStruggleCoursesOverOne()
	{
		this.calculateNumStruggleCourses();
		if (numStruggleCourses > 1)
		{
			double numStruggleCoursesOverOne = numStruggleCourses - 1.0;

			// Calculation to subtract from schedule score
			double pointsToSubtract = numStruggleCoursesOverOne * 7.0;
			scheduleScore -= pointsToSubtract;
		}

		return scheduleScore;
	}

	/**
	 * Purpose: To add all points from the courses in the schedule to the
	 * scheduleScore
	 * 
	 * @param desiredCampusLocation    The student's desired campus location
	 * @param studentsMajorDistinction The student's major distinction (STEM or
	 *                                 non-STEM)
	 * @return scheduleScore The new scheduleScore
	 */
	public double addCourseScores(String desiredCampusLocation,
			String studentsMajorDistinction)
	{
		for (Course course : currentScheduleCourses)
		{
			course.calculateCourseScore(desiredCampusLocation,
					studentsMajorDistinction);
			scheduleScore += course.getCourseScore();
		}

		return scheduleScore;
	}

	/**
	 * Purpose: To return all the instance variables info in the schedule
	 * 
	 * @return all the schedule's IV info
	 */
	@Override
	public String toString()
	{
		return "Current Schedule Courses: " + currentScheduleCourses
				+ " Current Schedule Times: " + currentScheduleTimes
				+ " Total Credits: " + totalCredits
				+ " Schedule's Start Times: "
				+ this.getEarliestStartTimeForEachDay()
				+ " Schedule's End Times: " + this.getLatestEndTimeForEachDay()
				+ " How many STEM courses this schedule cointains: "
				+ numSTEMCourses;
	}

	/**
	 * Purpose: To set the number of STEM courses in the schedule
	 * 
	 * @param newNumSTEMCourses The new number of STEM courses in the schedule
	 */
	public void setNumSTEMCourses(int newNumSTEMCourses)
	{
		numSTEMCourses = newNumSTEMCourses;
	}

	/**
	 * Purpose: To set the number of struggle courses in the schedule
	 * 
	 * @param newNumStruggleCourses The new number of struggle courses in the
	 *                              schedule
	 */
	public void setNumStruggleCourses(int newNumStruggleCourses)
	{
		numStruggleCourses = newNumStruggleCourses;
	}

}
