package t1314grupo14.behaviour;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.Behaviour;
import teams.ucmTeam.RobotAPI;

public class BloqueadorPortero extends Behaviour{
	
	private Vec2 balon;
	private boolean porteroDerecho;
	
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
		
		r.setDisplayString("Bloqueador del portero");
		
		if (myRobotAPI.toFieldCoordinates(myRobotAPI.getOpponentsGoal()).x < 0)
			porteroDerecho = false;
		else
			porteroDerecho = true;
		
		balon = new Vec2();
		actualizarVariables();
		
		myRobotAPI.setSteerHeading(myRobotAPI.getOpponentsGoal().t);
		myRobotAPI.setSpeed(1.0);
		
	}

	@Override
	public void onRelease(RobotAPI arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int takeStep() {
		
		// Si el portero a bloquear es el del campo derecho...
		if (porteroDerecho){
			
			// Si me alejor de la porteria, volver
			if (myRobotAPI.getPosition().x < 0.9)
				myRobotAPI.setSteerHeading(myRobotAPI.getOpponentsGoal().t);
			// Sino, ir a bloquear al portero
			else{
				myRobotAPI.setSteerHeading(myRobotAPI.getClosestOpponent().t);
				//myRobotAPI.blockGoalKeeper(); // JA! No bloquea, solo marea los robots!
				
				// Añadido para que si tiene la pelota cerca, deje de bloquear e intente chutar
				if (myRobotAPI.closestToBall()){
					myRobotAPI.setSteerHeading(balon.t);
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
					if (myRobotAPI.alignedToBallandGoal())
						myRobotAPI.kick();
				}
				
			}
		}
		// Sino, el portero a bloquear es el del campo izquierdo...
		else {
			// Si me alejor de la porteria, volver
			if (myRobotAPI.getPosition().x > -0.9)
				myRobotAPI.setSteerHeading(myRobotAPI.getOpponentsGoal().t);
			// Sino, ir a bloquear al portero
			else {
				myRobotAPI.setSteerHeading(myRobotAPI.getClosestOpponent().t);
				//myRobotAPI.blockGoalKeeper(); // JA! No bloquea, solo marea los robots!
				
				// Añadido para que si tiene la pelota cerca, deje de bloquear e intente chutar
				if (myRobotAPI.closestToBall()){
					myRobotAPI.setSteerHeading(balon.t);
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
					if (myRobotAPI.alignedToBallandGoal())
						myRobotAPI.kick();
				}
			}
		}
		
		myRobotAPI.setSpeed(1.0);		// Empotramos para bloquear xD
		
		return 0;
	}
	
	public void actualizarVariables(){
		balon = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
		
		
	}

}
