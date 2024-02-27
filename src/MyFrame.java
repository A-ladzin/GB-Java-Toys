import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    MyFrame(ToysMachine machine){
        this.setPreferredSize(new Dimension(640,480));
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        MyPanel panel = new MyPanel(machine);
        JScrollPane pane = new JScrollPane(panel);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(BorderLayout.CENTER, pane);
        this.setSize(640, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
