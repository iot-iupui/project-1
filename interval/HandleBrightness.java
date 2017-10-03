package interval;

import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class HandleBrightness implements GpioPinListenerDigital {

    FlickerLoop flickerLoop;
    int[] intensities = {25, 6, 100};
    int counter = 0;

    public HandleBrightness(FlickerLoop flickerLoop) {
        this.flickerLoop = flickerLoop;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
        if (gpioPinDigitalStateChangeEvent.getState().isHigh()){
            this.flickerLoop.setIntensity(intensities[counter++ % 3]);
        }
        System.out.println("HandleBrightness State Changed: " + gpioPinDigitalStateChangeEvent.getState().toString());
    }
}
