package sd.emse.shokoban;

import javax.swing.JFrame;


public class Square extends Shape {
	private Collision collision;
	
	/**
	 * @param position
	 */
	public Square(Position position) {
		super(position);
		this.setImageName("sokoban/transparent_cell.png");
		this.setIndex(4);
		
	}

	@Override
	public void draw(JFrame container) {
		// TODO Auto-generated method stub
		super.draw(container);
//		this.image.setOpaque(false);
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
