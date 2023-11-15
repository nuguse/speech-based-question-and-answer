/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 */

package qa.all.afaanoromoo;



import java.io.*;
import java.io.StringReader;
import java.util.*;
import java.io.IOException;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;

public class AfaanoromoShortFilter extends TokenFilter

{
	// private StopStemmer stemmer = null;
	public AfaanoromoShortFilter(TokenStream input) {
		super(input);
		// stemmer=new StopStemmer();

	}

	public final Token next() throws IOException {
		// return the first non-stop word found
		Token token2 = null;
		Token token1 = null;
		for (Token token = input.next(); token != null; token = input.next()) {

			String term = token.termText();
			shortword expand = new shortword();
			String str = expand.Expander(term);
		  //AfaanoromoTokenizer tokenizer=new AfaanoromoTokenizer();
			if (str.equals(term))
				return token;
			else {

				int index = str.indexOf(" ");
				if (index == -1) {
					token1 = new Token(str, token.startOffset(), token
							.endOffset()
							- index, token.type());
					return token1;
				} else if (index > 0) {

					TokenStream result = new AfaanoromoTokenizer(new StringReader(
							str));

					while ((token = result.next()) != null) {

						return token;

						// return token;
					}

				}

			}
			return null;
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		// AfaanoromoicShortFilter r=new Afaan oromooShortFilter();
		FileInputStream fin = new FileInputStream(new File("C:/ii.txt"));
		InputStreamReader read = new InputStreamReader(fin,"utf-8");
		BufferedReader rd = new BufferedReader(read);
		FileOutputStream fout = new FileOutputStream(new File("C:/kk.txt"));
		OutputStreamWriter rt = new OutputStreamWriter(fout );
		BufferedWriter rtt = new BufferedWriter(rt);
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		String sentence = sb.toString();
		Token token = null;
		TokenStream result = new AfaanoromoTokenizer(new StringReader(sentence));
		result = new AfaanoromoShortFilter(result);
		while ((token = result.next()) != null) {
			rtt.write(token.termText() + " ");

		}
		rtt.close();
	}
}