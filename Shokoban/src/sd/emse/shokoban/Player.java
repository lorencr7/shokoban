package sd.emse.shokoban;

public class Player extends Shape{
	private Collision collision;

	public Player(Position position) {
		super(position);
		this.setImageName("sokoban/player.png");
	}
}
