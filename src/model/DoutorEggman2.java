package model;

public class DoutorEggman2 extends DoutorEggman {

	public DoutorEggman2(int x, int y) {
		super(x, y, "assets\\inimigo3.png");
	}

	@Override
	public void mover() {
		x -= 4;
	}
}