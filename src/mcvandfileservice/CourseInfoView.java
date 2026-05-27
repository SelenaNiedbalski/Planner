package mcvandfileservice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.nio.file.Path;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

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
 * CourseInfoView is-a JFrame
 */

public class CourseInfoView extends JFrame
{

    // Instance Variables
	private AppController appController; 
	
	// Course number label IV
	private JLabel courseNumberLabel;
	
	// Course name IVs
	private JTextField courseName; 
	private JLabel courseNameError;
	
	// Required course radio buttons IVs
	private JRadioButton yesButton; 
	private JRadioButton noButton; 
	private ButtonGroup requiredButtonGroup; 
	private JLabel requiredCourseError;

	// Course type radio buttons IVs
	// Science
	private JRadioButton biologyRadioButton;
	private JRadioButton chemistryRadioButton;
	private JRadioButton physicsRadioButton;
	private JRadioButton environmentalScienceRadioButton;
	private JRadioButton otherScienceRadioButton;
	// Tech
	private JRadioButton computerScienceRadioButton;
	private JRadioButton informationTechnologyRadioButton;
	private JRadioButton computerInformationSystemsRadioButton;
	private JRadioButton cybersecurityAndInfoAssuranceRadioButton;
	private JRadioButton dataScienceAndAnalyticsRadioButton;
	private JRadioButton softwareEngineeringAndDevelopmentRadioButton;
	private JRadioButton computerEngineeringRadioButton;
	private JRadioButton otherTechRadioButton;
	// Engineering
	private JRadioButton civilEngineeringRadioButton;
	private JRadioButton mechanicalEngineeringRadioButton;
	private JRadioButton electricalEngineeringRadioButton;
	private JRadioButton chemicalEngineeringRadioButton;
	private JRadioButton otherEngineeringRadioButton;
	// Math
	private JRadioButton coreMathRadioButton;
	private JRadioButton pureMathRadioButton;
	private JRadioButton appliedMathRadioButton;
	private JRadioButton otherMathRadioButton;
	// Liberal Arts
	private JRadioButton historyRadioButton;
	private JRadioButton literatureRadioButton;
	private JRadioButton philosophyRadioButton;
	private JRadioButton psychologyRadioButton;
	private JRadioButton sociologyRadioButton;
	private JRadioButton politicalScienceRadioButton;
	private JRadioButton justiceAdministrationRadioButton;
	private JRadioButton otherLiberalArtsRadioButton;
	// Professional
	private JRadioButton businessRadioButton;
	private JRadioButton healthcareRadioButton;
	private JRadioButton lawRadioButton;
	private JRadioButton educationRadioButton;
	private JRadioButton vocationalRadioButton;
	private JRadioButton agricultureRadioButton;
	private JRadioButton otherProfessionalRadioButton;
	// Creative Arts and Electives
	private JRadioButton visualArtsAndDesignRadioButton;
	private JRadioButton foreignLanguageRadioButton;
	private JRadioButton culinaryArtsRadioButton;
	private JRadioButton communicationRadioButton;
	private JRadioButton performingArtsRadioButton;
	private JRadioButton physicalEducationAndNutritionRadioButton;
	private JRadioButton otherCreativeArtsAndElectivesRadioButton;
	// Course type button group
	private ButtonGroup courseTypeButtonGroup;
	private JLabel courseTypeError;
	
	// Available classes retrieval path IV
	private JTextField availableClassesRetrievalPath;
	
	// Buttons IVs
	private JButton backButton;
	private JButton csvTemplateButton;
	private JButton saveButton;
	private JButton nextButton;
	private JButton buildPlannerButton;
	
	
	// Course number IVs
	private int currentCourseNum;
	private int totalCourses;
	
	
	
	
	
	
	
	// Constructor
	/**
	 * Purpose: To construct a CourseInfoView
	 */
	public CourseInfoView()
	{
	    setTitle("Course Info");
	    setSize(700, 800);
	    setLocationRelativeTo(null);

	    // Background Panel
	    BackgroundPanel backgroundPanel = new BackgroundPanel(
	            getClass().getResource("/images/Course Info.png")
	    );
	    backgroundPanel.setLayout(new BorderLayout());

	    // Main Panel
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    mainPanel.setOpaque(false);
	    mainPanel.setBorder(BorderFactory.createEmptyBorder(80, 20, 40, 30));

	    // Colors and Fonts
	    Color darkHotPink = new Color(170, 0, 85);
	    Font instructionsAndLabelsFont = new Font("Sans Serif", Font.PLAIN, 12);
	    Font buttonFont = new Font("Lucida Calligraphy", Font.PLAIN, 12);
	    Font errorFont = new Font("Sans Serif", Font.ITALIC, 8);

	    // Top spacing before major section
	    mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

	    // Top panel (includes course number)
	    JPanel panel1 = new JPanel();
	    panel1.setOpaque(false);
	    panel1.setAlignmentX(Component.LEFT_ALIGNMENT); 	

	    
	    courseNumberLabel = new JLabel("");
	    courseNumberLabel.setFont(buttonFont);
	    courseNumberLabel.setForeground(Color.BLACK);
	    panel1.add(Box.createHorizontalStrut(-50));
	    panel1.add(courseNumberLabel);
	    mainPanel.add(panel1);

	    
	    
	 // Second panel (includes course name panel and required course panel)
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
        panel2.setOpaque(false);
        
        // Keep the second panel aligned to the left side of the main panel
        panel2.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        
        // Course name section
        JPanel courseNamePanel = new JPanel();
        courseNamePanel.setOpaque(false);
        courseNamePanel.setLayout(new BoxLayout(courseNamePanel, BoxLayout.X_AXIS));
        
        
        JLabel courseNameLabel = new JLabel("Course Name:");
        courseNameLabel.setFont(instructionsAndLabelsFont);
        courseNameLabel.setForeground(darkHotPink);
        courseNameLabel.setOpaque(false);
        
        courseName = new JTextField(22);
        courseName.setMaximumSize(courseName.getPreferredSize());
        
        courseNameError = new JLabel("");
        courseNameError.setFont(errorFont);
        courseNameError.setForeground(Color.RED);
        courseNameError.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        courseNamePanel.add(courseNameLabel);
        courseNamePanel.add(Box.createRigidArea(new Dimension(2, 0)));
        courseNamePanel.add(courseName);
        
        JPanel courseHolderPanel = new JPanel();
        courseHolderPanel.setOpaque(false);
        courseHolderPanel.setLayout(new BoxLayout(courseHolderPanel, BoxLayout.Y_AXIS));
        courseHolderPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        courseHolderPanel.add(courseNamePanel);
        courseHolderPanel.add(Box.createVerticalStrut(2));
        
        
     // Push the course name error slightly to the right
        courseNameError.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        courseHolderPanel.add(courseNameError);
        
        // Required course section
        JPanel requiredCoursePanel = new JPanel();
        requiredCoursePanel.setOpaque(false);
        requiredCoursePanel.setLayout(new BoxLayout(requiredCoursePanel, BoxLayout.Y_AXIS));
        requiredCoursePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel requiredCourseLabel = new JLabel("Is this a required course?");
        requiredCourseLabel.setFont(instructionsAndLabelsFont);
        requiredCourseLabel.setForeground(darkHotPink);
        requiredCourseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        yesButton = new JRadioButton("Yes");
        yesButton.setFont(instructionsAndLabelsFont);
        yesButton.setForeground(darkHotPink);
        yesButton.setOpaque(false);
        
        noButton = new JRadioButton("No");
        noButton.setFont(instructionsAndLabelsFont);
        noButton.setForeground(darkHotPink);
        noButton.setOpaque(false);
        
        requiredButtonGroup = new ButtonGroup();
        requiredButtonGroup.add(yesButton);
        requiredButtonGroup.add(noButton);
        
        // Purpose: To center the radio buttons under the required course label
        JPanel requiredButtonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 0));
        requiredButtonsPanel.setOpaque(false);
        requiredButtonsPanel.add(yesButton);
        requiredButtonsPanel.add(noButton);
        
        requiredCourseError = new JLabel("");
        requiredCourseError.setFont(errorFont);
        requiredCourseError.setForeground(Color.RED);
        
        // Center the required course error under the buttons
        JPanel requiredCourseErrorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        requiredCourseErrorPanel.setOpaque(false);
        requiredCourseErrorPanel.add(requiredCourseError);
        
        requiredCoursePanel.add(requiredCourseLabel);
        requiredCoursePanel.add(Box.createVerticalStrut(2));
        requiredCoursePanel.add(requiredButtonsPanel);
        requiredCoursePanel.add(Box.createVerticalStrut(2));
        requiredCoursePanel.add(requiredCourseErrorPanel);
        
        // Add the course name section and required course section tightly next to each other
        panel2.add(Box.createRigidArea(new Dimension(130, 0)));
        panel2.add(courseHolderPanel);
        panel2.add(Box.createRigidArea(new Dimension(-90, 0)));
        panel2.add(requiredCoursePanel);
        
        // Keep panel2 left aligned inside the main panel
        JPanel panel2Wrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panel2Wrapper.setOpaque(false);
        panel2Wrapper.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel2Wrapper.add(panel2);
        
        mainPanel.add(panel2Wrapper);
	    
	    
	    
	    
	    
	    // Third panel (Course Type label + scroll box + error) 
	    JPanel panel3 = new JPanel();
	    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
	    panel3.setOpaque(false);
	    panel3.setAlignmentX(Component.LEFT_ALIGNMENT);

	    JPanel courseTypePanel = new JPanel();
	    courseTypePanel.setOpaque(false);
	    courseTypePanel.setLayout(new BoxLayout(courseTypePanel, BoxLayout.Y_AXIS));
	    courseTypePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

	    JLabel courseTypeLabel = new JLabel("Course Type:");
	    courseTypeLabel.setFont(instructionsAndLabelsFont);
	    courseTypeLabel.setForeground(darkHotPink);
	    courseTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
	    courseTypePanel.add(courseTypeLabel);
	    courseTypePanel.add(Box.createVerticalStrut(5));

	    JPanel ctRadioButtonsPanel = new JPanel();
	    ctRadioButtonsPanel.setLayout(new BoxLayout(ctRadioButtonsPanel, BoxLayout.Y_AXIS));
	    ctRadioButtonsPanel.setOpaque(false);
	    ctRadioButtonsPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

	    biologyRadioButton = new JRadioButton("Biology");
	    chemistryRadioButton = new JRadioButton("Chemistry");
	    physicsRadioButton = new JRadioButton("Physics");
	    environmentalScienceRadioButton = new JRadioButton("Environmental Science");
	    otherScienceRadioButton = new JRadioButton("Other Science");
	    computerScienceRadioButton = new JRadioButton("Computer Science");
	    informationTechnologyRadioButton = new JRadioButton("Information Technology");
	    computerInformationSystemsRadioButton = new JRadioButton("Computer Information Systems");
	    cybersecurityAndInfoAssuranceRadioButton = new JRadioButton("Cybersecurity and Information Assurance");
	    dataScienceAndAnalyticsRadioButton = new JRadioButton("Data Science and Analytics");
	    softwareEngineeringAndDevelopmentRadioButton = new JRadioButton("Software Engineering and Development");
	    computerEngineeringRadioButton = new JRadioButton("Computer Engineering");
	    otherTechRadioButton = new JRadioButton("Other Tech");
	    civilEngineeringRadioButton = new JRadioButton("Civil Engineering");
	    mechanicalEngineeringRadioButton = new JRadioButton("Mechanical Engineering");
	    electricalEngineeringRadioButton = new JRadioButton("Electrical Engineering");
	    chemicalEngineeringRadioButton = new JRadioButton("Chemical Engineering");
	    otherEngineeringRadioButton = new JRadioButton("Other Engineering");
	    coreMathRadioButton = new JRadioButton("Core Math");
	    pureMathRadioButton = new JRadioButton("Pure Math");
	    appliedMathRadioButton = new JRadioButton("Applied Math");
	    otherMathRadioButton = new JRadioButton("Other Math");
	    historyRadioButton = new JRadioButton("History");
	    literatureRadioButton = new JRadioButton("Literature");
	    philosophyRadioButton = new JRadioButton("Philosophy");
	    psychologyRadioButton = new JRadioButton("Psychology");
	    sociologyRadioButton = new JRadioButton("Sociology");
	    politicalScienceRadioButton = new JRadioButton("Political Science");
	    justiceAdministrationRadioButton = new JRadioButton("Justice Administration");
	    otherLiberalArtsRadioButton = new JRadioButton("Other Liberal Arts");
	    businessRadioButton = new JRadioButton("Business");
	    healthcareRadioButton = new JRadioButton("Healthcare");
	    lawRadioButton = new JRadioButton("Law");
	    educationRadioButton = new JRadioButton("Education");
	    vocationalRadioButton = new JRadioButton("Vocational");
	    agricultureRadioButton = new JRadioButton("Agriculture");
	    otherProfessionalRadioButton = new JRadioButton("Other Professional");
	    visualArtsAndDesignRadioButton = new JRadioButton("Visual Arts and Design");
	    foreignLanguageRadioButton = new JRadioButton("Foreign Language");
	    culinaryArtsRadioButton = new JRadioButton("Culinary Arts");
	    communicationRadioButton = new JRadioButton("Communication");
	    performingArtsRadioButton = new JRadioButton("Performing Arts");
	    physicalEducationAndNutritionRadioButton = new JRadioButton("Physical Education and Nutrition");
	    otherCreativeArtsAndElectivesRadioButton = new JRadioButton("Other Creative Arts and Electives");

	    courseTypeButtonGroup = new ButtonGroup();
	    courseTypeButtonGroup.add(biologyRadioButton);
	    courseTypeButtonGroup.add(chemistryRadioButton);
	    courseTypeButtonGroup.add(physicsRadioButton);
	    courseTypeButtonGroup.add(environmentalScienceRadioButton);
	    courseTypeButtonGroup.add(otherScienceRadioButton);
	    courseTypeButtonGroup.add(computerScienceRadioButton);
	    courseTypeButtonGroup.add(informationTechnologyRadioButton);
	    courseTypeButtonGroup.add(computerInformationSystemsRadioButton);
	    courseTypeButtonGroup.add(cybersecurityAndInfoAssuranceRadioButton);
	    courseTypeButtonGroup.add(dataScienceAndAnalyticsRadioButton);
	    courseTypeButtonGroup.add(softwareEngineeringAndDevelopmentRadioButton);
	    courseTypeButtonGroup.add(computerEngineeringRadioButton);
	    courseTypeButtonGroup.add(otherTechRadioButton);
	    courseTypeButtonGroup.add(civilEngineeringRadioButton);
	    courseTypeButtonGroup.add(mechanicalEngineeringRadioButton);
	    courseTypeButtonGroup.add(electricalEngineeringRadioButton);
	    courseTypeButtonGroup.add(chemicalEngineeringRadioButton);
	    courseTypeButtonGroup.add(otherEngineeringRadioButton);
	    courseTypeButtonGroup.add(coreMathRadioButton);
	    courseTypeButtonGroup.add(pureMathRadioButton);
	    courseTypeButtonGroup.add(appliedMathRadioButton);
	    courseTypeButtonGroup.add(otherMathRadioButton);
	    courseTypeButtonGroup.add(historyRadioButton);
	    courseTypeButtonGroup.add(literatureRadioButton);
	    courseTypeButtonGroup.add(philosophyRadioButton);
	    courseTypeButtonGroup.add(psychologyRadioButton);
	    courseTypeButtonGroup.add(sociologyRadioButton);
	    courseTypeButtonGroup.add(politicalScienceRadioButton);
	    courseTypeButtonGroup.add(justiceAdministrationRadioButton);
	    courseTypeButtonGroup.add(otherLiberalArtsRadioButton);
	    courseTypeButtonGroup.add(businessRadioButton);
	    courseTypeButtonGroup.add(healthcareRadioButton);
	    courseTypeButtonGroup.add(lawRadioButton);
	    courseTypeButtonGroup.add(educationRadioButton);
	    courseTypeButtonGroup.add(vocationalRadioButton);
	    courseTypeButtonGroup.add(agricultureRadioButton);
	    courseTypeButtonGroup.add(otherProfessionalRadioButton);
	    courseTypeButtonGroup.add(visualArtsAndDesignRadioButton);
	    courseTypeButtonGroup.add(foreignLanguageRadioButton);
	    courseTypeButtonGroup.add(culinaryArtsRadioButton);
	    courseTypeButtonGroup.add(communicationRadioButton);
	    courseTypeButtonGroup.add(performingArtsRadioButton);
	    courseTypeButtonGroup.add(physicalEducationAndNutritionRadioButton);
	    courseTypeButtonGroup.add(otherCreativeArtsAndElectivesRadioButton);

	    JRadioButton[] courseTypeRadioButtons = {
	            biologyRadioButton, chemistryRadioButton, physicsRadioButton,
	            environmentalScienceRadioButton, otherScienceRadioButton,
	            computerScienceRadioButton, informationTechnologyRadioButton,
	            computerInformationSystemsRadioButton,
	            cybersecurityAndInfoAssuranceRadioButton,
	            dataScienceAndAnalyticsRadioButton,
	            softwareEngineeringAndDevelopmentRadioButton,
	            computerEngineeringRadioButton, otherTechRadioButton,
	            civilEngineeringRadioButton, mechanicalEngineeringRadioButton,
	            electricalEngineeringRadioButton,
	            chemicalEngineeringRadioButton, otherEngineeringRadioButton,
	            coreMathRadioButton, pureMathRadioButton,
	            appliedMathRadioButton, otherMathRadioButton,
	            historyRadioButton, literatureRadioButton,
	            philosophyRadioButton, psychologyRadioButton,
	            sociologyRadioButton, politicalScienceRadioButton,
	            justiceAdministrationRadioButton, otherLiberalArtsRadioButton,
	            businessRadioButton, healthcareRadioButton, lawRadioButton,
	            educationRadioButton, vocationalRadioButton,
	            agricultureRadioButton, otherProfessionalRadioButton,
	            visualArtsAndDesignRadioButton, foreignLanguageRadioButton,
	            culinaryArtsRadioButton, communicationRadioButton,
	            performingArtsRadioButton,
	            physicalEducationAndNutritionRadioButton,
	            otherCreativeArtsAndElectivesRadioButton
	    };

	    for (JRadioButton radioButton : courseTypeRadioButtons)
	    {
	        radioButton.setOpaque(false);
	        radioButton.setFont(instructionsAndLabelsFont);
	        radioButton.setForeground(darkHotPink);
	        ctRadioButtonsPanel.add(radioButton);
	    }

	    // Scroll pane for course type panel
	    JScrollPane scrollPane = new JScrollPane(ctRadioButtonsPanel);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setOpaque(false);
	    scrollPane.getViewport().setOpaque(false);
	    scrollPane.setBorder(BorderFactory.createLineBorder(darkHotPink, 2));
	    scrollPane.setPreferredSize(new Dimension(320, 270));
	    scrollPane.setMaximumSize(new Dimension(320, 270));
	    scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

	    courseTypePanel.add(scrollPane);
	    panel3.add(courseTypePanel);

	    courseTypeError = new JLabel("");
	    courseTypeError.setFont(errorFont);
	    courseTypeError.setForeground(Color.RED);

	    JPanel courseTypeErrorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
	    courseTypeErrorPanel.setOpaque(false);
	    courseTypeErrorPanel.add(courseTypeError);
	    panel3.add(Box.createVerticalStrut(2));
	    panel3.add(courseTypeErrorPanel);

	    // Add panel3 centered (no left-offset wrapper)
	    mainPanel.add(Box.createVerticalStrut(10));
	    mainPanel.add(panel3);

	    // Fourth panel (includes available classes retrieval path)
	    JPanel panel4 = new JPanel();
	    panel4.setLayout(new BoxLayout(panel4, BoxLayout.Y_AXIS));
	    panel4.setOpaque(false);
	    panel4.setAlignmentX(Component.LEFT_ALIGNMENT);

	    JLabel sharePathInfoLabel = new JLabel(
	            "<html><div style='width: 270px;'>"
	                    + "Input the CSV file path containing this course's available classes "
	                    + "(Ex. C:\\Users\\NateNiedbalski\\Desktop\\Example File.csv). "
	                    + "Make sure the file is formatted like the template. Click \"CSV Template\" "
	                    + " to download it to the path you saved in wishlist. "
	                    + "</div></html>"
	    );

	    sharePathInfoLabel.setFont(instructionsAndLabelsFont);
	    sharePathInfoLabel.setForeground(darkHotPink);
	    sharePathInfoLabel.setOpaque(false);

	    JPanel shareInstructionsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
	    shareInstructionsWrapper.setOpaque(false);
	    shareInstructionsWrapper.add(sharePathInfoLabel);

	    panel4.add(Box.createVerticalStrut(10));
	    panel4.add(shareInstructionsWrapper);

	    shareInstructionsWrapper.setMaximumSize(shareInstructionsWrapper.getPreferredSize());

	    JPanel sharePathInfoPanel = new JPanel();
	    sharePathInfoPanel.setLayout(new BoxLayout(sharePathInfoPanel, BoxLayout.X_AXIS));
	    sharePathInfoPanel.setOpaque(false);

	    availableClassesRetrievalPath = new JTextField(10);
	    availableClassesRetrievalPath.setText("");
	    Dimension availableClassesPathSize = new Dimension(350, 18);
	    availableClassesRetrievalPath.setMaximumSize(availableClassesPathSize);
	    availableClassesRetrievalPath.setPreferredSize(availableClassesPathSize);

	    sharePathInfoPanel.add(availableClassesRetrievalPath);
	    panel4.add(Box.createVerticalStrut(5));
	    panel4.add(sharePathInfoPanel);
	    panel4.add(Box.createVerticalStrut(10));

	    mainPanel.add(panel4);

	    // Bottom panel (includes buttons)
	    JPanel panel5 = new JPanel();
	    panel5.setOpaque(false);
	    panel5.setAlignmentX(Component.LEFT_ALIGNMENT);

	    backButton = new JButton("Back");
	    backButton.setFont(buttonFont);
	    backButton.setForeground(Color.WHITE);
	    backButton.setBackground(darkHotPink);

	    csvTemplateButton = new JButton("CSV Template");
	    csvTemplateButton.setFont(buttonFont);
	    csvTemplateButton.setForeground(Color.WHITE);
	    csvTemplateButton.setBackground(darkHotPink);

	    saveButton = new JButton("Save");
	    saveButton.setFont(buttonFont);
	    saveButton.setForeground(Color.WHITE);
	    saveButton.setBackground(darkHotPink);

	    nextButton = new JButton("Next");
	    nextButton.setFont(buttonFont);
	    nextButton.setForeground(Color.WHITE);
	    nextButton.setBackground(darkHotPink);

	    buildPlannerButton = new JButton("Build Planner");
	    buildPlannerButton.setFont(buttonFont);
	    buildPlannerButton.setForeground(Color.WHITE);
	    buildPlannerButton.setBackground(darkHotPink);
	    buildPlannerButton.setVisible(false);

	    panel5.add(backButton);
	    panel5.add(csvTemplateButton);
	    panel5.add(Box.createHorizontalStrut(45));
	    panel5.add(saveButton);
	    panel5.add(nextButton);
	    panel5.add(buildPlannerButton);

	    mainPanel.add(panel5);

	    // Lock error label sizes so panels don't resize when text changes
	    lockErrorLabelSize(courseNameError, 170, 16);
	    requiredCourseError.setPreferredSize(new Dimension(145, 16)); 
	    lockErrorLabelSize(courseTypeError, 260, 16);

	    backgroundPanel.add(mainPanel, BorderLayout.CENTER);
	    setContentPane(backgroundPanel);

	    setVisible(false);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    /**
     * Purpose: To prevent the error labels from resizing when the error messages are updated, this method locks the size of the given error label to the given width and height
     * @param lbl The error label to lock the size of
     * @param width The width to lock the error label to
     * @param height The height to lock the error label to
     */
	private void lockErrorLabelSize(JLabel lbl, int width, int height)
	{
		Dimension d = new Dimension(width, height);
		lbl.setPreferredSize(d);
		lbl.setMinimumSize(d);
		lbl.setMaximumSize(d);
	}

	
	
	
	
	
	
	
	
	
	
	/**
	 * Purpose: To return the app controller
	 * @return appController The app controller
	 */
	public AppController getAppController()
	{
		return appController;
	}
	
	/**
	 * Purpose: To set the app controller to the given value
	 * @param newAppController The new app controller
	 */
	public void setAppController(AppController newAppController)
	{
		appController = newAppController;
	}

	
	
	/**
	 * Purpose: To set the current course number and total courses for the course info view to the given values and update the course number label to reflect the new current course number and total courses
	 * 
	 */
	public void setCourseNumLabel(int newCurrentCourseNum)
	{
		courseNumberLabel.setText("Course " + newCurrentCourseNum + " of " + totalCourses);
	}
	
	/**
	 * Purpose: To set the total number of courses the student will input
	 * @param newTotalCourses The new numer of total courses the student will input
	 */
	public void setTotalCourses(int newTotalCourses)
	{
		totalCourses = newTotalCourses;
	}
	
	
	/**
	 * Purpose: To return the course name that the student will input in the course info view
	 * @return courseName The course name that the student will input in the course info view
	 */
	public String getCourseName()
	{
		return courseName.getText();
	}
	
	/**
	 * Purpose: To return the value of if the course is required or not that the student will select
	 * as a RadioButton in the course info view
	 */
	public Boolean getStudentRequiredCourse()
	{
	    if (yesButton.isSelected()) 
	    {
	        return Boolean.TRUE;
	    } 
	    else if (noButton.isSelected()) 
	    {
	        return Boolean.FALSE;
	    } 
	    else 
	    {
	        return null;
	    }
	}
	
	/**
	 * Purpose: To return the course type that the student will input in the course info view as a RadioButton
	 * @return courseType The course type that the student will input in the course info view as a RadioButton
	 */
	public String getCourseType()
	{
		if (biologyRadioButton.isSelected()) 
		{
			return "Biology";
		} 
		else if (chemistryRadioButton.isSelected()) 
		{
			return "Chemistry";
		} 
		else if (physicsRadioButton.isSelected()) 
		{
			return "Physics";
		} 
		else if (environmentalScienceRadioButton.isSelected()) 
		{
			return "Environmental Science";
		} 
		else if (otherScienceRadioButton.isSelected()) 
		{
			return "Other Science";
		} 
		else if (computerScienceRadioButton.isSelected()) 
		{
			return "Computer Science";
		} 
		else if (informationTechnologyRadioButton.isSelected()) 
		{
			return "Information Technology";
		} 
		else if (computerInformationSystemsRadioButton.isSelected()) 
		{
			return "Computer Information Systems";
		} 
		else if (cybersecurityAndInfoAssuranceRadioButton.isSelected()) 
		{
			return "Cybersecurity and Information Assurance";
		} 
		else if (dataScienceAndAnalyticsRadioButton.isSelected()) 
		{
			return "Data Science and Analytics";
		} 
		else if (softwareEngineeringAndDevelopmentRadioButton.isSelected()) 
		{
			return "Software Engineering and Development";
		} 
		else if (computerEngineeringRadioButton.isSelected()) 
		{
			return "Computer Engineering";
	    } 
	    else if (otherTechRadioButton.isSelected()) 
	    {
	        return "Other Tech";
	    } 
	    else if (civilEngineeringRadioButton.isSelected()) 
	    {
	        return "Civil Engineering";
	    } 
	    else if (mechanicalEngineeringRadioButton.isSelected()) 
	    {
	        return "Mechanical Engineering";
	    } 
	    else if (electricalEngineeringRadioButton.isSelected()) 
	    {
	        return "Electrical Engineering";
	    } 
	    else if (chemicalEngineeringRadioButton.isSelected()) 
	    {
	        return "Chemical Engineering";
	    } 
	    else if (otherEngineeringRadioButton.isSelected()) 
	    {
	        return "Other Engineering";
	    } 
	    else if (coreMathRadioButton.isSelected()) 
	    {
	        return "Core Math";
	    } 
	    else if (pureMathRadioButton.isSelected()) 
	    {
	        return "Pure Math";
	    }
	    else if(appliedMathRadioButton.isSelected()) 
	    {
	        return "Applied Math";
	    } 
	    else if (otherMathRadioButton.isSelected()) 
	    {
	        return "Other Math";
	    } 
	    else if (historyRadioButton.isSelected()) 
	    {
	        return "History";
	    } 
	    else if (literatureRadioButton.isSelected()) 
	    {
	        return "Literature";
	    } 
	    else if (philosophyRadioButton.isSelected()) 
	    {
	        return "Philosophy";
	    } 
	    else if (psychologyRadioButton.isSelected()) 
	    {
	        return "Psychology";
	    } 
	    else if (sociologyRadioButton.isSelected()) 
	    {
	        return "Sociology";
	    } 
	    else if (politicalScienceRadioButton.isSelected()) 
	    {
	        return "Political Science";
	    } 
	    else if (justiceAdministrationRadioButton.isSelected()) 
	    {
	        return "Justice Administration";
	    } 
	    else if (otherLiberalArtsRadioButton.isSelected()) 
	    {
	    	return "Other Liberal Arts";
	    }
	    else if (businessRadioButton.isSelected()) 
	    {
	        return "Business";
	    } 
	    else if (healthcareRadioButton.isSelected()) 
	    {
	        return "Healthcare";
	    } 
	    else if (lawRadioButton.isSelected()) 
	    {
	        return "Law";
	    } 
	    else if (educationRadioButton.isSelected()) 
	    {
	        return "Education";
	    } 
	    else if (vocationalRadioButton.isSelected()) 
	    {
	        return "Vocational";
	    } 
	    else if (agricultureRadioButton.isSelected()) 
	    {
	        return "Agriculture";
	    } 
	    else if (otherProfessionalRadioButton.isSelected()) 
	    {
	        return "Other Professional";
	    } 
	    else if (visualArtsAndDesignRadioButton.isSelected()) 
	    {
	        return "Visual Arts and Design";
	    } 
	    else if (foreignLanguageRadioButton.isSelected()) 
	    {
	        return "Foreign Language";
	    } 
	    else if (culinaryArtsRadioButton.isSelected()) 
	    {
	        return "Culinary Arts";
	    } 
	    else if (communicationRadioButton.isSelected()) 
	    {
	        return "Communication";
	    } 
	    else if (performingArtsRadioButton.isSelected()) 
	    {
	        return "Performing Arts";
	    } 
	    else if (physicalEducationAndNutritionRadioButton.isSelected()) 
	    {
	        return "Physical Education and Nutrition";
	    } 
	    else if (otherCreativeArtsAndElectivesRadioButton.isSelected()) 
	    {
	    	return "Other Creative Arts and Electives";
	    }
	    else 
	    {
	        return null;
	    }
	}
	
	
	/**
	 * Purpose: To return the available classes retrieval path that the student will input in the course info view
	 * @return availableClassesRetrievalPath The available classes retrieval path that the student will input in the course info view
	 */
	public Path getAvailableClassesRetrievalPath()
	{
	    String pathText = availableClassesRetrievalPath.getText();

	    // Remove leading and trailing spaces
	    pathText = pathText.trim();

	    // Check if empty after trimming
	    if (pathText.isEmpty())
	    {
	        return null;
	    }

	    // Remove quotation marks if user entered them
	    pathText = pathText.replace("\"", "");

	    return Path.of(pathText);
	}
	
	
	/**
	 * Purpose: To reset the fields in the course info view to their default values
	 */
	public void resetFields()
	{
		// Reset button groups
		requiredButtonGroup.clearSelection();
		courseTypeButtonGroup.clearSelection();
		
		// Reset text fields
		courseName.setText("");
		availableClassesRetrievalPath.setText("");
		
		// Clear error labels
		courseNameError.setText("");
		requiredCourseError.setText("");
		courseTypeError.setText("");
	}

	/**
	 * Purpose: To set the course name error message in the course info view to the given value
	 * @param errorMessage The error message to set for the course name error message in the course info view
	 */
	public void setCourseNameError(String errorMessage)
	{
		courseNameError.setText(errorMessage);
	}

	/**
	 * Purpose: To set the required course error message in the course info view to the given value
	 * @param errorMessage The error message to set for the required course error message in the course info view
	 */
	public void setStudentRequiredCourseError(String errorMessage)
	{
		requiredCourseError.setText(errorMessage);
	}


	/**
	 * Purpose: To set the course type error message in the course info view to the given value
	 * @param errorMessage The error message to set for the course type error message in the course info view
	 */
	public void setCourseTypeError(String errorMessage)
	{
		courseTypeError.setText(errorMessage);
	}


	/**
	 * Purpose: To return the back button in the course info view
	 * @return backButton The back button
	 */
	public JButton getBackButton()
	{
		return backButton;
	}


	/**
	 * Purpose: To return the save button in the course info view
	 * @return saveButton The save button
	 */
	public JButton getSaveButton()
	{
		return saveButton;
	}


	/**
	 * Purpose: To return the next button in the course info view
	 * @return nextButton The continue button
	 */
	public JButton getNextButton()
	{
		return nextButton;
	}


	/**
	 * Purpose: To return the schedule builder button in the course info view
	 * @return buildPlannerButton The schedule builder button
	 */
	public JButton getBuildPlannerButton()
	{
		return buildPlannerButton;
	}



	/**
	 * Purpose: To return the CSV template button in the course info view
	 * @return csvTemplateButton The CSV template button
	 */
	public JButton getCSVButton()
	{
		return csvTemplateButton;
	}
	

	

}
