package sd.emse.shokoban;

import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Shape extends Observable{
	private Position position;
	private String imageName;
	 JLabel image;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Shape() {
		super();
	}

	public Shape(Position position) {
		super();
		this.position = position;
	}

	public void draw(JFrame container) {
		if (this.image == null) {
			ImageIcon imageIcon = new ImageIcon(this.imageName);
			this.image = new JLabel(imageIcon);
			container.getContentPane().add(this.image);
		}
		this.image.setBounds(this.position.getLengthX(),
				this.position.getLengthY(), 80, 80);
	}

	public void move(Direction direction) {

		switch (direction) {
		case NORTH:
			this.position.setY(this.position.getY() - 1);
			break;
		case SOUTH:
			this.position.setY(this.position.getY() + 1);
			break;

		case EAST:
			this.position.setX(this.position.getX() + 1);
			break;
		case WEST:
			this.position.setX(this.position.getX() - 1);
			break;

		default:
			break;
		}
	}

	/**
	 * @return
	 */
	public abstract boolean isMovable();
}
