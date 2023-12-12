package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MainController;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;

public class telainicio extends JFrame {
    private Image backgroundImage;

    public telainicio() {
        setTitle("Tela Inicial");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        try {
            backgroundImage = ImageIO.read(getClass().getResource("/assets/fundo.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        mainPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Bem-vindo ao nosso jogo!");
        titleLabel.setBounds(220, 100, 400, 40);
        titleLabel.setForeground(Color.white);

        Font titleFont = new Font("Arial", Font.BOLD, 25); // Substitua pelos valores desejados
        titleLabel.setFont(titleFont);

        JButton startButton = new JButton("Iniciar Jogo");
        JButton exitButton = new JButton("Sair do Jogo");
        startButton.setBounds(200, 400, 150, 40);
        exitButton.setBounds(400, 400, 150, 40);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJogo();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encerrarJogo();
            }
        });

        mainPanel.add(titleLabel);
        mainPanel.add(startButton);
        mainPanel.add(exitButton);

        add(mainPanel);

        repaint();
    }

    private void iniciarJogo() {
        MainController.getInstance().init();
        MainController.getInstance().telaInicial();
        dispose();
    }

    private void encerrarJogo() {
        System.exit(0);
    }

}
