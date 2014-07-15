package es.ucm.fdi.isbc.viviendas.representacion;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class ExtrasOtros {
	
	Integer id;
	boolean patio;
	boolean balcon;
	boolean zonaDeportiva;
	boolean zonaComunitaria;
	boolean terraza;
	boolean piscinaComunitaria;
	boolean jardinPrivado;
	boolean zonaInfantil;
	boolean piscina;
	
	

	public ExtrasOtros(int id) {
		super();
		this.id = id;
	}

	public ExtrasOtros(String stringRep)
	{
		String[] values = stringRep.split(",");
		id = Integer.valueOf(values[0]);
		this.patio = Boolean.valueOf(values[1]);
		this.balcon = Boolean.valueOf(values[2]);
		this.zonaDeportiva = Boolean.valueOf(values[3]);
		this.zonaComunitaria = Boolean.valueOf(values[4]);
		this.terraza = Boolean.valueOf(values[5]);
		this.piscinaComunitaria = Boolean.valueOf(values[6]);
		this.jardinPrivado = Boolean.valueOf(values[7]);
		this.zonaInfantil = Boolean.valueOf(values[8]);
		this.piscina = Boolean.valueOf(values[9]);
	}
	
	@Override
	public String toString() {
		return id+","+ patio + "," + balcon + "," + zonaDeportiva + ","
				+ zonaComunitaria + "," + terraza + "," + piscinaComunitaria
				+ "," + jardinPrivado + "," + zonaInfantil + "," + piscina;
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

	public boolean isPatio() {
		return patio;
	}
	public void setPatio(boolean patio) {
		this.patio = patio;
	}
	public boolean isBalcon() {
		return balcon;
	}
	public void setBalcon(boolean balcon) {
		this.balcon = balcon;
	}
	public boolean isZonaDeportiva() {
		return zonaDeportiva;
	}
	public void setZonaDeportiva(boolean zonaDeportiva) {
		this.zonaDeportiva = zonaDeportiva;
	}
	public boolean isZonaComunitaria() {
		return zonaComunitaria;
	}
	public void setZonaComunitaria(boolean zonaComunitaria) {
		this.zonaComunitaria = zonaComunitaria;
	}
	public boolean isTerraza() {
		return terraza;
	}
	public void setTerraza(boolean terraza) {
		this.terraza = terraza;
	}
	public boolean isPiscinaComunitaria() {
		return piscinaComunitaria;
	}
	public void setPiscinaComunitaria(boolean piscinaComunitaria) {
		this.piscinaComunitaria = piscinaComunitaria;
	}
	public boolean isJardinPrivado() {
		return jardinPrivado;
	}
	public void setJardinPrivado(boolean jardinPrivado) {
		this.jardinPrivado = jardinPrivado;
	}
	public boolean isZonaInfantil() {
		return zonaInfantil;
	}
	public void setZonaInfantil(boolean zonaInfantil) {
		this.zonaInfantil = zonaInfantil;
	}
	public boolean isPiscina() {
		return piscina;
	}
	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}
	
	
}
