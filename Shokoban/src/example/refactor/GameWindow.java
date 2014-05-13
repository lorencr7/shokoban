package example.refactor;

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
		img.setSize(723, 511);

		application.getContentPane().add(img, null);

		application.setVisible(true);
	}
}