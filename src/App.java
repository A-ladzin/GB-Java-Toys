public class App {
    public static void main(String[] args) {


        ToysMachine tots = new ToysMachine(256);
//
        tots.put("Свинка",0.1f);
        tots.put("Зайчик", 0.2f);
        tots.put("йо-йо", 0.1f);
        tots.put("му-му", 0.1f);
        tots.put("фыр-фыр", 0.2f);
        tots.put("жук-жук", 0.3f);


        tots.fillMachine();
        tots.printToys();
        tots.printProbs();

        MyFrame frame = new MyFrame(tots);

    }
}
