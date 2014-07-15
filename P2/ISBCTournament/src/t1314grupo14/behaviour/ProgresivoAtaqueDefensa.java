package t1314grupo14.behaviour;

import teams.ucmTeam.Behaviour;
import teams.ucmTeam.RobotAPI;
import EDU.gatech.cc.is.util.Vec2;

public class ProgresivoAtaqueDefensa extends Behaviour {
		
	private boolean marcamosEnDerecha,nuestraPorteriaDerecha;
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
		
		r.setDisplayString("ProgresivoD_A");
		
		if (myRobotAPI.toFieldCoordinates(myRobotAPI.getOpponentsGoal()).x < 0)
			marcamosEnDerecha = false;
		else
			marcamosEnDerecha = true;
		
		if (myRobotAPI.toFieldCoordinates(myRobotAPI.getOurGoal()).x < 0)
			nuestraPorteriaDerecha = false;
		else
			nuestraPorteriaDerecha = true;
		
		balon = new Vec2();
		actualizarVariables();
		
		myRobotAPI.setSteerHeading(myRobotAPI.getOurGoal().t);
        myRobotAPI.setSpeed(1.0);
		
	}

	@Override
	public void onRelease(RobotAPI arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int takeStep() {
		
		
		if (myRobotAPI.getTimeStamp() < 30000)
			accionAtaqueAgresivo();						// Si llevamos menos de 30 seg -> Ataque Agresivo
		else if (myRobotAPI.getTimeStamp() < 60000)
				accionAtaque();							// Entre 30-60 seg -> Ataque
		else if (myRobotAPI.getTimeStamp() < 90000) 
				accionDefensaAgresiva();				// Entre 1min y 1m30 seg -> Defensa Agresiva
		else 
			accionDefensa();							// Entre 1m30 y 2m -> Defensa
					
		return 0;
		
	}

	private void accionDefensaAgresiva(){
		
	
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
		
	}
		
	private void accionDefensa(){
		
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
	}
	
	private void accionAtaqueAgresivo(){
		
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
		
	}
	
	private void accionAtaque(){
		
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
		
	}
	
	public void actualizarVariables(){
		balon =myRobotAPI.toFieldCoordinates(myRobotAPI.getBall());

	}

}

