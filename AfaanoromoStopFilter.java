/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa.all.afaanoromoo;


 import java.io.IOException;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

public class AfaanoromoStopFilter extends TokenFilter

{
	private StopStemmer stemmer = null;

	public AfaanoromoStopFilter(TokenStream input) {
		super(input);
		stemmer = new StopStemmer();
	}
	public final Token next() throws IOException {
		// return the first non-stop word found
		for (Token token = input.next(); token != null; token = input.next()) {
			String termText = token.termText();
			if (!stemmer.isStop(termText))
				return token;
		}
		// reached EOS -- return null
		return null;
	}
}
