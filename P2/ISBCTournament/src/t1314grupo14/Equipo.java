package t1314grupo14;

import teams.ucmTeam.TeamManager;
import teams.ucmTeam.UCMPlayer;

public class Equipo extends UCMPlayer {

	@Override
	protected TeamManager createTeamManager() {
		// TODO Auto-generated method stub
		return new Entrenador();
	}

}
