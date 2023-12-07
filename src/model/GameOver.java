package model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.MainController;

public class GameOver extends JPanel {
    private JButton replayButton;
    private JButton exitButton;
    ImageIcon referencia;
    Font fonte = new Font("Arial", Font.BOLD, 40);
    Color cor = Color.WHITE;

    public GameOver() {

        setLayout(null);
        setDoubleBuffered(true);

        // Crie e adicione os botões
        replayButton = new JButton("Jogar Novamente");
        exitButton = new JButton("Sair do Jogo");

        replayButton.setBounds(100, 550, 200, 50);
        exitButton.setBounds(700, 550, 200, 50);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        replayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Game.k_baixo = false;
                Game.k_cima = false;
                Game.tempo = 0;
                Game.jogoEncerrado = false;
                Game.timerTempo = new Timer();
                Game.timerTempo.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if (Game.jogoEncerrado == false) {
                            Game.tempo++;
                        }
                    }
                }, 0, 1000);
                Game.velocidade = 1.0;
                Game.sonic.posX = 100;
                Game.sonic.posY = 550;
                Game.sonic.pulo = false;
                Game.sonic.teste = false;
                Game.obstaculos = new ArrayList<>();
                MainController.getInstance().telaInicial();
            }
        });
        add(exitButton);
        add(replayButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        double att = lerTempoDoArquivo();
        double r = lerTempoDoArquivoRecord();
        Graphics2D graficos = (Graphics2D) g;
        if (att >= r) {
            try {
                String caminhoArquivo = "record\\record.txt";

                FileWriter fileWriter = new FileWriter(caminhoArquivo);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.printf("%.1f", Game.tempo);
                fileWriter.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
            graficos.drawImage(new ImageIcon("assets\\HighScore.png").getImage(), 0, 0, null);
        } else {
            graficos.drawImage(new ImageIcon("assets\\GameOver.png").getImage(), 0, 0, null);
        }
        graficos.setFont(fonte);
        graficos.setColor(cor);
        graficos.drawString("SCORE:" + Game.tempo, 104, 500);
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

            /* tempo usuario e tempo record */
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
