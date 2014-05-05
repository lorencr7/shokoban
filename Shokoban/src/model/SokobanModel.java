/**
 * 
 */
package model;

import sd.emse.shokoban.Board;
import sd.emse.shokoban.Collision;
import sd.emse.shokoban.Position;
import sd.emse.shokoban.Shape;

public class SokobanModel {

	// shapes contained in model
	private Board board;
	private Collision collision;

	// no-argument constructor
	public SokobanModel(Board board) {
		this.board = board;
		collision = new Collision();
	}

	/**
	 * @return the collision
	 */
	public Collision getCollision() {
		return collision;
	}

	/**
	 * @param collision
	 *            the collision to set
	 */
	public void setCollision(Collision collision) {
		this.collision = collision;
	}

	/**
	 * @return the board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * @param board
	 *            the board to set
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean move(Shape shape1, Integer dir) {
		Position next = shape1.getPosition();

		Shape shape2 = board.getNextShape(next, dir);

		if (collision.collide(shape1, shape2)) {
			// Push
			next = shape2.getPosition();
			Shape shape3 = board.getNextShape(next, dir);
			if (collision.collide(shape2, shape3)) {
				return false;
			} else {
				// move shape 1 and shape 2
				shape2.move(dir);
				shape1.move(dir);
				return true;
			}
		}
		// simple move
		shape1.move(dir);
		return true;

	}

}
