package StatePattern;

public class TurnedOffState implements State{

    private Printer printer;

    public TurnedOffState(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void pressPowerButton() {
        printer.setState(new LoadingState(printer));
    }

    @Override
    public void loadText(String text) throws Exception{
        throw new Exception("Printer is turned off");
    }

    @Override
    public void print() throws Exception{
        throw new Exception("Printer is turned off");
    }
}
