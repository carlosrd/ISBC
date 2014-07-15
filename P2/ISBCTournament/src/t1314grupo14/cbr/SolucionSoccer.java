package t1314grupo14.cbr;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class SolucionSoccer implements CaseComponent{
	
	private String casoId;
	private int jugador0;
	private int jugador1;
	private int jugador2;
	private int jugador3;
	private int jugador4;
	
	public int getJugador0() {
		return jugador0;
	}

	public void setJugador0(int jugador0) {
		this.jugador0 = jugador0;
	}

	public int getJugador1() {
		return jugador1;
	}

	public void setJugador1(int jugador1) {
		this.jugador1 = jugador1;
	}

	public int getJugador2() {
		return jugador2;
	}

	public void setJugador2(int jugador2) {
		this.jugador2 = jugador2;
	}

	public int getJugador3() {
		return jugador3;
	}

	public void setJugador3(int jugador3) {
		this.jugador3 = jugador3;
	}

	public int getJugador4() {
		return jugador4;
	}

	public void setJugador4(int jugador4) {
		this.jugador4 = jugador4;
	}

	public String getCasoId() {
		return casoId;
	}

	public void setCasoId(String casoId) {
		this.casoId = casoId;
	}

	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return new Attribute("casoId", SolucionSoccer.class);
	}
	
	@Override
	public String toString()
	{
		return "("+casoId+";"+jugador0+";"+jugador1+";"+jugador2+";"+jugador3+";"+jugador4+")";
	}

}
