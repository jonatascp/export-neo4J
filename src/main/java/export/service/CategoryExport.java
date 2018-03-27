package export.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.neo4j.graphdb.Node;

import export.util.CSVUtils;
import export.util.PropertiesUtil;

public class CategoryExport {

	
	private FileWriter writer;
	
	public CategoryExport() throws IOException {
		this.writer = new FileWriter(PropertiesUtil.getNameFileCategoriesCsv());
	}
	
	public void export() throws IOException {
		
		_log.info("....Start CategoryExport....");
		CSVUtils.writeLine(writer, Arrays.asList("Id", "Name", "Tags", "Description", "Images"));
		
		ExportService service = new ExportService();
		
		Iterable<Node> nodes = service.getAllNodes();
		List<String> values;
		
		for (Node node : nodes) {
			
			if (node.hasProperty("nameCategorys") && node.hasProperty("tags")) {
				
				values = new ArrayList<String>();
				values.add(node.getId() + "");
				values.add((String) node.getProperty("nameCategorys"));
				values.add((String) node.getProperty("tags"));
				values.add((String) node.getProperty("descriptionCategorys"));
				values.add((String) node.getProperty("imagesCategorys"));
				
				_log.info("Export Category Name: " + (String) node.getProperty("nameCategorys"));
				CSVUtils.writeLine(writer, values);
			}
		}

        writer.flush();
        writer.close();
        
        _log.info("....End CategoryExport....");
	}
	
	private static final Logger _log = Logger.getLogger(CategoryExport.class.getName());
}
