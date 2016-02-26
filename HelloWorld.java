import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;

public class HelloWorld {
  public static void main (String[] args) {
    System.out.println("Hello World!");
    Motor.B.setSpeed(360); // 1RPM
    Motor.D.setSpeed(360); // 1RPM
    System.out.println(1);
    Motor.B.backward();
    Motor.D.backward();
    System.out.println(2);
    Motor.C.setSpeed(720);
    Motor.C.forward();
    Button.ENTER.waitForPress();
    Motor.B.stop();
    Motor.D.stop();
    Motor.C.stop();
  }
}
