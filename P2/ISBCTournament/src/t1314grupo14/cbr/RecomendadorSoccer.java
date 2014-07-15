package t1314grupo14.cbr;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.DataBaseConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import jcolibri.method.retrieve.selection.SelectCases;




public class RecomendadorSoccer implements StandardCBRApplication{
	
	Connector _connector;
	CBRCaseBase _casoBase;
	Collection<CBRCase> casoDevuelto;
	
	final char barra = File.separatorChar;
	final String rutaDataBaseConfig = "t1314grupo14"+barra+"cbr"+barra+"databaseconfig.xml";
	
	public Collection<CBRCase>  llamadaAlCBR(int golesMarcados,int golesRecibidos,long tiempoDePartido, boolean aprender,CBRCase caso) {
		
		//lanzar el SGBD
		MiHSQLDBserver.init();
		
		// crear la tabla que usaremos para soccer

		try
		{
			//configuración
			
			configure();
		
			//preciclo
			preCycle();
			
			CBRQuery pregunta = new CBRQuery();
			
			//creamos un descriptor 
			DescripcionSoccer descripcion= new DescripcionSoccer();
			//rellanomos la descripcion del caso
			descripcion.setGolesMarcados(golesMarcados);
			descripcion.setGolesRecibidos(golesRecibidos);
			descripcion.setTiempoDePartido(tiempoDePartido);
			//asigamos la descripcion del caso a la pregunta
			pregunta.setDescription(descripcion);
			//creamos donde vamos a almacenar los caso devueltos
			casoDevuelto = new ArrayList<CBRCase>();
			//ejecutamos el cyclo con la pregunta
			cycle(pregunta);
			
			if(aprender)
				aprender(caso);
		
			
		}catch(Exception e)
		{
			e.printStackTrace();
			//org.apache.commons.logging.LogFactory.getLog(RecomendadorSoccer.class).error(e);
		}
		//apagar base de datos
		MiHSQLDBserver.shutDown();	
		return casoDevuelto;
		}
	

	@Override
	public void configure() throws ExecutionException {
		// TODO Auto-generated method stub
		try {
			//crear el conector con la base de casos
			
			_connector = new DataBaseConnector();
			//inicializar el conectror con su archivo xml de configuracion

			_connector.initFromXMLfile(jcolibri.util.FileIO.findFile(rutaDataBaseConfig));
			//la organizacion en memoria sera lineal
			_casoBase = new LinealCaseBase();
			System.out.println("termiando configure");
		} catch (Exception e) {
			throw new ExecutionException(e);
		}
	}

	@Override
	public void cycle(CBRQuery pregunta) throws ExecutionException {
		//para configurar el KNN se utiliza un objeto NNConfig
		NNConfig simConfig = new NNConfig();
		//Fijamos la funcion de similitud global
		simConfig.setDescriptionSimFunction(new Average());
		
		//Fijamos las funciones de similitud locales
		simConfig.addMapping(new Attribute("tiempoDePartido", DescripcionSoccer.class),new Equal());
		simConfig.addMapping(new Attribute("golesRecibidos", DescripcionSoccer.class),new Equal());
		simConfig.addMapping(new Attribute("golesMarcados", DescripcionSoccer.class),new Equal());
		
		//modificamos el peso del tiempo porque no es excesivamente importante
		simConfig.setWeight(new Attribute("tiempoDePartido",DescripcionSoccer.class), 0.4);
		
		//Ejecutamos la recuperacion del vecino mas proximo
		Collection<RetrievalResult> evaluado = NNScoringMethod.evaluateSimilarity(_casoBase.getCases(), pregunta, simConfig);
		
		//Seleccionamos el caso mejor 
		evaluado = SelectCases.selectTopKRR(evaluado, 1);
		
		
		System.out.println("Caso selecionado");
		for (RetrievalResult resul: evaluado){
			System.out.println(resul);
			casoDevuelto.add( resul.get_case());	
		}
	}

	@Override
	public void postCycle() throws ExecutionException {
		this._casoBase.close();
		
	}

	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		//cargar los casos desde el conector a la base de dasos
		_casoBase.init(_connector);
		//imprimir los casos leidos
		//esta linea se puede comentar es solo para depuración
		/*java.util.Collection<CBRCase> casos = _casoBase.getCases();
		for (CBRCase c: casos)
			System.out.println(c);*/
		return _casoBase;
	}
	
	private void aprender (CBRCase caso){
		Collection<CBRCase> listaDeCasos = new ArrayList<CBRCase>();
		System.out.println(caso);
		listaDeCasos.add(caso);
		_casoBase.learnCases(listaDeCasos);
		//copiarCasoAFichero((DescripcionSoccer)caso.getDescription(),(SolucionSoccer)caso.getSolution());
	}


	
}
// Esto es par ausar el visor de bases de datos y ver que se cargara la tabla
//jdbc(hsqldbserver)
//rellanar el formulario
//login sa
//pass
//host/ipadress localhost
//puerto 9001
//databasename tu base de datos
//crear objeto que almacena la consutla
//y dalre conectar una vez inicado el partido
/*una vez tiene una interfaz
 * tiene una consola donde poner nuevas colsultas
 * y tienes que escribar 
 * select * from tabla
 */
//creamos la pregunta
