package controller;

import javax.swing.JFrame;

import model.Game;

public class Main extends JFrame {
	public Main() {
		add(new Game()); 
		setTitle("Jogo 2D");
		setSize(1024, 728);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		this.setResizable(false);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main(); 
	}
}
