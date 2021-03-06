package interval;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.wiringpi.SoftPwm;

public class FlickerLoop implements Runnable {

    GpioPinDigitalOutput pin;
    private boolean buttonPress = false;
    private int intensity = 100;

    public FlickerLoop(GpioPinDigitalOutput pin) {
        //Gpio.wiringPiSetup();
        SoftPwm.softPwmCreate(pin.getPin().getAddress(), 0, 100);
        this.pin = pin;
    }

    public void run() {
        try {
            while (true) {
                for (int startingInterval = 1000; startingInterval > 0; startingInterval -= 150) {
                    for (int i = 0; i * startingInterval < 3000; i++) {
                        SoftPwm.softPwmWrite(pin.getPin().getAddress(), intensity);
                        Thread.sleep(startingInterval);
                        SoftPwm.softPwmWrite(pin.getPin().getAddress(), 0);
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

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }
}
