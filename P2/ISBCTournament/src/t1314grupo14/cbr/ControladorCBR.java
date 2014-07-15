package t1314grupo14.cbr;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import jcolibri.cbrcore.CBRCase;
import t1314grupo14.Entrenador;

public class ControladorCBR extends Thread {
	
	private RecomendadorSoccer cbr;
    private int golesMarcados;
    private int golesRecibidos;
    private int tiempoDePartido;
    private int diferenciaActual;
    private int diferenciaAnterior;
    private int[] jugadores;
    private Collection<CBRCase> casoDevuelto;
    private Entrenador entrandor;
    private boolean noEsPrimeraVez;
    private int duracionPartido;
   // private Collection<String> listaDeCasos;
    
	public ControladorCBR(Entrenador e){
		this.golesMarcados = 0;
		this.golesRecibidos = 0;
		this.tiempoDePartido = 0;
		this.diferenciaActual = 0;
		this.diferenciaAnterior = 0;
		entrandor = e;
		this.noEsPrimeraVez =false;
		jugadores = new int[5];
		
	}
	
	public void refrescaDatos(int golesMarcados, int golesRecibidos, int tiempoDePartido, int diferenciaActual, int duracionPartido){
		this.golesMarcados = golesMarcados;
		this.golesRecibidos = golesRecibidos;
		this.tiempoDePartido = tiempoDePartido;
		this.diferenciaActual =diferenciaActual;
		this.duracionPartido = duracionPartido;
	}
	
	public void llamaCBR(){
		
		// Interrumpimos al Thread de dormir cuando se marca un gol para llamar al CBR
		this.interrupt();
		
	}
	
	@Override
	public void run() {
		
		cbr = new RecomendadorSoccer();
		boolean aprender =false;
		CBRCase caso = new CBRCase();
		System.err.println("> Condicion while: " + tiempoDePartido + " < " + duracionPartido);
		while (duracionPartido-50 != tiempoDePartido) {
			
			try {
				Thread.sleep(10000);
				System.err.println("> CBR Control: Despierta el Thread!");
				System.err.println("> Condicion while: " + tiempoDePartido + " < " + duracionPartido);
			} catch (InterruptedException e) {
				// Lo interrumpimos cuando se ha marcado un gol
			}
			
			actualizarVariables();

				
			if (noEsPrimeraVez && diferenciaAnterior<=diferenciaActual){
				
				
				aprender= true;
				Date fecha=new Date();
				DescripcionSoccer descripcion = new DescripcionSoccer();
				String s= Long.toString(fecha.getTime()/1000);
				descripcion.setCasoId(s);
				descripcion.setGolesMarcados(golesMarcados);
				descripcion.setGolesRecibidos(golesRecibidos);
				descripcion.setTiempoDePartido(tiempoDePartido);
				caso.setDescription(descripcion);
				SolucionSoccer sol= new SolucionSoccer();
				sol.setCasoId(s);
				sol.setJugador0(jugadores[0]);
				sol.setJugador1(jugadores[1]);
				sol.setJugador2(jugadores[2]);
				sol.setJugador3(jugadores[3]);
				sol.setJugador4(jugadores[4]);
				caso.setSolution(sol);	
				copiarCasoArrayList((DescripcionSoccer)caso.getDescription(),(SolucionSoccer)caso.getSolution());
				
			}
			
			casoDevuelto = cbr.llamadaAlCBR(golesMarcados, golesRecibidos, tiempoDePartido,aprender,caso);
			asignarEstragegia(casoDevuelto);
			this.noEsPrimeraVez= true;
			aprender=false;
		}
		System.err.println("> CBR Control: Finaliza el Thread!");
		entrandor.finalDeTratamiento();
	}

	private void asignarEstragegia(Collection<CBRCase> caso){
		Iterator<CBRCase> iterator = caso.iterator();
		while (iterator.hasNext()) {
			CBRCase elemento = iterator.next();
			SolucionSoccer sol= (SolucionSoccer) elemento.getSolution();
			entrandor.setPlayer(0,sol.getJugador0());
			entrandor.setPlayer(1,sol.getJugador1());
			entrandor.setPlayer(2,sol.getJugador2());
			entrandor.setPlayer(3,sol.getJugador3());
			entrandor.setPlayer(4,sol.getJugador4());
	
		 }	
	}
	
	private void actualizarVariables(){
		for (int i=0;i<5;i++){
			String s=entrandor.getJugador(i);
			if (s.contains("PorteroBasico"))
				jugadores[i]=0;
				
			if (s.contains("CentrocampistaBasico"))
				jugadores[i]=1;
			
			if (s.contains("BloqueadorPortero"))
				jugadores[i]=2;
			
			
			if (s.contains("DefensaBasico"))
				jugadores[i]=3;
			
			if (s.contains("DelanteroBasico"))
				jugadores[i]=4;			
			
			if (s.contains("Aleatorio"))
				jugadores[i]=5;			
			
			if (s.contains("CentrocampistaAgresivo"))
				jugadores[i]=6;			
			
			if (s.contains("DefensaAgresivo"))
				jugadores[i]=7;			
			
			if (s.contains("DelanteroAgresivo"))
				jugadores[i]=8;			
			
			if (s.contains("ProgresivoAtaqueDefensa"))
				jugadores[i]=9;			
			
			if (s.contains("ProgresivoDefensaAtaque"))
				jugadores[i]=10;			
			
		}
		System.out.println(" > Controlador CBR: Finalizado!");
		entrandor.actualizaVariablesControlador();
	}
	
	public void copiarCasoArrayList(DescripcionSoccer des, SolucionSoccer sol){
		String s = "insert into soccer values(";
		s += "'" + des.getCasoId() + "'" + ", ";
		s += des.getTiempoDePartido() + ", ";
		s += des.getGolesRecibidos() + ", ";
		s += des.getGolesMarcados() + ", ";
		s += sol.getJugador0() + ", ";
		s += sol.getJugador1() + ", ";
		s += sol.getJugador2() + ", ";
		s += sol.getJugador3() + ", ";
		s += sol.getJugador4() + ");";
		entrandor.setListaDecasos(s);
		System.out.println(s);
		
		
}
	
	
}
	

