import javax.swing.*;

public class MyFrame extends JFrame {
    MyFrame(ToysMachine machine){
        MyPanel panel = new MyPanel(machine);
        this.add(panel);

        this.setResizable(true);
        this.setVisible(true);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }
}
