package sd.emse.shokoban;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameController;

public class Player extends Shape implements KeyListener {
	private Collision collision;

	public Player(Position position) {
		super(position);
		this.setImageName("sokoban/player.png");
		collision = new Collision();

		GameController.getInstance().getMainPanel().addKeyListener(this);

	}

	public boolean collide(Shape shape) {
		if (collision.collide(this, shape)) {
			return true;
		} else {
			return false;
		}

	}

	public void move(String direction) {

		switch (direction) {
		case "North":
			this.getPosition().setY(this.getPosition().getY() - 1);
			break;
		case "South":
			this.getPosition().setY(this.getPosition().getY() + 1);
			break;

		case "East":
			this.getPosition().setX(this.getPosition().getX() + 1);
			break;
		case "West":
			this.getPosition().setX(this.getPosition().getX() - 1);
			break;

		default:
			break;
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

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:

			GameController.getInstance().collide(this, Direction.NORTH);
			// getPosition().setY(getPosition().getY() - 1);

			break;
		case KeyEvent.VK_DOWN:

			GameController.getInstance().collide(this, Direction.SOUTH);
			// getPosition().setY(getPosition().getY() + 1);

			break;
		case KeyEvent.VK_LEFT:
			GameController.getInstance().collide(this, Direction.WEST);
			// getPosition().setX(getPosition().getX() - 1);

			break;
		case KeyEvent.VK_RIGHT:
			GameController.getInstance().collide(this, Direction.EAST);
			// getPosition().setX(getPosition().getX() + 1);

			break;
		default:
			break;
		}

		this.draw(GameController.getInstance().getMainPanel());

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
}
