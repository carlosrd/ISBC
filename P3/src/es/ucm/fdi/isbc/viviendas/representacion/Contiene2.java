/**
 * Equal.java
 * jCOLIBRI2 framework. 
 * @author Juan A. Recio-Garc?a.
 * GAIA - Group for Artificial Intelligence Applications
 * http://gaia.fdi.ucm.es
 * 28/10/2007
 */
package es.ucm.fdi.isbc.viviendas.representacion;

import jcolibri.exception.NoApplicableFilterPredicateException;
import jcolibri.method.retrieve.FilterBasedRetrieval.predicates.FilterPredicate;

/**
 * Predicate that compares if two objects are equal.
 * @author Juan A. Recio-Garcia
 * @author Developed at University College Cork (Ireland) in collaboration with Derek Bridge.
 * @version 1.0
 * @see jcolibri.method.retrieve.FilterBasedRetrieval.FilterBasedRetrievalMethod
 * @see jcolibri.method.retrieve.FilterBasedRetrieval.FilterConfig
 */
public class Contiene2 implements FilterPredicate
{
    public boolean compute(Object caseObject, Object queryObject) throws NoApplicableFilterPredicateException
    {
	if((caseObject == null)&&(queryObject==null))
	    return true;
	else if(caseObject == null)
	    return false;
	else if(queryObject == null)
	    return true;
	else 
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
