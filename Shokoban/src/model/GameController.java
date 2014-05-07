/**
 * 
 */
package model;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	private static JFrame mainPanel;
	public static final int unitSize = 80;

	// no-argument constructor
	public GameController(int x, int y) {
		this.board = new Board(x, y);
		this.collision = new Collision();
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
		this.createPanel();
		this.createBoxes();
		this.createWalls();
		this.createStorages();
		this.createPlayer();

		GameController.mainPanel.setVisible(true);
	}

	public void createPanel() {
		GameController.mainPanel = new JFrame("Sokoban");
		GameController.mainPanel.setBounds(0, 0,
				this.board.getWidth() * unitSize, this.board.getHeight()
						* unitSize + 20);
		// this.mainPanel.setBackground(new Color(83, 83, 83));
		// this.mainPanel.pack();
		GameController.mainPanel.setLayout(null);
		GameController.mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameController.mainPanel.setResizable(false);
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
			wall.draw(GameController.mainPanel);
			this.board.getShapes().add(wall);
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
			storage.draw(GameController.mainPanel);
			this.board.getShapes().add(storage);
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
			box.draw(GameController.mainPanel);
			this.board.getShapes().add(box);
		}
	}

	public void createPlayer() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(2, 2));
		for (Position position : positions) {
			Player player = new Player(position);
			player.draw(GameController.mainPanel);
			this.board.getShapes().add(player);
			// this.player = player;
		}
	}

	public boolean move(Shape shape1, Integer dir) {
		Position next = shape1.getPosition();

		Shape shape2 = this.getNextShape(next, dir);

		if (collision.collide(shape1, shape2)) {
			// Push
			next = shape2.getPosition();
			Shape shape3 =this.getNextShape(next, dir);
			if (collision.collide(shape2, shape3)) {
				return false;
			} else {
				// move shape 1 and shape 2
				shape2.move(dir);
				shape1.move(dir);
				return true;
			}
		}
		// simple move
		shape1.move(dir);
		return true;

	}

	public void draw(JFrame container) {
		this.createInitialBoard();
	}
	
	/**
	 * @return the mainPanel
	 */
	public static JFrame getMainPanel() {
		return mainPanel;
	}
	
	/**
	 * @param pos
	 * @param dir
	 * @return
	 */
	public Shape getNextShape(Position pos, Integer dir) {

		if (Direction.NORTH == dir || Direction.SOUTH == dir) {
			pos.setY(pos.getY() + dir);
		}

		if (Direction.EAST == dir || Direction.WEST == dir) {
			pos.setX(pos.getX() + dir);
		}

		for (Shape s : this.board.getShapes()) {
			if (s.getPosition().equals(pos)) {
				return s;
			}
		}
		return null;
	}
}
