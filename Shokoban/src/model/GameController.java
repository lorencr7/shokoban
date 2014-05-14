package model;

import java.awt.Color;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import sd.emse.shokoban.Board;
import sd.emse.shokoban.Box;
import sd.emse.shokoban.Collision;
import sd.emse.shokoban.Direction;
import sd.emse.shokoban.Player;
import sd.emse.shokoban.Position;
import sd.emse.shokoban.Shape;
import sd.emse.shokoban.Square;
import sd.emse.shokoban.Storage;
import sd.emse.shokoban.Wall;

public class GameController implements Observer {
	
	// Constants
	public static final int SQUARE_SIZE = 80;
	private static final int BOARD_WIDTH = 8;
	private static final int BOARD_HEIGHT = 9;

	private Board board;
	private Collision collision;
	private JFrame mainPanel;
	private int width = BOARD_WIDTH;
	private int height = BOARD_HEIGHT;

	public GameController() {
		board = new Board(0,0);
		collision = new Collision();
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
		
		this.createPanel();
		this.createBoardShapes(boardMap);
		this.drawAllShapes();
	}

	public void createPanel() {
		mainPanel = new JFrame("Sokoban");
		mainPanel.setBackground(new Color(83, 83, 83));
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setLayout(null);
		//Size and display the window.
	    Insets insets = mainPanel.getInsets();
		mainPanel.setSize(getWidth() * SQUARE_SIZE+insets.left + insets.right, 
				getHeight() * SQUARE_SIZE + insets.top + insets.bottom);
		
		mainPanel.setResizable(false);		
	}
		
	private void createBoardShapes(String[] boardMap) {
		
		for (int y = 0; y < boardMap.length; y++) {
			String line = boardMap[y];
			for (int x = 0; x < line.length(); x++) {
				Position position = new Position(x,y);
				char character = line.charAt(x);
				Shape shape = null;
				switch (character) {
				case PLAYER:
					shape = new Player(position);
					board.getShapes().add(shape);
					this.mainPanel.addKeyListener((Player)shape);
					shape.addObserver(this);
					break;
				case WALL:
					shape = new Wall(position);
					board.getShapes().add(shape);
					break;
				case BOX:
					//box
					shape = new Box(position);
					board.getShapes().add(shape);
					break;
				case BOXINSTORAGE:
					//box
					shape = new Box(position);
					board.getShapes().add(shape);
					//floor
					shape = new Storage(position);
					board.getShapes().add(shape);
					break;
				case STORAGE:
					shape = new Storage(position);
					board.getShapes().add(shape);
					break;
				case EMPTYSQUARE:
					shape = new Square(position);
					board.getShapes().add(shape);
					break;
				default:
					break;
				}				
			}
		}

		//Generate empty squares
		ArrayList<Shape> emptySquares = new ArrayList<>();
		for (Shape shape : this.board.getShapes()) {
			if (!(shape instanceof Square) && !(shape instanceof Wall)) {
				Square floor = new Square(shape.getPosition());
				emptySquares.add(floor);
			}
		}
		this.board.getShapes().addAll(emptySquares);
		
		//Sort by zIndex
		Collections.sort(this.board.getShapes());
	}

	private void drawAllShapes() {
		for (Shape shape : this.board.getShapes()) {
			shape.draw(this.mainPanel);
		}

		this.mainPanel.setVisible(true);
	}

	/**
	 * @return the mainPanel
	 */
	public JFrame getMainPanel() {
		return mainPanel;
	}

	public void play(Shape shape1, Direction direction) {
		Position next = shape1.getPosition();
		Shape shape2 = getNextShape(next, direction);
		if (shape2 == null ) {
			return;
		}
		if (shape2.isMovable()) {
			Shape shape3 = getNextShape(shape2.getPosition(), direction);
			if (shape3 == null ) {
				return;
			}
			if (collision.collide(shape2, shape3)) {
				return;
			} else {
				// Push: move shape 1 and shape 2
				shape2.move(direction);
				shape2.draw(mainPanel);
				shape1.move(direction);
				shape1.draw(mainPanel);
			}
		} else if (!collision.collide(shape1, shape2)) {
			// simple move
			shape1.move(direction);
			shape1.draw(mainPanel);
		}
	}

	/**
	 * @param pos
	 * @param direction
	 * @return
	 */
	public Shape getNextShape(Position pos, Direction direction) {
		Position nextPos = new Position(pos);
		switch (direction) {
		case NORTH:
			nextPos.setY(nextPos.getY() - 1);
			break;
		case SOUTH:
			nextPos.setY(nextPos.getY() + 1);
			break;

		case EAST:
			nextPos.setX(nextPos.getX() + 1);
			break;
		case WEST:
			nextPos.setX(nextPos.getX() - 1);
			break;

		default:
			break;
		}

		for (Shape s : board.getShapes()) {
			if (s.getPosition().equals(nextPos)) {
				return s;
			}
		}
		return null;
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("player moved");
		Direction direction = (Direction) arg;
		if (o instanceof Player) {
			Player player = (Player) o;
			play(player, direction);
			//TODO TEST
//			mainPanel.repaint();
			
		}
	}


	// class PlayerAdapter extends KeyAdapter {
	//
	// /**
	// *
	// */
	// public PlayerAdapter() {
	//
	// }
	//
	// public void keyPressed(KeyEvent e) {
	// Shape player = (Shape) getBoard().getShapes().get(
	// getBoard().getShapes().size() - 1);
	//
	// if (player.pos.getY() <= 0) {
	// player.pos.setY(0);
	// } else if (player.pos.getY() > (getHeight() - 1)) {
	// player.pos.setY(getHeight() - 1);
	// }
	//
	// if (player.pos.getX() <= 0) {
	// player.pos.setX(0);
	// } else if (player.pos.getX() > (getWidth() - 1)) {
	// player.pos.setX(getWidth() - 1);
	// }
	//
	// switch (e.getKeyCode()) {
	//
	// case KeyEvent.VK_UP:
	//
	// collide(player, Direction.NORTH);
	//
	// // pos.setY(pos.getY() - 1);
	//
	// break;
	// case KeyEvent.VK_DOWN:
	//
	// collide(player, Direction.SOUTH);
	// // pos.setY(pos.getY() + 1);
	// break;
	// case KeyEvent.VK_LEFT:
	// collide(player, Direction.WEST);
	// // pos.setX(pos.getX() - 1);
	// break;
	// case KeyEvent.VK_RIGHT:
	// collide(player, Direction.EAST);
	// // pos.setX(pos.getX() + 1);
	// break;
	// default:
	// break;
	// }
	//
	// board.getShapes().get(
	// getBoard().getShapes().size() - 1).setPosition(player.getPosition());
	// board.getShapes().get(
	// getBoard().getShapes().size() - 1).draw(getMainPanel());
	//
	// }
	// }

}
