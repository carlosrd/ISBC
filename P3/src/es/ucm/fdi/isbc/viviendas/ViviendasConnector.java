package es.ucm.fdi.isbc.viviendas;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CaseBaseFilter;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.InitializingException;
import es.ucm.fdi.isbc.viviendas.representacion.DescripcionVivienda;
import es.ucm.fdi.isbc.viviendas.representacion.SolucionVivienda;

public class ViviendasConnector implements Connector {

	@Override
	public void initFromXMLfile(URL file) throws InitializingException {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

	@Override
	public void storeCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteCases(Collection<CBRCase> cases) {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<CBRCase> retrieveAllCases() {
		ArrayList<CBRCase> cases = new ArrayList<CBRCase>();
		try
		{
			//BufferedReader reader = new BufferedReader(new FileReader("viviendas.txt"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("viviendas.txt"), "UTF-8"));
			String line = null;
			while ((line=reader.readLine())!=null)
			{
				DescripcionVivienda vivienda = new DescripcionVivienda(line);
				SolucionVivienda solucion = new SolucionVivienda();
				solucion.setId(vivienda.getId());
				solucion.setPrecio(vivienda.getPrecio());
				
				CBRCase _case = new CBRCase();
				_case.setDescription(vivienda);
				_case.setSolution(solucion);
				cases.add(_case);
				
			}
			reader.close();

		}catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return cases;
	}

	@Override
	public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Testing method
	 * @param args
	 
	public static void main(String[] args){
		ViviendasConnector vc = new ViviendasConnector();
		Collection<CBRCase> cases = vc.retrieveAllCases();
		for(CBRCase c : cases)
			System.out.println(c);
	}
	*/

}
