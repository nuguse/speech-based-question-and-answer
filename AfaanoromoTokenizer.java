package qa.all.afaanoromoo;


import java.io.*;
import java.io.Reader;
import org.apache.lucene.analysis.*;

/**
 * A Afaan oromo Toenizer is a tokenizer that divides text at whitespace and oromic
 * Language punctuations. as well as tab,newline and carriage return Adjacent
 * sequences of characters other than the above mensioned one form tokens.
 */

public class AfaanoromoTokenizer extends CharTokenizer {
	/** Construct a new WhitespaceTokenizer. */
	public AfaanoromoTokenizer(Reader in) {

		super(in);
	}

	/**
	 * Collects only characters which do not satisfy
	 * {@link Character#isWhitespace(char)}.
	 */
	protected boolean isTokenChar(char cc) {
		// Character cc=Character.parseChar(c);
		if ((cc == '\u1360') ||  (cc == '\u1362')
				//|| (cc == '\u1363')
				
				 || (cc == '\n') || (cc == '\r'))
			return false;
		else
			return true;
	}

	/**
	 * Replace characters that can be used interchangably in Afaan  language
	 */
	protected char normalize(char c) {
		if (c == '!') // 
			return ' ';
		else if (c == '?') 
			return '?';
		/**else if (c == 'B')
			return 'b';
		else if (c == 'C')
			return 'c';
		else if (c == 'D')
			return 'd';
		else if (c == 'F')
			return 'f';
		else if (c == 'G')
			return 'g';
		else if (c == 'H')
			return 'h';
		else if (c == 'I')
			return 'i';

		else if (c == 'J')
			return 'j';
		else if (c == 'K')
			return 'k';
		else if (c == 'L')
			return 'l';
		else if (c == 'M')
			return 'm';
		else if (c == 'N')
			return 'n';
		else if (c == 'O')
			return 'o';
		else if (c == 'P')
			return 'p';
		else if (c == 'Q')
			return 'q';

		else if (c == 'R') // Replace R with r
			return 'r';
		else if (c == 'S')
			return 's';
		else if (c == 'T')
			return 't';
		else if (c == 'U')
			return 'u';
		else if (c == 'U')
			return 'u';
		else if (c == 'V')
			return 'v';
		else if (c == 'W')
			return 'w';
		else if (c == 'X')
			return 'x';
		else if (c == 'Y') 
			return 'y';

		else if (c == 'Z') // 
			return 'z';
*/
		else
			return c;

	}

	public static void main(String[] args) throws Exception {
		System.out.println("hi");
		FileInputStream fin = new FileInputStream(new File("c:/n/name.txt"));
		InputStreamReader read = new InputStreamReader(fin);
		BufferedReader rd = new BufferedReader(read);
		FileOutputStream fout = new FileOutputStream(new File("c:/kio4.txt"));
		OutputStreamWriter rt = new OutputStreamWriter(fout);
		BufferedWriter rtt = new BufferedWriter(rt);
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		String sentence = sb.toString();
		Token token = null;
		AfaanoromoTokenizer tk = new AfaanoromoTokenizer(new StringReader(
				sentence));
		System.out.println("hi");
		while ((token = tk.next()) != null) {
			rtt.write(token.termText()+" ");

		}
		rtt.close();
	}

}
