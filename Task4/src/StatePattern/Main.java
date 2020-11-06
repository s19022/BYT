package StatePattern;

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer();
        printer.pressPowerButton();
        printer.loadText("some text");
        printer.print();        // prints "some text" to the console, changes state to LoadingState

        printer.pressPowerButton();
        printer.loadText("text2");  // prints "Printer is turned off"
        printer.print(); // prints "Printer is turned off"

        printer.pressPowerButton();
        printer.print(); // prints "Printer is loading"
        printer.loadText("text 3");
        printer.loadText("text 4");
        printer.print();        // prints "text 4" to the console, changes state to LoadingState
        printer.print(); // prints "Printer is loading"


    }
}
