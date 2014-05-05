package ejemplo;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class Java2DExample extends JFrame {

	private ImagePanel imagePanel;

	/**
	 * @return the imagePanel
	 */
	public ImagePanel getImagePanel() {
		return imagePanel;
	}

	/**
	 * @param imagePanel
	 *            the imagePanel to set
	 */
	public void setImagePanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}

	// initialize JMenuItems
	public Java2DExample() {
		super("Java 2D Image Processing Demo");
		

		// initialize ImagePanel
		imagePanel = new ImagePanel();
		
		imagePanel.createImage("sokoban/box.jpg");
		imagePanel.createImage("sokoban/player.jpg");
		imagePanel.setX(20);
		imagePanel.setY(50);

		getContentPane().add(imagePanel, BorderLayout.CENTER);

	} // end Java2DExample constructor

	// start program
	public static void main(String args[]) {
		Java2DExample application = new Java2DExample();

		application.setDefaultCloseOperation(EXIT_ON_CLOSE);
		application.pack();
		application.setSize(723, 511);
		application.setVisible(true);
	}
}