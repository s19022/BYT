package StatePattern;

public class ReadyState implements State{
    private Printer printer;
    private String toPrint;

    public ReadyState(Printer printer, String toPrint) {
        this.printer = printer;
        this.toPrint = toPrint;
    }

    @Override
    public void pressPowerButton() {
        printer.setState(new TurnedOffState(printer));
    }

    @Override
    public void loadText(String text) {
    }

    @Override
    public void print() {
        System.out.println(toPrint);
        printer.setState(new LoadingState(printer));
    }

}
