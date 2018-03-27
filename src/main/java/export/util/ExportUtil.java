package export.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.neo4j.graphdb.Node;

public class ExportUtil {

	public static String getProperty(Node node, String nameProperty) {
		String value = "";
		String valueProperty;
		
		if (node.hasProperty(nameProperty)) {
			valueProperty = (String) node.getProperty(nameProperty);
			if (valueProperty.trim() != "" && !valueProperty.trim().equals("null")) {
				value = valueProperty.trim();
			}
		}
		
		return value;
	}
	
	public static boolean saveImage(String urlImage, String nameImage) {
		
		boolean isSaved = false;
		
		if (urlImage != null && urlImage.startsWith("http")) {
			try {
				URL url = new URL(urlImage);
				InputStream inputStream = url.openStream();
				nameImage = PropertiesUtil.getImagePath() + "/" + nameImage;
				OutputStream outputStream = new FileOutputStream(nameImage);
				
				byte[] b = new byte[2048];
				int length;
			 
				while ((length = inputStream.read(b)) != -1) {
					outputStream.write(b, 0, length);
					isSaved = true;
				}
			 
				inputStream.close();
				outputStream.close();
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return isSaved;
	}
}
