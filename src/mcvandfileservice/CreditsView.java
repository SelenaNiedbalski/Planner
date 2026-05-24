package mcvandfileservice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
 * CreditsView is-a JFrame
 */

public class CreditsView extends JFrame
{
	// Instance Variables
	private AppController appController2; // A credits view has-an app controller
	
	// Credits IVs
	private JTextField minCreditsInput;
	private JTextField maxCreditsInput;
	
	// Credits Errors IVs
	private JLabel minCreditsError;
	private JLabel maxCreditsError;
	
	// Buttons IVs
	private JButton backButton;
	private JButton saveButton;
	private JButton continueButton;
	
	// Constructor
	/**
	 * Purpose: To construct a CreditsView
	 */
	public CreditsView()
	{
	    setTitle("Credits");
	    setSize(700, 800);
	    setLocationRelativeTo(null);
	    
	    // Background Panel
	    BackgroundPanel backgroundPanel = new BackgroundPanel(
	            getClass().getResource("/images/Credits.png")
	    );
	    backgroundPanel.setLayout(new BorderLayout());

	    // Main Panel
	    JPanel mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	    mainPanel.setOpaque(false);
	    mainPanel.setBorder(BorderFactory.createEmptyBorder(125, 30, 60, 30));
	    
	    
	    
	    // Colors and Fonts
	    Color darkHotPink = new Color(170, 0, 85);
	    Font instructionsAndLabelsFont = new Font("Sans Serif", Font.PLAIN, 16);
	    Font buttonFont = new Font("Lucida Calligraphy", Font.PLAIN, 12);
	    Font errorFont = new Font("Sans Serif", Font.ITALIC, 8);
	    
	    // Instructions Panel
	    JPanel instructionsPanel = new JPanel();
	    instructionsPanel.setOpaque(false);
	    instructionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    JTextArea instructionsLabel = new JTextArea("Please enter the minimum and maximum credits you are required to take below:");
	    instructionsLabel.setLineWrap(true);
	    instructionsLabel.setWrapStyleWord(true);
	    instructionsLabel.setEditable(false);
	    instructionsLabel.setFocusable(false);
	    instructionsLabel.setOpaque(false);
	    instructionsLabel.setBorder(null);
	    instructionsLabel.setHighlighter(null);
	    instructionsLabel.setFont(instructionsAndLabelsFont);
	    instructionsLabel.setForeground(darkHotPink);
	    instructionsLabel.setColumns(28);
	    instructionsLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, instructionsLabel.getPreferredSize().height));
	    instructionsPanel.add(instructionsLabel);
	    
	    // Min Credits Panel
	    JPanel minCreditsPanel = new JPanel();
	    minCreditsPanel.setLayout(new BoxLayout(minCreditsPanel, BoxLayout.X_AXIS));
	    minCreditsPanel.setOpaque(false);
	    minCreditsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    JLabel minCreditsLabel = new JLabel("Minimum Credits:");
	    minCreditsLabel.setFont(instructionsAndLabelsFont);
	    minCreditsLabel.setForeground(darkHotPink);
	    minCreditsInput = new JTextField("3", 2);
	    minCreditsInput.setPreferredSize(new Dimension(40, 22));
	    minCreditsInput.setMinimumSize(new Dimension(40, 22));
	    minCreditsInput.setMaximumSize(new Dimension(40, 22));
	    minCreditsPanel.add(Box.createHorizontalStrut(120));
	    minCreditsPanel.add(minCreditsLabel);
	    minCreditsPanel.add(Box.createHorizontalStrut(10));
	    minCreditsPanel.add(minCreditsInput);
	    minCreditsError = new JLabel("");
	    minCreditsError.setFont(errorFont);
	    minCreditsError.setForeground(darkHotPink);
	    minCreditsError.setPreferredSize(new Dimension(260, 16));
	    minCreditsError.setMinimumSize(new Dimension(260, 16));
	    minCreditsError.setMaximumSize(new Dimension(260, 16));
	    minCreditsPanel.add(Box.createHorizontalStrut(12));
	    minCreditsPanel.add(minCreditsError);
	    minCreditsPanel.add(Box.createHorizontalGlue());
	    
	    // Max Credits Panel
	    JPanel maxCreditsPanel = new JPanel();
	    maxCreditsPanel.setLayout(new BoxLayout(maxCreditsPanel, BoxLayout.X_AXIS));
	    maxCreditsPanel.setOpaque(false);
	    maxCreditsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
	    JLabel maxCreditsLabel = new JLabel("Maximum Credits:");
	    maxCreditsLabel.setFont(instructionsAndLabelsFont);
	    maxCreditsLabel.setForeground(darkHotPink);
	    maxCreditsInput = new JTextField("20", 2);
	    maxCreditsInput.setPreferredSize(new Dimension(40, 22));
	    maxCreditsInput.setMinimumSize(new Dimension(40, 22));
	    maxCreditsInput.setMaximumSize(new Dimension(40, 22));
	    maxCreditsPanel.add(Box.createHorizontalStrut(120));
	    maxCreditsPanel.add(maxCreditsLabel);
	    maxCreditsPanel.add(Box.createHorizontalStrut(10));
	    maxCreditsPanel.add(maxCreditsInput);
	    maxCreditsError = new JLabel("");
	    maxCreditsError.setFont(errorFont);
	    maxCreditsError.setForeground(darkHotPink);
	    maxCreditsError.setPreferredSize(new Dimension(260, 16));
	    maxCreditsError.setMinimumSize(new Dimension(260, 16));
	    maxCreditsError.setMaximumSize(new Dimension(260, 16));
	    maxCreditsPanel.add(Box.createHorizontalStrut(12));
	    maxCreditsPanel.add(maxCreditsError);
	    maxCreditsPanel.add(Box.createHorizontalGlue());
	    
	    
	    
	    // Buttons Panel
	    JPanel buttonsPanel = new JPanel();
	    buttonsPanel.setOpaque(false);
	    buttonsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

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
	    buttonsPanel.add(continueButton);

	    // Add panels to main panel
	    mainPanel.add(instructionsPanel);
	    mainPanel.add(Box.createVerticalStrut(150));
	    mainPanel.add(minCreditsPanel);
	    mainPanel.add(Box.createVerticalStrut(20));
	    mainPanel.add(maxCreditsPanel);
	    mainPanel.add(Box.createVerticalStrut(260));
	    mainPanel.add(buttonsPanel);

	    backgroundPanel.add(mainPanel, BorderLayout.CENTER);
	    setContentPane(backgroundPanel);

	    setVisible(false);
	}

     
     
    // Instance Variables
	private AppController appController; // A credits view has-an app controller
	
	
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
	 * Purpose: To return the back button
	 * @return backButton The back button
	 */
	public JButton getBackButton()
	{
		return backButton;
	}

	/**
	 * Purpose: To return the save button
	 * @return saveButton The save button
	 */
	public JButton getSaveButton()
	{
		return saveButton;
	}

	/**
	 * Purpose: To return the continue button
	 * @return continueButton The continue button
	 */
	public JButton getContinueButton()
	{
		return continueButton;
	}
	
	
	
	
	
	
	
	/**
	 * Purpose: To return the minimum required credits input field value
	 * @return minCreditsInput The minimum required credits input field value
	 */
	public int getMinCreditsInput()
	{
		return Integer.parseInt(minCreditsInput.getText().trim());
	}
	
	/**
	 * Purpose: To return the maximum required credits input field value
	 * @return maxCreditsInput The maximum required credits input field value
	 */
	public int getMaxCreditsInput()
	{
		return Integer.parseInt(maxCreditsInput.getText().trim());
	}
	
	
	/**
	 * Purpose: To set the error message for the minimum required credits input field
	 * @param errorMessage The error message to be set for the minimum required credits input field
	 */
	public void setMinCreditsError(String errorMessage)
	{
		minCreditsError.setText(errorMessage);
	}
	
	/**
	 * Purpose: To set the error message for the maximum required credits input field
	 * @param errorMessage The error message to be set for the maximum required credits input field
	 */
	public void setMaxCreditsError(String errorMessage)
	{
		maxCreditsError.setText(errorMessage);
	}
	

	/**
	 * Purpose: To reset all credits input fields to their default values
	 * 
	 */
	public void resetFields()
	{
		// Reset fields to default values
		minCreditsInput.setText("3");
		maxCreditsInput.setText("20");
		
		// Clear error labels
		minCreditsError.setText("");
		maxCreditsError.setText("");
	}
	
}
