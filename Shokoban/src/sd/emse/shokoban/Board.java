package sd.emse.shokoban;

import java.util.ArrayList;

import javax.swing.JFrame;

public class Board extends Shape {
	public static int width;
	public static int height;
	public static final int unitSize = 80;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public static int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		Board.width = width;
	}

	public static int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		Board.height = height;
	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void setShapes(ArrayList<Shape> shapes) {
		this.shapes = shapes;
	}

	public Board(int width, int height) {
		super();
		Board.width = width;
		Board.height = height;
	}

	@Override
	public void draw(JFrame container) {
		// this.createInitialBoard();
	}

}
