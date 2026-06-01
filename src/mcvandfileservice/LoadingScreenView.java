package mcvandfileservice;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import java.awt.*;

/**
 * Purpose: The reponsibility of LoadingScreen is ...
 *
 * LoadingScreen is-a ...
 * LoadingScreen is ...
 */
import javax.swing.*;
import java.awt.*;

public class LoadingScreenView extends JFrame
{
	// Instance Variables
	private AppController appController;
    private JProgressBar progressBar;
    private JLabel progressLabel;
    private JLabel backgroundLabel;
    private JLabel timeLabel;

    public LoadingScreenView()
    {
    	setTitle("Loading...");
    	setSize(700, 800);
    	setLocationRelativeTo(null);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	setUndecorated(true);

    	// Load GIF (no scaling)
    	ImageIcon gifIcon = new ImageIcon(
    	        getClass().getResource("/images/Loading Screen Gif.gif")
    	);

    	// Load background image
    	Image bgImage = new ImageIcon(
    	        getClass().getResource("/images/Loading Screen Background.jpg")
    	).getImage();

    	JLabel basePanel = new JLabel()
    	{
    	    @Override
    	    protected void paintComponent(Graphics g)
    	    {
    	        super.paintComponent(g);

    	        // Stretch background to fill entire window
    	        g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
    	    }
    	};

    	basePanel.setLayout(new BorderLayout());


    	// GIF on top (original size)
    	backgroundLabel = new JLabel(gifIcon);
    	backgroundLabel.setLayout(new BorderLayout());
    	backgroundLabel.setOpaque(false);

    	// Add GIF on top of background
    	basePanel.add(backgroundLabel, BorderLayout.CENTER);

        // North panel for loading text and progress bar
        JPanel topPanel = new JPanel();
        topPanel.setOpaque(false);
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));

        // Progress label
        progressLabel = new JLabel("0%");
        progressLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        progressLabel.setFont(new Font("Arial", Font.BOLD, 16));
        progressLabel.setForeground(Color.WHITE);

        // Time label
        timeLabel = new JLabel("Calculating time...");
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 12));
        timeLabel.setForeground(Color.WHITE);

        // Progress bar
        progressBar = new JProgressBar();
        Color electricBlue = new Color(100, 149, 237);
        progressBar.setForeground(electricBlue);
        progressBar.setBackground(Color.WHITE);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setStringPainted(false);
        progressBar.setPreferredSize(new Dimension(300, 25));
        progressBar.setMaximumSize(new Dimension(300, 25));
        progressBar.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(progressLabel);
        topPanel.add(Box.createVerticalStrut(5));
        topPanel.add(timeLabel);
        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(progressBar);

        backgroundLabel.add(topPanel, BorderLayout.NORTH);

        setContentPane(basePanel);
        setVisible(false);
    }

    /**
     * Purpose: To update the progress bar and the progress label with the current
     * @param current The current progress value
     * @param total The total value for completion
     */
    public void setProgress(int current, int total)
    {
        progressBar.setMaximum(total);
        progressBar.setValue(current);

        int percentComplete = 0;

        if (total > 0)
        {
            percentComplete = (int) (((double) current / total) * 100);
        }

        progressLabel.setText(percentComplete + "%");
    }


    /**
	 * Purpose: To update the time remaining label with the estimated time left
	 * for the loading process.
	 * 
	 * @param millis The estimated time remaining in milliseconds.
	 */
	public void setTimeRemaining(long millis)
	{
		if (millis <= 0)
		{
			timeLabel.setText("Calculating time...");
			return;
		}

		long seconds = millis / 1000;
		long minutes = seconds / 60;
		seconds = seconds % 60;

		timeLabel.setText("About " + minutes + "m " + seconds + "s remaining");
	}

	/**
	 * Purpose: To set the AppController reference for this view, allowing it to communicate
	 * @param newAppController
	 */
	public void setAppController(AppController newAppController)
	{
		appController = newAppController;
	}

    
}
