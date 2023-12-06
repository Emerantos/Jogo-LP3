package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

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

    public GameOver() {
        setLayout(new BorderLayout());
        setDoubleBuffered(true);

        ImageIcon referencia = new ImageIcon("assets\\GameOver.png");
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
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);

        graficos.setFont(fonte);
        graficos.setColor(cor);

        graficos.drawString("SCORE: 152 (seg)", 104, 500);
        g.dispose();
    }

}
