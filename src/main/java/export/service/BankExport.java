package export.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.neo4j.graphdb.Node;

import export.util.CSVUtils;
import export.util.ExportUtil;
import export.util.PropertiesUtil;

public class BankExport {

	
	private FileWriter writer;
	
	public BankExport() throws IOException {
		this.writer = new FileWriter(PropertiesUtil.getNameFileBanksCsv());
	}
	
	public void export() throws IOException {
		
		_log.info("....Start BankExport....");
		CSVUtils.writeLine(writer, Arrays.asList("Id","Name","Detail","Url","Url Image", "Name Image"));
		
		ExportService service = new ExportService();
		
		Iterable<Node> nodes = service.getAllNodes();
		List<String> values;
		String nameImage;
		
		for (Node node : nodes) {
			if (node.hasProperty("nameBank")) {
				_log.info("Export Bank Name: " + (String) node.getProperty("nameBank"));
				
				values = new ArrayList<String>();
				values.add(node.getId()+"");
				values.add(ExportUtil.getProperty(node, "nameBank"));
				values.add(ExportUtil.getProperty(node, "detailsBank"));
				values.add(ExportUtil.getProperty(node, "urlBank"));
				values.add(ExportUtil.getProperty(node, "urlImageBank"));
				
				nameImage = String.valueOf(UUID.randomUUID());
				if (ExportUtil.saveImage(ExportUtil.getProperty(node, "urlImageBank"), nameImage)) {
					values.add(nameImage);
				} else {
					values.add("");
				}
				
				//UUID.randomUUID()
				CSVUtils.writeLine(writer, values);
			}
		}

        writer.flush();
        writer.close();
        
        _log.info("....End BankExport....");
	}
	
	private static final Logger _log = Logger.getLogger(BankExport.class.getName());
}
