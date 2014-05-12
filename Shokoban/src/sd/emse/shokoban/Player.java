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
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:

			GameController.getInstance().play(this, Direction.NORTH);
			// getPosition().setY(getPosition().getY() - 1);

			break;
		case KeyEvent.VK_DOWN:

			GameController.getInstance().play(this, Direction.SOUTH);
			// getPosition().setY(getPosition().getY() + 1);

			break;
		case KeyEvent.VK_LEFT:
			GameController.getInstance().play(this, Direction.WEST);
			// getPosition().setX(getPosition().getX() - 1);

			break;
		case KeyEvent.VK_RIGHT:
			GameController.getInstance().play(this, Direction.EAST);
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

	/* (non-Javadoc)
	 * @see sd.emse.shokoban.Shape#isMovable()
	 */
	@Override
	public boolean isMovable() {
		return true;
	}
}
