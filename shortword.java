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

import java.util.HashMap;
import java.util.Map;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;

public class shortword {
	private HashMap<Character, ArrayList> LookupTable1 = new HashMap<Character, ArrayList>();
	private HashMap<Character, String> LookupTable2 = new HashMap<Character, String>();
	ArrayList<String> word = new ArrayList<String>();
	private static final String word1[] = { "Dr.",
			"Onk", "Inj","Sad" };
	private static final String word2[] = { "Mil",
			"Ado" };
	private static final String word3[] = { "Hag",
			"Bil" };
	private static final String word4[] = { "Mir",
			"Fkn" };

	public void replace(String str1, String str2) {
		str1 = str2;
		// return str2;
	}

       
	public static void main(String[] args) throws Exception {
		shortword rdd = new shortword();
		// rdd.create();
		FileInputStream fin = new FileInputStream(new File("c:/ii.txt"));
		InputStreamReader read = new InputStreamReader(fin);
		BufferedReader rd = new BufferedReader(read);
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		String sentence = sb.toString();
		sentence = sentence.trim();
		String newline = System.getProperty("line.separator");

		StringTokenizer tokens = new StringTokenizer(sentence,
				"");
	//"\u1361\u1362\u1363\u1364\u1365\u1366\u0020\u0021"); :: deleted above
		FileOutputStream fout = new FileOutputStream(new File("c:/kio2.txt"));
		OutputStreamWriter rt = new OutputStreamWriter(fout);
		BufferedWriter rtt = new BufferedWriter(rt);

		while (tokens.hasMoreTokens()) {
			String kk = tokens.nextToken().toString();

		

			

		}
		rtt.close();

	}

    String Expander(String term) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}