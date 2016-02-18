package org.mssw.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import org.mssw.decorator.Combatent;

public class Screen implements Runnable {

	private Combatent combatent1;
	private Combatent combatent2;

	int p1MaxHealth;
	int p2MaxHealth;
	private JProgressBar player1Health;
	private JProgressBar player2Health;

	private JProgressBar player1Ammo;
	private JProgressBar player2Ammo;
	
	private JLabel player1Avatar;
	private JLabel player2Avatar;

	private JLabel distance;
	
	List<BufferedImage> avatars;
	List<BufferedImage> failAvatars;

	public Screen(List<Combatent> combatents, Integer distance) throws IOException {
		combatent1 = combatents.get(0);
		combatent2 = combatents.get(1);
		this.distance = new JLabel(distance.toString(), SwingConstants.CENTER);
		
		avatars = new ArrayList<>();
		avatars.add(ImageIO.read(new File("images/doom1.png")));
		avatars.add(ImageIO.read(new File("images/doom2.png")));
		avatars.add(ImageIO.read(new File("images/doom3.png")));
		avatars.add(ImageIO.read(new File("images/doom4.png")));
		avatars.add(ImageIO.read(new File("images/doom5.png")));
		
		failAvatars = new ArrayList<>(); 
		failAvatars.add(ImageIO.read(new File("images/fail1.png")));
		failAvatars.add(ImageIO.read(new File("images/fail2.png")));
		failAvatars.add(ImageIO.read(new File("images/fail3.png")));
		failAvatars.add(ImageIO.read(new File("images/fail4.png")));
		failAvatars.add(ImageIO.read(new File("images/fail5.png")));
	}

	@Override
	public void run() {
		JFrame frame = new JFrame("dc-battle-royale");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1, 3));

		JPanel distancePanel = new JPanel();
		distancePanel.setLayout(new BorderLayout());

		JLabel distanceLabel = new JLabel("Distance", SwingConstants.CENTER);
		distancePanel.add(distanceLabel, BorderLayout.NORTH);

		distancePanel.add(distance, BorderLayout.CENTER);

		frame.add(setLeftPlayer());
		frame.add(distancePanel);
		frame.add(setRightPlayer());

		frame.pack();
		frame.setVisible(true);
	}

	private JPanel setLeftPlayer() {
		JPanel leftPlayer = new JPanel();
		leftPlayer.setLayout(new BorderLayout());

		JLabel label1 = new JLabel(combatent1.getName(), SwingConstants.CENTER);
		leftPlayer.add(label1, BorderLayout.NORTH);

		player1Health = new JProgressBar(0, combatent1.getHealth());
		p1MaxHealth = combatent1.getHealth();
		player1Health.setValue(combatent1.getHealth());
		player1Health.setStringPainted(true);
		leftPlayer.add(player1Health, BorderLayout.SOUTH);

		player1Ammo = new JProgressBar(JProgressBar.VERTICAL, 0, combatent1.getWeapon().getAmmoCapacity());
		player1Ammo.setValue(combatent1.getWeapon().getAmmoCapacity());
		player1Ammo.setStringPainted(true);
		leftPlayer.add(player1Ammo, BorderLayout.WEST);
		
		player1Avatar = new JLabel(new ImageIcon(avatars.get(0)));
		leftPlayer.add(player1Avatar, BorderLayout.CENTER);
		

		JLabel player1WeaponLabel = new JLabel(combatent1.getWeapon().getDescription(), SwingConstants.CENTER);
		leftPlayer.add(player1WeaponLabel, BorderLayout.EAST);

		return leftPlayer;
	}

	private JPanel setRightPlayer() {
		JPanel rightPlayer = new JPanel();
		rightPlayer.setLayout(new BorderLayout());

		JLabel label2 = new JLabel(combatent2.getName(), SwingConstants.CENTER);
		rightPlayer.add(label2, BorderLayout.NORTH);

		player2Health = new JProgressBar(0, combatent2.getHealth());
		p2MaxHealth = combatent2.getHealth();
		player2Health.setValue(combatent2.getHealth());
		player2Health.setStringPainted(true);
		rightPlayer.add(player2Health, BorderLayout.SOUTH);

		player2Ammo = new JProgressBar(JProgressBar.VERTICAL, 0, combatent2.getWeapon().getAmmoCapacity());
		player2Ammo.setValue(combatent2.getWeapon().getAmmoCapacity());
		player2Ammo.setStringPainted(true);
		rightPlayer.add(player2Ammo, BorderLayout.EAST);
		
		player2Avatar = new JLabel(new ImageIcon(avatars.get(0)));
		rightPlayer.add(player2Avatar, BorderLayout.CENTER);

		JLabel player2WeaponLabel = new JLabel(combatent2.getWeapon().getDescription(), SwingConstants.CENTER);
		rightPlayer.add(player2WeaponLabel, BorderLayout.WEST);

		return rightPlayer;
	}

	public void updateDistance(Integer distance) {
		this.distance.setText(distance.toString());
	}

	public void tick() {
		int p1Health = combatent1.getHealth();
		player1Health.setValue(p1Health);
		player1Ammo.setValue(combatent1.getCurrentClip());
		
		double p1Percent = (100D / (double) p1MaxHealth) * (double)p1Health;
		int index = 0;
		if( p1Percent >= 60 ){
			index = 0;
		}
		else if( p1Percent >= 40 && p1Percent < 60 ){
			index = 1;
		}
		else if( p1Percent >= 20 && p1Percent < 40 ){
			index = 2;
		}
		else if( p1Percent >= 1 && p1Percent < 20 ){
			index = 3;
		}
		else if( p1Percent < 1 ){
			index = 4;
		}
		
		if( combatent1.failed()){
			player1Avatar.setIcon(new ImageIcon(failAvatars.get(index)));
		}
		else{
			player1Avatar.setIcon(new ImageIcon(avatars.get(index)));
		}
		
		
		
		int p2Health = combatent2.getHealth();
		player2Health.setValue(combatent2.getHealth());
		player2Ammo.setValue(combatent2.getCurrentClip());
		
		index = 0;
		double p2Percent = (100D / (double) p2MaxHealth) * (double)p2Health;
		if( p2Percent >= 60 ){
			index = 0;
		}
		else if( p2Percent >= 40 && p2Percent < 60 ){
			index = 1;
		}
		else if( p2Percent >= 20 && p2Percent < 40 ){
			index = 2;
		}
		else if( p2Percent >= 1 && p2Percent < 20 ){
			index = 3;
		}
		else if ( p2Percent < 1 ){
			index = 4;
		}
		
		if( combatent2.failed()){
			player2Avatar.setIcon(new ImageIcon(failAvatars.get(index)));
		}
		else{
			player2Avatar.setIcon(new ImageIcon(avatars.get(index)));
		}
	}

}
