package mcvandfileservice;

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;
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
 * BackgroundPanel is-a JPanel
 */
public class BackgroundPanel extends JPanel
{
	// Instance Variables
	private Image backgroundImage;

	public BackgroundPanel(URL url)
	{
		backgroundImage = new ImageIcon(url).getImage();
	}

	/**
	 * Purpose: To paint the background image on the panel
	 * @param g The Graphics object to use for painting
	 */
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
	}

}
