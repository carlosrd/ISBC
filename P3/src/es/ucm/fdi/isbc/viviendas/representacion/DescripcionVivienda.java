package es.ucm.fdi.isbc.viviendas.representacion;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

public class DescripcionVivienda implements CaseComponent{

	public enum TipoVivienda {Atico, Plantabaja, Piso, Loft, Casaadosada, CasaChalet, Duplex, Estudio, Fincarustica, Apartamento}
	public enum EstadoVivienda { Muybien, Reformado, Areformar, Casinuevo, Bien};
		
	//EstadoVivienda e = EstadoVivienda.Muybien;
	
	Integer id;
	
	String titulo;
	String localizacion;
	String urlFoto;
	String url;
	
	TipoVivienda tipo;
	
	Integer superficie;
	Integer habitaciones;
	Integer banios;
	EstadoVivienda estado;
	String descripcion;
	
	Coordenada coordenada;
	
	Integer precio;
	Integer precioMedio;
	Integer precioZona;
	ExtrasFinca extrasFinca;
	ExtrasBasicos extrasBasicos;
	ExtrasOtros extrasOtros;
	
	Boolean ascensor;
	Boolean amueblado;
	Boolean terraza;
	
	
	public DescripcionVivienda(int id)
	{
		super();
		this.id = id;
	}
	
	
	public DescripcionVivienda (String stringRep)
	{
		String[] values = stringRep.split("#");
		id = Integer.valueOf(values[0]);
		titulo = values[1];
		localizacion = values[2];
		urlFoto = values[3];
		url = values[4];
		
		tipo = TipoVivienda.valueOf(values[5]);
		
		superficie = Integer.valueOf(values[6]);
		habitaciones= Integer.valueOf(values[7]);
		banios= Integer.valueOf(values[8]);
		estado= EstadoVivienda.valueOf(values[9]);
		
		coordenada = new Coordenada(Double.valueOf(values[10]),Double.valueOf(values[11]));
			
		precio= Integer.valueOf(values[12]);
		precioMedio= Integer.valueOf(values[13]);
		precioZona= Integer.valueOf(values[14]);
		
		extrasFinca= new ExtrasFinca(values[15]);
		extrasBasicos= new ExtrasBasicos(values[16]);
		extrasOtros= new ExtrasOtros(values[17]);
		
		ascensor = extrasFinca.isAscensor();
		amueblado = extrasBasicos.isAmueblado();
		terraza = extrasOtros.isTerraza();
		
		descripcion = values[18];

	}
	
	public Boolean getTerraza() {
		return terraza;
	}


	public void setTerraza(Boolean terraza) {
		this.terraza = terraza;
	}


	public DescripcionVivienda() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return  id + "#" + 
				titulo + "#" + 
				localizacion + "#" + 
				urlFoto + "#" + 
				url + "#" + 
				tipo + "#" +
				superficie + "#" + 
				habitaciones + "#" + 
				banios + "#" + 
				estado + "#" +
				coordenada.getLatitud() + "#" +
				coordenada.getLongitud() + "#" + 
				precio + "#" +
				precioMedio + "#" + 
				precioZona + "#" +
				extrasFinca + "#" + 
				extrasBasicos + "#" + 
				extrasOtros + "#" +
				descripcion.replace('#', ' '); 
	}
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoVivienda getTipo() {
		return tipo;
	}

	public void setTipo(TipoVivienda tipo) {
		this.tipo = tipo;
	}

	public ExtrasFinca getExtrasFinca() {
		return extrasFinca;
	}
	public void setExtrasFinca(ExtrasFinca extrasFinca) {
		this.extrasFinca = extrasFinca;
	}
	public ExtrasBasicos getExtrasBasicos() {
		return extrasBasicos;
	}
	public void setExtrasBasicos(ExtrasBasicos extrasBasicos) {
		this.extrasBasicos = extrasBasicos;
	}
	public ExtrasOtros getExtrasOtros() {
		return extrasOtros;
	}
	public void setExtrasOtros(ExtrasOtros extrasOtros) {
		this.extrasOtros = extrasOtros;
	}
	
	public String getLocalizacion() {
		return localizacion;
	}
	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}
	
	public Coordenada getCoordenada() {
		return coordenada;
	}
	public void setCoordenada(Coordenada coordenada) {
		this.coordenada = coordenada;
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public Integer getSuperficie() {
		return superficie;
	}
	public void setSuperficie(Integer superficie) {
		this.superficie = superficie;
	}
	public Integer getHabitaciones() {
		return habitaciones;
	}
	public void setHabitaciones(Integer habitaciones) {
		this.habitaciones = habitaciones;
	}
	public Integer getBanios() {
		return banios;
	}
	public void setBanios(Integer banios) {
		this.banios = banios;
	}
	public EstadoVivienda getEstado() {
		return estado;
	}
	public void setEstado(EstadoVivienda estado) {
		this.estado = estado;
	}
	public String getUrlFoto() {
		return urlFoto;
	}
	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	public Integer getPrecioMedio() {
		return precioMedio;
	}
	public void setPrecioMedio(Integer precioMedio) {
		this.precioMedio = precioMedio;
	}
	public Integer getPrecioZona() {
		return precioZona;
	}
	public void setPrecioZona(Integer precioZona) {
		this.precioZona = precioZona;
	}
	
	public Boolean getAmueblado() {
		return amueblado;
	}


	public void setAmueblado(Boolean amueblado) {
		this.amueblado = amueblado;
	}


	public Boolean getAscensor() {
		return ascensor;
	}
	public void setAscensor(Boolean ascensor) {
		this.ascensor = ascensor;
	}

	@Override
	public Attribute getIdAttribute() {
		return new Attribute("id", DescripcionVivienda.class);
	}
	
	
	
	
	
	
	
}
