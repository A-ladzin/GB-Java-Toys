import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements ActionListener {
    ArrayList<ToySlider> sliders = new ArrayList<>();

    Integer PANEL_WIDTH = 500;
    Integer PANEL_HEIGHT = 300;
    Timer timer = new Timer(20,this);

    ToysMachine machine;
    MyPanel(ToysMachine machine){
        this.machine = machine;
        this.setVisible(true);
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));


    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Integer y =0 ;
        for (ToysMachine.Toy toy: machine.getToys().keySet()) {
            ToySlider slider = new ToySlider(toy);
            sliders.add(slider);
            this.add(slider);
            slider.setBounds(0,y,100,100);
            y+=100;
            slider.setVisible(true);
            slider.setMaximum(machine.getCapacity()-(machine.getToys().keySet().size()-1));
            slider.setMinimum(1);
            slider.setValue(machine.getToys().get(toy));
            slider.addChangeListener(new ChangeListener() {
                public void stateChanged(ChangeEvent e) {
                    machine.getToys().put(toy,0);
                    Integer temp_cap = machine.getCapacity();
                    machine.setCapacity(machine.getCapacity()-slider.getValue());
                    machine.fillMachine();
                    machine.setCapacity(temp_cap);
                    machine.getToys().put(toy,slider.getValue());
                    System.out.println(machine.getToys());
                    for(ToySlider sl:sliders){
                        sl.setValue(machine.getToys().get(sl.toy));
                        sl.repaint();
                    }
                };
            });

        }


    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

}
