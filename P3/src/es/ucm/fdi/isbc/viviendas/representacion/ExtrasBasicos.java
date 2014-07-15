package es.ucm.fdi.isbc.viviendas.representacion;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class ExtrasBasicos {
	
	Integer id;
	boolean lavadero;
	boolean internet;
	boolean microondas;
	boolean horno;
	boolean amueblado;
	boolean cocinaOffice;
	boolean parquet;
	boolean domotica;
	boolean armarios;
	boolean tv;
	boolean lavadora;
	boolean electrodomesticos;
	boolean suiteConBanio;
	boolean puertaBlindada;
	boolean gresCeramica;
	boolean calefaccion;
	boolean aireAcondicionado;
	boolean nevera;
	

	public ExtrasBasicos(int id){
		super();
		this.id = id;
	}
	
	public ExtrasBasicos(String stringRep) {
		super();
		String[] values = stringRep.split(",");
		id = Integer.valueOf(values[0]);
		this.lavadero = Boolean.valueOf(values[1]);
		this.internet = Boolean.valueOf(values[2]);
		this.microondas = Boolean.valueOf(values[3]);
		this.horno = Boolean.valueOf(values[4]);
		this.amueblado = Boolean.valueOf(values[5]);
		this.cocinaOffice = Boolean.valueOf(values[6]);
		this.parquet = Boolean.valueOf(values[7]);
		this.domotica = Boolean.valueOf(values[8]);
		this.armarios = Boolean.valueOf(values[9]);
		this.tv = Boolean.valueOf(values[10]);
		this.lavadora = Boolean.valueOf(values[11]);
		this.electrodomesticos = Boolean.valueOf(values[12]);
		this.suiteConBanio = Boolean.valueOf(values[13]);
		this.puertaBlindada = Boolean.valueOf(values[14]);
		this.gresCeramica = Boolean.valueOf(values[15]);
		this.calefaccion = Boolean.valueOf(values[16]);
		this.aireAcondicionado = Boolean.valueOf(values[17]);
		this.nevera = Boolean.valueOf(values[18]);
	}
	@Override
	public String toString() {
		return id + ","+ lavadero + "," + internet + "," + microondas + "," + horno + ","
				+ amueblado + "," + cocinaOffice + "," + parquet + ","
				+ domotica + "," + armarios + "," + tv + "," + lavadora + ","
				+ electrodomesticos + "," + suiteConBanio + ","
				+ puertaBlindada + "," + gresCeramica + "," + calefaccion + ","
				+ aireAcondicionado + "," + nevera;
	}
	
	//@Override
	public Attribute getIdAttribute() {
		return new Attribute("id", DescripcionVivienda.class);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isNevera() {
		return nevera;
	}
	public void setNevera(boolean nevera) {
		this.nevera = nevera;
	}
	public boolean isLavadero() {
		return lavadero;
	}
	public void setLavadero(boolean lavadero) {
		this.lavadero = lavadero;
	}
	public boolean isInternet() {
		return internet;
	}
	public void setInternet(boolean internet) {
		this.internet = internet;
	}
	public boolean isMicroondas() {
		return microondas;
	}
	public void setMicroondas(boolean microondas) {
		this.microondas = microondas;
	}
	public boolean isHorno() {
		return horno;
	}
	public void setHorno(boolean horno) {
		this.horno = horno;
	}
	public boolean isAmueblado() {
		return amueblado;
	}
	public void setAmueblado(boolean amueblado) {
		this.amueblado = amueblado;
	}
	public boolean isCocinaOffice() {
		return cocinaOffice;
	}
	public void setCocinaOffice(boolean cocinaOffice) {
		this.cocinaOffice = cocinaOffice;
	}
	public boolean isParquet() {
		return parquet;
	}
	public void setParquet(boolean parquet) {
		this.parquet = parquet;
	}
	public boolean isDomotica() {
		return domotica;
	}
	public void setDomotica(boolean domotica) {
		this.domotica = domotica;
	}
	public boolean isArmarios() {
		return armarios;
	}
	public void setArmarios(boolean armarios) {
		this.armarios = armarios;
	}
	public boolean isTv() {
		return tv;
	}
	public void setTv(boolean tv) {
		this.tv = tv;
	}
	public boolean isLavadora() {
		return lavadora;
	}
	public void setLavadora(boolean lavadora) {
		this.lavadora = lavadora;
	}
	public boolean isElectrodomesticos() {
		return electrodomesticos;
	}
	public void setElectrodomesticos(boolean electrodomesticos) {
		this.electrodomesticos = electrodomesticos;
	}
	public boolean isSuiteConBanio() {
		return suiteConBanio;
	}
	public void setSuiteConBanio(boolean suiteConBanio) {
		this.suiteConBanio = suiteConBanio;
	}
	public boolean isPuertaBlindada() {
		return puertaBlindada;
	}
	public void setPuertaBlindada(boolean puertaBlindada) {
		this.puertaBlindada = puertaBlindada;
	}
	public boolean isGresCeramica() {
		return gresCeramica;
	}
	public void setGresCeramica(boolean gresCeramica) {
		this.gresCeramica = gresCeramica;
	}
	public boolean isCalefaccion() {
		return calefaccion;
	}
	public void setCalefaccion(boolean calefaccion) {
		this.calefaccion = calefaccion;
	}
	public boolean isAireAcondicionado() {
		return aireAcondicionado;
	}
	public void setAireAcondicionado(boolean aireAcondicionado) {
		this.aireAcondicionado = aireAcondicionado;
	}

	

	
}
