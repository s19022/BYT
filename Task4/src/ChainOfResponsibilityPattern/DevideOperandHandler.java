package ChainOfResponsibilityPattern;

public class DevideOperandHandler implements Handler{
    private Handler next;

    @Override
    public void setNext(Handler handler) {
        next = handler;
    }

    @Override
    public double handle(Request request) throws HandleException{
        if (request.getOperator() == '/') {
            if (request.getSecondOperand() == 0) throw new HandleException("Division by 0 is illegal");
            return request.getFirstOperand() / request.getSecondOperand();
        }
        if(next == null) throw new HandleException("Could not parse " + request.toString());

        return next.handle(request);
    }


}
