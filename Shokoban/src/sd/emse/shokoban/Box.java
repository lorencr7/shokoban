package sd.emse.shokoban;

import javax.swing.JFrame;

public class Box extends Shape {
	
	public Box(Position position) {
		super(position);
		this.setImageName("sokoban/box.jpg");
	}
	
	public void draw(JFrame container){
		super.draw(container);
	}
}
