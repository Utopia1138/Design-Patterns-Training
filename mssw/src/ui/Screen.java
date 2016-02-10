package ui;

import java.awt.*;

import javax.swing.*;

public class Screen implements Runnable{

	@Override
	public void run() {
		JFrame frame = new JFrame( "dc-battle-royale");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1, 3));
		
		
		
		JPanel distancePanel = new JPanel();
		distancePanel.setLayout(new BorderLayout());
		
		JLabel distanceLabel = new JLabel("Distance", SwingConstants.CENTER);
		distancePanel.add(distanceLabel, BorderLayout.NORTH);
		JLabel distance = new JLabel("150", SwingConstants.CENTER);
		distancePanel.add(distance, BorderLayout.CENTER);
		
		frame.add(setLeftPlayer());
		frame.add(distancePanel);
		frame.add(setRightPlayer());
		
		frame.pack();
		frame.setVisible(true);
	}
	
	public static void main( String... args){
		
		Screen screen = new Screen();
		SwingUtilities.invokeLater(screen);
	}
	
	private JPanel setLeftPlayer(){
		JPanel leftPlayer = new JPanel();		
		leftPlayer.setLayout(new BorderLayout());
		
		JLabel label1 = new JLabel("Player 1", SwingConstants.CENTER);
		leftPlayer.add(label1, BorderLayout.NORTH);
		
		JProgressBar player1Health = new JProgressBar(0, 100);
		player1Health.setValue(100);
		player1Health.setStringPainted(true);
		leftPlayer.add(player1Health, BorderLayout.SOUTH);
		
		return leftPlayer;
	}
	
	private JPanel setRightPlayer(){
		JPanel rightPlayer = new JPanel();
		rightPlayer.setLayout(new BorderLayout());
		
		JLabel label2 = new JLabel( "Player 2", SwingConstants.CENTER);
		rightPlayer.add(label2, BorderLayout.NORTH);
		
		JProgressBar player2Health = new JProgressBar(0, 100);
		player2Health.setValue(100);
		player2Health.setStringPainted(true);
		rightPlayer.add(player2Health, BorderLayout.SOUTH);
		
		return rightPlayer;
	}
	
	

	
}
