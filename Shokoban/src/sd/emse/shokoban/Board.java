package sd.emse.shokoban;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Board extends Shape {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();
	private final char WALL = 'W';
	private final char PLAYER = 'P';
	private final char STORAGE = 'S';
	private final char BOX = 'B';
	private final char BOXINSTORAGE = 'X';

	public Board (String[] boardMap) {
		this.createInitialBoard(boardMap);
	}

	/**
	 * This creates the game board according with the coded string array.
	 * @see GameController.createInitialBoard()
	 * @param boardMap a text description of the board (each text line represent a row in the board) 
	 */
	private void createInitialBoard(String[] boardMap) {
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
					shape = new Storage(position);	
					Shape storage = new Box(position);
					this.getShapes().add(storage);
				default:
					break;
				}
				if (shape != null) {
					this.getShapes().add(shape);
				}
	
				Shape square = new Square(position);
				this.getShapes().add(square);
			}
		}
	}

	@Override
	public void draw(JFrame container) {
		for (Shape shape : shapes) {
			shape.draw(container);
		}
	}

	@Override
	public void move(Direction direction, ArrayList<Shape> shapes) {
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	/**
	 * @return Returns all the boxes that are in the board 
	 */
	public ArrayList<Shape> getBoxes() {
		ArrayList<Shape> shapes = new ArrayList<>();
		for (Shape shape : this.shapes) {
			if (shape instanceof Box) {
				shapes.add(shape);
			}
		}
		return shapes;
	}
	
	/**
 	 * @return Returns all the storage squares that are in the board
	 */
	public ArrayList<Shape> getStorages() {
		ArrayList<Shape> shapes = new ArrayList<>();
		for (Shape shape : this.shapes) {
			if (shape instanceof Storage) {
				shapes.add(shape);
			}
		}
		return shapes;
	}
}
