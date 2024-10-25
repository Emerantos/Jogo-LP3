package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;
import javax.swing.JPanel;
import controller.MainController;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Game extends JPanel {

	private Fundo fundo;
	public static List<DoutorEggman> obstaculos;
	public int cont;
	public static Sonic sonic;
	private BufferedImage imgAtual;
	public static boolean k_cima = false;
	public static boolean k_baixo = false;
	private int x = 21;
	public static double velocidade = 1;
	private double fatorAcelerar = 0.05;
	public static double tempo;
	public static boolean jogoEncerrado = false;
	public static Timer timerTempo;

	Font fonte = new Font("Arial", Font.BOLD, 20);
	Color cor = Color.WHITE;
	private Font fonteRecorde = new Font("Arial", Font.BOLD, 20);
	private Color corRecorde = Color.YELLOW;

	public Game() {

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						k_cima = false;
						break;
					case KeyEvent.VK_DOWN:
						k_baixo = false;
						break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						k_cima = true;
						break;
					case KeyEvent.VK_DOWN:
						k_baixo = true;
						break;
				}
			}

		});

		sonic = new Sonic();
		fundo = new Fundo();
		setFocusable(true);
		obstaculos = new ArrayList<>();

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {

				criarObstaculoAleatorio();

			}

		}, 0, 3000);

		timerTempo = new Timer();
		timerTempo.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (jogoEncerrado == false) {
					tempo++;
				}
			}
		}, 0, 1000);

		new Thread(() -> {
			while (true) {
				eventosTeclado();
				atualizar();
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public static void gameloop() {

	}

	public void eventosTeclado() {
		requestFocus();
		sonic.velY = 0;
		imgAtual = sonic.getCurrentFrame();
		if (k_cima == true || sonic.teste) {
			if (k_baixo == true) {
				imgAtual = sonic.sonicPulo;
				sonic.pulo = true;
				if (!(sonic.posY >= 520)) {
					sonic.velY = 25;
				}
			} else if (sonic.pulo == true) {
				sonic.velY = x;
				imgAtual = sonic.sonicPulo;
				if (sonic.posY >= 520) {
					sonic.teste = false;
					sonic.pulo = false;
				}
			} else if (sonic.pulo == false) {
				sonic.velY = -x;
				sonic.teste = true;
				imgAtual = sonic.sonicPulo;
				if (sonic.posY <= 300) {
					sonic.pulo = true;
				}
			}

		}
	}

	public void atualizar() {
		velocidade += fatorAcelerar;
		sonic.posY = sonic.posY + sonic.velY;

		verificarColisao();
		if ((tempo == 10 && sonic.sprite == true) || (tempo == 20 && sonic.sprite == false)) {
			sonic.initializeFrames();
		}
		sonic.nextFrame();
		fundo.update();
		for (DoutorEggman obstaculo : obstaculos) {
			obstaculo.mover();
		}

	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graficos = (Graphics2D) g;

		graficos.drawImage(fundo.fundo1, (int) fundo.fundo1PosX, (int) fundo.fundoPosY, null);
		graficos.drawImage(fundo.fundo2, (int) fundo.fundo2PosX, (int) fundo.fundoPosY, null);
		graficos.drawImage(imgAtual, (int) sonic.posX, (int) sonic.posY, null);
		for (DoutorEggman obstaculo : obstaculos) {
			obstaculo.desenhar(graficos);
		}

		graficos.setFont(fonte);
		graficos.setColor(cor);

		// Desenha o tempo na tela
		graficos.drawString("Tempo: " + tempo, 15, 25);
		graficos.setFont(fonteRecorde);
		graficos.setColor(corRecorde);
		graficos.drawString("Recorde: " + lerRecordeAtual("record\\record.txt"), 15, 50);

		g.dispose();
	}

	public void criarObstaculo() {
		obstaculos.add(new DoutorEggman(999, 550));

	}

	private void criarObstaculoAleatorio() {
		// Lista de funções para criar obstáculos
		List<Consumer<Game>> criadoresObstaculos = Arrays.asList(
				Game::criarObstaculo);

		// Escolhe aleatoriamente uma função e a executa
		int indiceFuncao = (int) (Math.random() * criadoresObstaculos.size());
		criadoresObstaculos.get(indiceFuncao).accept(this);
	}

	public void verificarColisao() {
		for (DoutorEggman obstaculo : obstaculos) {
			if (sonic.posX < obstaculo.getX() + obstaculo.getLargura() &&
					sonic.posX + sonic.getLargura() > obstaculo.getX() &&
					sonic.posY < obstaculo.getY() + obstaculo.getAltura() &&
					sonic.posY + sonic.getAltura() > obstaculo.getY()) {
				encerrarJogo();
			}
		}
	}

	private void encerrarJogo() {
		jogoEncerrado = true;
		timerTempo.cancel();
		salvaTempo(tempo);

		MainController.getInstance().telaGameOver();
	}

	private void salvaTempo(double tempo) {
		try {
			String caminhoArquivo = "record\\tempo.txt";

			FileWriter fileWriter = new FileWriter(caminhoArquivo);
			PrintWriter printWriter = new PrintWriter(fileWriter);

			printWriter.printf("%.1f", tempo);

			printWriter.close();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private double lerRecordeAtual(String caminhoArquivo) {
    	try {
        	Scanner scanner = new Scanner( new File(caminhoArquivo));
        	if (scanner.hasNextDouble()) {
            	double recorde = scanner.nextDouble();
            	scanner.close(); //
            	return recorde;
        	}
        	scanner.close();
    	} catch (FileNotFoundException e) {
        	// Se o arquivo não existe, retorna 0 indicando que não há recorde ainda
        	return 0;
    	}
   		return 0;
	}
}
