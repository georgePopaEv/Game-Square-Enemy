package joc6.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Timer;
import javax.swing.JComponent;

public class Player extends JComponent implements ActionListener, KeyListener {

	private int speed;
	private Color color;
	private Ground playGround;
	private Dimension preferredSize = new Dimension(50, 50);
	private Timer t = new Timer(20, this);
//	private boolean isReleased = false;
	private boolean isPressedRight = false;
	private boolean isPressedLeft = false;
	private boolean isPressedUp = false;
	private boolean isPressedDown = false;

	public Player(Ground playGround) {
		setPreferredSize(new Dimension(50,50));
		this.playGround = playGround;
		speed = 5;
		color = Color.green;
		setFocusable(true);
		addKeyListener(this);
		t.start();
		
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void setColor(Color color) {
		this.color = color;

	}

	public Timer getTimer() {
		return t;
	}

	public void setPlayGround(Ground gr) {

		this.playGround = gr;

	}


	public Color getColor() {
		return color;
	}

	public int getSpeed() {
		return speed;
	}

	public Dimension getPreferredSize() {
		return preferredSize;
	}

	public void paint(Graphics g) {

		super.paintComponent(g);
		g.setColor(color);
		g.fillOval(0, 0, 50, 50);

	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			isPressedUp = true;

			break;
		case KeyEvent.VK_DOWN:
			isPressedDown = true;

			break;
		case KeyEvent.VK_LEFT:
			isPressedLeft = true;

			break;
		case KeyEvent.VK_RIGHT:
			isPressedRight = true;

			break;
		}

	}

	public void keyReleased(KeyEvent e) {
//		isReleased = true;

		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			isPressedUp = false;

			break;
		case KeyEvent.VK_DOWN:
			isPressedDown = false;

			break;
		case KeyEvent.VK_LEFT:
			isPressedLeft = false;

			break;
		case KeyEvent.VK_RIGHT:
			isPressedRight = false;

			break;
		}

//		isReleased = false;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if (playGround.isTerminat()) {
			t.stop();
		}

		if (isPressedRight)
			playGround.movePlayer(this, "RIGHT", speed);

		if (isPressedLeft)
			playGround.movePlayer(this, "LEFT", speed);

		if (isPressedUp)
			playGround.movePlayer(this, "UP", speed);

		if (isPressedDown)
			playGround.movePlayer(this, "DOWN", speed);
		repaint();
	}

}

