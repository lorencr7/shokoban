package sd.emse.shokoban;

public class Storage extends Square {
	public Storage(Position position) {
		super(position);
		this.setImageName("sokoban/storage.jpg");
		this.setIndex(1);
	}
}
