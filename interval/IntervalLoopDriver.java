package interval;

import com.pi4j.io.gpio.*;
import interval.FlickerLoop;
import interval.HandlePress;

class IntervalLoopDriver {
    public static void main (String... args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalOutput ledPinOut = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED_OUT", PinState.LOW);
        final GpioPinDigitalInput button = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);

        button.setShutdownOptions(true);
        ledPinOut.setShutdownOptions(true, PinState.LOW);

        FlickerLoop flickerLoop = new FlickerLoop(ledPinOut);
        HandlePress handlePress = new HandlePress(flickerLoop);

        button.addListener(handlePress);
        new Thread(flickerLoop).start();

        while(true) {
            Thread.sleep(500);
        }
    }
}