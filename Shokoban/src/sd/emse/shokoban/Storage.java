package sd.emse.shokoban;

import javax.swing.JFrame;

public class Storage extends Shape{

	public Storage(Position position) {
		super(position);
		this.setImageName("sokoban/storage.jpg");
	}
	
	public void draw(JFrame container){
		super.draw(container);
	}
}
