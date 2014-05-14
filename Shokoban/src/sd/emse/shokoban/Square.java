package sd.emse.shokoban;

import java.util.ArrayList;


public class Square extends Shape {
	public Square(Position position) {
		super(position);
		this.setImageName("sokoban/square.jpg");
		this.setIndex(0);		
	}
}
