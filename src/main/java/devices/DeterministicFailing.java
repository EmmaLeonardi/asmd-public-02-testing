package devices;

import java.util.function.BooleanSupplier;

public class DeterministicFailing implements FailingPolicy {
    private final BooleanSupplier supplier;
    private boolean failed = false;

    public DeterministicFailing(BooleanSupplier supplier){
        this.supplier=supplier;
    }

    @Override
    public boolean attemptOn() {
        this.failed = this.failed || supplier.getAsBoolean();
        return !this.failed;
    }

    @Override
    public void reset() {
        this.failed = false;
    }

    @Override
    public String policyName() {
        return "random";
    }
}
