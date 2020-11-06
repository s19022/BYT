package StatePattern;

public interface State {
    void pressPowerButton();
    void loadText(String text)throws Exception;
    void print()throws Exception;
}
