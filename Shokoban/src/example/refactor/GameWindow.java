package example.refactor;
import java.awt.BorderLayout;

import javax.swing.JFrame;

public class GameWindow {

	public static void main(String args[]) {
		JFrame application = new JFrame();
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setSize(723, 511);
		application.setResizable(false);
		
		// initialize ImagePanel
		Canvas img = new Canvas();
		img.createImage("sokoban/player.jpg");
		
		Canvas img2 = new Canvas();
		img2.createImage("sokoban/player.jpg");
		application.getContentPane().add(img2, BorderLayout.CENTER);
		
		application.setVisible(true);
	}
}