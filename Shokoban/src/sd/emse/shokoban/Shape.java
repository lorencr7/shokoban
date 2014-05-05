package sd.emse.shokoban;

import javax.swing.JFrame;

import ejemplo.ImagePanel;

public class Shape {
	private Position position;
	private String imageName;

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
		System.out.println("drawing " + this.imageName);
		ImagePanel imagePanel = new ImagePanel();
		imagePanel.createImage(this.imageName);
		imagePanel.setX(this.position.getLengthX());
		imagePanel.setY(this.position.getLengthY());
		container.getContentPane().add(imagePanel);
	}
	
	public void move(Direction direction) {
		
	}
}
