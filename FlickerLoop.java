import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class FlickerLoop implements GpioPinListenerDigital, Runnable {

    GpioPinDigitalOutput pin;
    boolean buttonPress;
    int even;

    public FlickerLoop(GpioPinDigitalOutput pin) {
        this.pin = pin;
        this.buttonPress = false;
        int even = 0;
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
                buttonPress = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent gpioPinDigitalStateChangeEvent) {
        if (even % 2 == 0){
            this.buttonPress = true;
        }
        even++;
        System.out.println("Pressed.\t Even: " + even);
    }
}
