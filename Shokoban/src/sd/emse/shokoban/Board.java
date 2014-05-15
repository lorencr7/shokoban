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

	/*public Board(int x, int y) {
		super(new Position(x, y));
	}*/
	public Board (String[] boardMap) {
		this.createInitialBoard(boardMap);
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}
	

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public Shape getShapeAt(Position p) {
		for (Shape s : this.shapes) {
			if (s.getPosition().equals(p)) {
				return s;
			}
		}
		return null;
	}
	
	public ArrayList<Shape> getBoxes() {
		ArrayList<Shape> shapes = new ArrayList<>();
		for (Shape shape : this.shapes) {
			if (shape instanceof Box) {
				shapes.add(shape);
			}
		}
		return shapes;
	}
	
	public ArrayList<Shape> getStorages() {
		ArrayList<Shape> shapes = new ArrayList<>();
		for (Shape shape : this.shapes) {
			if (shape instanceof Storage) {
				shapes.add(shape);
			}
		}
		return shapes;
	}
	
	

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
					shape = new Storage(position);//TODO CREATE SPECIAL SHAPE OR SOMETHING THAT CHANGES THE BOX COLOR		
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
}
