package garage;
import robocode.*;
import java.awt.Color;

public class KillerQueen extends Robot
	{
	double tamanho;
	boolean executou = false;
	
	public void run() {
	
		setBodyColor(Color.red);
		setGunColor(Color.yellow);
		setRadarColor(Color.black);
		setBulletColor(Color.white);
		setScanColor(Color.yellow);
		

		double alturaMapa = getBattleFieldHeight();
		double larguraMapa = getBattleFieldWidth();	
		double distanciaCima = alturaMapa - getY();
		double distanciaBaixo = getY();
		double distanciaEsquerda = getX();
		double distanciaDireita = larguraMapa - getX();
		double[] distanciasParede = {distanciaCima, distanciaBaixo, distanciaDireita, distanciaEsquerda};
		double menorDistancia = distanciasParede[0];
		int direcao = 0;

		for (int i = 0; i<4; i++){
			if (distanciasParede[i] < menorDistancia){
				menorDistancia = distanciasParede[i] ;
				direcao = i;
			}
		}
		
		switch (direcao){
			case 0:
				turnLeft(getHeading() + 20);
				ahead(distanciaCima - 45);
				executou = true;
				break;
			case 1:
				turnLeft(getHeading() - 160);
				ahead(distanciaBaixo - 45);
				executou = true;
				break;
			case 2:
				turnLeft(getHeading() + 250);
				ahead(distanciaDireita - 45);
				executou = true;
				break;
			case 3:
				turnLeft(getHeading() - 250);	
				ahead (distanciaEsquerda - 45);
				executou = true;
				break;
		}
			

				
		
		while(true) {
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
	
	public void onScannedRobot(ScannedRobotEvent e) {
		tamanho = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		System.out.println();
		if (executou){
			if(e.getDistance() < tamanho/5 && getEnergy() > 65){
				fire(3);
				turnRight(e.getBearing());
				scan();
			}else if(e.getDistance() < tamanho/4 && getEnergy() > 50){
				fire(2.5);
				turnRight(e.getBearing());
				scan();
			}else if(e.getDistance() < tamanho/3){
				fire(2);
				turnRight(e.getBearing());
				scan();
			}else if(e.getDistance() < tamanho/3 && getEnergy() <= 30 && e.getEnergy() > getEnergy()){
				fire(3);
				turnRight(e.getBearing());
				ahead(e.getDistance() + 10);
			}else{
				fire(1.5);
			}
		}
	}

	public void onHitByBullet(HitByBulletEvent e) {
		
	}
	
	public void onHitRobot(HitRobotEvent e){
		fire(3);
		back(20);
		turnRight(e.getBearing());
		ahead(40);
	}
	
	public void onHitWall(HitWallEvent e) {

		double alturaMapa = getBattleFieldHeight();
		double larguraMapa = getBattleFieldWidth();	

		if(getX() <= 30){  //Bate na esquerda
			//bate na esquerda (0,180)	
			if (getHeading()>= 270 && getHeading()<360){ //(0,270]
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnRight(90 + (360-getHeading()));
			}else{ //(270,180)
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnLeft(90+ (getHeading()-180));
			}
			ahead(larguraMapa/4);
		}else if(getX() >= larguraMapa - 30){ //Bate na direita
			//bate na direita (0,180)
			if (getHeading()>0 && getHeading()<=90){ //(0,90]
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnLeft(getHeading()+90 );
			}else{ //(90, 180)
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnRight(90 + (180-getHeading()));
			}	
			ahead(larguraMapa/4);
		}else if(getY() <= 30){ //Bate embaixo
			//bate embaixo (270,90)		
			if(getHeading() >90 && getHeading() <=180){ //(90,180]
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnLeft(getHeading());
			}else{ //(270,180)
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnRight(90+ (270-getHeading()));
			}
			ahead(alturaMapa/4);
		}else if(getY() >= alturaMapa - 30){ //Bate em cima
		//bate em cima (270, 90)
			if(getHeading()>=0 && getHeading()<90){ //[0,90)
				System.out.println("Bearing: " + e.getBearing() + " Heading: " + getHeading());
				turnRight(90+ (90-getHeading()));
			}else{ //(270,0)
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
