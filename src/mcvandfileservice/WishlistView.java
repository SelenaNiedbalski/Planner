package mcvandfileservice;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.nio.file.Path;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
 * Wishlistview is-a JFrame 
 */
import javax.swing.*;
import java.awt.*;

public class WishlistView extends JFrame
{
    // Instance Variables
    private AppController appController; // A wishlist view has-an app controller

    // Times IVs
    private JTextField monStartOfDay;
    private JTextField monEndOfDay;
    private JTextField tuesStartOfDay;
    private JTextField tuesEndOfDay;
    private JTextField wedStartOfDay;
    private JTextField wedEndOfDay;
    private JTextField thursStartOfDay;
    private JTextField thursEndOfDay;
    private JTextField friStartOfDay;
    private JTextField friEndOfDay;
    private JTextField satStartOfDay;
    private JTextField satEndOfDay;
    private JTextField sunStartOfDay;
    private JTextField sunEndOfDay;

    // Time Error IVs
    private JLabel monError;
    private JLabel tuesError;
    private JLabel wedError;
    private JLabel thursError;
    private JLabel friError;
    private JLabel satError;
    private JLabel sunError;

    // Break time IVs
    private JTextField minBreakTime;
    private JTextField maxBreakTime;

    // Campus location IV
    private JTextField campusLocation;

    // Desired SharePath IV
    private JTextField topThreeSchedulesDestinationPath;

    // Button IVs
    private JButton backButton;
    private JButton saveButton;
    private JButton continueButton;

	/**
	 * Purpose: To construct a WishlistView
	 */
    public WishlistView()
    {
        setTitle("Wishlist");
        setSize(600, 700);
        setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel(
                getClass().getResource("/images/Wishlist.png")
        );
        backgroundPanel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(120, 120, 80, 120));

        Color darkHotPink = new Color(170, 0, 85);
        Font uiFont = new Font("Lucida Calligraphy", Font.PLAIN, 10);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JTextArea timetableInstructionsLabel = new JTextArea(
                "Input your ideal start and end times for each day of the week in 24-hour format "
                        + "(Ex. 1:00pm would be 13:00). Blank out both boxes if you "
                        + "do not wish to attend classes on that day."
        );
        timetableInstructionsLabel.setFont(uiFont);
        timetableInstructionsLabel.setForeground(darkHotPink);
        timetableInstructionsLabel.setOpaque(false);
        timetableInstructionsLabel.setEditable(false);
        timetableInstructionsLabel.setFocusable(false);
        timetableInstructionsLabel.setLineWrap(true);
        timetableInstructionsLabel.setWrapStyleWord(true);
        timetableInstructionsLabel.setBorder(BorderFactory.createEmptyBorder());
        timetableInstructionsLabel.setColumns(32);

        JPanel timetableInstructionsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        timetableInstructionsWrapper.setOpaque(false);
        timetableInstructionsWrapper.add(timetableInstructionsLabel);

        topPanel.add(timetableInstructionsWrapper);
        topPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel timetablePanel = new JPanel(new GridLayout(8, 4, 10, 8));
        timetablePanel.setOpaque(false);
        timetablePanel.setPreferredSize(new Dimension(360, 260));

        JPanel timetableWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        timetableWrapper.setOpaque(false);
        timetableWrapper.add(timetablePanel);

        JLabel headerDay = new JLabel("Day", SwingConstants.CENTER);
        headerDay.setFont(uiFont);
        headerDay.setForeground(darkHotPink);

        JLabel headerStart = new JLabel("Start", SwingConstants.CENTER);
        headerStart.setFont(uiFont);
        headerStart.setForeground(darkHotPink);

        JLabel headerEnd = new JLabel("End", SwingConstants.CENTER);
        headerEnd.setFont(uiFont);
        headerEnd.setForeground(darkHotPink);

        JLabel headerError = new JLabel("", SwingConstants.CENTER);
        headerError.setFont(uiFont);
        headerError.setForeground(darkHotPink);

        timetablePanel.add(headerDay);
        timetablePanel.add(headerStart);
        timetablePanel.add(headerEnd);
        timetablePanel.add(headerError);

        Dimension timeFieldMax = new Dimension(60, 20);

        JLabel monLabel = new JLabel("Mon", SwingConstants.CENTER);
        monLabel.setFont(uiFont);
        monLabel.setForeground(darkHotPink);
        monStartOfDay = new JTextField(4);
        monStartOfDay.setText("08:00");
        monStartOfDay.setMaximumSize(timeFieldMax);
        monEndOfDay = new JTextField(4);
        monEndOfDay.setText("16:00");
        monEndOfDay.setMaximumSize(timeFieldMax);
        monError = new JLabel("", SwingConstants.CENTER);
        monError.setFont(uiFont);
        monError.setForeground(darkHotPink);
        timetablePanel.add(monLabel);
        timetablePanel.add(monStartOfDay);
        timetablePanel.add(monEndOfDay);
        timetablePanel.add(monError);

        JLabel tuesLabel = new JLabel("Tue", SwingConstants.CENTER);
        tuesLabel.setFont(uiFont);
        tuesLabel.setForeground(darkHotPink);
        tuesStartOfDay = new JTextField(4);
        tuesStartOfDay.setText("08:00");
        tuesStartOfDay.setMaximumSize(timeFieldMax);
        tuesEndOfDay = new JTextField(4);
        tuesEndOfDay.setText("16:00");
        tuesEndOfDay.setMaximumSize(timeFieldMax);
        tuesError = new JLabel("", SwingConstants.CENTER);
        tuesError.setFont(uiFont);
        tuesError.setForeground(darkHotPink);
        timetablePanel.add(tuesLabel);
        timetablePanel.add(tuesStartOfDay);
        timetablePanel.add(tuesEndOfDay);
        timetablePanel.add(tuesError);

        JLabel wedLabel = new JLabel("Wed", SwingConstants.CENTER);
        wedLabel.setFont(uiFont);
        wedLabel.setForeground(darkHotPink);
        wedStartOfDay = new JTextField(4);
        wedStartOfDay.setText("08:00");
        wedStartOfDay.setMaximumSize(timeFieldMax);
        wedEndOfDay = new JTextField(4);
        wedEndOfDay.setText("16:00");
        wedEndOfDay.setMaximumSize(timeFieldMax);
        wedError = new JLabel("", SwingConstants.CENTER);
        wedError.setFont(uiFont);
        wedError.setForeground(darkHotPink);
        timetablePanel.add(wedLabel);
        timetablePanel.add(wedStartOfDay);
        timetablePanel.add(wedEndOfDay);
        timetablePanel.add(wedError);

        JLabel thursLabel = new JLabel("Thu", SwingConstants.CENTER);
        thursLabel.setFont(uiFont);
        thursLabel.setForeground(darkHotPink);
        thursStartOfDay = new JTextField(4);
        thursStartOfDay.setText("08:00");
        thursStartOfDay.setMaximumSize(timeFieldMax);
        thursEndOfDay = new JTextField(4);
        thursEndOfDay.setText("16:00");
        thursEndOfDay.setMaximumSize(timeFieldMax);
        thursError = new JLabel("", SwingConstants.CENTER);
        thursError.setFont(uiFont);
        thursError.setForeground(darkHotPink);
        timetablePanel.add(thursLabel);
        timetablePanel.add(thursStartOfDay);
        timetablePanel.add(thursEndOfDay);
        timetablePanel.add(thursError);

        JLabel friLabel = new JLabel("Fri", SwingConstants.CENTER);
        friLabel.setFont(uiFont);
        friLabel.setForeground(darkHotPink);
        friStartOfDay = new JTextField(4);
        friStartOfDay.setText("08:00");
        friStartOfDay.setMaximumSize(timeFieldMax);
        friEndOfDay = new JTextField(4);
        friEndOfDay.setText("16:00");
        friEndOfDay.setMaximumSize(timeFieldMax);
        friError = new JLabel("", SwingConstants.CENTER);
        friError.setFont(uiFont);
        friError.setForeground(darkHotPink);
        timetablePanel.add(friLabel);
        timetablePanel.add(friStartOfDay);
        timetablePanel.add(friEndOfDay);
        timetablePanel.add(friError);

        JLabel satLabel = new JLabel("Sat", SwingConstants.CENTER);
        satLabel.setFont(uiFont);
        satLabel.setForeground(darkHotPink);
        satStartOfDay = new JTextField(4);
        satStartOfDay.setText("08:00");
        satStartOfDay.setMaximumSize(timeFieldMax);
        satEndOfDay = new JTextField(4);
        satEndOfDay.setText("16:00");
        satEndOfDay.setMaximumSize(timeFieldMax);
        satError = new JLabel("", SwingConstants.CENTER);
        satError.setFont(uiFont);
        satError.setForeground(darkHotPink);
        timetablePanel.add(satLabel);
        timetablePanel.add(satStartOfDay);
        timetablePanel.add(satEndOfDay);
        timetablePanel.add(satError);

        JLabel sunLabel = new JLabel("Sun", SwingConstants.CENTER);
        sunLabel.setFont(uiFont);
        sunLabel.setForeground(darkHotPink);
        sunStartOfDay = new JTextField(4);
        sunStartOfDay.setText("08:00");
        sunStartOfDay.setMaximumSize(timeFieldMax);
        sunEndOfDay = new JTextField(4);
        sunEndOfDay.setText("16:00");
        sunEndOfDay.setMaximumSize(timeFieldMax);
        sunError = new JLabel("", SwingConstants.CENTER);
        sunError.setFont(uiFont);
        sunError.setForeground(darkHotPink);
        timetablePanel.add(sunLabel);
        timetablePanel.add(sunStartOfDay);
        timetablePanel.add(sunEndOfDay);
        timetablePanel.add(sunError);

        mainPanel.add(timetableWrapper, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setOpaque(false);

        JTextArea breakTimesInstructionsLabel = new JTextArea(
                "Input your ideal minimum and maximum time for your breaks in minutes "
                        + "(Ex. 2 hours would be 120). Only make these blank if you do not wish to have a break."
        );
        breakTimesInstructionsLabel.setFont(uiFont);
        breakTimesInstructionsLabel.setForeground(darkHotPink);
        breakTimesInstructionsLabel.setOpaque(false);
        breakTimesInstructionsLabel.setEditable(false);
        breakTimesInstructionsLabel.setFocusable(false);
        breakTimesInstructionsLabel.setLineWrap(true);
        breakTimesInstructionsLabel.setWrapStyleWord(true);
        breakTimesInstructionsLabel.setBorder(BorderFactory.createEmptyBorder());
        breakTimesInstructionsLabel.setColumns(32);

        JPanel breakInstructionsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        breakInstructionsWrapper.setOpaque(false);
        breakInstructionsWrapper.add(breakTimesInstructionsLabel);

        bottomPanel.add(breakInstructionsWrapper);
        bottomPanel.add(Box.createVerticalStrut(6));

        JPanel breakTimesPanel = new JPanel();
        breakTimesPanel.setLayout(new BoxLayout(breakTimesPanel, BoxLayout.X_AXIS));
        breakTimesPanel.setOpaque(false);

        JLabel minBreakTimeLabel = new JLabel("Minimum Break Time: ");
        minBreakTimeLabel.setFont(uiFont);
        minBreakTimeLabel.setForeground(darkHotPink);
        minBreakTime = new JTextField(4);
        minBreakTime.setText("10");
        minBreakTime.setMaximumSize(new Dimension(50, 20));

        JLabel maxBreakTimeLabel = new JLabel("Maximum Break Time: ");
        maxBreakTimeLabel.setFont(uiFont);
        maxBreakTimeLabel.setForeground(darkHotPink);
        maxBreakTime = new JTextField(4);
        maxBreakTime.setText("120");
        maxBreakTime.setMaximumSize(new Dimension(50, 20));

        breakTimesPanel.add(minBreakTimeLabel);
        breakTimesPanel.add(minBreakTime);
        breakTimesPanel.add(Box.createHorizontalStrut(12));
        breakTimesPanel.add(maxBreakTimeLabel);
        breakTimesPanel.add(maxBreakTime);

        bottomPanel.add(breakTimesPanel);
        bottomPanel.add(Box.createVerticalStrut(6));

        JPanel campusLocationPanel = new JPanel();
        campusLocationPanel.setLayout(new BoxLayout(campusLocationPanel, BoxLayout.X_AXIS));
        campusLocationPanel.setOpaque(false);

        JLabel campusLocationLabel = new JLabel(
                "Your desired campus location (exactly as it appears on your schedule or blank for no preference): "
        );
        campusLocationLabel.setFont(uiFont);
        campusLocationLabel.setForeground(darkHotPink);

        campusLocation = new JTextField(8);
        campusLocation.setText("");
        campusLocation.setMaximumSize(new Dimension(120, 20));

        campusLocationPanel.add(campusLocationLabel);
        campusLocationPanel.add(campusLocation);

        bottomPanel.add(campusLocationPanel);
        bottomPanel.add(Box.createVerticalStrut(6));

        JTextArea sharePathInfoLabel = new JTextArea(
                "Input the folder path you would like to save your top 3 schedules to "
                        + "(Ex. C:\\Users\\Selena\\Desktop\\Wishlist Schedule): "
        );
        sharePathInfoLabel.setFont(uiFont);
        sharePathInfoLabel.setForeground(darkHotPink);
        sharePathInfoLabel.setOpaque(false);
        sharePathInfoLabel.setEditable(false);
        sharePathInfoLabel.setFocusable(false);
        sharePathInfoLabel.setLineWrap(true);
        sharePathInfoLabel.setWrapStyleWord(true);
        sharePathInfoLabel.setBorder(BorderFactory.createEmptyBorder());
        sharePathInfoLabel.setColumns(32);

        JPanel shareInstructionsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        shareInstructionsWrapper.setOpaque(false);
        shareInstructionsWrapper.add(sharePathInfoLabel);

        bottomPanel.add(shareInstructionsWrapper);

        JPanel sharePathInfoPanel = new JPanel();
        sharePathInfoPanel.setLayout(new BoxLayout(sharePathInfoPanel, BoxLayout.X_AXIS));
        sharePathInfoPanel.setOpaque(false);

        topThreeSchedulesDestinationPath = new JTextField(10);
        topThreeSchedulesDestinationPath.setText("");
        topThreeSchedulesDestinationPath.setMaximumSize(new Dimension(150, 20));

        sharePathInfoPanel.add(Box.createHorizontalStrut(0));
        sharePathInfoPanel.add(topThreeSchedulesDestinationPath);

        bottomPanel.add(sharePathInfoPanel);
        bottomPanel.add(Box.createVerticalStrut(10));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);

        backButton = new JButton("Back");
        backButton.setFont(uiFont);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(darkHotPink);

        saveButton = new JButton("Save");
        saveButton.setFont(uiFont);
        saveButton.setForeground(Color.WHITE);
        saveButton.setBackground(darkHotPink);

        continueButton = new JButton("Continue");
        continueButton.setFont(uiFont);
        continueButton.setForeground(Color.WHITE);
        continueButton.setBackground(darkHotPink);

        buttonsPanel.add(backButton);
        buttonsPanel.add(Box.createHorizontalStrut(160));
        buttonsPanel.add(saveButton);
        buttonsPanel.add(continueButton);

        bottomPanel.add(buttonsPanel);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        backgroundPanel.add(mainPanel, BorderLayout.CENTER);
        setContentPane(backgroundPanel);

        setVisible(false);
    }
    

    
	
	

	// Getters and Setters
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

}
