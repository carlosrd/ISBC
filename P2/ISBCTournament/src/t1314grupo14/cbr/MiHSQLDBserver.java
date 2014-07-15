package t1314grupo14.cbr;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import jcolibri.util.FileIO; 
import org.hsqldb.Server;
import jcolibri.test.database.SqlFile;;

public class MiHSQLDBserver {
	
	static boolean inicializar = false;
	
	final static char barra = File.separatorChar;
	final static String rutaDataBase = "t1314grupo14"+barra+"cbr"+barra+"soccer.sql";
	
	private static Server servidor;
	
	public static void init() {
		if (inicializar)
			return;
		org.apache.commons.logging.LogFactory.getLog(MiHSQLDBserver.class).info("Creando base de datos...");
		
		servidor = new Server();
		servidor.setDatabaseName(0, "soccer");
		servidor.setDatabasePath(0, "mem:soccer;sql.enforce_strict_size=true");
		
		//servidor.setDatabaseName(1, "soccerext");
		//servidor.setDatabasePath(1, "mem:soccerext;sql.enforce_strict_size=true");
		
		servidor.setLogWriter(null);
		servidor.setErrWriter(null);
		servidor.setSilent(true);
		servidor.start();
		
		inicializar = true;
		try {
			Class.forName("org.hsqldb.jdbcDriver");
			
			PrintStream out = new PrintStream(new ByteArrayOutputStream());
			Connection conn = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/soccer", "sa", "");
			SqlFile file = new SqlFile(new File(FileIO.findFile(rutaDataBase).getFile()),false,new HashMap());
			file.execute(conn,out,out, true);
			org.apache.commons.logging.LogFactory.getLog(MiHSQLDBserver.class).info("Generacion de la base de datos termianda");
		} catch (Exception e) {
			org.apache.commons.logging.LogFactory.getLog(MiHSQLDBserver.class).error(e);
		}
	}
	
	public static void shutDown()
     {
 
          if (inicializar)
          {
              servidor.stop();
               inicializar = false;
           }
       }
	
	public static void main(String[] args)
       {
           MiHSQLDBserver.init();
           MiHSQLDBserver.shutDown();
           System.exit(0);
           
       }

}
