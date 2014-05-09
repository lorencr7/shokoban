package sd.emse.shokoban;

import java.util.ArrayList;

public class GameRules {

	private Board board;
	private int steps;

	public GameRules(Board board)
	{
		this.board = board;
		this.steps = 0;
	}
	
	public boolean isGameComplete(){
		ArrayList<Shape> shapes = this.board.getShapes();
		
		for (Shape s : shapes) {
			if (s instanceof Storage) {
				Position pos = s.getPosition();
				Shape shape = this.board.getShapeAt(pos);
				if (shape == null) {
					return false;
				}else if (!(shape instanceof Box)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int computeScore() {
		return steps;
	}
	
	public void startGame() {
	}
	
	public void stopGame() {
	}
	
	public void recordMove() {
		steps++;
	}
	
}
