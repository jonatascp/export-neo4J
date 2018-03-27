package export;

import java.io.IOException;
import java.util.logging.Logger;

import export.service.BankExport;
import export.service.CategoryBankExport;
import export.service.CategoryExport;
import export.service.TagExport;
import export.service.TradeExport;

public class Export {

	public static void main(String[] args) throws IOException {
	
		
		_log.info("....Start Export....");
		
		TagExport tagExport = new TagExport();
		tagExport.export();
		
		CategoryExport categoryExport = new CategoryExport();
		categoryExport.export();
		
		BankExport bankExport = new BankExport();
		bankExport.export();
		
		TradeExport tradeExport = new TradeExport();
		tradeExport.export();
		
		CategoryBankExport categoryBankExport = new CategoryBankExport();
		categoryBankExport.export();
		
		_log.info("....Finish Export....");
	}
	
	private static final Logger _log = Logger.getLogger(Export.class.getName());
}
