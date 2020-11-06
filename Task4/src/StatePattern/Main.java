package StatePattern;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.pressPowerButton();
        printer.loadText("some text");
        printer.print();
    }
}
