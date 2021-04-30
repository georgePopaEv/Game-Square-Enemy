package joc6.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.Timer;

public class Enemy extends JComponent implements ActionListener{
	private int speed;
	private Color color;
	private Ground playGround;
	private int height=50;
	private int width=50;
	private Timer t;
	private Dimension preferredSize;
	
	
	public Enemy(Ground playGround) {
		setPreferredSize(new Dimension(50,50));
		t =  new Timer(10, this);
		this.playGround = playGround;
		speed = 5;
		//color = Color.red;
		t.start();
		
	}
	
	public void setSpeed(int speed) {  //set viteza
		this.speed = speed;
	}

	public void setColor(Color color) {  //set culoare
		this.color = color;

	}
	public void setHeight(int height) { //set inaltime
		this.height = height;
	}

	public void setWidth(int width) { //set largime
		this.width = width;

	}

	public void setPlayGround(Ground gr) { //setarea spatiului de joc

		this.playGround = gr;

	}
	
	public void setPreferredSize(Dimension preferredSize) { //setarea dimensiunii
		this.preferredSize = preferredSize;
	}
	
	
	public Color getColor() { //get culoare
		return color;
	}

	public int getSpeed() { // get viteza
		return speed;
	}
	public int getHeight() { //get inaltime
		return height;
	}
	public int getWidth() { //get largime
		return width;
	}
	public Dimension getPreferredSize() { //get dimensiune
		return preferredSize;
	}
	

	public void paint(Graphics g) { //desenam un dreptunghiuri rosii inamici

		super.paintComponent(g);
		g.setColor(Color.red);
		g.fillRect(0, 0, 50, 50);

	}
	
	public void actionPerformed(ActionEvent arg0) {
		if ((int)this.getLocation().getY()<=600) {
			playGround.moveEnemy(this, speed);
		}else {
			playGround.addScore();// se adauga scor atunci cand inamicul trece de toata harta
			t.stop();
			playGround.removeEnemy(this);
		}
		
		repaint();
		
	}
	

}
