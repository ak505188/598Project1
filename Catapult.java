package catapult;

import lejos.hardware.Button;
import lejos.hardware.motor.Motor;

public class Catapult {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		while(true)
		{
			int press = Button.waitForAnyPress();
			
			switch(press)
			{
				case (Button.ID_ENTER):
					launch();
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
		Motor.A.rotateTo(-75);
		
		Motor.A.rotateTo(0);
		
		return launched;
	}

}
