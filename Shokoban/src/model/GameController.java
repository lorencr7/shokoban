package model;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sd.emse.shokoban.Board;
import sd.emse.shokoban.Direction;
import sd.emse.shokoban.Shape;

/**
 * Class that handles keyboard events from the user and processes the rules of the game.  
 */
public class GameController implements KeyListener {

	/**
	 * Size in pixels of the squares in the board 
	 */
	public static final int SQUARE_SIZE = 80;
	/**
	 * Number of horizontal squares of the board
	 */
	private int width = 0;
	/**
	 * Number of vertical squares of the board
	 */
	private int height = 0;
	
	private Board board;
	
	private JFrame mainPanel;
	
	
	public void initGame() {
		createInitialBoard();
	}
	
	
	/**
	 * Creation of the board. For changing the setup of the board use the following char codes,
	 * (each line represent a row in the real board).
	 * The codes of the shapes are: <br>
	 * <ul>
	 * 	<li>P: Player</li>
	 *  <li>W: Wall</li>
	 *  <li>S: Storage</li>
	 *  <li>B: Box</li>
	 *  <li>X: Box in storage</li>
	 *  <li>' ': A blank is an empty square</li>
	 * </ul>
	 */
	private void createInitialBoard() {
		
		String [] boardMap = {
				"  WWWWW ",//1
				"WWW   W ",//2
				"WSPB  W ",//3
				"WWW BSW ",//4
				"WSWWB W ",//5
				"W W S WW",//6
				"WB XBBSW",//7
				"W   S  W",//8
				"WWWWWWWW"//9
		};
		
		for (String string : boardMap) {
			this.width = Math.max(this.width, string.length());
		}
		this.height = boardMap.length;
		this.board = new Board(boardMap);	
		this.createPanel();
		this.board.draw(mainPanel);
		this.mainPanel.setVisible(true);	
	}

	/**
	 * Set size and properties of the main window that contains the board.
	 */
	private void createPanel() {
		mainPanel = new JFrame("Sokoban");
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setLayout(null);
		
		Insets insets = mainPanel.getInsets();
		mainPanel.setSize(this.width * SQUARE_SIZE+insets.left + insets.right, 
				this.height * SQUARE_SIZE + insets.top + insets.bottom + 20);

		mainPanel.setResizable(false);		
		this.mainPanel.addKeyListener(this);
	}

	/**
	 * This method is called in each player's move to check if the player has won. 
	 */
	private void checkWinConditions () {
		ArrayList<Shape> boxes = this.board.getBoxes();
		ArrayList<Shape> storages = this.board.getStorages();
		int numberOfBoxesInStorages = 0;
		boolean victory = false;
		for (Shape storage : storages) {
			for (Shape box : boxes) {
				if (box.getPosition().equals(storage.getPosition())) {
					numberOfBoxesInStorages++;
					break;
				}
			}
		}
		
		System.out.println("Boxes in target: "+numberOfBoxesInStorages + " vs total storage squares: " + storages.size());
		
		victory = numberOfBoxesInStorages == storages.size();
		if (victory) {
			JOptionPane.showMessageDialog(this.mainPanel, "Congratulations!! you have won the game!!");
			this.mainPanel.removeKeyListener(this);
			System.out.println("Congratulations!! you won the game!!");
		}
	}

	/**
	 * Handling of key events: It processes the keys for movement (the arrows up, dowm, right, left)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		Direction direction = null;
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			direction = Direction.NORTH;
			break;
		case KeyEvent.VK_DOWN:
			direction = Direction.SOUTH;
			break;
		case KeyEvent.VK_LEFT:
			direction = Direction.WEST;
			break;
		case KeyEvent.VK_RIGHT:
			direction = Direction.EAST;
			break;
		default:
			break;
		}
		
		if (direction != null) {
			
			// Move shapes in the desired direction: Only movable shapes will respond
			ArrayList<Shape> boardState = this.board.getShapes();
			for (Shape shape : this.board.getShapes()) {
				shape.move(direction, boardState);
			}
			this.checkWinConditions();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

}
