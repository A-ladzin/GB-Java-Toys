public class App {
    public static void main(String[] args) {
        ToysMachine tots = new ToysMachine(15);
        tots.putP("Свинка",0.33f);
//        tots.putP("Зайчик", 0.5f);
//        tots.putN("йо-йо", 2);

//        tots.printToys();
//        tots.printProbs();
//        tots.shuffle();
//
//        for (int i = 0; i < 20; i++) {
//            tots.get();
//        }
//
//        tots.printDeck();

        tots.addToys(1,2);
        tots.printToys();

    }
}
