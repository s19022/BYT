package ChainOfResponsibilityPattern;

public class Request {
        private final double firstOperand;
        private final double secondOperand;
        private final char operator;

    public Request(double firstOperand, double secondOperand, char operator) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
        this.operator = operator;
    }

    public double getFirstOperand() {
        return firstOperand;
    }

    public double getSecondOperand() {
        return secondOperand;
    }

    public char getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "Request{" +
                "firstOperand=" + firstOperand +
                ", secondOperand=" + secondOperand +
                ", operator=" + operator +
                '}';
    }
}
