package StatePattern;

public class Printer {
    private State state;

    public Printer() {
        state = new TurnedOffState(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void pressPowerButton(){
        state.pressPowerButton();
    }

    public void loadText(String text){
        state.loadText(text);
    }

    public void print(){
        state.print();
    }
}
