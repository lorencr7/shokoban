package sd.emse.shokoban;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		ImageIcon imageIcon = new ImageIcon(this.imageName);
		JLabel label = new JLabel(imageIcon);
		//label.setLocation(, );
		label.setBounds(this.position.getLengthX(), this.position.getLengthY(), 80, 80);
		//label.setSize(80, 80);
		//label.setVisible(true);
		container.getContentPane().add(label);
		
		/*ImagePanel imagePanel = new ImagePanel();
		imagePanel.createImage(this.imageName);
		imagePanel.setX(this.position.getLengthX());
		imagePanel.setY(this.position.getLengthY());
		container.getContentPane().add(imagePanel);*/
		
		//container.setVisible(true);
	}
	
	public void move(Direction direction) {
		
	}
}
