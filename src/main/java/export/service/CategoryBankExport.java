package export.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;

import export.util.CSVUtils;
import export.util.ExportUtil;
import export.util.PropertiesUtil;

public class CategoryBankExport {

	
	private FileWriter writer;
	
	public CategoryBankExport() throws IOException {
		this.writer = new FileWriter(PropertiesUtil.getNameFileCategoriesBanksCsv());
	}
	
	public void export() throws IOException {
		
		_log.info("....Start CategoryBankExport....");
		CSVUtils.writeLine(writer, Arrays.asList("Id","Name Bank", "Name Category","Tags","Description","Image", "Image Hover", "Position", "Name Image", "Name Image Hover"));
		
		ExportService service = new ExportService();
		
		Iterable<Node> nodes = service.getAllNodes();
		Iterable<Relationship> categoryBanks = new ArrayList<Relationship>();
		List<String> values;
		String nameBank;
		String nameImage;
		String nameImageHover;
		
		for (Node node : nodes) {
			if (node.hasProperty("nameBank")) {
				
				nameBank = (String) node.getProperty("nameBank");
				_log.info("Export Categories Banks: " + nameBank);
				
				categoryBanks = node.getRelationships(RelTypes.CATE_DE,
						Direction.OUTGOING);
				for (Relationship caterel : categoryBanks) {
					
					Node nodeRelationship = caterel.getEndNode();
					if (nodeRelationship.hasProperty("nameCategoryBanks")) {
						
						values = new ArrayList<String>();
						values.add(nodeRelationship.getId()+"");
						values.add(nameBank);
						values.add((String) nodeRelationship.getProperty("nameCategoryBanks"));
						values.add((String) nodeRelationship.getProperty("tags"));
						values.add((String) nodeRelationship.getProperty("descriptionCategorys"));
						values.add((String) nodeRelationship.getProperty("image"));
						values.add((String) nodeRelationship.getProperty("imagehover"));
						values.add((String) nodeRelationship.getProperty("position_categoribank"));
						
						nameImage = String.valueOf(UUID.randomUUID());
						if (ExportUtil.saveImage(ExportUtil.getProperty(nodeRelationship, "image"), nameImage)) {
							values.add(nameImage);
						} else {
							values.add("");
						}
						
						nameImageHover = String.valueOf(UUID.randomUUID());
						if (ExportUtil.saveImage(ExportUtil.getProperty(nodeRelationship, "imagehover"), nameImageHover)) {
							values.add(nameImageHover);
						} else {
							values.add("");
						}
						
						CSVUtils.writeLine(writer, values);
					}
				}
			}
		}
		
        writer.flush();
        writer.close();
        
        _log.info("....End CategoryBankExport....");
	}
	
	private static enum RelTypes implements RelationshipType {
		BANKS, CATE_DE
	}
	
	private static final Logger _log = Logger.getLogger(CategoryBankExport.class.getName());
}
