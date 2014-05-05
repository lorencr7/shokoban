package sd.emse.shokoban;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Board extends Shape {
	private int width;
	private int height;
	public static final int unitSize = 80;
	private JFrame mainPanel;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private Player player;

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

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public Board(int width, int height) {
		super();
		this.width = width;
		this.height = height;
		// this.createInitialBoard();
	}

	@Override
	public void draw(JFrame container) {
		this.createInitialBoard();
	}

	public void createInitialBoard() {
		this.createPanel();
		this.createPlayer();
		this.createBoxes();
		this.createWalls();
		this.createStorages();

		this.mainPanel.setVisible(true);
	}

	public void createPanel() {
		this.mainPanel = new JFrame("Sokoban");
		this.mainPanel.setBounds(0, 0, this.width * unitSize, this.height
				* unitSize + 20);
		// this.mainPanel.setBackground(new Color(83, 83, 83));
		// this.mainPanel.pack();
		this.mainPanel.setLayout(null);
		this.mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainPanel.setResizable(false);
		this.mainPanel.addKeyListener(new BoardAdapter());

	}

	class BoardAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			Position position = player.getPosition();
			switch (e.getKeyCode()) {

			case KeyEvent.VK_UP:
				position.setY(position.getY() - 1);
				break;
			case KeyEvent.VK_DOWN:
				position.setY(position.getY() + 1);
				break;
			case KeyEvent.VK_LEFT:
				position.setX(position.getX() - 1);
				break;
			case KeyEvent.VK_RIGHT:
				position.setX(position.getX() + 1);
				break;
			default:
				break;
			}
			if (position.getY() <= 0) {
				position.setY(0);
			} else if (position.getY() > (height - 1)) {
				position.setY(height - 1);
			}

			if (position.getX() <= 0) {
				position.setX(0);
			} else if (position.getX() > (width - 1)) {
				position.setX(width - 1);
			}
			player.setPosition(position);
			player.draw(mainPanel);
			mainPanel.repaint();
		}
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
			wall.draw(this.mainPanel);
			this.shapes.add(wall);
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
			storage.draw(this.mainPanel);
			this.shapes.add(storage);
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
			box.draw(this.mainPanel);
			this.shapes.add(box);
		}
	}

	public void createPlayer() {
		ArrayList<Position> positions = new ArrayList<Position>();
		positions.add(new Position(2, 2));
		for (Position position : positions) {
			Player player = new Player(position);
			player.draw(this.mainPanel);
			this.shapes.add(player);
			this.player = player;
		}
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

		for (Shape s : this.shapes) {
			if (s.getPosition().equals(pos)) {
				return s;
			}
		}
		return null;
	}

}
