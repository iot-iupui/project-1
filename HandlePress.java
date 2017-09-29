import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class HandlePress implements GpioPinListenerDigital {

    FlickerLoop flickerLoop;

    public HandlePress(FlickerLoop flickerLoop) {
        this.flickerLoop = flickerLoop;
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
        if (gpioPinDigitalStateChangeEvent.getState().isHigh()){
            this.flickerLoop.toggleButtonPress();
        }
        System.out.println("State Changed: " + gpioPinDigitalStateChangeEvent.getState().toString());
    }
}
