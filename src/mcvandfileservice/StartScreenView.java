package mcvandfileservice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
 *         Responsibilities of class: To represent the start screen of the
 *         application, which includes a background image and a start button. It
 *         also has a reference to the app controller to allow for communication
 *         between the view and the controller.
 * 
 *         StartScreenView is-a JFrame
 */
public class StartScreenView extends JFrame
{
	// Instance Variables
	private AppController appController; // A start screen view has-an app
											// controller
	private JButton startButton; // A start screen view has-a start button

	// Constructor
	/**
	 * Purpose: To construct a StartScreenView with a background image and a
	 * start button
	 */
	public StartScreenView()
	{
		setTitle("Start Screen");
		setSize(700, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Background panel

		BackgroundPanel backgroundPanel = new BackgroundPanel(
				getClass().getResource("/images/StartScreen.png"));

		backgroundPanel.setLayout(new BorderLayout());

		// Create button
		startButton = new JButton("Start");
		startButton.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
		startButton.setForeground(Color.WHITE);
		Color darkHotPink = new Color(170, 0, 85);
		startButton.setBackground(darkHotPink);

		// Create center panel (transparent so background shows)
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		centerPanel.setOpaque(false);

		// Add vertical space to push button down
		centerPanel.add(Box.createVerticalStrut(400));

		// Center the button horizontally
		startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		centerPanel.add(startButton);

		// Add to background
		backgroundPanel.add(centerPanel, BorderLayout.CENTER);

		// Set content
		setContentPane(backgroundPanel);

		// Show window
		setVisible(true);
	}

	// Getters and Setters
	/**
	 * Purpose: To return the app controller
	 * 
	 * @return appController The app controller instance variable
	 */
	public AppController getAppController()
	{
		return appController;
	}

	/**
	 * Purpose: To set the app controller
	 * 
	 * @param newAppController The app controller to set the app controller
	 *                         instance variable to
	 */
	public void setAppController(AppController newAppController)
	{
		appController = newAppController;
	}

	/**
	 * Purpose: To return the start button
	 * 
	 * @return startButton The start button
	 */
	public JButton getStartButton()
	{
		return startButton;
	}
}
