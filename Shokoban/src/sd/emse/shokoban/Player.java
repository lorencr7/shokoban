package sd.emse.shokoban;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.GameController;

public class Player extends Shape implements KeyListener{
	private Collision collision;

	public Player(Position position) {
		super(position);
		this.setImageName("sokoban/player.png");
		GameController.getMainPanel().addKeyListener(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		Position position = this.getPosition();
		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP:
			position.setY(position.getY() - 1);
			break;
		case KeyEvent.VK_DOWN:
			position.setY(position.getY() + 1);
			break;
		case KeyEvent.VK_LEFT:
			position.setX(position.getX() - 1);
			break;
		case KeyEvent.VK_RIGHT:
			position.setX(position.getX() + 1);
			break;
		default:
			break;
		}
		
		if (position.getY() <= 0) {
			position.setY(0);
		} else if (position.getY() > (Board.getHeight() - 1)) {
			position.setY(Board.getHeight() - 1);
		}

		if (position.getX() <= 0) {
			position.setX(0);
		} else if (position.getX() > (Board.getWidth() - 1)) {
			position.setX(Board.getWidth() - 1);
		}
		
		this.setPosition(position);
		this.draw(GameController.getMainPanel());
		
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
