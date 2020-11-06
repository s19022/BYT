package ChainOfResponsibilityPattern;

public class MultiplyOperandHandler implements Handler {
    private Handler next;

    @Override
    public void setNext(Handler handler) {
        next = handler;
    }

    @Override
    public double handle(Request request) throws HandleException{
        if (request.getOperator() == '*') return request.getFirstOperand() * request.getSecondOperand();
        if(next == null) throw new HandleException("Could not parse " + request.toString());
        return next.handle(request);
    }

}
