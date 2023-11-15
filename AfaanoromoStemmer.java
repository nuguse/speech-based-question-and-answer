/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa.all.afaanoromoo;
import java.util.Vector;
import java.io.*;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.*;

//import org.apache.lucene.index.DirectoryReader;


/**
 * A stemmer for Afaanoromoo words.
 */
public class AfaanoromoStemmer {
	private final Vector<String> preffixList = new Vector<>();
	private final Vector<String> suffixList = new Vector<>();
        private final Vector<String> stopword =new Vector<>();
	private final String[] Stopword = { "inna", "ffan",
			"kan", "irra","kun","sun","kam",
			//"chiisne", "amoo", "ame",
			//"chiiste", "aatii", "ise",
			//"oofte","atu","tetta",
			//"aatii","oofta","amaa",
			//"amoo",
                        
                        
                        
                        
			//"anne", "naan",
			//"annu", "atini",
			//"amti", "a",
			//"af", "as", "eet",
			//"sisna", "ate", "ifte",
		//"adhe", "ifaan",
			
		//	"innii", "inni", "anis","amtani","atani" ,"chisiiste", "ifna", "iste", "neerr",
                     //   "sisna", "uufan",
			//"ita", "atini", "amanii",
			//"amani", "amoo", "chisiiste",
			//"chiisna", "chisiista",
			//"eera", "e", "aniiru", "achuuf",
			//"isna", "naan",
			//"", "eeti", "eet",
			//"ani", "fi", 
			"yoo" };//stopword
	private String sufword[] = { "ootaa","otaa","ota", "an", "inni", "in","fii",
			"ffaan", "faa", "issan", "isaa", "soota", "echaan",
			"inni", "ataa", "otaa", "inin", "si",
			"si", "en", "een", "oon",
			//"oon", "innii", "aan",
			//"ichaa", "echaa", "eessu", "essi",
		  // "offaan", "n","en","an","ef","eef","oo","o","eddu","aaf"
			"istuu" };//suffix
	private String preword[] = { "hin", "kan", "issin",
			"kunniin", "kannin", "isaani", "isa", "inni",
			"kunis", "miti", "walin",
			"wal", "irra", "irraa",
			"yoo" };// "prefix",

	public String Stem(String term) throws IOException {

		for (String words : sufword)
			suffixList.add(words);
		for (String words : preword)
			preffixList.add(words);
		String stem = term;
		//Person names not to be stemmed
		String name = FileUtils.readFileToString(new File ("datadir\\Gazetteers\\personname.txt"));
		String title = FileUtils.readFileToString(new File ("datadir\\Gazetteers\\titles.txt"));
		if(name.contains(stem)||name.contains(stem.substring(0,stem.length()-1))
				|| name.contains(stem.substring(0,stem.length()-2))
						||name.contains(stem.substring(1,stem.length()))
								||name.contains(stem.substring(2,stem.length()))
								||name.equals(stem))
		{
		//stem = Remove_rep(stem);
		//stem = suffixRemover(stem);
		}
		// titles not to be stemed
		else if (title.contains(stem))
		{}
		else
		{
		stem = preffixRemover(term);
		stem = Remove_rep(stem);
		stem = suffixRemover(stem);
		stem = ChangeToSades(stem);
		}
		return stem;
	}
	private String Remove_rep(String str) {
		if (str.length() > 0) {

			char chararray[] = str.toCharArray();
			for (int i = 0; i < chararray.length - 1; i++) {
				Character temp1 = chararray[i];
				Character temp2 = chararray[i + 1];
				int s1 = temp1.charValue();
				int s2 = temp2.charValue();
				if (s1 == s2 + 3) {
					String str1 = str.substring(0, i);
					String str2 = str.substring(i + 1);
					str = str1 + str2;
					return str;
				}
			}
		}
		return str;
	}

	private boolean check(String str) {
		if (str.length() == 3) {
			int count = 0;

			char chararray[] = str.toCharArray();
			for (int i = 0; i < chararray.length; i++) {
				Character str1 = chararray[i];
                             //   if
                                        //(str1="kan") ||(str1= "wan"){
				if ((str1 == 'n') || (str1 == 'f')
						//|| (str1 == 'k') || (str1 == 'p')
						|| (str1 == 'j') || (str1 == 'l')
						
						|| (str1 == 'l'))
					count++;
			}
			if (count == 3)
				return true;
			else
				return false;

		}
		return false;

	}

	private String ChangeToSades(String str) {
		if (str.length() > 1) {

			char chararray[] = str.toCharArray();
			Character str1 = chararray[chararray.length - 1];
			int s = str1.charValue();

			// char str1 = str.substring(str.length()-1);
			String str2 = str.substring(0, str.length() - 1);
			
		}
		return str;
	}

	public String suffixRemover(String str) {
		if (str.length() < 3)
			return str;
		else {
			while (str.length() >= 3) {
				String str1;
				str1 = str.substring(str.length() - 3);
				if (suffixList.contains(str1)) {
					if (context(str1, str)) {

						str = context1(str1, str);
						return str;
					} else {

						str = str.substring(0, str.length() - 3);
						suffixRemover(str);
					}

				} else

				{
					str1 = str.substring(str.length() - 2);
					if (suffixList.contains(str1)) {
						if (context(str1, str)) {

							str = context1(str1, str);
							return str;
						} else {
							str = str.substring(0, str.length() - 2);
							suffixRemover(str);
						}
					}
					else {
						str1 = str.substring(str.length() - 1);
						if (suffixList.contains(str1)) {
							if (context(str1, str)) {
								str = context1(str1, str);
								return str;
							} else {
								str = str.substring(0, str.length() - 1);
								suffixRemover(str);
							}
						} else
							return str;
					}
				}
			}
		}
		return str;

	}

	public String preffixRemover(String str) {
		if (str.length() < 3)
			return str;
		else {
			while (str.length() >= 3) {

				String str1;
				str1 = str.substring(0, 3);
				if (preffixList.contains(str1)) {
					if (context(str1, str)) {

						str = context1(str1, str);
						return str;
					} else {
						str = str.substring(3);
						preffixRemover(str);
					}
				} else {
					str1 = str.substring(0, 2);
					if (preffixList.contains(str1)) {
						if (context(str1, str)) {

							str = context1(str1, str);
							return str;
						} else {

							str = str.substring(2);
							preffixRemover(str);
						}
					} else {
						str1 = str.substring(0, 1);
						if (preffixList.contains(str1)) {
							if (context(str1, str)) {

								str = context1(str1, str);
								return str;
							} else {

								str = str.substring(1);
								preffixRemover(str);
							}
						}

						else
							return str;
					}
				}
			}
		}
		return str;

	}

	private boolean context(String affix, String str) {

		if (str.length() > 2) {

			if (affix.equals("wal")) // the prefix K

				return true;
			else if ((affix.equals("kan")) || (affix.equals("hin"))) // the
																			// pref											// and															// L
				return true;
			else if (affix.equals("kunnin")) // the suffx nno C
				return true;

			else if (affix.equals("innis")) // the suffix NE

				return true;

			/*
			 * else if(affix.equals("\u1275")) // the suffix TE
			 * 
			 * return true;
			 * 
			 * 
			 * else if(affix.equals("\u1290\u1275")) // the suffix NTE
			 * 
			 * 
			 * return true;
			 */

			else if (affix.equals("l")) //

				return true;

			else if (affix.equals("an")) // the suffix an

				return true;

			/* else if (affix.equals("in")) // the suffix in
				return true;

			else if (affix.equals("isa")) // the suffix CE HU
				return true;

			else if (affix.equals("isaa")) // the suffix C WE
				return true;

			else if (affix.equals("istuu")) // the suffix TU
				return true;

			else if (affix.equals("awwaan")) // the suffix 
				return true;

			
			else if (affix.equals("iif")) // the suffix TWA LE

				return true;

			
			else if (affix.equals("oof")) // the suffix oof

				return true;
**/
			

			else if (affix.equals("nna")) // the suffix nna

				return true;

			else if (affix.equals("iin") || affix.equals("")) // the
																				// prefix
																				// XE
																				// and
																				// the
																				// suffix
																				// NTE

				return true;

			else if (affix.equals("kan") || affix.equals("kan")) // the
																		// suffix
																		// ME
																		// and
																		// the
																		// prefix
																		// XE

				return true;

			else if (affix.equals("kan")) // the suffix LE TE

				return true;

		}
		return false;

	}

	private String context1(String affix, String str) {
		if (str.length() > 2) {
			if (affix.equals("hin")) // the prefix hin
			{
				if (str.equals("hin")
						|| str.equals("wan")
						//|| str.equals("kan")
						//|| str.equals("off")
						//|| str.equals("gad")
						//|| str.equals("anaf")
						//|| str.equals("kunis")
						//|| str.equals("miti")
						//|| str.equals("kunis")
						//|| str.equals("kunnin")
						//|| str.equals("fan")
						|| str.equals("anaf"))

					return str;
				else if (str.length() == 3) {
					if (check(str))
						return str;
				} else {
					str = str.substring(1);
					str = preffixRemover(str);
					return str;
				}

			}
			if ((affix.equals("wal")) || (affix.equals("walin"))) // 
																		// prefix
																		// "B"
																		// and
																		// "L"
			{
				if ((str.equals("wal"))
						|| (str.equals("walin"))
						|| (str.equals("walif"))
						|| (str.equals("walim")))
					return str;
				else if (check(str))
					return str;
				else {
					str = str.substring(1);
					str = preffixRemover(str);
					return str;
				}

			} else if (affix.equals("ootta")) // the suffix otta
			{
				if (str.equals("a"))
					return str;
			}

			else if (affix.equals("aan")) // the suffix an
			{
				if (str.equals("een")
						
						|| str.equals("iin")
						|| str.equals("oon")
						|| str.equals("uun")
						|| str.equals("een")
						|| str.equals("een")
						)
					return str;
				else {
					str = str.substring(0, str.length() - 1);
					// str=ChangeToSades(str);
					str = suffixRemover(str);
					return str;
				}
			} else if (affix.equals("iis")) // the suffix L TE
			{
				if (str.equals("iis")
						|| str.equals("is")
						|| str.equals("s"))
					return str;

				else {
					str = str.substring(0, str.length() - 2);
					str = ChangeToSades(str);
					str = suffixRemover(str);
					return str;

				}
			}
			
			else if (affix.equals("oota")) // the suffix WO CE
			{
				if (str.equals("ootaa")) {
					str = str.substring(0, str.length() - 4);
					str = ChangeToSades(str);
					return str;
				} else {
					str = str.substring(0, str.length() - 3);
					// str=ChangeToSades(str);
					str = suffixRemover(str);
					return str;

				}
			} else if (affix.equals("aan")) // the suffix nno CE
			{
				if (str.equals("aan")) {
					str = str.substring(0, str.length() - 2);
					str = ChangeToSades(str);
					return str;

				} else {

					str = str.substring(0, str.length() - 2);
					str = ChangeToSades(str);
					str = suffixRemover(str);
					return str;

				}
			} else if (affix.equals("iif")) // the suffix iif
			{
				str = str.substring(0, str.length() - 2);
				str = ChangeToSades(str);
				str = suffixRemover(str);
				return str;

			} else if (affix.equals("iistuu")) // the suffix CE NE
			{
				str = str.substring(0, str.length() - 4);
				str = ChangeToSades(str);
				str = suffixRemover(str);
				return str;

			} else if (affix.equals("ffaa")) // the suffix ffaaa
			{
				str = str.substring(0, str.length() - 4);
				str = ChangeToSades(str);
				str = suffixRemover(str);
				return str;

			

			

			} else if (affix.equals("uchuu")) // the suffix 
			{
				str = str.substring(0, str.length() - 4);
				str = ChangeToSades(str);
				str = suffixRemover(str);
				return str;

			}
			
			else if (affix.equals("awwaan")) // the suffix TWA LE
			{
				if (str.equals("awwaan")) {

					str = str.substring(0, str.length() - 1);
					str = ChangeToSades(str);
					return str;
				} else {
					str = str.substring(0, str.length() - 2);
					str = ChangeToSades(str);
					str = suffixRemover(str);
					return str;

				}
			}

			else if (affix.equals("mee")) // the suffix ME
			{
				if (str.equals("merre")
						|| str.equals("meef")
						|| str.equals("meeg"))
					return str;
				else {

					str = str.substring(0, str.length() - 3);
					str = ChangeToSades(str);
					str = suffixRemover(str);
					return str;
				}
			}

		}
		return str;
	}

	public static void main(String[] args) throws Exception {
		AfaanoromoStemmer r = new AfaanoromoStemmer();
		FileInputStream fin = new FileInputStream(new File("C:/n/name.txt"));
		InputStreamReader read = new InputStreamReader(fin,"utf-8");
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
		AfaanoromoTokenizer tk = new AfaanoromoTokenizer(new StringReader(sentence));
		while ((token = tk.next()) != null) {
			rtt.write(r.Stem(token.termText()) + "\r\n");

		}
		rtt.close();
	}

}