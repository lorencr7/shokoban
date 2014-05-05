package sd.emse.shokoban;

public class Wall extends Shape {
	private Collision collision;
	
	public Wall(Position position) {
		super(position);
		this.setImageName("sokoban/wall.jpg");
	}
	
}
