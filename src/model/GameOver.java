package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JPanel {
    private Image fundo;
    private JButton replayButton;
    private JButton exitButton;
    Font fonte = new Font("Arial", Font.BOLD, 40);
    Color cor = Color.WHITE;
    private double tempo;
    double att;
    double r;

    public GameOver() {
        setLayout(new BorderLayout());
        setDoubleBuffered(true);

        ImageIcon referencia;

        att = lerTempoDoArquivo();
        r  = lerTempoDoArquivoRecord();

        System.out.println("Record: "+ r);
        System.out.println("\nAtual: "+ att);


        if (att>r) {

            referencia = new ImageIcon("assets\\HighScore.png");
        } else {
            referencia = new ImageIcon("assets\\GameOver.png");

        }
        
        fundo = referencia.getImage();
        JLabel backgroundLabel = new JLabel(referencia);
        add(backgroundLabel, BorderLayout.CENTER);

        // Crie e adicione os botões
        replayButton = new JButton("Jogar Novamente");
        exitButton = new JButton("Sair do Jogo");

        replayButton.setBounds(100, 500, 200, 50);
        exitButton.setBounds(400, 500, 200, 50);
        add(replayButton);
        add(exitButton);
    }

    public void paint(Graphics g) {
        tempo = lerTempoDoArquivo();

        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);

        graficos.setFont(fonte);
        graficos.setColor(cor);

        graficos.drawString("SCORE:" + tempo, 104, 500);
        g.dispose();
    }

    private double lerTempoDoArquivo() {
        try {
            String caminhoArquivo = "record\\tempo.txt";
            
            Scanner scanner = new Scanner(new File(caminhoArquivo));
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;  
    }

    private double lerTempoDoArquivoRecord() {
        try {
            String caminhoArquivo = "record\\record.txt";
            
            /* tempo usuario e tempo record  */
            Scanner scanner = new Scanner(new File(caminhoArquivo));
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;  
    }

}
