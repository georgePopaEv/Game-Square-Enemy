package joc6.com;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Ground extends JPanel implements ActionListener {

	private Dimension preferredSize = new Dimension(800, 600);
	private MainFrame controller;
	private Image backgroundImage;

	private Player player1;
	private Player player2;
	private Random random = new Random();
	private int randomEnemyNumber;
	private int score = 0;
	private boolean terminat = false;
	Timer t;

	public Ground(MainFrame controller) {

		this.controller = controller;
		t = new Timer(1000, this);
		t.start();
		setLayout(null);
		setBackground(new Color(255,255,255));

		player1 = new Player(this);
		addPlayer(0, 200, player1);

	}

	public void paint(Graphics g) {
		// Desenarea unui contur negru
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawRect(0, 1, 799, 598);

	}

	public void addPlayer(int x, int y, Player player) {
		this.add(player);
		player.setLocation(x, y);
		player.setSize(player.getPreferredSize());
		player.setVisible(true);
		repaint();
	}

	public void addEnemy(Enemy enemy) {
		// genereaza x intre 0 si 750
		int x = random.nextInt(751);

		this.add(enemy);
		enemy.setLocation(x, 0);
		enemy.setSize(enemy.getPreferredSize());
		enemy.setVisible(true);
		repaint();
	}

	public void movePlayer(Player player, String where, int howMuch) {

		// Verificam cazul in care player-ul se misca, daca ar sarea din ecran, nu se
		// misca, daca nu, se misca

		if (where.equals("RIGHT") && (int) player.getLocation().getX() + howMuch <= 750) {
			player.setLocation((int) player.getLocation().getX() + howMuch, (int) player.getLocation().getY());
		} else {
			if (where.equals("LEFT") && (int) player.getLocation().getX() - howMuch >= 0) {
				player.setLocation((int) player.getLocation().getX() - howMuch, (int) player.getLocation().getY());

			} else {
				if (where.equals("UP")

						&& (int) player.getLocation().getY() - howMuch >= 0) {
					player.setLocation((int) player.getLocation().getX(), (int) player.getLocation().getY() - howMuch);

				} else {
					if (where.equals("DOWN") && (int) player.getLocation().getY() + howMuch <= 550) {
						player.setLocation((int) player.getLocation().getX(),
								(int) player.getLocation().getY() + howMuch);

					}
				}
			}
		}
	}

	public void moveEnemy(Enemy enemy, int howMuch) {

		enemy.setLocation((int) enemy.getLocation().getX(), (int) enemy.getLocation().getY() + howMuch);
		verifyColideWithPlayers(enemy);
	}

	public Dimension getPreferredSize() {

		return preferredSize;

	}

	public void removeEnemy(Enemy enemy) { // stergem inamicii(atunci cand trec de harta)
		remove(enemy);
		enemy = null;
	}

	public void verifyColideWithPlayers(Enemy enemy) {

		for (Component c : getComponents()) {
			if (c instanceof Player) {

				int enemyX = (int) enemy.getLocation().getX();
				int enemyY = (int) enemy.getLocation().getY();
				int enemyWidth = enemy.getWidth();
				int enemyHeight = enemy.getHeight();
				int playerX = (int) ((Player) c).getLocation().getX();
				int playerY = (int) ((Player) c).getLocation().getY();

				if (playerX + 50 > enemyX && playerY + 50 > enemyY && playerX < enemyX + enemyWidth
						&& playerY < enemyY + enemyHeight) {
					removeAll();
					terminat = true;
					JOptionPane.showMessageDialog(null, "Ai pierdut!Scor final " + score);
					controller.getFrame().dispose();
					break;
				}
			}
		}

	}

	public void addScore() {
		score++;
		controller.setScoreLabel(score);
	}

	public boolean isTerminat() {
		return terminat;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Enemy enemy = new Enemy(this);
		addEnemy(enemy);

	}

}
