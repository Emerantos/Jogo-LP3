package model;

public class ObstaculoVoador extends Obstaculo {

	public ObstaculoVoador(int x, int y) {
		super(x, y, "assets\\inimigo1.1.png");
	}

	@Override
	public void mover() {
		x = x + (-1*Game.velocidade);;
	}
}