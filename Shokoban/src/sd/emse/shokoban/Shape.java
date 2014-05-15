package sd.emse.shokoban;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Shape extends Observable {
	private Position position;
	private String imageName;
	JLabel image;
	/**The z-index of the shape */
	private Integer index = 100;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = new Position(position);
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public Shape() {
		super();
	}

	public Shape(Position position) {
		super();
		this.position = new Position(position);
	}
	public Shape (Shape shape) {
		super();
		this.position = shape.position;
	}

	public void draw(JFrame container) {
		if (this.image == null) {
			ImageIcon imageIcon = new ImageIcon(getClass().getClassLoader().getResource(imageName));
			this.image = new JLabel(imageIcon);
			container.getLayeredPane().add(this.image, this.index);
		}
		this.image.setBounds(this.position.getLengthX(), this.position.getLengthY(), 80, 80);
	}

	public  void move(Direction direction, ArrayList<Shape> shapes) {
		
	}
	
	public ArrayList<Shape> getShapesAt(Position p, ArrayList<Shape> shapes) {
		ArrayList<Shape> shapesAtPosition = new ArrayList<>();
		for (Shape shape : shapes) {
			if (shape.getPosition().equals(p)) {
				shapesAtPosition.add(shape);
			}
		}
		return shapesAtPosition;
	}
	
	public void performMove (Direction direction) {
		Position position = new Position(this.getPosition());
		switch (direction) {
		case NORTH:
			position.setY(position.getY() - 1);
			break;
		case SOUTH:
			position.setY(position.getY() + 1);
			break;

		case EAST:
			position.setX(position.getX() + 1);
			break;
		case WEST:
			position.setX(position.getX() - 1);
			break;

		default:
			break;
		}
		this.setPosition(position);
		this.draw(null);
	}
	
}
