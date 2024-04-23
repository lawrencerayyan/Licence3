package tp1exo1;

import javax.swing.*;
import java.awt.*;

public class Panel extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 100, 100);
     /*   g.setColor(Color.RED);
        g.fillRect(100,0,100,100);
        g.setColor(Color.BLUE);
        g.fillRect(200,0,100,100); */
    }
}
