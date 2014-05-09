package example.refactor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class Canvas extends JPanel {

	private static final long serialVersionUID = -6960082725415143691L;
	private Image image; // image to load
	private int x, y;

	public Canvas() {
		addKeyListener(new BoardAdapter());
		setFocusable(true);
	} 

	public void createImage(String imageURL) {
		image = Toolkit.getDefaultToolkit().createImage(imageURL);
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		graphics.drawImage(image, x, y , null);
		repaint();
	}

	public Dimension getPreferredSize() {
		return new Dimension(image.getWidth(null),
				image.getHeight(null));
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	class BoardAdapter extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {

			case KeyEvent.VK_UP:
				y = y - 81;
				break;
			case KeyEvent.VK_DOWN:
				y = y + 81;
				break;
			case KeyEvent.VK_LEFT:
				x = x - 79;
				break;
			case KeyEvent.VK_RIGHT:
				x = x + 79;
				break;
			default:
				break;
			}
			if (y <= 0) {
				y = 0;
			} else if (y >= 511) {
				y = 511;
			} 
			
			if (x <= 0) {
				x = 0;
			} else if (x >= 723) {
				x = 723;
			}
			
			repaint();
		}
	}
}
