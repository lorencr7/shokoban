package model;

import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import sd.emse.shokoban.Board;
import sd.emse.shokoban.Box;
import sd.emse.shokoban.Direction;
import sd.emse.shokoban.Player;
import sd.emse.shokoban.Position;
import sd.emse.shokoban.Shape;
import sd.emse.shokoban.Square;
import sd.emse.shokoban.Storage;
import sd.emse.shokoban.Wall;

public class GameController implements KeyListener {

	// Constants
	public static final int SQUARE_SIZE = 80;
	//private static int BOARD_WIDTH;
	//private static int BOARD_HEIGHT;

	private Board board;
	private JFrame mainPanel;
	private int width = 0;
	private int height = 0;

	public GameController() {
		board = new Board(0,0);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}


	public void initGame() {
		draw();
	}

	public void draw() {
		createInitialBoard();
	}

	public final char WALL = 'W';
	public final char PLAYER = 'P';
	public final char STORAGE = 'S';
	public final char EMPTYSQUARE = ' ';
	public final char BOX = 'B';
	public final char BOXINSTORAGE = 'X';

	public void createInitialBoard() {


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

		this.createPanel();
		this.createBoardShapes(boardMap);
		this.board.draw(mainPanel);
		this.mainPanel.setVisible(true);		
	}

	public void createPanel() {
		mainPanel = new JFrame("Sokoban");
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setLayout(null);
		//Size and display the window.
		Insets insets = mainPanel.getInsets();
		mainPanel.setSize(getWidth() * SQUARE_SIZE+insets.left + insets.right, 
				getHeight() * SQUARE_SIZE + insets.top + insets.bottom + 20);

		mainPanel.setResizable(false);		
		this.mainPanel.addKeyListener(this);
	}

	private void createBoardShapes(String[] boardMap) {

		for (int y = 0; y < boardMap.length; y++) {
			String line = boardMap[y];
			for (int x = 0; x < line.length(); x++) {
				Position position = new Position(x,y);
				char character = line.charAt(x);
				Shape shape = null;
				switch (character) {
				case WALL:
					shape = new Wall(position);
					break;
				case PLAYER:
					shape = new Player(position);
					break;
				case STORAGE:
					shape = new Storage(position);
					break;
				case BOX:
					shape = new Box(position);
					break;
				case BOXINSTORAGE:
					shape = new Storage(position);//TODO CREATE SPECIAL SHAPE OR SOMETHING THAT CHANGES THE BOX COLOR		
					Shape storage = new Box(position);
					board.getShapes().add(storage);
					storage.draw(this.mainPanel);
				default:
					break;
				}
				if (character != EMPTYSQUARE) {
					board.getShapes().add(shape);
					shape.draw(this.mainPanel);
				}

				Shape square = new Square(position);
				square.draw(this.mainPanel);
				board.getShapes().add(square);

				//Sort by zIndex
				//Collections.sort(this.board.getShapes());
			}
		}
	}


	/**
	 * @return the mainPanel
	 */
	public JFrame getMainPanel() {
		return mainPanel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
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
			ArrayList<Shape> boardState = this.board.getShapes();
			for (Shape shape : this.board.getShapes()) {//Mando mover todas las figuras
				shape.move(direction, boardState);
			}
			this.checkWinConditions();
		}
	}

	void checkWinConditions () {
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
		System.out.println(numberOfBoxesInStorages + " vs " + storages.size());
		victory = numberOfBoxesInStorages == storages.size();
		if (victory) {
			JOptionPane.showMessageDialog(this.mainPanel, "Congratulations!! you won the game!!");
			this.mainPanel.removeKeyListener(this);
			System.out.println("Congratulations!! you won the game!!");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
