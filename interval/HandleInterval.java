package interval;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class HandleInterval implements GpioPinListenerDigital {

    FlickerLoop flickerLoop;

    public HandleInterval(FlickerLoop flickerLoop) {
        this.flickerLoop = flickerLoop;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
        if (gpioPinDigitalStateChangeEvent.getState().isHigh()){
            this.flickerLoop.toggleButtonPress();
        }
        System.out.println("HandleInterval State Changed: " + gpioPinDigitalStateChangeEvent.getState().toString());
    }
}
