
import java.util.ArrayList;
import java.util.PriorityQueue;

public class ToysMachine {

    ArrayList<Toy> coleccion = new ArrayList<>();

    PriorityQueue<Toy> deck = new PriorityQueue<>(); //Нахуя?

    private class Toy{
        Integer id;
        Integer weight;
        String name;
        public Toy(String id, String weight, String name){
            this.id = Integer.parseInt(id);
            this.weight = Integer.parseInt(weight);
            this.name = name;
        }
    }

    public void put(String specs){
        String[] splitten = specs.split(" ");
        Toy toy = new Toy(splitten[0], splitten[1],splitten[2]);
        coleccion.add(Toy);
    }



}
