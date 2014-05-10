package sd.emse.shokoban;

import javax.swing.JFrame;

public class Square extends Shape {
	private Collision collision;
	
	public void draw(JFrame container){
		super.draw(container);
	}
	
	public void move(Position pos){
		this.setPosition(pos);
	}
}
