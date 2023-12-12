package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sonic {
    public BufferedImage spriteSheet; // A imagem contendo a sprite completa
    private BufferedImage[] frames; // Vetor de imagens para armazenar partes da sprite
    private int currentFrame; // Índice do quadro atual na animação
    private int frameWidth; // Largura de cada quadro na sprite
    private int frameHeight;
    public BufferedImage sonicPulo; // Altura de cada quadro na sprite
    public int posX;
    public int posY;
    public int raio;
    public int velY;
    public boolean pulo = false;
    public boolean teste = false;
    public boolean sprite = true;

    public Sonic() {
        try {
            sonicPulo = ImageIO.read(new File("assets\\sonicPulo.png"));
            spriteSheet = ImageIO.read(new File("assets\\sonicCorrendo.png"));
            frameWidth = 128; // Ajuste conforme necessário
            frameHeight = 104;
            // Ajuste conforme necessário
            initializeFrames();
        } catch (Exception e) {
            System.out.println("Erro ao carregar imagem");
        }

        posX = 100;
        posY = 550;
        velY = 0;
    }

    public void initializeFrames() {
        if (Game.tempo == 10 && sprite) {
            try {
                spriteSheet = ImageIO.read(new File("assets\\sonicCorrendo2.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            frameWidth = 128; // Ajuste conforme necessário
            frameHeight = 104;
            sprite = false;
        } else if (Game.tempo == 20 && sprite == false) {
            try {
                spriteSheet = ImageIO.read(new File("assets\\sonicCorrendo3.png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            posY = 570;
            frameWidth = 128; // Ajuste conforme necessário
            frameHeight = 80;
            sprite = true;
        } else {
            try {
                spriteSheet = ImageIO.read(new File("assets\\sonicCorrendo.png"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            frameWidth = 128; // Ajuste conforme necessário
            frameHeight = 104;
        }
        // Calcula o número total de quadros horizontalmente na sprite
        int numFrames = spriteSheet.getWidth() / frameWidth;
        frames = new BufferedImage[numFrames];

        // Corta a sprite em quadros individuais e armazena no vetor frames
        for (int i = 0; i < numFrames; i++) {
            frames[i] = spriteSheet.getSubimage(i * frameWidth, 0, frameWidth, frameHeight);
        }

        currentFrame = 0; // Inicializa o índice do quadro atual
    }

    // Adicionado: Retorna a largura do Sonic
    public int getLargura() {
        return frameWidth; // Ou qualquer valor apropriado para a largura do Sonic
    }

    // Adicionado: Retorna a altura do Sonic
    public int getAltura() {
        return frameHeight; // Ou qualquer valor apropriado para a altura do Sonic
    }

    // Método para obter o quadro atual na animação
    public BufferedImage getCurrentFrame() {
        return frames[currentFrame];
    }

    // Método para avançar para o próximo quadro na animação
    public void nextFrame() {
        currentFrame = (currentFrame + 1) % frames.length;
    }
}
