package sd.emse.shokoban;

import java.util.ArrayList;



public class Storage extends Shape {
	public Storage(Position position) {
		super(position);
		this.setImageName("sokoban/storage.jpg");
		this.setIndex(1);
	}
}
