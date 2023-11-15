/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa.all.afaanoromoo;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import java.io.IOException;

/**
 * A filter that stems Amharic words.
 */
public final class AfaanoromoStemFilter extends TokenFilter {
	/**
	 * The actual token in the input stream
	 */
	private Token token = null;
	private AfaanoromoStemmer stemmer = null;

	public AfaanoromoStemFilter(TokenStream in) {
		super(in);
		stemmer = new AfaanoromoStemmer();
	}

	/**
	 * @return Returns the next token in the stream, or null at EOS
	 */
	public final Token next() throws IOException {
		if ((token = input.next()) == null) {
			return null;
		} else {
                        @SuppressWarnings("deprecation")
			String s = stemmer.Stem(token.termText());
			if (!s.equals(token.termText())) {
				return new Token(s, token.startOffset(), token.endOffset(),
						token.type());
			}
			return token;
		}
	}

}