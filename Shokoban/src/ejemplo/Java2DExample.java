package ejemplo;

import javax.swing.JFrame;

import model.GameController;

public class Java2DExample extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// start program
	public static void main(String args[]) {
		GameController game = new GameController(8, 9);
		game.draw(null);
	}
}