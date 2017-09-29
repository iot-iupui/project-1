import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

class ToggleLightTest {
    public static void main (String... args) throws InterruptedException {
        final GpioController gpio = GpioFactory.getInstance();

        final GpioPinDigitalOutput ledPinOut = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "LED_OUT", PinState.LOW);
        final GpioPinDigitalInput button = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);

        button.setShutdownOptions(true);
        ledPinOut.setShutdownOptions(true, PinState.LOW);

        button.addListener((GpioPinListenerDigital) GpioPinDigitalStateChangeEvent -> {
            ledPinOut.toggle();
        });

        while(true) {
            Thread.sleep(500);
        }
    }
}