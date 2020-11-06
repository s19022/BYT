package ChainOfResponsibilityPattern;

public class FinalHandler implements Handler {
    @Override
    public void setNext(Handler handler) {
        //ignore
    }

    @Override
    public double handle(Request request) throws HandleException {
        throw new HandleException("Operator \"" + request.getOperator() + "\" is not supported");
    }
}
