package t1314grupo14.behaviour;

import teams.ucmTeam.Behaviour;
import teams.ucmTeam.RobotAPI;

public class DefensaAgresivo extends Behaviour {
		
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

		r.setDisplayString("DefensaAgresivo");
		
		if (myRobotAPI.toFieldCoordinates(myRobotAPI.getOurGoal()).x < 0)
			nuestraPorteriaDerecha = false;
		else
			nuestraPorteriaDerecha = true;

		myRobotAPI.setSteerHeading(myRobotAPI.getOurGoal().t);
        myRobotAPI.setSpeed(1.0);
		
	}

	@Override
	public void onRelease(RobotAPI arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int takeStep() {
		
		// Si jugamos en el campo derecho...
		if (nuestraPorteriaDerecha){
			
			// Si nos alejamos de nuestro campo, volvemos
			if(myRobotAPI.getPosition().x < 0.5)
				myRobotAPI.setSteerHeading(myRobotAPI.getOurGoal().t);

			// Sino, estamos en nuestro campo y vamos a bloquear el oponente mas cercano
			else
				myRobotAPI.setSteerHeading(myRobotAPI.getClosestOpponent().t);

			if (myRobotAPI.canKick())
				myRobotAPI.kick();;
			
		}
		// Sino, jugamos en el campo izquierdo
		else{
			// Si nos alejamos de nuestro campo, volvemos
			if (myRobotAPI.getPosition().x > -0.5)
				myRobotAPI.setSteerHeading(myRobotAPI.getOurGoal().t);

			// Sino, estamos en nuestro campo y vamos a bloquear el oponente mas cercano
			else
				myRobotAPI.setSteerHeading(myRobotAPI.getClosestOpponent().t);
			
			if (myRobotAPI.canKick())
				myRobotAPI.kick();
		}
		
		myRobotAPI.setSpeed(1);
		
		return 0;
	}

}
