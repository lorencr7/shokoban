package sd.emse.shokoban;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import ejemplo.ImagePanel;

public class Shape {
	private Position position;
	private String imageName;
	private JLabel image;

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
		System.out.println("drawing " + this.imageName);
		this.image.setBounds(this.position.getLengthX(), this.position.getLengthY(), 80, 80);
	}
	
	public void move(Direction direction) {
		
	}
}
