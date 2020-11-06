package ChainOfResponsibilityPattern;

public interface Handler {
    void setNext(Handler handler);

    double handle(Request request) throws HandleException;
}
