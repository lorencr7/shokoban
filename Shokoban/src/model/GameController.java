package model;

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

	// shapes contained in model
	private Board board;
	private Collision collision;
	private JFrame mainPanel;
	public static final int unitSize = 80;
	private int width;
	private int height;
	private static GameController gameController = null;

	// private PlayerAdapter playerListener;

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
	public void createPanel() {
		mainPanel = new JFrame("Sokoban");
		mainPanel.setBounds(0, 0, getWidth() * unitSize, getHeight() * unitSize
				+ 20);
		// mainPanel.setBackground(new Color(83, 83, 83));
		// mainPanel.pack();
		mainPanel.setLayout(null);
		mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel.setResizable(false);
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

		// getMainPanel().addKeyListener(new PlayerAdapter());
		mainPanel.setVisible(true);
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
					board.getShapes().add(shape);
					break;
				case PLAYER:
					shape = new Player(position);
					this.mainPanel.addKeyListener((Player)shape);
					shape.addObserver(this);
					board.getShapes().add(shape);
					
					{
					Shape square = new Square(position);
					square.draw(this.mainPanel);
					board.getShapes().add(square);
					}
					break;
				case STORAGE:
					shape = new Storage(position);
					board.getShapes().add(shape);
					break;
				case EMPTYSQUARE:
					shape = new Square(position);
					board.getShapes().add(shape);
					break;
				case BOX:
					shape = new Box(position);
					board.getShapes().add(shape);
					{
					Shape square = new Square(position);
					square.draw(this.mainPanel);
					board.getShapes().add(square);
					}		
					break;
				case BOXINSTORAGE:
					shape = new Storage(position);//TODO CREATE SPECIAL SHAPE OR SOMETHING THAT CHANGES THE BOX COLOR
					board.getShapes().add(shape);
					
					Shape storage = new Box(position);
					board.getShapes().add(storage);
					storage.draw(this.mainPanel);
					break;

				default:
					break;
				}
				shape.draw(this.mainPanel);
				
			}
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

	public boolean move(Shape player, Direction dir) {
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
				// Push
				// move shape 1 and shape 2
				shape2.move(direction);
				shape1.move(direction);
			}
		} else if (!collision.collide(shape1, shape2)) {
			// simple move
			shape1.move(direction);
		}
		
		//FIXME this.draw(GameController.getInstance().getMainPanel());

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
			move(player, direction);
			player.draw(mainPanel);
			mainPanel.repaint();
			//TODO TEST
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
