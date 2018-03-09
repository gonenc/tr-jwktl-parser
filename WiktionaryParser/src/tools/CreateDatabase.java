package tools;

import util.DataParser;

public class CreateDatabase {
	
	
	public static void main(String[] args) {
		
		DataParser dataParser = new DataParser();
		dataParser.parseData("trwiktionary-20180220-pages-articles.xml.bz2", "database", "tr");
		
		
	}

}
