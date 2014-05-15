package sd.emse.shokoban;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Board extends Shape {
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public Board(int x, int y) {
		super(new Position(x, y));
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
