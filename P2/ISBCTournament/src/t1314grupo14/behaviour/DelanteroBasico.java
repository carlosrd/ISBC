package t1314grupo14.behaviour;

import teams.ucmTeam.Behaviour;
import teams.ucmTeam.RobotAPI;
import EDU.gatech.cc.is.util.Vec2;

public class DelanteroBasico extends Behaviour {
	
	private Vec2 balon;
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

		r.setDisplayString("Defensa");
		
		if (myRobotAPI.toFieldCoordinates(myRobotAPI.getOpponentsGoal()).x < 0)
			marcamosEnDerecha = false;
		else
			marcamosEnDerecha = true;
		
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
		
		if (marcamosEnDerecha){
			
			// Si nos alejamos del campo del contrario, volvemos
			if (myRobotAPI.getPosition().x < 0.1){
				myRobotAPI.setSteerHeading(myRobotAPI.getOpponentsGoal().t);
				
			}
			else {
				// Si balon en campo propio, bloquear al mas proximo
				if (balon.x < 0)
					myRobotAPI.blockClosest();
				// sino, ir a por el balon y sacarlo de nuestro campo
				else {
					myRobotAPI.setSteerHeading(balon.t);
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
					if (myRobotAPI.alignedToBallandGoal())
						myRobotAPI.kick();
				}
			}
				
		}
		else{
			
			// Si nos alejamos del campo del contrario, volvemos
			if (myRobotAPI.getPosition().x > -0.1){
				myRobotAPI.setSteerHeading(myRobotAPI.getOpponentsGoal().t);
			}
			else {
				// Si balon en campo propio, bloquear al mas proximo
				if (balon.x > 0)
					myRobotAPI.blockClosest();
				// sino, ir a por el balon y sacarlo de nuestro campo
				else {
					myRobotAPI.setSteerHeading(balon.t);
					myRobotAPI.setBehindBall(myRobotAPI.getOpponentsGoal());
					if (myRobotAPI.alignedToBallandGoal())
						myRobotAPI.kick();
				}
			}
			
		}
		
		myRobotAPI.setSpeed(1.0);
		
		return 0;
	}
	
	public void actualizarVariables(){
		balon = myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());
	}

}

