public class EvaluateConstant implements IEvaluable {
    private int constant;

    public EvaluateConstant(int constant) {
        this.constant = constant;
    }

    @Override
    public int evaluate(){
        return constant;
    }
}
