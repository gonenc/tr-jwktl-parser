package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IWikiString;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.util.IWiktionaryIterator;

public class ReadEtymology {
	
	public static void main(String[] args) throws Exception {
	
		IWiktionaryEdition edition = JWKTL.openEdition(new File("database"));
		IWiktionaryIterator<IWiktionaryEntry> entries = edition.getAllEntries();
		int count=0;
		HashMap<String, Integer> countEty = new HashMap<>();
		
		BufferedWriter fileWriter = new BufferedWriter(new FileWriter(new File("/home/gonenc/Desktop/etymology.txt")));
		
		while (entries.hasNext()) {
			IWiktionaryEntry entry = (IWiktionaryEntry) entries.next();
			IWikiString ety = entry.getWordEtymology();
			if(entry.getWordLanguage()!=null && entry.getWordLanguage().getISO639_3().equals("tur")) {
				if(ety!=null) {
					count++;
					
					fileWriter.write(entry.getKey() + " ; " + entry.getWord() +";"+ ety.getText() +"\n");
					
					if(countEty.containsKey(ety.getPlainText())) {
						countEty.put(ety.getPlainText(), countEty.get(ety.getPlainText())+1);
					} else {
						countEty.put(ety.getPlainText(), 1);
					}
				}	
			}
			
			
		}
		fileWriter.close();
		//System.out.println(count);
		
/*		for (String ety : countEty.keySet()) { 
			if(countEty.get(ety)<3)
			System.out.printf("%s \t %d\n", ety, countEty.get(ety).intValue());
		}*/
		


	}

}
