package tp1exo1;

import javax.swing.*;

public class Frame extends JFrame {
    public Frame(String title){
        setTitle(title);
        setSize(300,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);

        add(new Panel());
    }
}
