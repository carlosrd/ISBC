import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import es.ucm.fdi.gaia.ontobridge.OntoBridge;
import es.ucm.fdi.gaia.ontobridge.OntologyDocument;
import es.ucm.fdi.gaia.ontobridge.test.gui.PnlConceptsTree;

public class Practica4 {
	
	private static OntoBridge ob;
	private static javax.swing.JPanel myPanel;
	private static javax.swing.JPanel resultados;
	private static javax.swing.JFrame window;

	public static void main(String[] args) {
		
		// Cargamos la ontología 
		ob = new OntoBridge();
		ob.initWithPelletReasoner();
		OntologyDocument mainOnto = new OntologyDocument("file:ontologias/familia-real.owl");
		ArrayList<OntologyDocument> subOntologies = new ArrayList<OntologyDocument>();
		ob.loadOntology(mainOnto, subOntologies, false);
		
		// Oeste: Ontologia
		window = new JFrame(mainOnto.getURL());
		window.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		PnlConceptsTree tree = new PnlConceptsTree(ob);
		myPanel = new JPanel();
		myPanel.setLayout(new BorderLayout());
		myPanel.add(tree, BorderLayout.WEST);
		
		// Este: Botones
		JPanel botones = new JPanel();
		botones.setLayout(new GridLayout(4,1));
		JButton consulta1 = new JButton("Foto 1");
		botones.add(consulta1);
		JButton consulta2 = new JButton("Familiares");
		botones.add(consulta2);
		JButton consulta3 = new JButton("Lista Fotos familiares");
		botones.add(consulta3);
		myPanel.add(botones, BorderLayout.CENTER);
		
		resultados = new JPanel();
		//resultados.setLayout(new GridLayout(4,2));
		
		JScrollPane scroll = new JScrollPane();
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setViewportView(resultados);
		//scroll.addComponentListener();
		window.getContentPane().add(scroll, java.awt.BorderLayout.CENTER);
		
		myPanel.add(resultados, BorderLayout.EAST);
		
		consulta1.addActionListener( 
				new java.awt.event. 
				ActionListener() { 
				public void actionPerformed( 
				java.awt.event. 
				ActionEvent evt) { 
					try {
						getConsulta1(evt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				});  
		
		consulta2.addActionListener( 
				new java.awt.event. 
				ActionListener() { 
				public void actionPerformed( 
				java.awt.event. 
				ActionEvent evt) { 
					try {
						getConsulta2(evt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				});  
		
		consulta3.addActionListener( 
				new java.awt.event. 
				ActionListener() { 
				public void actionPerformed( 
				java.awt.event. 
				ActionEvent evt) { 
					try {
						getConsulta3(evt);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} 
				});  
		
		window.add(myPanel);
		window.pack();
		window.setSize(900, 700);
		window.setVisible(true);
		
	}

	//Método para la consulta1
	public static void getConsulta1(java.awt.event.ActionEvent evt) throws IOException { 
		
		// Mostramos por consola las propiedades de una instancia
		/* 
		Iterator<String> fotos = ob.listOfInstances("Familiar");
		while(fotos.hasNext())
		{
			String instance = fotos.next();
			System.out.println(instance);
		}
		*/
		
		resultados.removeAll();
		resultados.repaint();
	
		// Sacamos por pantalla cada propiedad y su valor de una instancia (en este caso solo la primera, el datatype)
		ArrayList<String> propertiesF1 = new ArrayList<String>();
		ArrayList<String> valuesF1 = new ArrayList<String>();
		ob.listInstancePropertiesValues("f1", propertiesF1, valuesF1);
		Iterator<String>  valI = valuesF1.iterator();
			String value = valI.next();

		// Cortamos el URL a partir del caracter ^ para que solo quede la ruta correcta	
		int corte = value.indexOf("^");
		String value2 = value.substring(0, corte);
		System.out.println(value2);
		
		// Cargamos la imagen para mostrarla por el JPanel
		BufferedImage foto1;
		foto1 = ImageIO.read(new File(value2));
		JLabel resultado1 = new JLabel(new ImageIcon(foto1));
		resultados.add(resultado1);
		window.pack();
	}
	
	//Método para la CONSULTA 2
	public static void getConsulta2(java.awt.event.ActionEvent evt) throws IOException { 
		// Mostramos por consola las propiedades de una instancia
		
		ArrayList<String> properties = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();
		JLabel resultado;
		Iterator<String> propI;
		Iterator<String>  valI;
		BufferedImage foto;
		
		Iterator<String> fotos = ob.listInstanceProperties("Familiar");
		while(fotos.hasNext())
		{
			String instance = fotos.next();
			
			// Sacamos por pantalla cada propiedad y su valor de una instancia (en este caso solo la primera, el datatype)
			
			ob.listInstancePropertiesValues(instance, properties, values);
			propI = properties.iterator();
			valI = values.iterator();
				String property = propI.next();
				String value = valI.next();
				System.out.println(property);
				System.out.println(value);

			// Cortamos el URL a partir del caracter ^ para que solo quede la ruta correcta	
			int corte = value.indexOf("^");
			String value2 = value.substring(0, corte);
			System.out.println(value2);
			
			// Cargamos la imagen para mostrarla por el JPanel
			foto = ImageIO.read(new File(value2));
			resultado = new JLabel(new ImageIcon(foto));
			resultados.add(resultado);
			window.pack();
			
		}
	}
	
	//Método para la consulta3
	public static void getConsulta3(java.awt.event.ActionEvent evt) throws IOException { 
		
		resultados.removeAll();
		resultados.repaint();
		
		Iterator<String> fotos = ob.listInstances("Familiar"); // Obtenemos las instancias de Fotos Familiares
		while(fotos.hasNext())  // Recorremos cada foto
		{
			String instance = fotos.next();
			String nombreF = instance.substring(53); // solo nos quedamos con el nombre de la foto
			
			ArrayList<String> properties = new ArrayList<String>();
			ArrayList<String> values = new ArrayList<String>();
			ob.listInstancePropertiesValues(nombreF, properties, values); // obtenemos las propiedades y sus valores de cada foto
			Iterator<String>  valI = values.iterator(); // sabemos q la primera propiedad es el datatype URlFoto
				String value = valI.next();

			// Cortamos el URL a partir del caracter ^ para que solo quede la ruta correcta	
			int corte = value.indexOf("^");
			String value2 = value.substring(0, corte);
			
			// Cargamos la imagen para mostrarla por el JPanel
			BufferedImage foto;
			foto = ImageIO.read(new File(value2));
			JLabel resultado = new JLabel(new ImageIcon(foto));
			resultados.add(resultado);
			window.pack();
			
		}
	}

} // fin clase



