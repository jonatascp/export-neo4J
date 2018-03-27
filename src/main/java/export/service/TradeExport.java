package export.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.neo4j.graphdb.Node;

import export.util.CSVUtils;
import export.util.ExportUtil;
import export.util.PropertiesUtil;

public class TradeExport {

	
	private FileWriter writer;
	
	public TradeExport() throws IOException {
		this.writer = new FileWriter(PropertiesUtil.getNameFileTradesCsv());
	}
	
	public void export() throws IOException {
		
		_log.info("....Start TradeExport....");
		CSVUtils.writeLine(writer, Arrays.asList("Id","Name","Nit","Url","Url Bank PSE", "Detail", "Tags", "Id banks", "Id Categories"));
		
		ExportService service = new ExportService();
		
		Iterable<Node> nodes = service.getAllNodes();
		List<String> values;
		
		for (Node node : nodes) {
			if (node.hasProperty("nameTrade")) {
				_log.info("Export Trade Name: " + (String) node.getProperty("nameTrade"));
				
				values = new ArrayList<String>();
				values.add(node.getId()+"");
				values.add(ExportUtil.getProperty(node, "nameTrade"));
				values.add(ExportUtil.getProperty(node, "nitTrade"));
				values.add(ExportUtil.getProperty(node, "urlTrade"));
				values.add(ExportUtil.getProperty(node, "urlBankPSE"));
				values.add(ExportUtil.getProperty(node, "detailTrade"));
				values.add(ExportUtil.getProperty(node, "tradeTags"));
				values.add(ExportUtil.getProperty(node, "idBank"));
				values.add(ExportUtil.getProperty(node, "idCategory"));
				CSVUtils.writeLine(writer, values);
			}
		}

        writer.flush();
        writer.close();
        
        _log.info("....End TradeExport....");
    }
	
	private static final Logger _log = Logger.getLogger(TradeExport.class.getName());
}
