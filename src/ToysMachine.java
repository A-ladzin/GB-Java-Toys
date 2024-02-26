
import java.util.*;

public class ToysMachine {

    private Deque<Toy> deck;
    private Integer capacity = null;
    private TreeMap<Toy, Integer> coleccion = new TreeMap<>();



    public ToysMachine(Integer capacity){
        this.capacity = capacity;
    }

    public ToysMachine(){}

    public class Toy implements Comparable<Toy>{
        //Имя
        private String name;
        static private Integer pk =0;
        private  Integer id;
        // при константном количестве игрушек в начале розыгрыша, не может быть реализована
        // константная вероятность получения определенной игрушки
        // т.к в момент, когда одна из игрушек закончится, вероятности все так же должны приходить к 100%
        // поэтому вижу обоснованным отойти от условия, и оставить что-то одно.
        // Поэтому шанс выпадения игрушки - часть игрушек от всех игрушек.
        // С возможностью указания константного общего количества игрушек и добавления по вероятности
        public Toy(String name){
            this.id = ++pk;
            this.name = name;
        }



        public Integer getId(){
            return this.id;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int compareTo(Toy o) {
            return this.getId() - o.getId();
        }
    }

    public void putN(String name, Integer number){

        Toy toy = new Toy(name);
        if (capacity != null){
            if (getNToys()+number > capacity)
            {
                System.out.println("Not enough space: " + (getNToys()+number) + "/" + capacity );
                return;
            }
        }
        System.out.println("" + number + " toys added");
        coleccion.putIfAbsent(toy,number);
    }

    public void putP(String name, float prob){
        if (this.capacity == null){
            System.out.println("Not allowed");
            return;
        }
        Toy toy = new Toy(name);
        Integer number = Math.round(capacity*prob);
        if(number <= 0){
            System.out.println("No toys added");
            return;
        }
        if(getNToys()+number > capacity) {
            System.out.println("Probability must be up to "+ (1.-(double)getNToys()/capacity));
        }
        else if (getNToys()+number == capacity+1){
            System.out.println("" + (number-1) + " toys added");
            coleccion.putIfAbsent(toy,number-1);
        }
        else{
            System.out.println("" + number + " toys added");
            coleccion.putIfAbsent(toy,number);
        }
    }


    public Integer getNToys(){
        return coleccion.values().stream().mapToInt(d->d).sum();
    }


    public void pp(){
        System.out.println(Arrays.deepToString(coleccion.values().toArray()));
    }


    public void shuffle(){
        deck = new ArrayDeque<>();
        ArrayList<Toy> set = new ArrayList<>();
        set.addAll(coleccion.keySet());
        Random rand = new Random();
        while(set.size() > 0){
            Integer n = rand.nextInt(set.size());
            Toy toy = set.get(n);
            // Для сохранения интриги последние экземпляры кидаем в конец очереди
            if(coleccion.get(toy) <= 1) {
                deck.addLast(toy);
                coleccion.remove(toy);
                set.remove(toy);
            }
            else{
                deck.addFirst(toy);
                coleccion.put(toy,coleccion.get(toy)-1);
            }
        }
        System.out.println("Shuffled");

    }

    public Toy get(){
        if(deck.isEmpty()){
            System.out.println("No toys left");
            return null;
        }
        if(deck == null)
        {
            System.out.println("Toys are not shuffled");
            return  null;
        }
        System.out.println(deck.peek().name);
        return deck.pop();

    }

    public void printDeck(){
        System.out.println(deck);
    }


    public void printToys()
    {
        for(Toy toy: coleccion.keySet()){
            System.out.println(toy.getId() + " " + toy.name + " : " + coleccion.get(toy));
        }
    }

    public void printProbs()
    {
        for(Toy toy: coleccion.keySet()){
            float prob = Math.round((float)coleccion.get(toy)/getNToys()*100)/100.f;
            System.out.println(toy.getId() + " " + toy.name + " : " + prob);
        }
    }


    public void addToys(Integer id, Integer number)
    {
        if(id > coleccion.keySet().size() || id < 1)
        {
            System.out.println("No such id in collection");
            return;
        }
        Toy toy = (Toy)coleccion.keySet().toArray()[id-1];

        if (getNToys()+number > capacity)
        {
            System.out.println("Not enough space: " + (getNToys()+number) + "/" + capacity );
            return;
        }
        coleccion.put(toy,coleccion.get(toy)+number);
    }


    public void fillMachine(){

        for (Toy toy: coleccion.keySet()){
            coleccion.put(toy,Math.round((float)coleccion.get(toy)/getNToys()*capacity));
        }

        while(getNToys() > capacity){
            coleccion.put(coleccion.lastKey(),coleccion.get(coleccion.lastKey())-1);
        }
        while(getNToys() < capacity){
            coleccion.put(coleccion.lastKey(),coleccion.get(coleccion.lastKey())+1);
        }



    }

    public TreeMap<Toy, Integer> getToys(){
        return coleccion;
    }



    public Integer getCapacity(){
        return capacity;
    }

    public void setCapacity(Integer cap){
        this.capacity = cap;
    }



}
