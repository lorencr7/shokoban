package sd.emse.shokoban;

public class Square extends Shape {
	public Square(Position position) {
		super(position);
		this.setImageName("sokoban/square.jpg");
		this.setIndex(0);		
	}
}
