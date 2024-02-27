import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MyPanel extends JPanel implements ActionListener {
    ArrayList<ToySlider> sliders = new ArrayList<>();

    JButton addButton;

    ArrayList<JLabel> labels = new ArrayList<>();

    ArrayList<JButton> deletes = new ArrayList<>();

    Integer PANEL_WIDTH = 800;
    JFrame frame;
    ToysMachine machine;
    private JTextField newName = new JTextField();
    MyPanel(ToysMachine machine){
        newName.setBounds(400,0,200,50);
        newName.setVisible(true);
        newName.setFont(new Font("Monospace",3,36));
        this.add(newName);
        addButton = new JButton();
        addButton.addActionListener(this);
        addButton.setText("ADD");
        addButton.setFont(new Font("Monospace",0,72));
        this.machine = machine;
        this.setVisible(true);
        this.setLayout(null);
        createGui();
        this.add(addButton);



    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);




    }


    private void createGui()
    {

        for (int i = 0; i < labels.size(); i++) {
            this.remove(labels.get(i));
            this.remove(sliders.get(i));
            this.remove(deletes.get(i));
        }

        this.revalidate();

        deletes = new ArrayList<>();
        sliders = new ArrayList<>();
        labels  = new ArrayList<>();

        addButton.setBounds(400,0,200,labels.size()*100+50);
        addButton.setVisible(true);
        Integer y =0 ;


        for (ToysMachine.Toy toy: machine.getToys().keySet()) {

            ToySlider slider = new ToySlider(toy);
            sliders.add(slider);
            this.add(slider);
            slider.setBounds(0,y+50,400,50);
            JLabel label = new JLabel();
            this.add(label);
            labels.add(label);
            label.setBounds(0, y, 400, 50);
            label.setVisible(true);
            label.setText("TOY: "+ toy+"  Chance: " +machine.getProb(toy) +   "  Count:  " + machine.getToys().get(toy));
            JButton del = new JButton();
            deletes.add(del);
            del.setBounds(350,y,50,50);
            del.setVisible(true);
            del.addActionListener(this);
            this.add(del);
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
                    machine.cycleFill();
                    machine.setCapacity(temp_cap);
                    machine.getToys().put(toy,slider.getValue());
                    System.out.println(machine.getToys());
                    for (int i = 0; i < sliders.size(); i++) {
                        ToysMachine.Toy toy = (ToysMachine.Toy)machine.getToys().keySet().toArray()[i];
                        ToySlider sl = sliders.get(i);
                        sl.setValue(machine.getToys().get(sl.toy));
                        sl.repaint();
                        JLabel l = labels.get(i);
                        l.setText("TOY: "+ toy+"  Chance: " +machine.getProb(toy) +   "  Count:  " + machine.getToys().get(toy));

                    }
                };
            });

        }
        addButton.setBounds(400,50,200,labels.size()*100+50);
        this.setPreferredSize(new Dimension(PANEL_WIDTH,(labels.size()+2)*100));
    }





    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){
            machine.put(this.newName.getText(),0.2f);
            createGui();
        }

        if (deletes.contains(e.getSource())){
            if(deletes.size() < 2){
                return;
            }
            Integer idx = deletes.indexOf(e.getSource());
            ToysMachine.Toy toy = (ToysMachine.Toy)machine.getToys().keySet().toArray()[idx];
            machine.getToys().remove(toy);
            machine.resetCycle();
            machine.cycleFill();
            deletes.remove(idx);
            createGui();
            machine.printToys();
        }


    }

}
