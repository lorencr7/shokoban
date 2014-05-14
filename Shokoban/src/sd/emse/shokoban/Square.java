package sd.emse.shokoban;

import javax.swing.JFrame;


public class Square extends Shape {
	
	/**
	 * @param position
	 */
	public Square(Position position) {
		super(position);
		this.setImageName("");
		this.setIndex(5);		
	}

	@Override
	public void draw(JFrame container) {
		super.draw(container);
		//TODO this.image.setOpaque(false);
	}

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
