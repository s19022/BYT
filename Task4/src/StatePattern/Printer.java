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
        try{
            state.loadText(text);
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    public void print(){
        try{
            state.print();
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }

    }
}
