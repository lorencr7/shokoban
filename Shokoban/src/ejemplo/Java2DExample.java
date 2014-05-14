package ejemplo;

import model.GameController;

public class Java2DExample{

	public static void main(String args[]) {
		GameController game = new GameController();
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