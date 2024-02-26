public class App {
    public static void main(String[] args) {
        ToysMachine tots = new ToysMachine(20);
        tots.putP("Свинка",0.1f);
        tots.putP("Зайчик", 0.3f);
        tots.putP("йо-йо", 0.4f);

//        tots.printToys();
//        tots.printProbs();
//        tots.shuffle();
//
//        for (int i = 0; i < 20; i++) {
//            tots.get();
//        }
//
//        tots.printDeck();

//        tots.addToys(1,2);
        tots.fillMachine();
        tots.printToys();
        tots.printProbs();

    }
}
