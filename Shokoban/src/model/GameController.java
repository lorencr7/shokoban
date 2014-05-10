/**
 * 
 */
package model;

import java.util.ArrayList;

import javax.swing.JFrame;

import sd.emse.shokoban.Board;
import sd.emse.shokoban.Box;
import sd.emse.shokoban.Collision;
import sd.emse.shokoban.Direction;
import sd.emse.shokoban.Player;
import sd.emse.shokoban.Position;
import sd.emse.shokoban.Shape;
import sd.emse.shokoban.Storage;
import sd.emse.shokoban.Wall;

public class GameController {

	// shapes contained in model
	private Board board;
	private Collision collision;
	private JFrame mainPanel;
	public static final int unitSize = 80;
	private int width;
	private int height;
	private static GameController gameController = null;
	//private PlayerAdapter playerListener;

	public static GameController getInstance() {
		if (gameController == null) {
			gameController = new GameController(8, 9);
		}
		return gameController;
	}

	// no-argument constructor
	private GameController(int x, int y) {
		width = x;
		height = y;
		board = new Board(x, y);
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

	/**
	 * @return the collision
	 */
	public Collision getCollision() {
		return collision;
	}

	/**
	 * @param collision
	 *            the collision to set
	 */
	public void setCollision(Collision collision) {
		this.collision = collision;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board
	 *            the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	public void createInitialBoard() {
		createPanel();
		createBoxes();
		createWalls();
		createStorages();
		createPlayer();
//		getMainPanel().addKeyListener(new PlayerAdapter());
		mainPanel.setVisible(true);
	}

	public void createPanel() {
		mainPanel = new JFrame("Sokoban");
		mainPanel.setBounds(0, 0, getWidth() * unitSize, getHeight() * unitSize
				+ 20);
		// mainPanel.setBackground(new Color(83, 83, 83));
		// mainPanel.pack();
		mainPanel.setLayout(null);
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setResizable(false);
		// SokobanModel.mainPanel.addKeyListener(new BoardAdapter());

	}

	public void createWalls() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(2, 0));
		positions.add(new Position(3, 0));
		positions.add(new Position(4, 0));
		positions.add(new Position(5, 0));
		positions.add(new Position(6, 0));

		positions.add(new Position(0, 1));
		positions.add(new Position(1, 1));
		positions.add(new Position(2, 1));
		positions.add(new Position(6, 1));

		positions.add(new Position(0, 2));
		positions.add(new Position(6, 2));
		positions.add(new Position(0, 3));
		positions.add(new Position(1, 3));
		positions.add(new Position(2, 3));
		positions.add(new Position(6, 3));

		positions.add(new Position(0, 4));
		positions.add(new Position(2, 4));
		positions.add(new Position(3, 4));
		positions.add(new Position(6, 4));

		positions.add(new Position(0, 5));
		positions.add(new Position(2, 5));
		positions.add(new Position(6, 5));
		positions.add(new Position(7, 5));

		positions.add(new Position(0, 6));
		positions.add(new Position(7, 6));

		positions.add(new Position(0, 7));
		positions.add(new Position(7, 7));

		positions.add(new Position(0, 8));
		positions.add(new Position(1, 8));
		positions.add(new Position(2, 8));
		positions.add(new Position(3, 8));
		positions.add(new Position(4, 8));
		positions.add(new Position(5, 8));
		positions.add(new Position(6, 8));
		positions.add(new Position(7, 8));

		for (Position position : positions) {
			Wall wall = new Wall(position);
			wall.draw(mainPanel);
			board.getShapes().add(wall);
		}
	}

	public void createStorages() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(1, 2));
		positions.add(new Position(6, 4));
		positions.add(new Position(1, 4));
		positions.add(new Position(4, 5));
		positions.add(new Position(3, 6));
		positions.add(new Position(6, 6));
		positions.add(new Position(4, 7));
		for (Position position : positions) {
			Storage storage = new Storage(position);
			storage.draw(mainPanel);
			board.getShapes().add(storage);
		}
	}

	public void createBoxes() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(3, 2));
		positions.add(new Position(4, 3));
		positions.add(new Position(4, 4));
		positions.add(new Position(1, 6));
		positions.add(new Position(3, 6));
		positions.add(new Position(4, 6));
		positions.add(new Position(5, 6));
		for (Position position : positions) {
			Box box = new Box(position);
			box.draw(mainPanel);
			board.getShapes().add(box);
		}
	}

	public void createPlayer() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(2, 2));
		for (Position position : positions) {
			Player player = new Player(position);
			player.draw(mainPanel);
			board.getShapes().add(player);
			// player = player;
		}
	}

	public void draw(JFrame container) {
		createInitialBoard();
	}

	/**
	 * @return the mainPanel
	 */
	public JFrame getMainPanel() {
		return mainPanel;
	}

	public boolean move(Shape player, String dir) {
		Position next = player.getPosition();

		Shape shape2 = getNextShape(next, dir);

		if (collision.collide(player, shape2)) {
			// Push
			next = shape2.getPosition();
			Shape shape3 = getNextShape(next, dir);
			if (collision.collide(shape2, shape3)) {
				return false;
			} else {
				// move shape 1 and shape 2
				shape2.move(dir);
				player.move(dir);
				return true;
			}
		}
		// simple move
		player.move(dir);
		return true;

	}

	public void collide(Shape shape1, String direction) {
		Position next = shape1.getPosition();

		Shape shape2 = getNextShape(next, direction);

		if (collision.collide(shape1, shape2)) {
			// Push
			next = shape2.getPosition();
			Shape shape3 = getNextShape(next, direction);
			if (collision.collide(shape2, shape3)) {
				return;
			} else {
				// move shape 1 and shape 2
				shape2.move(direction);
				shape1.move(direction);
				return;
			}
		}
		// simple move
		shape1.move(direction);
	}

	/**
	 * @param pos
	 * @param direction
	 * @return
	 */
	public Shape getNextShape(Position pos, String direction) {

		
		switch (direction) {
		case "North":
			pos.setY(pos.getY() - 1);
			break;
		case "South":
			pos.setY(pos.getY() + 1);
			break;

		case "East":
			pos.setX(pos.getX() + 1);
			break;
		case "West":
			pos.setX(pos.getX() - 1);
			break;

		default:
			break;
		}
		
		
		for (Shape s : board.getShapes()) {
			if (s.getPosition().equals(pos)) {
				return s;
			}
		}
		return null;
	}

//	class PlayerAdapter extends KeyAdapter {
//
//		/**
//		 * 
//		 */
//		public PlayerAdapter() {
//
//		}
//
//		public void keyPressed(KeyEvent e) {
//			Shape player = (Shape) getBoard().getShapes().get(
//					getBoard().getShapes().size() - 1);
//
//			if (player.pos.getY() <= 0) {
//				player.pos.setY(0);
//			} else if (player.pos.getY() > (getHeight() - 1)) {
//				player.pos.setY(getHeight() - 1);
//			}
//
//			if (player.pos.getX() <= 0) {
//				player.pos.setX(0);
//			} else if (player.pos.getX() > (getWidth() - 1)) {
//				player.pos.setX(getWidth() - 1);
//			}
//
//			switch (e.getKeyCode()) {
//
//			case KeyEvent.VK_UP:
//
//				collide(player, Direction.NORTH);
//
//				// pos.setY(pos.getY() - 1);
//
//				break;
//			case KeyEvent.VK_DOWN:
//
//				collide(player, Direction.SOUTH);
//				// pos.setY(pos.getY() + 1);
//				break;
//			case KeyEvent.VK_LEFT:
//				collide(player, Direction.WEST);
//				// pos.setX(pos.getX() - 1);
//				break;
//			case KeyEvent.VK_RIGHT:
//				collide(player, Direction.EAST);
//				// pos.setX(pos.getX() + 1);
//				break;
//			default:
//				break;
//			}
//
//			board.getShapes().get(
//					getBoard().getShapes().size() - 1).setPosition(player.getPosition());
//			board.getShapes().get(
//					getBoard().getShapes().size() - 1).draw(getMainPanel());
//			
//		}
//	}

}
