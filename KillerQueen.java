package garage;
import robocode.*;
//import java.awt.Color;

// API help : https://robocode.sourceforge.io/docs/robocode/robocode/Robot.html

/**
 * KillerQueen - a robot by (your name here)
 */
public class KillerQueen extends Robot
{
	/**
	 * run: KillerQueen's default behavior
	 */
	double tamanho;
	
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		
		double alturaMapa = getBattleFieldHeight();
		double larguraMapa = getBattleFieldWidth();	

		turnLeft(getHeading());
		
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(alturaMapa/4);
			turnGunRight(360);
			turnLeft(90);
			ahead(larguraMapa/4);
			turnGunRight(360);
			turnRight(90);
			ahead(alturaMapa/4);
			turnGunRight(360);
			turnLeft(90);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		tamanho = Math.max(getBattleFieldWidth(), getBattleFieldHeight());

		if(e.getDistance() < tamanho/5 && getEnergy() > 50){
			fire(3);
			turnRight(e.getBearing());
			scan();
		}else if(e.getDistance() < tamanho/4 && getEnergy() > 30){
			fire(2.5);
			turnRight(e.getBearing());
			scan();
		}else if(e.getDistance() < tamanho/3 && getEnergy() > 10){
			fire(2);
			turnRight(e.getBearing());
		}else{
			fire(1.5);
		}
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		
		
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		
		double alturaMapa = getBattleFieldHeight();
		double larguraMapa = getBattleFieldWidth();	

		if(getX() <= 24){  //Bate na esquerda
			turnLeft(getHeading() - 90);
			ahead(larguraMapa/4);
		}else if(getX() >= larguraMapa - 24){ //Bate na direita
			turnLeft(getHeading() + 90);
			ahead(larguraMapa/4);
		}else if(getY() <= 24){ //Bate embaixo
			turnLeft(getHeading());
			ahead(alturaMapa/4);
		}else if(getY() >= alturaMapa - 24){ //Bate em cima
			turnLeft(getHeading() + 180);
			ahead(alturaMapa/4);
		}
	}
}
