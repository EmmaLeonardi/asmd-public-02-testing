package devices;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class MyDeviceTest {

    private Device device;
    private FailingPolicy failingPolicy;
    private Device spyDevice;
    private FailingPolicy spyPolicy;

    @BeforeEach
    void init() {
        this.failingPolicy=mock(FailingPolicy.class);
        this.device=new StandardDevice(failingPolicy);
        this.spyPolicy=spy(FailingPolicy.class);
        this.spyDevice=new StandardDevice(spyPolicy);
    }

    @Test
    void testInitiallyOff(){
        assertFalse(device.isOn());
    }

    @Test
    void testDeviceTurnsOn(){
        assertFalse(device.isOn());
        when(failingPolicy.attemptOn()).thenReturn(true);
        device.on();
        assertTrue(device.isOn());
    }

    @Test
    void testDeviceDoesntTurnOn(){
        assertFalse(device.isOn());
        when(failingPolicy.attemptOn()).thenReturn(false);
        try{
            device.on();
            fail();
        }catch (IllegalStateException _){
            assertFalse(device.isOn());
        }
    }

    @Test
    void testDeviceTurnsOff(){
        when(failingPolicy.attemptOn()).thenReturn(true);
        device.on();
        device.off();
        assertFalse(device.isOn());
    }

    @Test
    void testDeviceResets(){
        verifyNoInteractions(this.spyPolicy);
        spyDevice.reset();
        verify(this.spyPolicy).reset();
    }
}
