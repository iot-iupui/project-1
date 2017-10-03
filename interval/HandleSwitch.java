package interval;

import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class HandleSwitch implements GpioPinListenerDigital {
    private GpioPinListenerDigital[] listeners = new GpioPinListenerDigital[2];
    private GpioPinDigitalInput button;
    private int counter;

    public HandleSwitch(FlickerLoop flickerLoop, GpioPinDigitalInput button) {
        this.listeners[0] = new HandleBrightness(flickerLoop);
        this.listeners[1] = new HandleInterval(flickerLoop);
        this.button = button;
        button.addListener(this.listeners[counter++ % 2]);
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
        if (gpioPinDigitalStateChangeEvent.getState().isHigh()){
            this.button.removeAllListeners();
            this.button.addListener(this.listeners[counter++ % 2]);
        }
        System.out.println("HandleSwitch State Changed: " + gpioPinDigitalStateChangeEvent.getState().toString());
    }
}
