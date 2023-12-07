package view;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Frame extends JFrame{
    public Frame(){
        setTitle("Jogo 2D");
		setSize(1024, 728);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                encerrar();
            }
        });
        setResizable(false);
    }
    private int encerrar(){
        System.exit(0);
        return 0;
    }
}
