package t1314grupo14.cbr;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;


public class DescripcionSoccer implements CaseComponent {
	
	private String casoId;
	private long tiempoDePartido;
	private int golesRecibidos;
	private int golesMarcados;

	
	@Override
	public String toString()
	{
		return "("+casoId+";"+tiempoDePartido+";"+golesRecibidos+";"+golesMarcados+")";
	}
	
	@Override
	public Attribute getIdAttribute() {
		// TODO Auto-generated method stub
		return new Attribute("casoId", DescripcionSoccer.class);
	}

	public long getTiempoDePartido() {
		return tiempoDePartido;
	}

	public void setTiempoDePartido(long tiempoDePartido) {
		this.tiempoDePartido = tiempoDePartido;
	}

	public int getGolesRecibidos() {
		return golesRecibidos;
	}

	public void setGolesRecibidos(int golesRecibidos) {
		this.golesRecibidos = golesRecibidos;
	}

	public int getGolesMarcados() {
		return golesMarcados;
	}

	public void setGolesMarcados(int golesMarcados) {
		this.golesMarcados = golesMarcados;
	}

	public String getCasoId() {
		return casoId;
	}

	public void setCasoId(String casoId) {
		this.casoId = casoId;
	}
	
	

}
