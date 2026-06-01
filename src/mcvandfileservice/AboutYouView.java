package mcvandfileservice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

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
 *         Responsibilities of class: To represent the about you view for the
 *         course recommendation system, which includes all about you input
 *         fields and buttons, as well as methods for getting about you input
 *         and setting about you error messages
 * 
 *         AboutYouView is-a JFrame
 */

public class AboutYouView extends JFrame
{
	// Instance Variables
	private AppController appController; // An about you view has-an app
											// controller

	// Major Distinction IVs
	private JRadioButton theSTEMButton; // An about you view has-a STEM radio
										// button
	private JRadioButton theLiberalArtsButton; // An about you view has-a
												// liberal arts radio button
	private JRadioButton theUndecidedButton; // An about you view has-an
												// undecided radio button
	private ButtonGroup majorButtonGroup; // An about you view has-a button
											// group for the major distinction
											// radio buttons

	// Struggle with Courses IVs
	// Science
	private JCheckBox biologyCheckbox; // An about you view has-a biology
										// checkbox
	private JCheckBox chemistryCheckbox; // An about you view has-a chemistry
											// checkbox
	private JCheckBox physicsCheckbox; // An about you view has-a physics
										// checkbox
	private JCheckBox environmentalScienceCheckbox; // An about you view has-an
													// environmental science
													// checkbox
	private JCheckBox otherScienceCheckbox; // An about you view has-an other
											// science checkbox
	// Tech
	private JCheckBox computerScienceCheckbox; // An about you view has-a
												// computer science checkbox
	private JCheckBox informationTechnologyCheckbox; // An about you view has-an
														// information
														// technology checkbox
	private JCheckBox computerInformationSystemsCheckbox; // An about you view
															// has-a computer
															// information
															// systems checkbox
	private JCheckBox cybersecurityAndInfoAssuranceCheckbox; // An about you
																// view has-a
																// cybersecurity
																// and
																// information
																// assurance
																// checkbox
	private JCheckBox dataScienceAndAnalyticsCheckbox; // An about you view
														// has-a data science
														// and analytics
														// checkbox
	private JCheckBox softwareEngineeringAndDevelopmentCheckbox; // An about you
																	// view
																	// has-a
																	// software
																	// engineering
																	// and
																	// development
																	// checkbox
	private JCheckBox computerEngineeringCheckbox; // An about you view has-a
													// computer engineering
													// checkbox
	private JCheckBox otherTechCheckbox; // An about you view has-an other tech
											// checkbox
	// Engineering
	private JCheckBox civilEngineeringCheckbox; // An about you view has-a civil
												// engineering checkbox
	private JCheckBox mechanicalEngineeringCheckbox; // An about you view has-a
														// mechanical
														// engineering checkbox
	private JCheckBox electricalEngineeringCheckbox; // An about you view has-an
														// electrical
														// engineering checkbox
	private JCheckBox chemicalEngineeringCheckbox; // An about you view has-a
													// chemical engineering
													// checkbox
	private JCheckBox otherEngineeringCheckbox; // An about you view has-an
												// other engineering checkbox
	// Math
	private JCheckBox coreMathCheckbox; // An about you view has-a core math
										// checkbox
	private JCheckBox pureMathCheckbox; // An about you view has-a pure math
										// checkbox
	private JCheckBox appliedMathCheckbox; // An about you view has-an applied
											// math checkbox
	private JCheckBox otherMathCheckbox; // An about you view has-an other math
											// checkbox
	// Liberal Arts
	private JCheckBox historyCheckbox; // An about you view has-a history
										// checkbox
	private JCheckBox literatureCheckbox; // An about you view has-a literature
											// checkbox
	private JCheckBox philosophyCheckbox; // An about you view has-a philosophy
											// checkbox
	private JCheckBox psychologyCheckbox; // An about you view has-a psychology
											// checkbox
	private JCheckBox sociologyCheckbox; // An about you view has-a sociology
											// checkbox
	private JCheckBox politicalScienceCheckbox; // An about you view has-a
												// political science checkbox
	private JCheckBox justiceAdministrationCheckbox; // An about you view has-a
														// justice
														// administration
														// checkbox
	private JCheckBox otherLiberalArtsCheckbox; // An about you view has-an
												// other liberal arts checkbox
	// Professional
	private JCheckBox businessCheckbox; // An about you view has-a business
										// checkbox
	private JCheckBox healthcareCheckbox; // An about you view has-a healthcare
											// checkbox
	private JCheckBox lawCheckbox; // An about you view has-a law checkbox
	private JCheckBox educationCheckbox; // An about you view has-an education
											// checkbox
	private JCheckBox vocationalCheckbox; // An about you view has-a vocational
											// checkbox
	private JCheckBox agricultureCheckbox; // An about you view has-an
											// agriculture checkbox
	private JCheckBox otherProfessionalCheckbox; // An about you view has-an
													// other professional
													// checkbox
	// Creative Arts and Electives
	private JCheckBox visualArtsAndDesignCheckbox; // An about you view has-a
													// visual arts and design
													// checkbox
	private JCheckBox foreignLanguageCheckbox; // An about you view has-a
												// foreign language checkbox
	private JCheckBox culinaryArtsCheckbox; // An about you view has-a culinary
											// arts checkbox
	private JCheckBox communicationCheckbox; // An about you view has-a
												// communication checkbox
	private JCheckBox performingArtsCheckbox; // An about you view has-a
												// performing arts checkbox
	private JCheckBox physicalEducationAndNutritionCheckbox; // An about you
																// view has-a
																// physical
																// education and
																// nutrition
																// checkbox
	private JCheckBox otherCreativeArtsAndElectivesCheckbox; // An about you
																// view has-an
																// other
																// creative arts
																// and electives
																// checkbox

	// Num Courses IV
	private JTextField numCourses; // An about you view has-a text field for the
									// number of courses the user is interested
									// in

	// About You Errors IVs
	private JLabel majorError; // An about you view has-a label for errors
								// related to the major distinction input
	private JLabel numCoursesError; // An about you view has-a label for errors
									// related to the number of courses input

	// Buttons IVs
	private JButton backButton; // An about you view has-a back button
	private JButton saveButton; // An about you view has-a save button
	private JButton continueButton; // An about you view has-a continue button

	// Constructor
	/**
	 * Purpose: To construct an AboutYouView
	 */
	public AboutYouView()
	{
		setTitle("About You");
		setSize(700, 800);
		setLocationRelativeTo(null);

		// Background Panel
		BackgroundPanel backgroundPanel = new BackgroundPanel(
				getClass().getResource("/images/About You.png"));
		backgroundPanel.setLayout(new BorderLayout());

		// Main Panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setOpaque(false);
		mainPanel.setBorder(BorderFactory.createEmptyBorder(60, 30, 40, 30));

		// Colors and Fonts
		Color darkHotPink = new Color(170, 0, 85);
		Font instructionsAndLabelsFont = new Font("Sans Serif", Font.PLAIN, 12);
		Font buttonFont = new Font("Lucida Calligraphy", Font.PLAIN, 12);
		Font errorFont = new Font("Sans Serif", Font.ITALIC, 8);

		// Top Panel (includes major)
		JPanel panel1 = new JPanel();
		panel1.add(Box.createRigidArea(new Dimension(0, 40)));
		panel1.setOpaque(false);

		panel1.add(Box.createHorizontalStrut(100));

		JPanel majorPanel = new JPanel();
		majorPanel.setOpaque(false);
		majorPanel.setLayout(new BoxLayout(majorPanel, BoxLayout.Y_AXIS));
		JLabel majorLabel = new JLabel("What is your major distinction?");
		majorLabel.setFont(instructionsAndLabelsFont);
		majorLabel.setForeground(darkHotPink);
		majorPanel.add(majorLabel);

		JPanel majorRadioButtonsPanel = new JPanel();
		majorRadioButtonsPanel.setOpaque(false);
		majorRadioButtonsPanel.setLayout(
				new BoxLayout(majorRadioButtonsPanel, BoxLayout.Y_AXIS));

		theSTEMButton = new JRadioButton("STEM");
		theLiberalArtsButton = new JRadioButton("Liberal Arts");
		theUndecidedButton = new JRadioButton("Undecided");

		majorButtonGroup = new ButtonGroup();
		majorButtonGroup.add(theSTEMButton);
		majorButtonGroup.add(theLiberalArtsButton);
		majorButtonGroup.add(theUndecidedButton);

		JRadioButton[] majors = { theSTEMButton, theLiberalArtsButton,
				theUndecidedButton };
		for (JRadioButton rb : majors)
		{
			rb.setOpaque(false);
			rb.setFont(instructionsAndLabelsFont);
			rb.setForeground(darkHotPink);
			majorRadioButtonsPanel.add(rb);
		}

		majorPanel.add(majorRadioButtonsPanel);

		JPanel majorErrorPanel = new JPanel();
		majorErrorPanel.setOpaque(false);
		majorErrorPanel.setPreferredSize(new Dimension(300, 18));
		majorErrorPanel.setMaximumSize(new Dimension(180, 18));

		majorError = new JLabel(" ");
		majorError.setFont(errorFont);
		majorError.setForeground(Color.RED);

		majorError.setPreferredSize(new Dimension(180, 14));
		majorError.setMaximumSize(new Dimension(180, 14));

		majorErrorPanel.add(majorError);

		panel1.add(Box.createHorizontalStrut(5));

		JPanel innerPanel1 = new JPanel();
		innerPanel1.setOpaque(false);
		innerPanel1.setLayout(new BoxLayout(innerPanel1, BoxLayout.X_AXIS));

		innerPanel1.add(Box.createHorizontalStrut(100));
		innerPanel1.add(majorPanel);
		innerPanel1.add(Box.createHorizontalStrut(5));
		innerPanel1.add(majorErrorPanel);

		panel1.add(innerPanel1);

		mainPanel.add(panel1);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Struggle Panel
		JPanel strugglePanel = new JPanel();
		strugglePanel.setOpaque(false);
		strugglePanel.setLayout(new BoxLayout(strugglePanel, BoxLayout.Y_AXIS));

		JLabel struggleLabel = new JLabel(
				"Select any courses you struggle with below:");
		struggleLabel.setFont(instructionsAndLabelsFont);
		struggleLabel.setForeground(darkHotPink);
		struggleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		strugglePanel.add(struggleLabel);
		strugglePanel.add(Box.createRigidArea(new Dimension(0, 5)));

		// Checkboxes Panel
		JPanel checkboxesPanel = new JPanel();
		checkboxesPanel.setOpaque(false);
		checkboxesPanel
				.setLayout(new BoxLayout(checkboxesPanel, BoxLayout.Y_AXIS));
		checkboxesPanel
				.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));

		biologyCheckbox = new JCheckBox("Biology");
		chemistryCheckbox = new JCheckBox("Chemistry");
		physicsCheckbox = new JCheckBox("Physics");
		environmentalScienceCheckbox = new JCheckBox("Environmental Science");
		otherScienceCheckbox = new JCheckBox("Other Science");
		computerScienceCheckbox = new JCheckBox("Computer Science");
		informationTechnologyCheckbox = new JCheckBox("Information Technology");
		computerInformationSystemsCheckbox = new JCheckBox(
				"Computer Information Systems");
		cybersecurityAndInfoAssuranceCheckbox = new JCheckBox(
				"Cybersecurity and Information Assurance");
		dataScienceAndAnalyticsCheckbox = new JCheckBox(
				"Data Science and Analytics");
		softwareEngineeringAndDevelopmentCheckbox = new JCheckBox(
				"Software Engineering and Development");
		computerEngineeringCheckbox = new JCheckBox("Computer Engineering");
		otherTechCheckbox = new JCheckBox("Other Tech");
		civilEngineeringCheckbox = new JCheckBox("Civil Engineering");
		mechanicalEngineeringCheckbox = new JCheckBox("Mechanical Engineering");
		electricalEngineeringCheckbox = new JCheckBox("Electrical Engineering");
		chemicalEngineeringCheckbox = new JCheckBox("Chemical Engineering");
		otherEngineeringCheckbox = new JCheckBox("Other Engineering");
		coreMathCheckbox = new JCheckBox("Core Math");
		pureMathCheckbox = new JCheckBox("Pure Math");
		appliedMathCheckbox = new JCheckBox("Applied Math");
		otherMathCheckbox = new JCheckBox("Other Math");
		historyCheckbox = new JCheckBox("History");
		literatureCheckbox = new JCheckBox("Literature");
		philosophyCheckbox = new JCheckBox("Philosophy");
		psychologyCheckbox = new JCheckBox("Psychology");
		sociologyCheckbox = new JCheckBox("Sociology");
		politicalScienceCheckbox = new JCheckBox("Political Science");
		justiceAdministrationCheckbox = new JCheckBox("Justice Administration");
		otherLiberalArtsCheckbox = new JCheckBox("Other Liberal Arts Courses");
		businessCheckbox = new JCheckBox("Business");
		healthcareCheckbox = new JCheckBox("Healthcare");
		lawCheckbox = new JCheckBox("Law");
		educationCheckbox = new JCheckBox("Education");
		vocationalCheckbox = new JCheckBox("Vocational");
		agricultureCheckbox = new JCheckBox("Agriculture");
		otherProfessionalCheckbox = new JCheckBox("Other Professional Courses");
		visualArtsAndDesignCheckbox = new JCheckBox("Visual Arts and Design");
		foreignLanguageCheckbox = new JCheckBox("Foreign Language");
		culinaryArtsCheckbox = new JCheckBox("Culinary Arts");
		communicationCheckbox = new JCheckBox("Communication");
		performingArtsCheckbox = new JCheckBox("Performing Arts");
		physicalEducationAndNutritionCheckbox = new JCheckBox(
				"Physical Education and Nutrition");
		otherCreativeArtsAndElectivesCheckbox = new JCheckBox(
				"Other Creative Arts and Electives");

		JCheckBox[] allCheckboxes = { biologyCheckbox, chemistryCheckbox,
				physicsCheckbox, environmentalScienceCheckbox,
				otherScienceCheckbox, computerScienceCheckbox,
				informationTechnologyCheckbox,
				computerInformationSystemsCheckbox,
				cybersecurityAndInfoAssuranceCheckbox,
				dataScienceAndAnalyticsCheckbox,
				softwareEngineeringAndDevelopmentCheckbox,
				computerEngineeringCheckbox, otherTechCheckbox,
				civilEngineeringCheckbox, mechanicalEngineeringCheckbox,
				electricalEngineeringCheckbox, chemicalEngineeringCheckbox,
				otherEngineeringCheckbox, coreMathCheckbox, pureMathCheckbox,
				appliedMathCheckbox, otherMathCheckbox, historyCheckbox,
				literatureCheckbox, philosophyCheckbox, psychologyCheckbox,
				sociologyCheckbox, politicalScienceCheckbox,
				justiceAdministrationCheckbox, otherLiberalArtsCheckbox,
				businessCheckbox, healthcareCheckbox, lawCheckbox,
				educationCheckbox, vocationalCheckbox, agricultureCheckbox,
				otherProfessionalCheckbox, visualArtsAndDesignCheckbox,
				foreignLanguageCheckbox, culinaryArtsCheckbox,
				communicationCheckbox, performingArtsCheckbox,
				physicalEducationAndNutritionCheckbox,
				otherCreativeArtsAndElectivesCheckbox };

		for (JCheckBox cb : allCheckboxes)
		{
			cb.setOpaque(false);
			cb.setFont(instructionsAndLabelsFont);
			cb.setForeground(darkHotPink);
			checkboxesPanel.add(cb);
		}

		// Scroll Pane for Checkboxes
		JScrollPane scrollPane = new JScrollPane(checkboxesPanel);
		scrollPane.setVerticalScrollBarPolicy(
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createLineBorder(darkHotPink, 2));
		scrollPane.setPreferredSize(new Dimension(300, 280));
		scrollPane.setMaximumSize(new Dimension(300, 280));
		scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

		strugglePanel.add(scrollPane);

		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.add(strugglePanel);

		mainPanel.add(panel2);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Third panel (includes numCourses)
		JPanel panel3 = new JPanel();
		panel3.setOpaque(false);
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.X_AXIS));
		panel3.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel3.add(Box.createHorizontalStrut(50));

		JLabel numCoursesInstructionLabel = new JLabel(
				"How many courses are you interested in?");
		numCoursesInstructionLabel.setFont(instructionsAndLabelsFont);
		numCoursesInstructionLabel.setForeground(darkHotPink);
		panel3.add(numCoursesInstructionLabel);

		panel3.add(Box.createRigidArea(new Dimension(10, 0)));

		numCourses = new JTextField(5);
		numCourses.setText("4");
		numCourses.setPreferredSize(new Dimension(45, 25));
		numCourses.setMaximumSize(new Dimension(45, 25));
		panel3.add(numCourses);
		panel3.add(Box.createHorizontalStrut(5));

		JPanel numCoursesErrorPanel = new JPanel();
		numCoursesErrorPanel.setOpaque(false);
		numCoursesErrorPanel.setPreferredSize(new Dimension(200, 18));
		numCoursesErrorPanel.setMaximumSize(new Dimension(150, 18));

		numCoursesError = new JLabel(" ");
		numCoursesError.setFont(errorFont);
		numCoursesError.setForeground(Color.RED);
		numCoursesError.setPreferredSize(new Dimension(150, 14));
		numCoursesError.setMaximumSize(new Dimension(150, 14));

		numCoursesErrorPanel.add(numCoursesError);
		panel3.add(numCoursesErrorPanel);

		mainPanel.add(panel3);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));

		// Fourth panel (includes buttons)
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setOpaque(false);
		buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

		backButton = new JButton("Back");
		backButton.setFont(buttonFont);
		backButton.setForeground(Color.WHITE);
		backButton.setBackground(darkHotPink);

		saveButton = new JButton("Save");
		saveButton.setFont(buttonFont);
		saveButton.setForeground(Color.WHITE);
		saveButton.setBackground(darkHotPink);

		continueButton = new JButton("Continue");
		continueButton.setFont(buttonFont);
		continueButton.setForeground(Color.WHITE);
		continueButton.setBackground(darkHotPink);

		buttonsPanel.add(backButton);
		buttonsPanel.add(Box.createHorizontalStrut(160));
		buttonsPanel.add(saveButton);
		buttonsPanel.add(Box.createHorizontalStrut(5));
		buttonsPanel.add(continueButton);

		mainPanel.add(buttonsPanel);

		backgroundPanel.add(mainPanel, BorderLayout.CENTER);
		setContentPane(backgroundPanel);

		setVisible(false);
	}

	/**
	 * Purpose: To return the app controller
	 * 
	 * @return appController The app controller
	 */
	public AppController getAppController()
	{
		return appController;
	}

	/**
	 * Purpose: To set the app controller to the given value
	 * 
	 * @param newAppController The new app controller
	 */
	public void setAppController(AppController newAppController)
	{
		appController = newAppController;
	}

	/**
	 * Purpose: To return the back button
	 * 
	 * @return backButton The back button
	 */
	public JButton getBackButton()
	{
		return backButton;
	}

	/**
	 * Purpose: To reset all about you input fields to default values
	 */
	public void resetFields()
	{
		// Reset radio buttons
		majorButtonGroup.clearSelection();

		// Reset checkboxes
		biologyCheckbox.setSelected(false);
		chemistryCheckbox.setSelected(false);
		physicsCheckbox.setSelected(false);
		environmentalScienceCheckbox.setSelected(false);
		otherScienceCheckbox.setSelected(false);
		computerScienceCheckbox.setSelected(false);
		informationTechnologyCheckbox.setSelected(false);
		computerInformationSystemsCheckbox.setSelected(false);
		cybersecurityAndInfoAssuranceCheckbox.setSelected(false);
		dataScienceAndAnalyticsCheckbox.setSelected(false);
		softwareEngineeringAndDevelopmentCheckbox.setSelected(false);
		computerEngineeringCheckbox.setSelected(false);
		otherTechCheckbox.setSelected(false);
		civilEngineeringCheckbox.setSelected(false);
		mechanicalEngineeringCheckbox.setSelected(false);
		electricalEngineeringCheckbox.setSelected(false);
		chemicalEngineeringCheckbox.setSelected(false);
		otherEngineeringCheckbox.setSelected(false);
		coreMathCheckbox.setSelected(false);
		pureMathCheckbox.setSelected(false);
		appliedMathCheckbox.setSelected(false);
		otherMathCheckbox.setSelected(false);
		historyCheckbox.setSelected(false);
		literatureCheckbox.setSelected(false);
		philosophyCheckbox.setSelected(false);
		psychologyCheckbox.setSelected(false);
		sociologyCheckbox.setSelected(false);
		politicalScienceCheckbox.setSelected(false);
		justiceAdministrationCheckbox.setSelected(false);
		otherLiberalArtsCheckbox.setSelected(false);
		businessCheckbox.setSelected(false);
		healthcareCheckbox.setSelected(false);
		lawCheckbox.setSelected(false);
		educationCheckbox.setSelected(false);
		vocationalCheckbox.setSelected(false);
		agricultureCheckbox.setSelected(false);
		otherProfessionalCheckbox.setSelected(false);
		visualArtsAndDesignCheckbox.setSelected(false);
		foreignLanguageCheckbox.setSelected(false);
		culinaryArtsCheckbox.setSelected(false);
		communicationCheckbox.setSelected(false);
		performingArtsCheckbox.setSelected(false);
		physicalEducationAndNutritionCheckbox.setSelected(false);
		otherCreativeArtsAndElectivesCheckbox.setSelected(false);

		// Reset num courses field
		numCourses.setText("4");

		// Clear error labels
		majorError.setText("");
		numCoursesError.setText("");
	}

	/**
	 * Purpose: To return the save button
	 * 
	 * @return saveButton The save button
	 */
	public JButton getSaveButton()
	{
		return saveButton;
	}

	/**
	 * Purpose: To return the continue button
	 * 
	 * @return continueButton The continue button
	 */
	public JButton getContinueButton()
	{
		return continueButton;
	}

	/**
	 * Purpose: To set the major error label to the given string
	 * 
	 * @param errorMessage The error message to set the major error label to
	 */
	public void setMajorError(String errorMessage)
	{
		majorError.setText(errorMessage);
	}

	/**
	 * Purpose: To set the num courses error label to the given string
	 * 
	 * @param errorMessage The error message to set the num courses error label
	 *                     to
	 */
	public void setNumCoursesError(String errorMessage)
	{
		numCoursesError.setText(errorMessage);
	}

	/**
	 * Purpose: To return the major input from the about you view
	 * 
	 * @return majorInput The major input from the about you view
	 */
	public String getMajorInput()
	{
		if (theSTEMButton.isSelected())
		{
			return "STEM";
		}
		else if (theLiberalArtsButton.isSelected())
		{
			return "Liberal Arts";
		}
		else if (theUndecidedButton.isSelected())
		{
			return "Undecided";
		}
		else
		{
			return "";
		}
	}

	/**
	 * Purpose: To return the number of courses input from the about you view
	 * 
	 * @return numCoursesInput The number of courses input from the about you
	 *         view
	 */
	public int getNumCoursesInput()
	{
		try
		{
			return Integer.parseInt(numCourses.getText().trim());
		}
		catch (Exception e)
		{
			return -1;
		}
	}

	/**
	 * Purpose: To return the struggle courses input from the about you view
	 * 
	 * @return struggleCoursesInput The struggle courses input from the about
	 *         you view
	 */
	public List<String> getStruggleCoursesInput()
	{
		if (biologyCheckbox.isSelected() || chemistryCheckbox.isSelected()
				|| physicsCheckbox.isSelected()
				|| environmentalScienceCheckbox.isSelected()
				|| otherScienceCheckbox.isSelected()
				|| computerScienceCheckbox.isSelected()
				|| informationTechnologyCheckbox.isSelected()
				|| computerInformationSystemsCheckbox.isSelected()
				|| cybersecurityAndInfoAssuranceCheckbox.isSelected()
				|| dataScienceAndAnalyticsCheckbox.isSelected()
				|| softwareEngineeringAndDevelopmentCheckbox.isSelected()
				|| computerEngineeringCheckbox.isSelected()
				|| otherTechCheckbox.isSelected()
				|| civilEngineeringCheckbox.isSelected()
				|| mechanicalEngineeringCheckbox.isSelected()
				|| electricalEngineeringCheckbox.isSelected()
				|| chemicalEngineeringCheckbox.isSelected()
				|| otherEngineeringCheckbox.isSelected()
				|| coreMathCheckbox.isSelected()
				|| pureMathCheckbox.isSelected()
				|| appliedMathCheckbox.isSelected()
				|| otherMathCheckbox.isSelected()
				|| historyCheckbox.isSelected()
				|| literatureCheckbox.isSelected()
				|| philosophyCheckbox.isSelected()
				|| psychologyCheckbox.isSelected()
				|| sociologyCheckbox.isSelected()
				|| politicalScienceCheckbox.isSelected()
				|| justiceAdministrationCheckbox.isSelected()
				|| otherLiberalArtsCheckbox.isSelected()
				|| businessCheckbox.isSelected()
				|| healthcareCheckbox.isSelected() || lawCheckbox.isSelected()
				|| educationCheckbox.isSelected()
				|| vocationalCheckbox.isSelected()
				|| agricultureCheckbox.isSelected()
				|| otherProfessionalCheckbox.isSelected()
				|| visualArtsAndDesignCheckbox.isSelected()
				|| foreignLanguageCheckbox.isSelected()
				|| culinaryArtsCheckbox.isSelected()
				|| communicationCheckbox.isSelected()
				|| performingArtsCheckbox.isSelected()
				|| physicalEducationAndNutritionCheckbox.isSelected()
				|| otherCreativeArtsAndElectivesCheckbox.isSelected())
		{
			List<String> struggleCoursesInput = new java.util.ArrayList<String>();

			if (biologyCheckbox.isSelected())
			{
				struggleCoursesInput.add("Biology");
			}
			if (chemistryCheckbox.isSelected())
			{
				struggleCoursesInput.add("Chemistry");
			}
			if (physicsCheckbox.isSelected())
			{
				struggleCoursesInput.add("Physics");
			}
			if (environmentalScienceCheckbox.isSelected())
			{
				struggleCoursesInput.add("Environmental Science");
			}
			if (otherScienceCheckbox.isSelected())
			{
				struggleCoursesInput.add("Other Science");
			}
			if (computerScienceCheckbox.isSelected())
			{
				struggleCoursesInput.add("Computer Science");
			}
			if (informationTechnologyCheckbox.isSelected())
			{
				struggleCoursesInput.add("Information Technology");
			}
			if (computerInformationSystemsCheckbox.isSelected())
			{
				struggleCoursesInput.add("Computer Information Systems");
			}
			if (cybersecurityAndInfoAssuranceCheckbox.isSelected())
			{
				struggleCoursesInput
						.add("Cybersecurity and Information Assurance");
			}
			if (dataScienceAndAnalyticsCheckbox.isSelected())
			{
				struggleCoursesInput.add("Data Science and Analytics");
			}
			if (softwareEngineeringAndDevelopmentCheckbox.isSelected())
			{
				struggleCoursesInput
						.add("Software Engineering and Development");
			}
			if (computerEngineeringCheckbox.isSelected())
			{
				struggleCoursesInput.add("Computer Engineering");
			}
			if (otherTechCheckbox.isSelected())
			{
				struggleCoursesInput.add("Other Tech");
			}
			if (civilEngineeringCheckbox.isSelected())
			{
				struggleCoursesInput.add("Civil Engineering");
			}
			if (mechanicalEngineeringCheckbox.isSelected())
			{
				struggleCoursesInput.add("Mechanical Engineering");
			}
			if (electricalEngineeringCheckbox.isSelected())
			{
				struggleCoursesInput.add("Electrical Engineering");
			}
			if (chemicalEngineeringCheckbox.isSelected())
			{
				struggleCoursesInput.add("Chemical Engineering");
			}
			if (otherEngineeringCheckbox.isSelected())
			{
				struggleCoursesInput.add("Other Engineering");
			}
			if (coreMathCheckbox.isSelected())
			{
				struggleCoursesInput.add("Core Math");
			}
			if (pureMathCheckbox.isSelected())
			{
				struggleCoursesInput.add("Pure Math");
			}
			if (appliedMathCheckbox.isSelected())
			{
				struggleCoursesInput.add("Applied Math");
			}
			if (otherMathCheckbox.isSelected())
			{
				struggleCoursesInput.add("Other Math");
			}
			if (historyCheckbox.isSelected())
			{
				struggleCoursesInput.add("History");
			}
			if (literatureCheckbox.isSelected())
			{
				struggleCoursesInput.add("Literature");
			}
			if (philosophyCheckbox.isSelected())
			{
				struggleCoursesInput.add("Philosophy");
			}
			if (psychologyCheckbox.isSelected())
			{
				struggleCoursesInput.add("Psychology");
			}
			if (sociologyCheckbox.isSelected())
			{
				struggleCoursesInput.add("Sociology");
			}
			if (politicalScienceCheckbox.isSelected())
			{
				struggleCoursesInput.add("Political Science");
			}
			if (justiceAdministrationCheckbox.isSelected())
			{
				struggleCoursesInput.add("Justice Administration");
			}
			if (otherLiberalArtsCheckbox.isSelected())
			{
				struggleCoursesInput.add("Other Liberal Arts Courses");
			}
			if (businessCheckbox.isSelected())
			{
				struggleCoursesInput.add("Business");
			}
			if (healthcareCheckbox.isSelected())
			{
				struggleCoursesInput.add("Healthcare");
			}
			if (lawCheckbox.isSelected())
			{
				struggleCoursesInput.add("Law");
			}
			if (educationCheckbox.isSelected())
			{
				struggleCoursesInput.add("Education");
			}
			if (vocationalCheckbox.isSelected())
			{
				struggleCoursesInput.add("Vocational");
			}
			if (agricultureCheckbox.isSelected())
			{
				struggleCoursesInput.add("Agriculture");
			}
			if (otherProfessionalCheckbox.isSelected())
			{
				struggleCoursesInput.add("Other Professional Courses");
			}
			if (visualArtsAndDesignCheckbox.isSelected())
			{
				struggleCoursesInput.add("Visual Arts and Design");
			}
			if (foreignLanguageCheckbox.isSelected())
			{
				struggleCoursesInput.add("Foreign Language");
			}
			if (culinaryArtsCheckbox.isSelected())
			{
				struggleCoursesInput.add("Culinary Arts");
			}
			if (communicationCheckbox.isSelected())
			{
				struggleCoursesInput.add("Communication");
			}
			if (performingArtsCheckbox.isSelected())
			{
				struggleCoursesInput.add("Performing Arts");
			}
			if (physicalEducationAndNutritionCheckbox.isSelected())
			{
				struggleCoursesInput.add("Physical Education and Nutrition");
			}
			if (otherCreativeArtsAndElectivesCheckbox.isSelected())
			{
				struggleCoursesInput.add("Other Creative Arts and Electives");
			}

			return struggleCoursesInput;
		}
		else
		{
			return null;
		}
	}

}
