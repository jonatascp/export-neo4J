package export;

import java.util.logging.Logger;

import org.neo4j.graphdb.Node;

import export.service.ExportService;

public class Export {

	public static void main(String[] args) {
	
		
		_log.info("....Start Export....");
		ExportService service = new ExportService();
		
		Iterable<Node> nodes = service.getAllNodes();
		
		for (Node node : nodes) {
				_log.info(".............Node toString.........");
				_log.info(node.toString());
		}
		
		_log.info("....Finish Export....");
	}
	
	private static final Logger _log = Logger.getLogger(Export.class.getName());
}
