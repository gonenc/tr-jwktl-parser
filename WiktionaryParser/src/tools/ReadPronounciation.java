package tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

import de.tudarmstadt.ukp.jwktl.JWKTL;
import de.tudarmstadt.ukp.jwktl.api.IPronunciation;
import de.tudarmstadt.ukp.jwktl.api.IPronunciation.PronunciationType;
import de.tudarmstadt.ukp.jwktl.api.IWikiString;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEdition;
import de.tudarmstadt.ukp.jwktl.api.IWiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.api.entry.Pronunciation;
import de.tudarmstadt.ukp.jwktl.api.util.IWiktionaryIterator;

public class ReadPronounciation {
	
	public static void main(String[] args) throws Exception {
	
		IWiktionaryEdition edition = JWKTL.openEdition(new File("database"));
		IWiktionaryIterator<IWiktionaryEntry> entries = edition.getAllEntries();
		int count=0;
		HashMap<PronunciationType, Integer> countEty = new HashMap<>();
		countEty.put(PronunciationType.IPA, 0);
		countEty.put(PronunciationType.RHYME, 0);
		
		
		while (entries.hasNext()) {
			IWiktionaryEntry entry = (IWiktionaryEntry) entries.next();
			List<IPronunciation> pronunciations = entry.getPronunciations();
			
			if(pronunciations!=null) {
				for (IPronunciation p : pronunciations) {
					System.out.println(p.getText());
					countEty.put(p.getType(),countEty.get(p.getType())+1);
				}
				
			}
			
		}
		
		//System.out.println(count);
		
		for (PronunciationType ety : countEty.keySet()) { 
			System.out.printf("%s \t %d\n", ety, countEty.get(ety).intValue());
		}
		


	}

}
