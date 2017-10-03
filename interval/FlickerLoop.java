package interval;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class FlickerLoop implements Runnable {

    GpioPinDigitalOutput pin;
    private boolean buttonPress = false;

    public FlickerLoop(GpioPinDigitalOutput pin) {
        this.pin = pin;
    }

    public void run() {
        try {
            while (true) {
                for (int startingInterval = 1000; startingInterval > 0; startingInterval -= 150) {
                    for (int i = 0; i * startingInterval < 3000; i++) {
                        pin.toggle();
                        Thread.sleep(startingInterval);
                        if (buttonPress) {
                            i--;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toggleButtonPress() {
        this.buttonPress = !buttonPress;
    }
}
