import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    Panel panel;

    Frame(){
        panel = new Panel();
        this.add(panel);
        this.setTitle("Bouncing ball");
        this.setResizable(false);
        this.setBackground(Color.BLACK);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();

        this.setVisible(true);

        this.setLocationRelativeTo(null);
    }

}
