package devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyIntegrationTest {
    public Device device;
    public int limit;

    @BeforeEach
    void init(){
        this.limit=5;
        this.device=new StandardDevice(new CounterFailing(this.limit));
    }

    @Test
    void attemptOnOnce(){
        assertFalse(this.device.isOn());
        this.device.on();
        assertTrue(this.device.isOn());
    }

    @Test
    void attemptOnManyTimes(){
        for (int i=0;i<this.limit;i++){
            assertFalse(this.device.isOn());
            this.device.on();
            assertTrue(this.device.isOn());
            this.device.off();

        }
    }

    @Test
    void attemptOnMoreThanLimit(){
        for (int i=0;i<this.limit;i++){
            assertFalse(this.device.isOn());
            this.device.on();
            assertTrue(this.device.isOn());
            this.device.off();
        }
        try {
            this.device.on();
            fail();
        }catch (IllegalStateException _){
            assertFalse(this.device.isOn());
        }
    }

    @Test
    void attemptOnReset(){
        for (int i=0;i<this.limit;i++){
            assertFalse(this.device.isOn());
            this.device.on();
            assertTrue(this.device.isOn());
            this.device.off();
        }
        this.device.reset();
        for (int i=0;i<this.limit;i++){
            assertFalse(this.device.isOn());
            this.device.on();
            assertTrue(this.device.isOn());
            this.device.off();
        }
    }
}
