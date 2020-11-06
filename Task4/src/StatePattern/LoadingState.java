package StatePattern;

public class LoadingState implements State {

    private Printer printer;

    public LoadingState(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void pressPowerButton() {
        printer.setState(new TurnedOffState(printer));
    }

    @Override
    public void loadText(String text) {
        printer.setState(new ReadyState(printer, text));
    }

    @Override
    public void print() {

    }

}
