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
		
		while(true)
		{
			int press = Button.waitForAnyPress();
			
			switch(press)
			{
				case (Button.ID_ENTER):
					
					boolean exit = false;
					while(!exit)
					{
						Motor.B.forward();
						//This gets the sensor data and stores it in the distance float
						sample.fetchSample(distance, 0);
						//The distance data is stored in index 0 of the array
						//If there is an object within 2 CM (Meters is default measurement) 
						if (distance[0] < .02)
						{
							//Fire catapult!
							Motor.B.stop();
							launch();
						}
						press = Button.waitForAnyEvent(3000);
						
						if(press != Button.ID_ENTER)
						{
							exit = true;
						}
					}
					break;
				case (Button.ID_ESCAPE):
					System.exit(0);
					break;
			}
		}

	}
	
	public static boolean launch()
	{
		boolean launched = false;
		
		Motor.A.setSpeed(Motor.A.getMaxSpeed());
		Motor.A.rotateTo(-95);
		
		Motor.A.rotateTo(0);
		
		return launched;
	}

}
