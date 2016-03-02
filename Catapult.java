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

public class Catapult implements Sounds{

	public static File file = new File("./hammertime.wav");
	
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    /*    
    // This assumes that the Sensor is plugged into the first Sensor Port
    Port p1 = LocalEV3.get().getPort("S1");
    // Create a ultrasonic object using p1
    EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(p1);
    // EV3 sensors use the sample provider class to store their data
    SampleProvider sample = sensor.getMode("Distance");
    // sample provider stores data in a float. This gets the size the float
    // needs to be
    float[] distance = new float[sample.sampleSize()];
    */
    // Set Speed

    EV3UltrasonicSensor distSensor = new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
    distSensor.enable();
    SampleProvider distSample = distSensor.getDistanceMode();
    float[] distance = new float[1];
    Motor.A.rotateTo(90);
    
    Motor.B.forward();
    while(true) {
    
    	/*
      if(!distSensor.isEnabled())
      {
    	  distance = null;
    	  distance = new float[1];
    	  distSensor.enable();
      }
      */
    	
      distSample.fetchSample(distance, 0);
      //System.out.println("DistSample SampleSize: " + distSample.sampleSize());
      //System.out.println("Distance: " + distance[0]);
      
      //This gets the sensor data and stores it in the distance float
      //The distance data is stored in index 0 of the array
      //If there is an object within 2 CM (Meters is default measurement) 
      if (distance[0] < .05) {
    	
        //file = new File("./hammertime.wav");
		//Sound.playSample(file, 100);
		//file = null;
    	
		Motor.B.stop();
    	//distSensor.disable();
		hammerTime();
		Motor.B.forward();
		distance[0] = 2;
		
      }
      
      if (Button.ESCAPE.isDown()) {
        Motor.B.stop();
        distSensor.close();
        System.exit(1);
      }
    }

  }
  
  public static void hammerTime()
  {
    
    Motor.A.setSpeed(Motor.A.getMaxSpeed());
    //Sound.setVolume(20);
    //Sound.beepSequenceUp();
    
    //for (int i = 0; i < 3; i++) {  
      Motor.A.rotateTo(-80);
      Motor.A.rotateTo(0);
    //}
     
  }
}
