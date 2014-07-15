package t1314grupo14.behaviour;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.Behaviour;
import teams.ucmTeam.RobotAPI;

public class DefensaBasico extends Behaviour {
	
	private Vec2 balon;
	private boolean nuestraPorteriaDerecha;

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInit(RobotAPI r) {

		r.setDisplayString("Defensa");
		
		if (myRobotAPI.toFieldCoordinates(myRobotAPI.getOurGoal()).x < 0)
			nuestraPorteriaDerecha = false;
		else
			nuestraPorteriaDerecha = true;
		
		balon = new Vec2();
		actualizarVariables();
		
		myRobotAPI.setSteerHeading(balon.t);
        myRobotAPI.setSpeed(1.0);
		
	}

	@Override
	public void onRelease(RobotAPI arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int takeStep() {

		actualizarVariables();
		
		if (nuestraPorteriaDerecha){
			
			// Si nos alejamos de nuestro campo, volvemos
			if (myRobotAPI.getPosition().x < 0.25)
				
				myRobotAPI.setSteerHeading(myRobotAPI.getOurGoal().t);

			else {
				// Si balon en campo contrario, bloquear al mas proximo a la porteria
				if (balon.x < 0)
					myRobotAPI.blockForward();
				// sino, ir a por el balon y sacarlo de nuestro campo
				else {
					myRobotAPI.setSteerHeading(balon.t);
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
				}
			}
				
		}
		else{
			
			if (myRobotAPI.getPosition().x > -0.25)
				
				myRobotAPI.setSteerHeading(myRobotAPI.getOurGoal().t);
				
			else {
				// Si balon en campo contrario, bloquear al mas proximo a la porteria
				if (balon.x > 0)
					myRobotAPI.blockForward();
				// sino, ir a por el balon y sacarlo de nuestro campo
				else {
					myRobotAPI.setSteerHeading(balon.t);
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
				}
			}
			
		}
		
		return 0;
	}
	
	public void actualizarVariables(){
		balon =myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
		
		
	}

}
