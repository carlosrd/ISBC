package es.ucm.fdi.isbc.viviendas.representacion;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class SolucionVivienda implements CaseComponent {

	Integer id;
	Integer precio;
	
	
	
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getPrecio() {
		return precio;
	}



	public void setPrecio(Integer precio) {
		this.precio = precio;
	}



	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id", SolucionVivienda.class);
	}

}
