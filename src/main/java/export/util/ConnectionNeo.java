package export.util;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;

public class ConnectionNeo {
	
	//TODO get path in the properties file
	private static final String DB_PATH = "/home/jcp/dbACH/ACHneo4jpseBank";
	
	private static ConnectionNeo INSTANCE;
	private GraphDatabaseService graphDb;
	
	private ConnectionNeo() {
		
	}
	
	public static ConnectionNeo getInstance() {
		if (INSTANCE == null) {
			_log.info("....Create instance connection....");
			INSTANCE = new ConnectionNeo();
		}
		return INSTANCE;
	}
	
	public void createConnection() {
		_log.info("....Create connection....");
		registerShutdownHook(graphDb);
		
		try {

			_log.info("....Init open file database....");
			graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(DB_PATH);
			_log.info("....Opened file database....");

			registerShutdownHook(graphDb);
			
			
		} catch (Exception e) {
			_log.log(Level.INFO, "....Error in createConnection...", e);
		}
		
		setGraphDb(graphDb);
	}
	
	private static void registerShutdownHook(final GraphDatabaseService graphDb) {

		_log.info("....Register shutdownHook....");
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				if (graphDb != null) {
					graphDb.shutdown();
				}
			}
		});
	}
	
	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

	public void setGraphDb(GraphDatabaseService graphDb) {
		this.graphDb = graphDb;
	}

	private static final Logger _log = Logger.getLogger(ConnectionNeo.class.getName());
}
