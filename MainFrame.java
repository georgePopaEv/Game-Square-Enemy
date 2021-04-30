package joc6.com;

import javax.swing.JFrame;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.security.CodeSource;

public class MainFrame extends JPanel {
	
	JLabel scoreLabel = new JLabel("Score = 0");
	JFrame frame;

	void buildUI(JFrame frame) {

		frame.setLayout(null);
		Ground ground = new Ground(this);
		frame.add(ground);
		ground.setSize(ground.getPreferredSize());
		ground.setLocation(0, 0);
		ground.setVisible(true);
		JPanel scorePanel = new JPanel();
		frame.add(scorePanel);
		scorePanel.setSize(new Dimension((int)ground.getPreferredSize().getWidth(),30));
		scorePanel.setLocation(0, (int)ground.getPreferredSize().getHeight());
		scorePanel.setBackground(Color.WHITE);
		scorePanel.setVisible(true);
		scorePanel.setLayout(null);
		scorePanel.add(scoreLabel);
		scoreLabel.setLocation(0,0);
		scoreLabel.setSize(new Dimension((int)ground.getPreferredSize().getWidth(),30));
		scoreLabel.setVisible(true);
		
		
	}
	
	public void setScoreLabel( int score) {
		scoreLabel.setText("Score = "+score);
	}
	
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public static void main(String[] args) {
		JFrame f = new JFrame("Joc Saptamana 6");
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		MainFrame controller = new MainFrame();
		controller.setFrame(f);
		controller.buildUI(f);
		
		f.setPreferredSize(new Dimension(810, 670));
		f.pack();

		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}

}
