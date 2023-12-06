package controller;

import javax.swing.JFrame;

import model.Game;
import model.GameOver;
import view.Frame;

public class MainController {
	private static MainController singleton = null;
	private static Game game;
	private static GameOver gameOver;
	private static Frame frame;

	private MainController() {
	}

	public static MainController getInstance() {
		if (singleton == null) {
			singleton = new MainController();
		}
		return singleton;
	}

	public void init() {
		frame = new Frame();
		game = new Game();
		gameOver = new GameOver();
	}

	public void telaInicial() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(game);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

		frame.repaint();
		frame.validate();
	}

	public void telaGameOver() {
		frame.getContentPane().removeAll();
		frame.getContentPane().add(gameOver);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

		frame.repaint();
		frame.validate();
	}
}
