package sd.emse.shokoban;

import javax.swing.JFrame;

public class Square extends Shape {
	private Collision collision;
	
	public void move(Position pos){
		this.setPosition(pos);
	}

	/* (non-Javadoc)
	 * @see sd.emse.shokoban.Shape#isMovable()
	 */
	@Override
	public boolean isMovable() {
		return false;
	}
}
