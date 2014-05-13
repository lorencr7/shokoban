package sd.emse.shokoban;

import javax.swing.JFrame;

public class Storage extends Shape{

	public Storage(Position position) {
		super(position);
		this.setImageName("sokoban/storage.jpg");
		this.setIndex(3);
	}
	

	/* (non-Javadoc)
	 * @see sd.emse.shokoban.Shape#isMovable()
	 */
	@Override
	public boolean isMovable() {
		return false;
	}
}
