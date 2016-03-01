package catapult;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

import lejos.hardware.motor.Motor;

public class Catapult {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// This assumes that the Sensor is plugged into the first Sensor Port
		Port p1 = LocalEV3.get().getPort("S1");
		// Create a ultrasonic object using p1
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(p1);
		// EV3 sensors use the sample provider class to store their data
		SampleProvider sample = sensor.getMode("Distance");
		// sample provider stores data in a float. This gets the size the float
		// needs to be
		float[] distance = new float[sample.sampleSize()];

    // Set Speed

		Motor.B.forward();
		while(true) {
			sample.fetchSample(distance, 0);
			//This gets the sensor data and stores it in the distance float
			//The distance data is stored in index 0 of the array
			//If there is an object within 2 CM (Meters is default measurement) 
			if (distance[0] < .02) {
			  // Fire catapult!
				launch();
			}
      if (Button.ESCAPE.isDown()) {
        Motor.B.stop();
        sensor.close();
        System.exit(1);
			}
		}

	}
	
	public static void launch()
	{
		Motor.B.stop();
		Motor.A.setSpeed(Motor.A.getMaxSpeed());

    for (int i = 0; i < 3; i++) {	
		  Motor.A.rotateTo(-95);
		  Motor.A.rotateTo(0);
    }
    Motor.B.forward();	
	}
}
