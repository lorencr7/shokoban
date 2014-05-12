package sd.emse.shokoban;


public class Square extends Shape {
	private Collision collision;
	
	/**
	 * @param position
	 */
	public Square(Position position) {
		super(position);
		this.setImageName("sokoban/square.jpg");
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
