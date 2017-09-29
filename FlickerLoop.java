import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class FlickerLoop implements GpioPinListenerDigital {

    GpioPinDigitalOutput pin;
    boolean buttonPress;

    public FlickerLoop(GpioPinDigitalOutput pin) {
        this.pin = pin;
        this.buttonPress = false;
    }

    void runLightFrequency() throws InterruptedException {
        while (true) {
            for (int startingInterval = 1000; startingInterval > 0; startingInterval -= 150) {
                for (int i = 0; i * startingInterval < 3000; i++) {
                    pin.toggle();
                    Thread.sleep(startingInterval);
                    if(buttonPress){
                        break;
                    }
                }
                if(buttonPress){
                    break;
                }
            }
            buttonPress = false;
        }
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
        this.buttonPress = true;
    }
}
