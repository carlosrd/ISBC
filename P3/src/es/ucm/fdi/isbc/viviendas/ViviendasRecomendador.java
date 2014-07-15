/**
 * Houses8.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc?a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 23/10/2007
 */
package es.ucm.fdi.isbc.viviendas;

import es.ucm.fdi.isbc.viviendas.interfaz.MyDisplayCases;
import es.ucm.fdi.isbc.viviendas.representacion.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import jcolibri.casebase.LinealCaseBase;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.connector.PlainTextConnector;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.extensions.recommendation.askingAndProposing.AskingAndProposingPreferenceElicitation;
import jcolibri.extensions.recommendation.askingAndProposing.DisplayCasesIfNumberAndChangeNavigation;
import jcolibri.extensions.recommendation.casesDisplay.DisplayCasesTableMethod;
import jcolibri.extensions.recommendation.casesDisplay.UserChoice;
import jcolibri.extensions.recommendation.conditionals.BuyOrQuit;
import jcolibri.extensions.recommendation.conditionals.ContinueOrFinish;
import jcolibri.extensions.recommendation.conditionals.DisplayCasesIfNumber;
import jcolibri.extensions.recommendation.navigationByAsking.InformationGain;
import jcolibri.extensions.recommendation.navigationByAsking.ObtainQueryWithAttributeQuestionMethod;
import jcolibri.extensions.recommendation.navigationByAsking.SelectAttributeMethod;
import jcolibri.extensions.recommendation.navigationByProposing.CriticalUserChoice;
import jcolibri.extensions.recommendation.navigationByProposing.CritiqueOption;
import jcolibri.extensions.recommendation.navigationByProposing.DisplayCasesTableWithCritiquesMethod;
import jcolibri.extensions.recommendation.navigationByProposing.queryElicitation.MoreLikeThis;
import jcolibri.extensions.recommendation.tabuList.TabuList;
import jcolibri.method.gui.formFilling.ObtainQueryWithFormMethod;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.FilterBasedRetrieval.FilterBasedRetrievalMethod;
import jcolibri.method.retrieve.FilterBasedRetrieval.FilterConfig;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.Contiene2;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.Equal;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.NotEqual;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.QueryLess;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.QueryLessOrEqual;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.QueryMore;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.QueryMoreOrEqual;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import jcolibri.method.retrieve.NNretrieval.similarity.LocalSimilarityFunction;
import jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import jcolibri.method.retrieve.NNretrieval.similarity.local.EnumDistance;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString;
import jcolibri.method.retrieve.NNretrieval.similarity.local.Table;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.InrecaLessIsBetter;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.InrecaMoreIsBetter;
import jcolibri.method.retrieve.NNretrieval.similarity.local.recommenders.McSherryMoreIsBetter;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.JaccardCoefficient;
import jcolibri.method.retrieve.NNretrieval.similarity.local.textual.TextualSimUtils;
import jcolibri.method.retrieve.selection.SelectCases;
import jcolibri.method.retrieve.selection.compromiseDriven.CompromiseDrivenSelection;
import jcolibri.method.retrieve.selection.diversity.BoundedGreedySelection;
import jcolibri.test.recommenders.housesData.HouseDescription;

public class ViviendasRecomendador implements StandardCBRApplication
{
    /** Connector object */
    Connector _connector;
    /** CaseBase object */
    CBRCaseBase _caseBase;
    
    /** Configuration object for Form Filling */
    Map<Attribute,String> labels;
    /** Configuration object for Form Filling */
    ArrayList<Attribute> hiddenAtts;

    FilterConfig filterConfig;  
    NNConfig simConfig;
    //SelectAttributeMethod selectAttributeMethod;
    Collection<CritiqueOption> critiques;
    
    public void configure() throws ExecutionException
    {
	// Create a data base connector
	//_connector = new PlainTextConnector();
	
    	
    _connector = new ViviendasConnector();
    Collection<CBRCase> cases = _connector.retrieveAllCases();
	for(CBRCase c : cases)
		System.out.println(c);
	
	_caseBase = new LinealCaseBase();
	
	// Configure Form Filling
	hiddenAtts = new ArrayList<Attribute>();
	hiddenAtts.add(new Attribute("titulo", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("urlFoto", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("url", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("urlFoto", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("superficie", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("estado", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("descripcion", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("coordenada", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("precioMedio", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("precioZona", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("extrasFinca", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("extrasBasicos", DescripcionVivienda.class));
	hiddenAtts.add(new Attribute("extrasOtros", DescripcionVivienda.class));
	labels = new HashMap<Attribute,String>();
	labels.put(new Attribute("precio", DescripcionVivienda.class), "Max precio");
	labels.put(new Attribute("ascensor", DescripcionVivienda.class), "¿Con ascensor?");
	labels.put(new Attribute("amueblado", DescripcionVivienda.class), "¿Amueblado?");
	labels.put(new Attribute("terraza", DescripcionVivienda.class), "¿Con terraza?");
	
	
	
	filterConfig = new FilterConfig();
	filterConfig.addPredicate(new Attribute("localizacion", DescripcionVivienda.class), new Contiene());
	filterConfig.addPredicate(new Attribute("tipo", DescripcionVivienda.class), new Equal());
	filterConfig.addPredicate(new Attribute("habitaciones", DescripcionVivienda.class), new QueryLessOrEqual());
	filterConfig.addPredicate(new Attribute("banios", DescripcionVivienda.class), new QueryLessOrEqual());
	filterConfig.addPredicate(new Attribute("precio", DescripcionVivienda.class), new QueryMoreOrEqual());
	filterConfig.addPredicate(new Attribute("ascensor", DescripcionVivienda.class), new Equal());
	filterConfig.addPredicate(new Attribute("amueblado", DescripcionVivienda.class), new Equal());
	filterConfig.addPredicate(new Attribute("terraza", DescripcionVivienda.class), new Equal());

	
	//Lets configure the KNN
	simConfig = new NNConfig();
	// Set the average() global similarity function for the description of the case
	simConfig.setDescriptionSimFunction(new Average());
	simConfig.addMapping(new Attribute("localizacion", DescripcionVivienda.class), new MaxString());
	simConfig.addMapping(new Attribute("tipo", DescripcionVivienda.class), new jcolibri.method.retrieve.NNretrieval.similarity.local.Equal());
	//simConfig.addMapping(new Attribute("habitaciones", DescripcionVivienda.class), new McSherryMoreIsBetter(0,0));
	simConfig.addMapping(new Attribute("habitaciones", DescripcionVivienda.class), new InrecaMoreIsBetter(0.5));
	simConfig.addMapping(new Attribute("banios", DescripcionVivienda.class), new InrecaMoreIsBetter(0.5));
	simConfig.addMapping(new Attribute("coordenada", DescripcionVivienda.class), new Cercano());
	//simConfig.addMapping(new Attribute("precio", DescripcionVivienda.class), new InrecaLessIsBetter(2000, 0.5));
	simConfig.addMapping(new Attribute("precio", DescripcionVivienda.class), new Interval(25));
	//simConfig.addMapping(new Attribute("ascensor", DescripcionVivienda.class), new Igual());
	//simConfig.addMapping(new Attribute("amueblado", DescripcionVivienda.class), new jcolibri.method.retrieve.NNretrieval.similarity.local.Equal());
	//simConfig.addMapping(new Attribute("terraza", DescripcionVivienda.class), new jcolibri.method.retrieve.NNretrieval.similarity.local.Equal());
	
		
	
	critiques = new ArrayList<CritiqueOption>();
	critiques.add(new CritiqueOption("Más barato",new Attribute("precio", DescripcionVivienda.class),new QueryMore()));
	critiques.add(new CritiqueOption("Más habitaciones",new Attribute("habitaciones", DescripcionVivienda.class),new QueryLess()));
	critiques.add(new CritiqueOption("Más baños",new Attribute("banios", DescripcionVivienda.class),new QueryLess()));
	//critiques.add(new CritiqueOption("Cambiar tipo vivienda",new Attribute("tipo", DescripcionVivienda.class),new Equal()));
	critiques.add(new CritiqueOption("Misma localización",new Attribute("localizacion", DescripcionVivienda.class),new Contiene2()));
	
	
	/*

	//critiques.add(new CritiqueOption("Change Area",new Attribute("area", DescripcionVivienda.class),new jcolibri.method.retrieve.FilterBasedRetrieval.predicates.Equal()));
	//critiques.add(new CritiqueOption("Another Area",new Attribute("area", DescripcionVivienda.class),new NotEqual()));
	*/
	
    }

    public void cycle(CBRQuery query) throws ExecutionException
    {	

    // Obtain the query
    ObtainQueryWithFormMethod.obtainQueryWithInitialValues(query,hiddenAtts,labels);
    	
	// Jump to the conversation cycle
	sequence1(query, this._caseBase.getCases(), this.filterConfig);

    }
   
    
    public void sequence1(CBRQuery query, Collection<CBRCase> cases, FilterConfig filterConfig2) throws ExecutionException
    {
	// Retrieve using filter based retrieval
    	// Execute Filter
    	Collection<CBRCase> filtered = FilterBasedRetrievalMethod.filterCases(cases, query, filterConfig2);

    	// Execute NN
    	Collection<RetrievalResult> retrievedCases = NNScoringMethod.evaluateSimilarity(filtered, query, simConfig);
    	
    	Collection<CBRCase> allCases = SelectCases.selectAll(retrievedCases);
    	
    	// Select cases
    	Collection<CBRCase> selectedCases = SelectCases.selectTopK(retrievedCases, 50);
    	
    	//CriticalUserChoice choice = DisplayCasesTableWithCritiquesMethod.displayCasesInTableWithCritiques(selectedCases, critiques, filtered);
    	

        	
 	
	// Display condition based on the number of cases.
    	if(DisplayCasesIfNumber.displayCasesWithMessage(Integer.MAX_VALUE, 1, selectedCases))
	    sequence2(query, selectedCases, allCases);
	/*else {
		 // Obtain the query
	    ObtainQueryWithFormMethod.obtainQueryWithInitialValues(query,hiddenAtts,labels);
	 // Jump to the conversation cycle
		sequence1(query, cases, filterConfig2);
		
	}
	*/
    }
    
    public void sequence2(CBRQuery query, Collection<CBRCase> selectedCases, Collection<CBRCase> allCases) throws ExecutionException
    {
    	//UserChoice choice = DisplayCasesTableMethod.displayCasesInTableEditQuery(selectedCases);
    	CriticalUserChoice choice = MyDisplayCases.displayCasesInTableWithCritiques(selectedCases, critiques, allCases);

    	// Update Tabu list
    	//TabuList.updateTabuList(selectedCases);
    	
    	if (choice.getChoice()==-4) // si pulsamos el boton "Editar formulario"
    	{
    	    ObtainQueryWithFormMethod.obtainQueryWithInitialValues(query,hiddenAtts,labels);
    		sequence1(query, this._caseBase.getCases(), this.filterConfig);
    	} else {
    		if(ContinueOrFinish.continueOrFinish(choice))
    		sequence3(choice.getSelectedCaseAsQuery(), choice, allCases); // hemos pulsado una "critica"
    	else 
    	{
    	    sequence4(choice, allCases); // hemos pulsado Elegir o Salir
    	}
    	}
    }
    
    /*
    public void backup_sequence3(CBRQuery query) throws ExecutionException
    {
	// Refine query and back to the main sequence
	ObtainQueryWithFormMethod.obtainQueryWithInitialValues(query,hiddenAtts,labels);

	sequence1(query);
    }*/
    
    public void sequence3(CBRQuery query, CriticalUserChoice cuc, Collection<CBRCase> allCases) throws ExecutionException
    {
	// Replaze current query with the critizied one
	MoreLikeThis.moreLikeThis(query, cuc.getSelectedCase());
	
	sequence1(query, allCases, cuc.getFilterConfig());
	//sequence1(query, filterConfig);
    }
    
    public void sequence4(UserChoice choice, Collection<CBRCase> retrievedCases)
    {
	if(BuyOrQuit.buyOrQuit(choice))
	    System.out.println("Finish - User Buys: "+choice.getSelectedCase());
	
	else
	    System.out.println("Finish - User Quits");
    }


    public void postCycle() throws ExecutionException
    {
    }

    public CBRCaseBase preCycle() throws ExecutionException
    {
	// Load cases from connector into the case base
	_caseBase.init(_connector);		
	// Print the cases
	java.util.Collection<CBRCase> cases = _caseBase.getCases();
	for(CBRCase c: cases)
		System.out.println(c);
	return _caseBase;
    }
    
    public static void main(String[] args) {
	StandardCBRApplication recommender = new ViviendasRecomendador();
	try
	{
	    recommender.configure();
	    
	    recommender.preCycle();
	    
	    CBRQuery query = new CBRQuery();
	    
	    DescripcionVivienda dv = new DescripcionVivienda();
	    dv.setTipo(DescripcionVivienda.TipoVivienda.Piso);
	    dv.setHabitaciones(2);
	    dv.setBanios(1);
	    dv.setCoordenada(new Coordenada(0,0));
	    dv.setPrecio(200000);
	    dv.setDescripcion("");
	    
	    
	    query.setDescription(dv);
	    
	    recommender.cycle(query);
	    
	    recommender.postCycle();
	    
	    //System.exit(0);
	} catch (Exception e)
	{
	    org.apache.commons.logging.LogFactory.getLog(ViviendasRecomendador.class).error(e);
	    
	}
	

    }

    public class Contiene implements FilterPredicate
    {
        public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException
        {
    	if((caseObject == null)&&(queryObject==null))
    	    return true;
    	else if(caseObject == null)
    	    return false;
    	else if(queryObject == null)
    	    return true;
    	/*else if (! ((caseObject instanceof java.lang.Number)||(caseObject instanceof Enum)))
    		throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), caseObject.getClass());
    	else if (! ((queryObject instanceof java.lang.Number)||(queryObject instanceof Enum)))
    		throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), queryObject.getClass());
    	*/else 
    	{
    	    String caseValue = (String) caseObject;
    	    String queryValue = (String) queryObject;
    	    
    	    caseValue = caseValue.toLowerCase();
    	    queryValue = queryValue.toLowerCase();
    	    
    	    if(caseValue.contains(queryValue))
    	    return true;
    	    else
    	    	return false;
    	}
        }

    }
    
    public class TieneExtra implements FilterPredicate
    {
        public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException
        {
    	if((caseObject == null)&&(queryObject==null))
    	    return true;
    	else if(caseObject == null)
    	    return false;
    	else if(queryObject == null)
    	    return true;
    	/*else if (! ((caseObject instanceof java.lang.Number)||(caseObject instanceof Enum)))
    		throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), caseObject.getClass());
    	else if (! ((queryObject instanceof java.lang.Number)||(queryObject instanceof Enum)))
    		throw new jcolibri.exception.NoApplicableFilterPredicateException(this.getClass(), queryObject.getClass());
    	*/else 
    	{
    	    Boolean caseValue = (Boolean) caseObject;
    	    Boolean queryValue = (Boolean) queryObject;
    	    if(caseValue)
    	    return true;
    	    else
    	    	return false;
    	}
        }

    }
    
    public class Cercano implements LocalSimilarityFunction {
	public double compute(Object cO, Object qO) throws jcolibri.exception.NoApplicableSimilarityFunctionException
		{
		if ((cO == null) || (qO == null))
			return 0;
		/*if (!(s instanceof java.lang.String))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), s.getClass());
		if (!(t instanceof java.lang.String))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), s.getClass());
*/

		Coordenada c = (Coordenada) cO;
		Coordenada q = (Coordenada) qO;
		
		if (getDistanceQK(c,q)<3000)
			return 1.0;
			else
				return 0.0;
		
	}
	
	public int getDistanceQK(Coordenada pos1, Coordenada pos2){
        int radius = 6371000; //Radio de la tierra
        double dLat = Math.toRadians(pos2.getLatitud()-pos1.getLatitud());
        double dLon = Math.toRadians(pos2.getLongitud()-pos1.getLongitud());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(pos1.getLatitud())) * Math.cos(Math.toRadians(pos2.getLatitud())) * Math.sin(dLon /2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return (int) (radius * c);  
  }

	@Override
	public boolean isApplicable(Object o1, Object o2) {
		if((o1==null)&&(o2==null))
			return true;
		else
			return false;
		/*else if(o1==null)
			return o2 instanceof String;
		else if(o2==null)
			return o1 instanceof String;
		else
			return (o1 instanceof String)&&(o2 instanceof String);*/
	}
    }
    
    public class Igual implements LocalSimilarityFunction {
	public double compute(Object n1, Object n2) throws jcolibri.exception.NoApplicableSimilarityFunctionException
		{
		if ((n1 == null) || (n2 == null))
			return 0;
		/*if (!(s instanceof java.lang.String))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), s.getClass());
		if (!(t instanceof java.lang.String))
			throw new jcolibri.exception.NoApplicableSimilarityFunctionException(this.getClass(), s.getClass());
*/

		n1 = (Boolean) n1;
		n2 = (Boolean) n2;
		
		if (n1==n2)
			return 1.0;
			else
				return 0.0;
		
	}

	@Override
	public boolean isApplicable(Object o1, Object o2) {
		if((o1==null)&&(o2==null))
			return true;
		else
			return false;
		/*else if(o1==null)
			return o2 instanceof String;
		else if(o2==null)
			return o1 instanceof String;
		else
			return (o1 instanceof String)&&(o2 instanceof String);*/
	}
    }
    
    
}


