package t1314grupo14.behaviour;

import teams.ucmTeam.Behaviour;
import teams.ucmTeam.RobotAPI;

public class CentrocampistaAgresivo extends Behaviour {
	
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
		
		r.setDisplayString("CentroCampistaAgresivo");
        
		myRobotAPI.setSteerHeading(myRobotAPI.getClosestOpponent().t);
        myRobotAPI.setSpeed(1.0);
		
	}

	@Override
	public void onRelease(RobotAPI arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int takeStep() {
		
		// Ir a bloquear al oponente mas cercano
		myRobotAPI.setSteerHeading(myRobotAPI.getClosestOpponent().t);
		myRobotAPI.setSpeed(1.0);
		
		// Si esta la pelota cerca, vamos a despejarla hacia la porteria del contrario
		if (myRobotAPI.closestToBall()){
			myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
			if (myRobotAPI.alignedToBallandGoal())
				myRobotAPI.kick();
		}
		
		return 0;
		
	}
	
}