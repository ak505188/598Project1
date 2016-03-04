package catapult;

import java.io.File;
import java.io.FileNotFoundException;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Sound;
import lejos.hardware.Sounds;
import lejos.hardware.motor.Motor;

public class Hammer implements Sounds{

  public static EV3UltrasonicSensor distSensor =
      new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
  //public static File file = new File("./hammertime.wav");
  
  public static void main(String[] args) {

    // Create Sensor object and enable it
    distSensor.enable();

    // Create a sample provider to get distance from sensor
    SampleProvider distSample = distSensor.getDistanceMode();

    // SampleProvider writes the data into a float array
    // Array is necessary despite it only having one value
    float[] distance = new float[1];

    // Move hammer to starting position
    Motor.A.rotateTo(90);
    
    // Make McHammer drive forward
    Motor.B.forward();

    // Infinite loop for running bot
    while(true) {
      // This gets the sensor data and stores it in the distance float
      // The distance data is stored in index 0 of the array
      // If there is an object within 2 CM (Meters is default measurement) 
      distSample.fetchSample(distance, 0);

      // System.out.println("Distance: " + distance[0]);

      // If close enough to object runs hammerTime
      if (distance[0] < .05) {
        Motor.B.stop();
        Sound.beepSequence();
        
        hammerTime();
        Motor.B.forward();
      }
      
      if (Button.ESCAPE.isDown()) {
        Motor.B.stop();
        distSensor.close();
        System.exit(1);
      }
    }
  }

  public static void hammerTime() {
    Motor.A.setSpeed(Motor.A.getMaxSpeed());
    
      Motor.A.rotateTo(-80);
      Motor.A.rotateTo(0);
   
  }

}
