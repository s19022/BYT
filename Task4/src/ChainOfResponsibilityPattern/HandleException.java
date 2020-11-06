package ChainOfResponsibilityPattern;

public class HandleException extends Exception{
    private Request request;
    private String message;

    public HandleException(Request _request){
        request = _request;
    }

    public HandleException(String _message){
        message = _message;
    }


    @Override
    public String toString() {
        return message;
    }
}
