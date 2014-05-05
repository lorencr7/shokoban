package sd.emse.shokoban;



public class Position {
	private int x;
	private int y;
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Position(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public int getLengthX() {
		return this.x * Board.unitSize;
	}

	public int getLengthY() {
		return this.y * Board.unitSize;
	}


}
