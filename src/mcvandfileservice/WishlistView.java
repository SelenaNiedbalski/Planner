package mcvandfileservice;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.nio.file.Path;
import java.time.LocalTime;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

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
        setSize(700, 800);
        setLocationRelativeTo(null);

        BackgroundPanel backgroundPanel = new BackgroundPanel(
                getClass().getResource("/images/Wishlist.png")
        );
        backgroundPanel.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setOpaque(false);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(80, 80, 60, 80));

        Color darkHotPink = new Color(170, 0, 85);
        Font instructionsAndLabelsFont = new Font("Sans Serif", Font.PLAIN, 12);
        Font buttonFont = new Font("Lucida Calligraphy", Font.PLAIN, 12);

        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        JTextArea timetableInstructionsLabel = new JTextArea(
                "Input your ideal start and end times for each day of the week in 24-hour format "
                        + "(Ex. 1:00pm would be 13:00). Blank out both boxes if you "
                        + "do not wish to attend classes on that day."
        );
        timetableInstructionsLabel.setFont(instructionsAndLabelsFont);
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

        JPanel timetablePanel = new JPanel(new GridLayout(8, 4, 6, 6));
        timetablePanel.setOpaque(false);
        timetablePanel.setPreferredSize(new Dimension(300, 190));

        // Helper to prevent GridLayout from stretching JTextFields:
        java.util.function.Function<JComponent, JPanel> fit = (comp) -> {
            JPanel p = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
            p.setOpaque(false);
            p.add(comp);
            return p;
        };

        JPanel timetableWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        timetableWrapper.setOpaque(false);
        timetableWrapper.add(timetablePanel);

        JLabel headerDay = new JLabel("Day", SwingConstants.CENTER);
        headerDay.setFont(instructionsAndLabelsFont);
        headerDay.setForeground(darkHotPink);

        JLabel headerStart = new JLabel("Start", SwingConstants.CENTER);
        headerStart.setFont(instructionsAndLabelsFont);
        headerStart.setForeground(darkHotPink);

        JLabel headerEnd = new JLabel("End", SwingConstants.CENTER);
        headerEnd.setFont(instructionsAndLabelsFont);
        headerEnd.setForeground(darkHotPink);

        JLabel headerError = new JLabel("", SwingConstants.CENTER);
        headerError.setFont(instructionsAndLabelsFont);
        headerError.setForeground(darkHotPink);

        timetablePanel.add(headerDay);
        timetablePanel.add(headerStart);
        timetablePanel.add(headerEnd);
        timetablePanel.add(headerError);

        Dimension timeFieldMax = new Dimension(38, 20);

        JLabel monLabel = new JLabel("Monday", SwingConstants.CENTER);
        monLabel.setFont(instructionsAndLabelsFont);
        monLabel.setForeground(darkHotPink);
        monStartOfDay = new JTextField(4);
        monStartOfDay.setText("08:00");
        monStartOfDay.setPreferredSize(timeFieldMax);
        monStartOfDay.setMaximumSize(timeFieldMax);
        monEndOfDay = new JTextField(4);
        monEndOfDay.setText("16:00");
        monEndOfDay.setPreferredSize(timeFieldMax);
        monEndOfDay.setMaximumSize(timeFieldMax);
        monError = new JLabel("", SwingConstants.CENTER);
        monError.setFont(instructionsAndLabelsFont);
        monError.setForeground(darkHotPink);
        timetablePanel.add(monLabel);
        timetablePanel.add(fit.apply(monStartOfDay));
        timetablePanel.add(fit.apply(monEndOfDay));
        timetablePanel.add(monError);

        JLabel tuesLabel = new JLabel("Tuesday", SwingConstants.CENTER);
        tuesLabel.setFont(instructionsAndLabelsFont);
        tuesLabel.setForeground(darkHotPink);
        tuesStartOfDay = new JTextField(4);
        tuesStartOfDay.setText("08:00");
        tuesStartOfDay.setPreferredSize(timeFieldMax);
        tuesStartOfDay.setMaximumSize(timeFieldMax);
        tuesEndOfDay = new JTextField(4);
        tuesEndOfDay.setText("16:00");
        tuesEndOfDay.setPreferredSize(timeFieldMax);
        tuesEndOfDay.setMaximumSize(timeFieldMax);
        tuesError = new JLabel("", SwingConstants.CENTER);
        tuesError.setFont(instructionsAndLabelsFont);
        tuesError.setForeground(darkHotPink);
        timetablePanel.add(tuesLabel);
        timetablePanel.add(fit.apply(tuesStartOfDay));
        timetablePanel.add(fit.apply(tuesEndOfDay));
        timetablePanel.add(tuesError);

        JLabel wedLabel = new JLabel("Wednesday", SwingConstants.CENTER);
        wedLabel.setFont(instructionsAndLabelsFont);
        wedLabel.setForeground(darkHotPink);
        wedStartOfDay = new JTextField(4);
        wedStartOfDay.setText("08:00");
        wedStartOfDay.setPreferredSize(timeFieldMax);
        wedStartOfDay.setMaximumSize(timeFieldMax);
        wedEndOfDay = new JTextField(4);
        wedEndOfDay.setText("16:00");
        wedEndOfDay.setPreferredSize(timeFieldMax);
        wedEndOfDay.setMaximumSize(timeFieldMax);
        wedError = new JLabel("", SwingConstants.CENTER);
        wedError.setFont(instructionsAndLabelsFont);
        wedError.setForeground(darkHotPink);
        timetablePanel.add(wedLabel);
        timetablePanel.add(fit.apply(wedStartOfDay));
        timetablePanel.add(fit.apply(wedEndOfDay));
        timetablePanel.add(wedError);

        JLabel thursLabel = new JLabel("Thursday", SwingConstants.CENTER);
        thursLabel.setFont(instructionsAndLabelsFont);
        thursLabel.setForeground(darkHotPink);
        thursStartOfDay = new JTextField(4);
        thursStartOfDay.setText("08:00");
        thursStartOfDay.setPreferredSize(timeFieldMax);
        thursStartOfDay.setMaximumSize(timeFieldMax);
        thursEndOfDay = new JTextField(4);
        thursEndOfDay.setText("16:00");
        thursEndOfDay.setPreferredSize(timeFieldMax);
        thursEndOfDay.setMaximumSize(timeFieldMax);
        thursError = new JLabel("", SwingConstants.CENTER);
        thursError.setFont(instructionsAndLabelsFont);
        thursError.setForeground(darkHotPink);
        timetablePanel.add(thursLabel);
        timetablePanel.add(fit.apply(thursStartOfDay));
        timetablePanel.add(fit.apply(thursEndOfDay));
        timetablePanel.add(thursError);

        JLabel friLabel = new JLabel("Friday", SwingConstants.CENTER);
        friLabel.setFont(instructionsAndLabelsFont);
        friLabel.setForeground(darkHotPink);
        friStartOfDay = new JTextField(4);
        friStartOfDay.setText("08:00");
        friStartOfDay.setPreferredSize(timeFieldMax);
        friStartOfDay.setMaximumSize(timeFieldMax);
        friEndOfDay = new JTextField(4);
        friEndOfDay.setText("16:00");
        friEndOfDay.setPreferredSize(timeFieldMax);
        friEndOfDay.setMaximumSize(timeFieldMax);
        friError = new JLabel("", SwingConstants.CENTER);
        friError.setFont(instructionsAndLabelsFont);
        friError.setForeground(darkHotPink);
        timetablePanel.add(friLabel);
        timetablePanel.add(fit.apply(friStartOfDay));
        timetablePanel.add(fit.apply(friEndOfDay));
        timetablePanel.add(friError);

        JLabel satLabel = new JLabel("Saturday", SwingConstants.CENTER);
        satLabel.setFont(instructionsAndLabelsFont);
        satLabel.setForeground(darkHotPink);
        satStartOfDay = new JTextField(4);
        satStartOfDay.setText("08:00");
        satStartOfDay.setPreferredSize(timeFieldMax);
        satStartOfDay.setMaximumSize(timeFieldMax);
        satEndOfDay = new JTextField(4);
        satEndOfDay.setText("16:00");
        satEndOfDay.setPreferredSize(timeFieldMax);
        satEndOfDay.setMaximumSize(timeFieldMax);
        satError = new JLabel("", SwingConstants.CENTER);
        satError.setFont(instructionsAndLabelsFont);
        satError.setForeground(darkHotPink);
        timetablePanel.add(satLabel);
        timetablePanel.add(fit.apply(satStartOfDay));
        timetablePanel.add(fit.apply(satEndOfDay));
        timetablePanel.add(satError);

        JLabel sunLabel = new JLabel("Sunday", SwingConstants.CENTER);
        sunLabel.setFont(instructionsAndLabelsFont);
        sunLabel.setForeground(darkHotPink);
        sunStartOfDay = new JTextField(4);
        sunStartOfDay.setText("08:00");
        sunStartOfDay.setPreferredSize(timeFieldMax);
        sunStartOfDay.setMaximumSize(timeFieldMax);
        sunEndOfDay = new JTextField(4);
        sunEndOfDay.setText("16:00");
        sunEndOfDay.setPreferredSize(timeFieldMax);
        sunEndOfDay.setMaximumSize(timeFieldMax);
        sunError = new JLabel("", SwingConstants.CENTER);
        sunError.setFont(instructionsAndLabelsFont);
        sunError.setForeground(darkHotPink);
        timetablePanel.add(sunLabel);
        timetablePanel.add(fit.apply(sunStartOfDay));
        timetablePanel.add(fit.apply(sunEndOfDay));
        timetablePanel.add(sunError);

        mainPanel.add(timetableWrapper, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setOpaque(false);

        JTextArea breakTimesInstructionsLabel = new JTextArea(
                "Input your ideal minimum and maximum time for your breaks       in minutes "
                        + "(Ex. 2 hours would be 120). Break time must be at least 1 minute."
        );
        breakTimesInstructionsLabel.setFont(instructionsAndLabelsFont);
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

        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(breakInstructionsWrapper);
        bottomPanel.add(Box.createVerticalStrut(10));

        JPanel breakTimesPanel = new JPanel();
        breakTimesPanel.setLayout(new BoxLayout(breakTimesPanel, BoxLayout.X_AXIS));
        breakTimesPanel.setOpaque(false);

        JLabel minBreakTimeLabel = new JLabel("Min Break Time: ");
        minBreakTimeLabel.setFont(instructionsAndLabelsFont);
        minBreakTimeLabel.setForeground(darkHotPink);
        minBreakTime = new JTextField(4);
        minBreakTime.setText("10");
        Dimension breakTimeSize = new Dimension(30, 18);
        minBreakTime.setMaximumSize(breakTimeSize);
        minBreakTime.setPreferredSize(breakTimeSize);

        JLabel maxBreakTimeLabel = new JLabel("Max Break Time: ");
        maxBreakTimeLabel.setFont(instructionsAndLabelsFont);
        maxBreakTimeLabel.setForeground(darkHotPink);
        maxBreakTime = new JTextField(4);
        maxBreakTime.setText("120");
        maxBreakTime.setMaximumSize(breakTimeSize);
        maxBreakTime.setPreferredSize(breakTimeSize);

        breakTimesPanel.add(minBreakTimeLabel);
        breakTimesPanel.add(minBreakTime);
        breakTimesPanel.add(Box.createHorizontalStrut(12));
        breakTimesPanel.add(maxBreakTimeLabel);
        breakTimesPanel.add(maxBreakTime);

        bottomPanel.add(breakTimesPanel);
        bottomPanel.add(Box.createVerticalStrut(10));

        JTextArea campusLocationLabel = new JTextArea(
                "Your desired campus location (exactly as it appears on your schedule or blank for no preference): "
        );
        campusLocationLabel.setFont(instructionsAndLabelsFont);
        campusLocationLabel.setForeground(darkHotPink);
        campusLocationLabel.setOpaque(false);
        campusLocationLabel.setEditable(false);
        campusLocationLabel.setFocusable(false);
        campusLocationLabel.setLineWrap(true);
        campusLocationLabel.setWrapStyleWord(true);
        campusLocationLabel.setBorder(BorderFactory.createEmptyBorder());
        campusLocationLabel.setColumns(32);

        JPanel campusLocationLabelWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        campusLocationLabelWrapper.setOpaque(false);
        campusLocationLabelWrapper.add(campusLocationLabel);

        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(campusLocationLabelWrapper);
        bottomPanel.add(Box.createVerticalStrut(10));

        JPanel campusLocationPanel = new JPanel();
        campusLocationPanel.setLayout(new BoxLayout(campusLocationPanel, BoxLayout.X_AXIS));
        campusLocationPanel.setOpaque(false);

        campusLocation = new JTextField(8);
        campusLocation.setText("");
        Dimension campusLocationSize = new Dimension(350, 18);
        campusLocation.setMaximumSize(campusLocationSize);
        campusLocation.setPreferredSize(campusLocationSize);

        campusLocationPanel.add(campusLocation);

        bottomPanel.add(campusLocationPanel);
        bottomPanel.add(Box.createVerticalStrut(10));

        JTextArea sharePathInfoLabel = new JTextArea(
                "Input the folder path you would like to save your top 3 schedules to "
                        + "(Ex. C:\\Users\\Selena\\Desktop\\Wishlist Schedule): "
        );
        sharePathInfoLabel.setFont(instructionsAndLabelsFont);
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

        bottomPanel.add(Box.createVerticalStrut(10));
        bottomPanel.add(shareInstructionsWrapper);
        bottomPanel.add(Box.createVerticalStrut(10));

        JPanel sharePathInfoPanel = new JPanel();
        sharePathInfoPanel.setLayout(new BoxLayout(sharePathInfoPanel, BoxLayout.X_AXIS));
        sharePathInfoPanel.setOpaque(false);

        topThreeSchedulesDestinationPath = new JTextField(10);
        topThreeSchedulesDestinationPath.setText("");
        Dimension topThreeSchedSize = new Dimension(350, 18);
        topThreeSchedulesDestinationPath.setMaximumSize(topThreeSchedSize);
        topThreeSchedulesDestinationPath.setPreferredSize(topThreeSchedSize);

        sharePathInfoPanel.add(Box.createHorizontalStrut(0));
        sharePathInfoPanel.add(topThreeSchedulesDestinationPath);

        bottomPanel.add(sharePathInfoPanel);
        bottomPanel.add(Box.createVerticalStrut(10));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setOpaque(false);

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

        bottomPanel.add(buttonsPanel);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        backgroundPanel.add(mainPanel, BorderLayout.CENTER);
        setContentPane(backgroundPanel);

        setVisible(false);
    }
    
	
	

	// Getters and Setters
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
	 * 
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
	 * Purpose: To return the minimum desired break time
	 * @return minDesiredBreakTime The minimum desired break time
	 */
	public Long getMinBreakTime()
	{
	    if (minBreakTime.getText().isEmpty())
	    {
	        return null;
	    }
	    return Long.parseLong(minBreakTime.getText());
	}




	/**
	 * Purpose: To return the maximum desired break time
	 * @return maxDesiredBreakTime The maximum desired break time
	 */
	public Long getMaxBreakTime()
	{
	    if (maxBreakTime.getText().isEmpty())
	    {
	        return null;
	    }
	    return Long.parseLong(maxBreakTime.getText());
	}




	/**
	 * Purpose: To return the desired start time for Monday
	 * @return monStartOfDay The desired start time for Monday
	 */
	public LocalTime getMonStartOfDay()
	{
	    if (monStartOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(monStartOfDay.getText());
	}





	/**
	 * Purpose: To return the desired @return monEndOfDay The desired end time for Monday * Purpose: To return the desired end time for Monday
	 */
	public LocalTime getMonEndOfDay()
	{
	    if (monEndOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(monEndOfDay.getText());
	}





	/**
	 * Purpose: To return the desired * @return tuesStartOfDay The desired start time for Tuesday * Purpose: To return the desired start time for Tuesday
	 */
	public LocalTime getTuesStartOfDay()
	{
	    if (tuesStartOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(tuesStartOfDay.getText());
	}





	/**
	 * Purpose: To return the desired end time for Tuesday
	 * @return tuesEndOfDay The desired end time for Tuesday
	 */
	public LocalTime getTuesEndOfDay()
	{
	    if (tuesEndOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(tuesEndOfDay.getText());
	}




	public LocalTime getWedStartOfDay()
	{
	    if (wedStartOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(wedStartOfDay.getText());
	}




	/**
	 * Purpose: To return the desired end time for Wednesday
	 * @return wedEndOfDay The desired end time for Wednesday
	 */
	public LocalTime getWedEndOfDay()
	{
	    if (wedEndOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(wedEndOfDay.getText());
	}




	/**
	 * Purpose: To return the desired start time for Thursday
	 * @return thursStartOfDay The desired start time for Thursday
	 */
	public LocalTime getThursStartOfDay()
	{
	    if (thursStartOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(thursStartOfDay.getText());
	}




	/**
	 * Purpose: To return the desired end time for Thursday
	 * @return thursEndOfDay The desired end time for Thursday
	 */
	public LocalTime getThursEndOfDay()
	{
	    if (thursEndOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(thursEndOfDay.getText());
	}




	/**
	 * Purpose: To return the desired start time for Friday
	 * @return friStartOfDay The desired start time for Friday
	 */
	public LocalTime getFriStartOfDay()
	{
	    if (friStartOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(friStartOfDay.getText());
	}




	/**
	 * Purpose: To return the desired end time for Friday
	 * @return friEndOfDay The desired end time for Friday
	 */
	public LocalTime getFriEndOfDay()
	{
	    if (friEndOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(friEndOfDay.getText());
	}




	/**
	 * Purpose: To return the desired start time for Saturday
	 * @return satStartOfDay The desired start time for Saturday
	 */
	public LocalTime getSatStartOfDay()
	{
	    if (satStartOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(satStartOfDay.getText());
	}




	/**
	 * Purpose: To return the desired end time for Saturday
	 * @return satEndOfDay The desired end time for Saturday
	 */
	public LocalTime getSatEndOfDay()
	{
	    if (satEndOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(satEndOfDay.getText());
	}




	/**
	 * Purpose: To return the desired start time for Sunday
	 * @return sunStartOfDay The desired start time for Sunday
	 */
	public LocalTime getSunStartOfDay()
	{
	    if (sunStartOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(sunStartOfDay.getText());
	}




	/**
	 * Purpose: To return the desired end time for Sunday
	 * @return sunEndOfDay The desired end time for Sunday
	 */
	public LocalTime getSunEndOfDay()
	{
	    if (sunEndOfDay.getText().isEmpty())
	    {
	        return null;
	    }
	    return LocalTime.parse(sunEndOfDay.getText());
	}




	/**
	 * Purpose: To return the desired campus location
	 * @return campusLocation The desired campus location
	 */
	public String getCampusLocation()
	{
		return campusLocation.getText();
	}




	/**
	 * Purpose: To return the desired * @return topThreeSchedulesDestinationPath The desired share path for the top three schedules * Purpose: To return the desired share path for the top three schedules
	 */
	public Path getTopThreeSchedulesDestinationPath()
	{
	    if (topThreeSchedulesDestinationPath.getText().isEmpty())
	    {
	        return null;
	    }

	    String pathText = topThreeSchedulesDestinationPath.getText();

	    // Remove quotation marks if user entered them
	    pathText = pathText.replace("\"", "");

	    return Path.of(pathText);
	}





	/**
	 * Purpose: To set the error message for the desired start and end time for Monday
	 * @param string The error message for the desired start and end time for Monday
	 */
	public void setMonError(String string)
	{
		monError.setText(string);		
	}


	/**
	 * Purpose: To set the error message for the desired start and end time for Tuesday
	 * @param string The error message for the desired start and end time for Tuesday
	 */
	public void setTuesError(String string)
	{
		tuesError.setText(string);				
	}

	/**
	 * Purpose: To set the error message for the desired start and end time for Wednesday
	 * @param string The error message for the desired start and end time for Wednesday
	 */
	public void setWedError(String string)
	{
		wedError.setText(string);		
	}

	/**
	 * Purpose: To set the error message for the desired start and end time for Thursday
	 * @param string The error message for the desired start and end time for Thursday
	 */
	public void setThursError(String string)
	{
		thursError.setText(string);		
	}

	/**
	 * Purpose: To set the error message for the desired start and end time for Friday
	 * @param string The error message for the desired start and end time for Friday
	 */
	public void setFriError(String string)
	{
		friError.setText(string);		
	}


	/**
	 * Purpose: To set the error message for the desired start and end time for Saturday
	 * @param string The error message for the desired start and end time for Saturday
	 */
	public void setSatError(String string)
	{
		satError.setText(string);		
	}




	/**
	 * Purpose: To set the error message for the desired start and end time for Sunday
	 * @param string The error message for the desired start and end time for Sunday
	 */
	public void setSunError(String string)
	{
		sunError.setText(string);		
	}
	
	

}
