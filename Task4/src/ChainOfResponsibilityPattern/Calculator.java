package ChainOfResponsibilityPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculator {
    private Request request;
    private Handler handler;

    public Calculator(){
        setHandlers();
        calculate();
    }

    public void setRequest(String input){
        parseRequest(input);
    }

    public void calculate(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try{
            while (true){
                System.out.println("Enter data in format \"number operator number\"");
                setRequest(reader.readLine());
                System.out.println(handler.handle(request));
            }
        }catch (IOException ignore){}
        catch (HandleException ex){
            System.out.println(ex.toString());
        }
    }

    private void parseRequest(String input){
        String[] inputArray = input.split("\\s");
        int firstOperand = Integer.parseInt(inputArray[0]);
        char operator = inputArray[1].charAt(0);
        int secondOperand = Integer.parseInt(inputArray[2]);

        request = new Request(firstOperand, secondOperand, operator);
    }

    public void setHandlers(){
        Handler minus = new SubtractOperandHandler();
        Handler plus = new AddOperandHandler();
        Handler multiply = new MultiplyOperandHandler();
        Handler divide = new DevideOperandHandler();
        Handler fin = new FinalHandler();

        minus.setNext(plus);
        plus.setNext(multiply);
        multiply.setNext(divide);
        divide.setNext(fin);

        handler = minus;

    }

    public static void main(String[] args){
        new Calculator();
    }

}
