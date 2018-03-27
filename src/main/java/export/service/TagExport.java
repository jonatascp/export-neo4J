package export.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Logger;

import org.neo4j.graphdb.Node;

import export.util.CSVUtils;
import export.util.PropertiesUtil;

public class TagExport {

	
	private FileWriter writer;
	
	public TagExport() throws IOException {
		this.writer = new FileWriter(PropertiesUtil.getNameFileTagsCsv());
	}
	
	public void export() throws IOException {
		
		_log.info("....Start TagExport....");
		CSVUtils.writeLine(writer, Arrays.asList("Id","Name"));
		
		ExportService service = new ExportService();
		
		Iterable<Node> nodes = service.getAllNodes();
		
		for (Node node : nodes) {
			if (node.hasProperty("nametag")) {
				_log.info("Export Tag Name: " + (String) node.getProperty("nametag"));
				CSVUtils.writeLine(writer, Arrays.asList(node.getId()+"", (String) node.getProperty("nametag")));
			}
		}

        writer.flush();
        writer.close();
        
        _log.info("....End TagExport....");
	}
	
	private static final Logger _log = Logger.getLogger(TagExport.class.getName());
}
