package es.ucm.fdi.isbc.viviendas.representacion;

public class Coordenada {

	public Coordenada(){
		
	}
	
	double latitud;
	double longitud;
	
	/**
	 * @param latitud
	 * @param longitud
	 */
	public Coordenada(double latitud, double longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	
	
}
