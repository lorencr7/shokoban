package sd.emse.shokoban;

public class Direction {
	public static final String NORTH = "NORTH";
	public static final String SOUTH = "SOUTH";
	public static final String EAST = "EAST";
	public static final String WEST = "WEST";
	
	private String direction;
	private int steps;
	
	public Direction(String direction, int steps) {
		super();
		this.direction = direction;
		this.steps = steps;
	}

	public String getDirection() {
		return direction;
	}

	public int getSteps() {
		return steps;
	}
	
	
	
}
