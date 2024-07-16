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
	boolean executou = false;
	
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		// Robot main loop
		
		double alturaMapa = getBattleFieldHeight();
		double larguraMapa = getBattleFieldWidth();	
		double up = alturaMapa - getY();
		double down = getY();
		double left = getX();
		double right = larguraMapa - getX();
		double[] wallDistance = {up, down, right, left};
		double menorDistancia = wallDistance[0];
		int direcao = 0;


// se robo = posição 10x em um mapa de 50x e 15y em um mapa de 35y 

		for (int i = 0; i<4; i++){
			if (wallDistance[i] < menorDistancia){
				menorDistancia = wallDistance[i] ;
				direcao = i;
			}
		}
		
		switch (direcao){
			case 0:
				turnLeft(getHeading() + 20);
				ahead(up - 45);
				executou = true;
				break;
			case 1:
				turnLeft(getHeading() - 160);
				ahead(down - 45);
				executou = true;
				break;
			case 2:
				turnLeft(getHeading() + 250);
				ahead(right - 45);
				executou = true;
				break;
			case 3:
				turnLeft(getHeading() - 250);	
				ahead (left - 45);
				executou = true;
				break;
		}
			

				
		
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

		if (executou){
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

		if(getX() <= 30){  //Bate na esquerda
		//bate na esquerda (0,180)
			//(0,270]
			if (getHeading()>= 270 && getHeading()<360){
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnRight(90 + (360-getHeading()));
			}
			//(270,180)
			else{
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnLeft(90+ (getHeading()-180));
			}
			ahead(larguraMapa/4);
			
		}else if(getX() >= larguraMapa - 30){ //Bate na direita
		//bate na direita (0,180)
			//(0,90]
			if (getHeading()>0 && getHeading()<=90){
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnLeft(getHeading()+90 );
			}
			//(90, 180)
			else{
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnRight(90 + (180-getHeading()));
		}
			ahead(larguraMapa/4);
			

		}else if(getY() <= 30){ //Bate embaixo
		//bate embaixo (270,90)
		//(90,180]
		if(getHeading() >90 && getHeading() <=180){
			System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
			turnLeft(getHeading());
		}
		//(270,180)
		else{
			System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
			turnRight(90+ (270-getHeading()));
		}
			ahead(alturaMapa/4);
			

		}else if(getY() >= alturaMapa - 30){ //Bate em cima
		//bate em cima (270, 90)
		//[0,90)
		if(getHeading()>=0 && getHeading()<90){
			System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
			turnRight(90+ (90-getHeading()));
		}
		//(270,0)
		else{
			System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
			turnLeft(90+ (getHeading()-270));
		}
	
		}
			ahead(alturaMapa/4);
	}
	
	public void onWin(WinEvent e) {
			for(int i = 0; i <2; i++){
			turnLeft(30);
			turnRight(30);
			}
			turnGunRight(360);
	}

	
}
