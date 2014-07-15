package t1314grupo14.behaviour;

import teams.ucmTeam.Behaviour;
import teams.ucmTeam.RobotAPI;
import EDU.gatech.cc.is.util.Vec2;

public class PorteroBasico extends Behaviour {
		
	private Vec2 balon;
	
	@Override
	public void onInit(RobotAPI r){
		
        r.setDisplayString("Portero");
        myRobotAPI.setSteerHeading(myRobotAPI.getOurGoal().t);
        balon = new Vec2();
        	
        actualizarVariables();	
        myRobotAPI.setSpeed(1.0);

    }
	
	@Override
	public int takeStep() {

		// Si me han bloqueado, intenta evitarlo
		if (myRobotAPI.opponentBlocking() || myRobotAPI.teammateBlocking())			
			
			myRobotAPI.avoidCollisions();

		// Si el balon esta en la parte superior del campo
		else if (balon.y >= 0){
			
			if (myRobotAPI.getPosition().y >= 0.15)
				// Ponemos la cabeza del portero mirando para abajo en radianes
				myRobotAPI.setSteerHeading(4.71);
			
			else if (myRobotAPI.getPosition().y <= 0)
					//Ponemos la cabeza del portero mirando para arriba en radianes
					myRobotAPI.setSteerHeading( 1.57);
			
		}
		// Si el balon en la parte inferior del campo
		else {
			
			if (myRobotAPI.getPosition().y <= -0.15)
				//Ponemos la cabeza del portero mirando para arriba en radianes
				myRobotAPI.setSteerHeading( 1.57);
			
			else if (myRobotAPI.getPosition().y >= 0)
					//Ponemos la cabeza del portero mirando para abajo en radianes
					myRobotAPI.setSteerHeading( 4.71);	
			
		}
		
		// Si tiene cerca la pelota, que intente despejarla
		if (myRobotAPI.closestToBall()){
			myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
			if (myRobotAPI.alignedToBallandGoal())
				myRobotAPI.kick();
		}
			
		myRobotAPI.setSpeed(1);
		actualizarVariables();
		
		return 0;
	}

	public void actualizarVariables(){
		balon = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
	}

	@Override
	public void configure() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRelease(RobotAPI arg0) {
		// TODO Auto-generated method stub
		
	}

}
