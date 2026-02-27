package devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyDeterministicPolicyTest {

    public FailingPolicy policyFalse;
    public FailingPolicy policyTrue;

    @BeforeEach
    void init() {
        this.policyFalse=new DeterministicFailing(()->true);
        this.policyTrue=new DeterministicFailing(()->false);
    }

    @Test
    void testAttemptOnFail(){
        assertFalse(this.policyFalse.attemptOn());
    }

    @Test
    void testAttemptOnFailManyTimes(){
        for(var i=0; i<10; i++) {
            assertFalse(this.policyFalse.attemptOn());
        }
    }

    @Test
    void testAttemptOnPass(){
        assertTrue(this.policyTrue.attemptOn());
    }

    @Test
    void testAttemptOnPassManyTimes(){
        for(var i=0; i<10; i++) {
            assertTrue(this.policyTrue.attemptOn());
        }
    }






}
