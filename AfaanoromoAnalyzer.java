/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa.all.afaanoromoo;

import java.io.*;
import java.util.*;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;

/**
 * Title: AfaanoromoAnalyzer Description: build from a AfaanoromoTokenizer, filtered
 * with AfaanoromoStopFilter and AfaanoromoStemFilter.
 * 
 */

public class AfaanoromoAnalyzer extends Analyzer {

	public AfaanoromoAnalyzer() {
	}

	/**
	 * Creates a TokenStream which tokenizes all the text in the provided
	 * Reader.
	 *
	 */
	public final TokenStream tokenStream(String fieldName, Reader reader) {
		TokenStream result = new AfaanoromoTokenizer(reader);
		StringBuilder sb = new StringBuilder();
		try {
			for (Token token = result.next(); token != null; token = result
					.next()) {
				String term = token.termText();
				shortword expand = new shortword();
				term = expand.Expander(term);
				sb.append(term + " ");}
		} catch (Exception e) {
		}
		String sentence = sb.toString();
		TokenStream results = new AfaanoromoTokenizer(new StringReader(sentence));
		results = new AfaanoromoShortFilter(results);
		results = new AfaanoromoStemFilter(results);
		return results;
	}

  
}