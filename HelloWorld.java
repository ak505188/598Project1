import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;

public class HelloWorld {
  public static void main (String[] args) {
    System.out.println("Hello World!");
    Motor.A.setSpeed(360); // 1RPM
    Motor.A.forward();
    Button.ENTER.waitForPress();
    Motor.A.stop();
  }
}
