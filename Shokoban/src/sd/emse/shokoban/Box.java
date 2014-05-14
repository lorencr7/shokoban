package sd.emse.shokoban;

import javax.swing.JFrame;

public class Box extends Shape {
	
	public Box(Position position) {
		super(position);
		this.setImageName("sokoban/box.jpg");
		this.setIndex(2);
	}
	

	/* (non-Javadoc)
	 * @see sd.emse.shokoban.Shape#isMovable()
	 */
	@Override
	public boolean isMovable() {
		return true;
	}
}
