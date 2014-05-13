package sd.emse.shokoban;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameController;

public class Player extends Shape implements KeyListener {
	private Collision collision;

	public Player(Position position) {
		super(position);
		this.setImageName("sokoban/player.png");
		this.setIndex(1);
		collision = new Collision();
	}

	public boolean collide(Shape shape) {
		if (collision.collide(this, shape)) {
			return true;
		} else {
			return false;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(this.getPosition().toString());
		setChanged();
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			//(this, Direction.NORTH);
			notifyObservers(Direction.NORTH);
			break;
		case KeyEvent.VK_DOWN:
			notifyObservers(Direction.SOUTH);
			break;
		case KeyEvent.VK_LEFT:
			notifyObservers(Direction.WEST);
			break;
		case KeyEvent.VK_RIGHT:
			notifyObservers(Direction.EAST);
			break;
		default:
			break;
		}


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see sd.emse.shokoban.Shape#isMovable()
	 */
	@Override
	public boolean isMovable() {
		return true;
	}
	
	
}
