package t1314grupo14.behaviour;

import EDU.gatech.cc.is.util.Vec2;
import teams.ucmTeam.Behaviour;
import teams.ucmTeam.RobotAPI;

public class CentrocampistaBasico extends Behaviour {
	
	private Vec2 balon;
	
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
		
		r.setDisplayString("CentroCampista");
		
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
		
		myRobotAPI.setSteerHeading(balon.t);
		myRobotAPI.setSpeed(1.0);
		myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
		
		if (Math.abs(myRobotAPI.getPosition().x - myRobotAPI.getOpponentsGoal().x) < 0.7 && myRobotAPI.alignedToBallandGoal())
			myRobotAPI.kick();
		
		return 0;
		
	}
	
	public void actualizarVariables(){
		balon = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
	}
}
