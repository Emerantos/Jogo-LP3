package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Fundo {
    public BufferedImage fundo1,fundo2;
    int fundoLargura,fundoAltura;
    double fundoVelX;
	double fundo1PosX,fundo2PosX,fundoPosY;

	public Fundo() {
		try {
            fundo1 = ImageIO.read(new File("assets\\fundo.png"));
            fundo2 = ImageIO.read(new File("assets\\fundo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
		fundoLargura = 1000;
		fundoAltura = 480;
        fundoVelX = -1*Game.velocidade;

        fundo1PosX = 0;
        fundoPosY = 0;
        fundo2PosX = fundo1PosX+fundoLargura;
	}
    public void update(){
        // atualiza o fundo
        fundoVelX = -1*Game.velocidade;
		fundo1PosX = fundo1PosX + fundoVelX;
	    fundo2PosX = fundo2PosX + fundoVelX;
		remontarFundo();
    }
    
	public void remontarFundo(){
		if(fundo1PosX+fundoLargura <= 0){
            fundo1PosX = fundo2PosX+fundoLargura;
        }else if(fundo2PosX+fundoLargura <= 0){
            fundo2PosX = fundo1PosX+fundoLargura;
        }
	}
}
