package sd.emse.shokoban;

import javax.swing.JFrame;

public class Wall extends Shape {
	private Collision collision;
	
	public Wall(Position position) {
		super(position);
		this.setImageName("sokoban/wall.jpg");
	}
	/* (non-Javadoc)
	 * @see sd.emse.shokoban.Shape#isMovable()
	 */
	@Override
	public boolean isMovable() {
		return false;
	}
	
}
