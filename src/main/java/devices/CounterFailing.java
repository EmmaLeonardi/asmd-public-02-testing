package devices;

public class CounterFailing implements FailingPolicy{

    private final int numberFailures;
    private int counter;

    public CounterFailing(int numberFailures){
        this.numberFailures=numberFailures;
        this.counter=0;
    }

    @Override
    public boolean attemptOn() {
        if(counter<this.numberFailures){
            counter++;
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void reset() {
        this.counter=0;
    }

    @Override
    public String policyName() {
        return "counter";
    }
}
