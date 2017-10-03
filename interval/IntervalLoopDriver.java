package interval;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

class IntervalLoopDriver {
    public static void main (String... args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalOutput ledPinOut = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED_OUT", PinState.LOW);
        final GpioPinDigitalInput buttonA = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
        final GpioPinDigitalInput buttonB = gpio.provisionDigitalInputPin(RaspiPin.GPIO_03, PinPullResistance.PULL_DOWN);

        buttonA.setShutdownOptions(true);
        buttonB.setShutdownOptions(true);
        ledPinOut.setShutdownOptions(true, PinState.LOW);

        FlickerLoop flickerLoop = new FlickerLoop(ledPinOut);
        GpioPinListenerDigital handlePress = new HandleSwitch(flickerLoop, buttonA);

        buttonB.addListener(handlePress);
        new Thread(flickerLoop).start();

        while(true) {
            Thread.sleep(500);
        }
    }
}