import javax.swing.*;

public class ToySlider extends JSlider {
    public ToysMachine.Toy toy;
    public ToySlider(ToysMachine.Toy toy){
        super();
        this.toy = toy;
    }
}
