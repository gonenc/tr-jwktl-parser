package components;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.tudarmstadt.ukp.jwktl.api.IPronunciation.PronunciationType;
import de.tudarmstadt.ukp.jwktl.api.entry.Pronunciation;
import de.tudarmstadt.ukp.jwktl.api.entry.WikiString;
import de.tudarmstadt.ukp.jwktl.api.entry.WiktionaryEntry;
import de.tudarmstadt.ukp.jwktl.parser.components.BlockHandler;
import de.tudarmstadt.ukp.jwktl.parser.util.ParsingContext;

/**
 * 
 * TREtymologyHandler is a component to parse etymology information (the part starting with "Köken") from TR wiktionary pages.
 * e.g. from the page "car"
 * {{Köken}}
 * :{{k|Türkçe}}
 * 
 * @author mervess
 *
 */
public class TRPronounciationHandler extends BlockHandler
{
	private StringBuilder etymology;
	private static final Pattern PRONOUNCIATION_PATTERN = 
			Pattern.compile("\\{\\{(IPA(-Telaffuz|-Söyleniş)?|h|ses)\\|([^\\}]+)\\}\\}\\s*"); 
	
	public TRPronounciationHandler()
	{
		super("Söyleniş", "Heceleme");
	}
	
	public TRPronounciationHandler(final String... labels)
	{
		super(labels);
	}

	@Override
	public boolean processHead(final String textLine, final ParsingContext context)
	{	
		etymology = new StringBuilder();
		return super.processHead(textLine, context);
	}

	@Override
	public boolean processBody(String textLine, final ParsingContext context)
	{
		textLine = textLine.trim();
		
		Matcher matcher = PRONOUNCIATION_PATTERN.matcher(textLine);
		if (matcher.find()) {
			String etymologyText = matcher.group();
			if (etymologyText != null 
					&& !etymologyText.isEmpty()) {
				WiktionaryEntry posEntry = context.findEntry();
				PronunciationType type = null;
				if(textLine.contains("IPA")) {
					type= PronunciationType.IPA;
				} else if(textLine.contains("h|") || textLine.contains("ses|")) {
					type= PronunciationType.RHYME;
				} 
				posEntry.addPronunciation(new Pronunciation(type, matcher.group(3), null));
			}
		} else if (!textLine.isEmpty()) {		
			etymology.append(textLine);
		}
		return false;
	}

	public void fillContent(final ParsingContext context)
	{		
		if (etymology.length() > 0) {
			WiktionaryEntry posEntry = context.findEntry();
			//posEntry.addPronunciation(new Pronunciation(PronunciationType.IPA, etymology.toString(), null));
		}
	}
	
	public String getEtymology()
	{
		return etymology.toString();
	}
}
