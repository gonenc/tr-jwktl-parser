package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IPronunciation;
import de.tudarmstadt.ukp.jwktl.api.IWikiString;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryRelation;
import de.tudarmstadt.ukp.jwktl.api.util.IWiktionaryIterator;

public class ReadRelations {
	
	public static void main(String[] args) throws Exception {
	
		IWiktionaryEdition edition = JWKTL.openEdition(new File("database"));
		IWiktionaryIterator<IWiktionaryEntry> entries = edition.getAllEntries();
		int count=0;
		HashMap<String, Integer> countEty = new HashMap<>();
		
				
		while (entries.hasNext()) {
			IWiktionaryEntry entry = (IWiktionaryEntry) entries.next();
			
			List<IWiktionaryRelation> relations = entry.getRelations();
			if(relations!=null) {
				for (IWiktionaryRelation r : relations) {
					System.out.println(r.getTarget() + " " + r.getRelationType().toString() + " " + entry.getWord());
				}	
			}
			
			
			
			
		}
		
		

	}

}
