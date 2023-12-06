package model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class DoutorEggman {

	protected double x;
	protected int y;
	protected int largura;  
    private int altura;   
	protected Image imagemObstaculo;
	private int larguraColisao; 
    private int alturaColisao;

	public DoutorEggman(int x, int y) {
		this.x = x;
		this.y = y;
 
		ImageIcon referencia = new ImageIcon("assets\\inimigo1.png");
		imagemObstaculo = referencia.getImage();

		// Define as dimensões do obstáculo (largura e altura)
		largura = imagemObstaculo.getWidth(null);
		altura = imagemObstaculo.getHeight(null);
		//Proporção da colisão
		larguraColisao = (int) (largura * 0.8);
        alturaColisao = (int) (altura * 0.8);

		new Thread(() -> {
			while (true) {
				mover();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public DoutorEggman(int x2, int y2, String obstaculo) {
		this.x = x2;
		this.y = y2;

		ImageIcon referencia = new ImageIcon(obstaculo);
		imagemObstaculo = referencia.getImage();

		largura = imagemObstaculo.getWidth(null);
        altura = imagemObstaculo.getHeight(null);

		new Thread(() -> {
			while (true) {
				mover();
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

	public double getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}

	public void mover() {
		x = x + (-1*Game.velocidade);
	}


    public void desenhar(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        graficos.drawImage(imagemObstaculo, (int) x, y, null);

        graficos.setColor(Color.RED);
        graficos.drawRect((int) x, y, larguraColisao, alturaColisao);
    }

 
}
