package model;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Obstaculo {

	protected double x;
	private int y;
	private Image imagemObstaculo;

	public Obstaculo(int x, int y) {
		this.x = x;
		this.y = y;

		ImageIcon referencia = new ImageIcon("assets\\inimigo21.png");
		imagemObstaculo = referencia.getImage();

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

	public Obstaculo(int x2, int y2, String obstaculo) {
		this.x = x2;
		this.y = y2;

		ImageIcon referencia = new ImageIcon(obstaculo);
		imagemObstaculo = referencia.getImage();

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

	public void mover() {
		x = x + (-1*Game.velocidade);
	}

	public void desenhar(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(imagemObstaculo, (int)x, y, null);
	}
}
