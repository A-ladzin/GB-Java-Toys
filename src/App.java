public class App {
    public static void main(String[] args) {


        ToysMachine tots = new ToysMachine(128);

        tots.putP("Свинка",0.1f);
        tots.putP("Зайчик", 0.2f);
        tots.putP("йо-йо", 0.1f);
        tots.putP("му-му", 0.1f);
        tots.putP("фыр-фыр", 0.1f);
        tots.putP("жук-жук", 0.1f);

        tots.fillMachine();
        tots.printToys();
        tots.printProbs();

        MyFrame frame = new MyFrame(tots);

    }
}
