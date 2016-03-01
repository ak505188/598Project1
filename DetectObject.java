package Ultrasonic;

import lejos.hardware.Button;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class DetectObject
{
	public static void main(String[] args)
	{
		//This assumes that the Sensor is plugged into the first Sensor Port
		Port p1 = LocalEV3.get().getPort("S1");
		//Create a ultrasonic object using p1
		EV3UltrasonicSensor sensor = new EV3UltrasonicSensor(p1);
		//EV3 sensors use the sample provider class to store their data
		SampleProvider sample = sensor.getMode("Distance");
		//sample provider stores data in a float. This gets the size the float needs to be
		float[] distance = new float[sample.sampleSize()];
	
		while(true)
		{
			//This gets the sensor data and stores it in the distance float
			sample.fetchSample(distance, 0);
			//The distance data is stored in index 0 of the array
			//If there is an object within 2 CM (Meters is default measurement) 
			if (distance[0] < .2)
			{
				//Fire catapult!
			}
		
			//Exit loop if the escape button is pressed on the EV3 Brick
			if (Button.ESCAPE.isDown())
			{
				sensor.close();
				System.exit(0);
			}

		}
	}

}
