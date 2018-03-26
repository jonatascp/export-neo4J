package export.service;

import java.util.logging.Logger;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import export.util.ConnectionNeo;

public class ExportService {

	private GraphDatabaseService graphDb;
	
	public Iterable<Node> getAllNodes() {
		
		Iterable<Node> allNodes = null;
		
		_log.info("...........Start Import Tags.........");
		
		graphDb = ConnectionNeo.getInstance().getGraphDb();
		if(graphDb ==null){
			ConnectionNeo.getInstance().createConnection();
			graphDb=ConnectionNeo.getInstance().getGraphDb();
		}
		
		_log.info("...........Begin transaction.........");
		Transaction tx = graphDb.beginTx();
		
		try {
			_log.info("...........Get All Nodes.........");
			allNodes = graphDb.getAllNodes();
			_log.info("...........Transaction Success.........");
			tx.success();
		} finally {
			_log.info("...........Transaction Finish.........");
			tx.finish();
		}
		
		return allNodes;
	}
	
	private static final Logger _log = Logger.getLogger(ExportService.class.getName());
}
