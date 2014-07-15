package t1314grupo14.messages;

import teams.ucmTeam.Message;
import teams.ucmTeam.RobotAPI;

public class MensajePorteroBloqueado extends Message {

	private RobotAPI rBlocked;
	
	public RobotAPI getRblocked() {
		return rBlocked;
	}
	
	public void setRblocked(RobotAPI rBlocked) {
		this.rBlocked = rBlocked;
	}
	
}
