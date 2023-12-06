package model;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class Game extends JPanel {

	private Fundo fundo;
	private List<DoutorEggman> obstaculos;
	public int cont;
	private Sonic sonic;
    private BufferedImage imgAtual;
	private boolean k_cima = false;
    private boolean k_baixo = false;
    private int x = 21;
	public static double velocidade = 1;
	private double fatorAcelerar = 0.05;

    public Game(){

        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e){

            }
            @Override
            public void keyReleased(KeyEvent e){
                switch(e.getKeyCode()){
                    case KeyEvent.VK_UP: k_cima = false; break;
                    case KeyEvent.VK_DOWN: k_baixo = false; break;
                }
            }
            @Override
            public void keyPressed(KeyEvent e){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP: k_cima = true; break;
                    case KeyEvent.VK_DOWN: k_baixo = true; break;
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
	public void eventosTeclado(){
		sonic.velX = 0;
        sonic.velY = 0;
        imgAtual = sonic.getCurrentFrame();
		if(k_cima == true || sonic.teste){
            if(k_baixo == true){
				  imgAtual = sonic.sonicPulo;
                  sonic.pulo = true;
                if (!(sonic.posY >= 520)) {
                    sonic.velY = 25;                   
                } 
            }
            else if(sonic.pulo == true){
                sonic.velY = x;
				imgAtual = sonic.sonicPulo;
                if (sonic.posY >= 520) {
                    sonic.teste = false;
                    sonic.pulo = false;               
                }
            }else if (sonic.pulo == false) {
                sonic.velY = -x;
                sonic.teste = true;
				imgAtual = sonic.sonicPulo;
                if(sonic.posY <= 300){
                    sonic.pulo = true;
                }
            }
            
        }
	}
	public void atualizar() {
		velocidade += fatorAcelerar;
		sonic.posX = sonic.posX + sonic.velX;
		sonic.posY = sonic.posY + sonic.velY;
	
		verificarColisao(); 
		System.out.println(velocidade);
		if(velocidade == 30.00000000000029 || velocidade == 60.99999999999867){
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

		graficos.drawImage(fundo.fundo1, (int)fundo.fundo1PosX, (int)fundo.fundoPosY, null);
		graficos.drawImage(fundo.fundo2, (int)fundo.fundo2PosX, (int)fundo.fundoPosY, null);
        graficos.drawImage(imgAtual, (int)sonic.posX, (int)sonic.posY, null);
		for (DoutorEggman obstaculo : obstaculos) {
			obstaculo.desenhar(graficos);
		}
		g.dispose();
	}
	
	public void criarObstaculo() {
		obstaculos.add(new DoutorEggman(999, 550));

	}
	
	public void criarObstaculo2() {
		obstaculos.add(new DoutorEggman3(999, 550));
	}

	public void criarObstaculo3() {

		obstaculos.add(new DoutorEggman2(999, 550));
	}

	private void criarObstaculoAleatorio() {
        // Lista de funções para criar obstáculos
        List<Consumer<Game>> criadoresObstaculos = Arrays.asList(
            Game::criarObstaculo,
            Game::criarObstaculo2,
            Game::criarObstaculo3
            // Adicione mais funções conforme necessário
        );

        // Escolhe aleatoriamente uma função e a executa
        int indiceFuncao = (int) (Math.random() * criadoresObstaculos.size());
        criadoresObstaculos.get(indiceFuncao).accept(this);
    }
	
	public void verificarColisao() {
		for (DoutorEggman obstaculo : obstaculos) {
			if (sonic.posX < obstaculo.getX() + obstaculo.getLargura() &&
				sonic.posX + sonic.getLargura() > obstaculo.getX() &&
				sonic.posY < obstaculo.getY() + obstaculo.getAltura() &&
				sonic.posY + sonic.getAltura() > obstaculo.getY())
				{
					encerrarJogo(); 

				}
		}
	}
	private void encerrarJogo() {

		// Encerra o jogo
		System.exit(0);
	}

}
