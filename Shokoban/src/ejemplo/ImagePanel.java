package ejemplo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {

	private static final long serialVersionUID = -6960082725415143691L;
	private BufferedImage originalImage; // original image
	private Image image; // image to load
	private MediaTracker mediaTracker;
	private int x, y;

	ArrayList<Image> imagens;

	// ImagePanel constructor
	public ImagePanel() {
		addKeyListener(new BoardAdapter());
		setFocusable(true);
		imagens = new ArrayList<Image>();

		// create MediaTracker for image
		mediaTracker = new MediaTracker(this);
	} // end ImagePanel constructor

	public void createImage(String imageURL) {
		image = Toolkit.getDefaultToolkit().createImage(imageURL);
		mediaTracker.addImage(image, 0);
		imagens.add(image);

		// wait for Image to load
		try {
			mediaTracker.waitForAll();
		}

		// exit program on error
		catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}

		// create BufferedImages from Image
		originalImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);
		// get BufferedImage’s graphics context
		Graphics2D graphics = originalImage.createGraphics();

		graphics.drawImage(image, x, y, null);

	}

	// set Image to originalImage
	public void displayOriginalImage() {
		originalImage = new BufferedImage(image.getWidth(null),
				image.getHeight(null), BufferedImage.TYPE_INT_RGB);

		Graphics2D graphics = originalImage.createGraphics();
		graphics.drawImage(originalImage, null, null);
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics = (Graphics2D) g;

		graphics.drawImage(originalImage, getX(), getY(), null);
		this.repaint();
	}

	// draw ImagePanel
	// public void paintComponent(Graphics g) {
	// super.paintComponent(g);
	// Graphics2D graphics = (Graphics2D) g;
	// graphics.drawImage(originalImage, getX(), getY(), null);
	// this.repaint();
	//
	// }

	// get preferred ImagePanel size
	public Dimension getPreferredSize() {
		return new Dimension(originalImage.getWidth(),
				originalImage.getHeight());
	}

	// get minimum ImagePanel size
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
		}
	}

}
