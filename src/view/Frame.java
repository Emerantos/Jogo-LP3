package view;

import javax.swing.JFrame;

public class Frame extends JFrame{
    public Frame(){
        setTitle("Jogo 2D");
		setSize(1024, 728);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setResizable(false);
    }
}
