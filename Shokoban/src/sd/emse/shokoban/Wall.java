package sd.emse.shokoban;

public class Wall extends Collision {
	public Wall(Position position) {
		super(position);
		this.setImageName("sokoban/wall.jpg");
		this.setIndex(1);
	}
}
