package ejemplo;

import javax.swing.*;

import sd.emse.shokoban.Board;

public class Java2DExample extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// start program
	public static void main(String args[]) {
		Board board = new Board(8, 9);
		board.draw(null);
	}
}