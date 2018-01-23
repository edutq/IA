// import lejos.nxt.Button;
// import lejos.nxt.LCD;
// import lejos.nxt.Motor;
import lejos.robotics.navigation.*;
import lejos.nxt.*;

public class Move {
	public static DifferentialPilot pilot = new DifferentialPilot(28f, 0.996063f, Motor.A, Motor.B);
	public static DifferentialPilot pilot2 = new DifferentialPilot(4.4f, 0.996063f, Motor.A, Motor.B);
	
	public static TouchSensor leftBump = new TouchSensor(SensorPort.S1);
    public static TouchSensor rightBump = new TouchSensor(SensorPort.S2);

    public static LightSensor lightSensor = new LightSensor(SensorPort.S3);
    // public static UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S4);

	public static void main(String[] args) {
		double degrees_turn = 90, distance = 100;
		int counter = 0;

		LCD.clear();

		lightSensor.calibrateHigh();
		lightSensor.setHigh(100);
		LCD.drawString("Value: " +lightSensor.readValue(), 0, 0);

		Button.waitForPress();

		while(!leftBump.isPressed() && !rightBump.isPressed()) {
			LCD.drawString("Triangle", 0, 2);
			pilot.travel(distance);
			pilot.rotate(degrees_turn);
		}

		while (counter < 5) {
			LCD.drawString("Inverse square", 0, 3);
			pilot.travel(distance * -1);
			pilot.rotate(degrees_turn * -1);
			counter++;
		}

		counter = 0;

		while (counter < 2) {
			pilot2.travel(distance);
			pilot2.rotate(degrees_turn);
		}

		Button.waitForPress();
		
		pilot.stop();
	}
}