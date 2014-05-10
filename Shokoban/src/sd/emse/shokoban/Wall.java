package sd.emse.shokoban;

import javax.swing.JFrame;

public class Wall extends Shape {
	private Collision collision;
	
	public Wall(Position position) {
		super(position);
		this.setImageName("sokoban/wall.jpg");
	}
	public void draw(JFrame container){
		super.draw(container);
	}
	
}
