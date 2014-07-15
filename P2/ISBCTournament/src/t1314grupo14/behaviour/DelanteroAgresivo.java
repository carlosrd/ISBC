package t1314grupo14.behaviour;

import teams.ucmTeam.Behaviour;
import teams.ucmTeam.RobotAPI;

public class DelanteroAgresivo extends Behaviour{
	
	private boolean marcamosEnDerecha;

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

		r.setDisplayString("DelanteroAgresivo");
		
		if (myRobotAPI.toFieldCoordinates(myRobotAPI.getOpponentsGoal()).x < 0)
			marcamosEnDerecha = false;
		else
			marcamosEnDerecha = true;

		myRobotAPI.setSteerHeading(myRobotAPI.getOurGoal().t);
        myRobotAPI.setSpeed(1.0);
		
	}

	@Override
	public void onRelease(RobotAPI arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int takeStep() {

		// Si marcamos en el campo derecho...
		if (marcamosEnDerecha){
			
			// Si nos alejamos de nuestro campo, volvemos
			if (myRobotAPI.getPosition().x < 0.5)
				myRobotAPI.setSteerHeading(myRobotAPI.getOpponentsGoal().t);
			// Sino, estamos en el campo del contrario y vamos a bloquear el oponente mas cercano
			else
				myRobotAPI.setSteerHeading(myRobotAPI.getClosestOpponent().t);
				
			if (myRobotAPI.canKick())
				myRobotAPI.kick();
			
		}
		// Sino, marcamos en el campo izquierdo
		else {
			// Si nos alejamos del campo del contrario, volvemos
			if (myRobotAPI.getPosition().x > -0.5)
				myRobotAPI.setSteerHeading(myRobotAPI.getOpponentsGoal().t);
	
			// Sino, estamos en el campo del contrario y vamos a bloquear el oponente mas cercano
			else
				myRobotAPI.setSteerHeading(myRobotAPI.getClosestOpponent().t);
				
			if (myRobotAPI.canKick())
				myRobotAPI.kick();
		}
		
		myRobotAPI.setSpeed(1);
		
		return 0;
	}

}
