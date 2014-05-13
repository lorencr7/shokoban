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
		GameController game = GameController.getInstance();
		game.initGame();
		
		
	    //Schedule a job for the event-dispatching thread:
	    //creating and showing this application's GUI.
	    /*
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	        createAndShowGUI();
	      }
	    });
	    */
	  }
}