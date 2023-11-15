/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa.all.afaanoromoo;

/**
 *
 * @author Nuguse
 */

import java.util.Vector;
import java.io.*;
import org.apache.lucene.analysis.*;

/**
 * A stemmer for Amharic words.
 */
public class StopStemmer {
	private final Vector<String> stoppreffixList = new Vector<>();
	private final Vector<String> CstopaffixList = new Vector<>();
	private final Vector<String> stopsuffixList = new Vector<>();
	private final Vector<String> stopWordList = new Vector<>();
	private final String[] Stopword;
	// private String
	// stopsufword[]={"\u12ED\u1271","\u12EB\u12CA","\u1279","\u121B","\u12EB","\u1278\u1201","\u129E\u1279","\u12A5\u1295\u12F2","\u127D\u1293","\u1278","\u127B","\u127D","\u1275","\u12CE\u127D","\u1295","\u12E8","\u12EC","\u12CE","\u1205","\u1238","\u12CB","\u127D\u1295","\u127D\u1201","\u127D\u12CD","\u1278\u12CD","\u1276","\u12CD","\u1271","\u12A5","\u127D","\u12ED","\u12A9","\u1201","\u12AD","\u1205","\u123D","\u129D","\u1290\u1275","\u12CA\u1275","\u121D","\u1271","\u1273","\u1293","\u12CA","\u129B","\u12EB","\u12A6","\u1246","\u12CB\u120D","\u1260\u1275","\u129E\u127D","\u1208\u1275","\u1209\u1271","u12CD\u12EB\u1295"};
	private String Cstopaffword[] = { "nu", "Koos", "fuullee",
			"hoggas" };
	private String stopsufword[] = { "Ani", "gama","Isa","isiniifillee", "jiru", "nuu", "silas", "too",
                         // "Anis", "Isaa", "isiniifis" ,"Koof", "nuun" ,"simmoo",
                       //"Ati", "gararraa" ,"isaaf","Isiniin", "kaa'uun" ,"Koos", "nuy" ,"sinitti", "ture" 
        };// "\u1275",
	private String stoppreword[] = { "Kiyyas", "nuu", "silaa", "tokko",
                       // "Ani", "gama","Isa","isiniifillee", "jirutti", "Koo", "nuuf", "silas", "too",
                        //  "Anis", "gara", "Isaa", "isiniifis", "ka" ,"Koof", "nuun" ,"simmoo" ,
                     //  "Ati", "gararraa" ,"isaaf","Isiniin", "kaa'uun" ,"Koos", "nuy" ,"sinitti", "ture"
        };

    public StopStemmer() {
        this.Stopword = new String[]{"kan", "Adda", "Ati", "gararraa", "isaaf",
            //"Isiniin", "kaa'uun", "Koos", "nuy", "ture"
        };
    }

	public boolean isStop(String str) {
		create();
		if (stopWordList.contains(str))
			return true;
		else {
			String str1 = CaffixRemover(str);
			if (stopWordList.contains(str1))
				return true;
			else {
				String stem = stopPreffixRemover(str);
				stem = stopSuffixRemover(stem);
				stem = ChangeToSades(stem);
				if (stopWordList.contains(str))
					return true;
				else if (stem.equals("koo")
						|| stem.equals("koofille")
						|| stem.equals("koofket")
						)
					return true;
				else
					return false;
			}
		}
	}

	private void create() {
		for (String words : Stopword)
			stopWordList.add(words);
		for (String words : Cstopaffword)
			CstopaffixList.add(words);
		for (String words : stoppreword)
			stoppreffixList.add(words);
		for (String words : stopsufword)
			stopsuffixList.add(words);
	}

	public String CaffixRemover(String str) {
		if (str.length() > 2)

		{

			String str1 = str.substring(str.length() - 1);

			if (CstopaffixList.contains(str1)) {
				if (str1.equals("know")) {

					if (str.equals("kes"))
						return str;
				} else {

					str = str.substring(0, str.length() - 1);
					CaffixRemover(str);
				}
			} else {

				str1 = str.substring(0, 1);
				if (str1.equals("who")) {
					if (str.equals("where")
							|| str.equals("how"))
						return str;
				} else if (CstopaffixList.contains(str1)) {

					str = str.substring(1);
					CaffixRemover(str);
				}

				else
					return str;
			}
			return str;
		}
		return str;
	}

	private String ChangeToSades(String str) {
		if (str.length() > 1) {

			char chararray[] = str.toCharArray();
			Character str1 = chararray[chararray.length - 1];
			int s = str1.charValue();

			// char str1 = str.substring(str.length()-1);
			String str2 = str.substring(0, str.length() - 1);
			if (('z' <= s) && (s <= 'y')) {
				str1 = 'z';
				str = str2 + str1;
				return str;
			} else if (('z' <= s) && (s <= 'y')) {
				str1 = 'y';
				str = str2 + str1;
				return str;

			} 

		}
		return str;
	}

	public String stopSuffixRemover(String str) {
		if (str.length() < 3)
			return str;
		else {
			while (str.length() >= 3) {
				String str1;
				str1 = str.substring(str.length() - 3);
				if (stopsuffixList.contains(str1)) {

					str = str.substring(0, str.length() - 3);
					stopSuffixRemover(str);
				}

				else

				{
					str1 = str.substring(str.length() - 2);
					if (stopsuffixList.contains(str1)) {

						str = str.substring(0, str.length() - 2);
						stopSuffixRemover(str);
					}

					else {
						str1 = str.substring(str.length() - 1);
						if (stopsuffixList.contains(str1)) {

							str = str.substring(0, str.length() - 1);
							stopSuffixRemover(str);
						} else

							return str;

					}
				}
			}
		}
		return str;

	}

	public String stopPreffixRemover(String str) {
		if (str.length() < 3)
			return str;
		else {
			while (str.length() >= 3) {

				String str1;
				str1 = str.substring(0, 3);
				if (stoppreffixList.contains(str1)) {
					str = str.substring(3);
					stopPreffixRemover(str);

				} else {
					str1 = str.substring(0, 2);
					if (stoppreffixList.contains(str1)) {

						str = str.substring(2);
						stopPreffixRemover(str);
					}

					else {
						str1 = str.substring(0, 1);
						if (stoppreffixList.contains(str1)) {

							str = str.substring(1);
							stopPreffixRemover(str);
						}

						else
							return str;
					}
				}
			}
		}
		return str;

	}

	public static void main(String[] args) throws Exception {
		StopStemmer r = new StopStemmer();
		FileInputStream fin = new FileInputStream(new File("c:/n/name.txt"));
		InputStreamReader read = new InputStreamReader(fin ,"UTF-8");
		BufferedReader rd = new BufferedReader(read);
		FileOutputStream fout = new FileOutputStream(new File("c:/kio2.txt"));
		OutputStreamWriter rt = new OutputStreamWriter(fout,"UTF-8");
		BufferedWriter rtt = new BufferedWriter(rt);
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		String sentence = sb.toString();
		Token token = null;
		//
		AfaanoromoTokenizer tk = new AfaanoromoTokenizer(new StringReader(sentence));
		while ((token = tk.next()) != null) {
			if (!r.isStop(token.termText())) {
				rtt.write(token.termText() + " ");

			}
		}
		rtt.close();
	}

}
