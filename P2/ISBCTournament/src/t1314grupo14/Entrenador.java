package t1314grupo14;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import t1314grupo14.behaviour.Aleatorio;
import t1314grupo14.behaviour.BloqueadorPortero;
import t1314grupo14.behaviour.CentrocampistaAgresivo;
import t1314grupo14.behaviour.CentrocampistaBasico;
import t1314grupo14.behaviour.DefensaAgresivo;
import t1314grupo14.behaviour.DefensaBasico;
import t1314grupo14.behaviour.DelanteroAgresivo;
import t1314grupo14.behaviour.DelanteroBasico;
import t1314grupo14.behaviour.PorteroBasico;
import t1314grupo14.behaviour.ProgresivoAtaqueDefensa;
import t1314grupo14.behaviour.ProgresivoDefensaAtaque;
import t1314grupo14.cbr.ControladorCBR;
import teams.ucmTeam.Behaviour;
import teams.ucmTeam.TeamManager;

public class Entrenador extends TeamManager{

	private int diferenciaActual, diferenciaAnterior;
    private int golesMarcados;
    private int golesRecibidos;
    private int tiempoDePartido;
   // private Timer timer;
    ControladorCBR cbrThread;
    private Collection<String> listaDeCasos;
    
	@Override
	public Behaviour[] createBehaviours() {
		
		// Creamos un controlador para el CBR. Este se encargara de llamarlo cada 10 seg o cada vez
		// que se marque un gol.
		cbrThread = new ControladorCBR(this);
		cbrThread.start();
		listaDeCasos = new ArrayList<String>();
		
		
		return new Behaviour[] { new PorteroBasico(),new CentrocampistaBasico(), new BloqueadorPortero(),new DefensaBasico(), new DelanteroBasico(),
								new Aleatorio(),new CentrocampistaAgresivo(),new DefensaAgresivo(),new DelanteroAgresivo(),new ProgresivoAtaqueDefensa(),new ProgresivoDefensaAtaque()};
		
		
	}

	@Override
	public Behaviour getDefaultBehaviour(int id) {
		
		switch (id){
		
			case 0:	PorteroBasico portero = (PorteroBasico) _behaviours[0];
					return portero;	
			case 1:	CentrocampistaBasico centrocampista = (CentrocampistaBasico) _behaviours[1];
					return centrocampista;	
			case 2:	BloqueadorPortero bloqueadorPortero = (BloqueadorPortero) _behaviours[2];
					return bloqueadorPortero;	
			case 3:	DefensaBasico defensa = (DefensaBasico) _behaviours[3];
					//DefensaAgresivo defensa = (DefensaAgresivo) _behaviours[3];
					return defensa;	
			case 4:	DelanteroBasico delantero = (DelanteroBasico) _behaviours[4];
					//DelanteroAgresivo delantero = (DelanteroAgresivo) _behaviours[4];
					return delantero;	
		}

		return null;
	}

	@Override
	public int onConfigure() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void onTakeStep() {
		
		actualizaVariables();
		// Si se ha marcado un gol, llamamos a CBR
		if (diferenciaActual != diferenciaAnterior){
			cbrThread.llamaCBR();
			diferenciaAnterior = diferenciaActual;
			
		}
		

	}

	private void actualizaVariables(){
	    golesMarcados = _players[0].getRobotAPI().getMyScore();
	    golesRecibidos = _players[0].getRobotAPI().getOpponentScore();
	    tiempoDePartido = (int) _players[0].getRobotAPI().getMatchTotalTime() - (int) _players[0].getRobotAPI().getMatchRemainingTime();
	    diferenciaActual = golesMarcados - golesRecibidos;
	}
	
	public void setPlayer (int jugador, int estrategia){
		
		_players[jugador].setBehaviour(_behaviours[estrategia]);
		
	}
	
	public String getJugador(int jugador){
		return _players[jugador].getBehaviour().toString();
	}
	
	public void actualizaVariablesControlador(){
		actualizaVariables();
	    cbrThread.refrescaDatos(golesMarcados, golesRecibidos, tiempoDePartido,diferenciaActual,(int) _players[0].getRobotAPI().getMatchTotalTime());
	}
	
	public void setListaDecasos(String s){
		listaDeCasos.add(s);
	}
	
	public Collection<String> getListaCasos(){
		return listaDeCasos;
	}
	
	public void finalDeTratamiento(){
		System.out.println("Entramos en el final");
		try {
			FileWriter fich = new FileWriter("src/t1314grupo14/soccer.sql",true);
			PrintWriter pw = new PrintWriter(fich);
			Iterator<String> it = listaDeCasos.iterator();
		
			while (it.hasNext()) {
			
				String s = it.next();
				pw.println(s);
				System.out.println(s);	
			}
			fich.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
