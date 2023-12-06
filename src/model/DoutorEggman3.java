package model;

public class DoutorEggman3 extends DoutorEggman {

	public DoutorEggman3(int x, int y) {
		super(x, y, "assets\\inimigo2png");
	}

	@Override
	public void mover() {
		x -= 4;
	}
}