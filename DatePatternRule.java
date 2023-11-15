/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa.all.afaanoromoo;

import java.util.HashMap;

/**
 *
 * @author Nuguse
 */

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatePatternRule {
	public HashMap<String, String> matchdate(String sentence) {
//match ሰኔ 21.2001, ......
		String patt = "(\\b((hagayya)|(fulbaana)|(Onkololeessa)|(sadaasa)|(muddee)|"
				+ "(amajjii)|(guraandhala)|(bitootessa)|(ebla)|(caamsaa)|(waxabajjii|(adoolessa))\\b"
				+ "\\s*([1-9]|0[1-9]|1[0-9]|2[0-9]|30)\\s*\\b(guyya)\\b"
				+ "(\\s)*([,/.,])*(\\s)*((19|20)\\d\\d))|"
				+ "(\\b((waxabajjii)|(fulbaana)|(Onkololeessa)|([sadaasa)|(muddee])|"
				+ "(amajjii)|(guraandhala)|(bitootessa)|(ebla)|(caamsaa)|(waxabajjii)|(adoolessa))\\b"
				+ "\\s*([1-9]|0[1-9]|1[0-9]|2[0-9]|30)"
				+ "(\\s)*([/,.,])*(\\s)*((19|20)\\d\\d))|" 
				+	"(Adoolessa|fulbaana|Onkololeessa|sadaasa|muddee|"
				+ "amajjii|guraandhala|bitootessa|ebla|caamsaa|adoolessa|hagayya)(\\s*)(\\d)*"
				+"(\\s*)(wixata|kibxata|roobi|guyya|kamisa|sanbata|dilbataa|jimata)*\\s*(([1-9][0-9])\\d\\d))*";
		//match 01/02/2009, 10-10-1998......
		String patt2 = "((0[1-9]|1[0123])[-/.,](0[1-9]|[12][0-9]|30)[-/.,]"
				+ "(19|20)\\d\\d)";// to match date like 01/01/2007-sample ......
		//to match date like 1998-1999, 1900/1901, 2001,2002......
		String patt3 = "\\b((19)\\d\\d)\\b(\\s)*([,/.,])*(\\s)*\\b((20)\\d\\d)\\b";
		//to match 1991 ......
		String patt4 = "((Har'a|Bara kana|kaleessa|wixata|kibxata|roobi|kamisa|sanbata|dilbata|jimaata|darbe)\\s*|" +
				"([hangan])((([0-9][0-9])\\d\\d)|\\d+)+\\s*|[ganda]*(tokko|lama|sadi|afur|\\d*|shan|" +
				"jaha|torba|saddet|sagal|yeroodarbe|ittianuu|ofduraa)+\\s*" +
				"(waggaa|waggoota|torbee|torbanotaa|ji'a|guyya|guyyota|sa'atii|daqiiqaa|sekondii" +
				"|ji'otaa|sa'atiwwan|wixata|kibxata|roobi|kamisa|sanbata|dilbata|jimataa)\\s*" +
				"(duraa|dubaa)*\\s*|([ganda]*tokko|lama|sadi|afur|shan|" +
				"jaha|torba|saddet|sagal|kudhan|kudha)+\\s*" +
				"(wagga|waggota|torbee|torbanotaa|ji'a|daqiqaa|sekondii|guyya|guyyota|sa'atii|ji'otaa|sa'atiwwan)\\s*)+";
		Pattern regPat = Pattern.compile(patt);
		// Pattern regPat1 = Pattern.compile(patt1);
		Pattern regPat2 = Pattern.compile(patt2);
		Pattern regPat3 = Pattern.compile(patt3);
		Pattern regPat4 = Pattern.compile(patt4);
		Matcher matcher = regPat.matcher(sentence);
		Matcher matcher2 = regPat2.matcher(sentence);
		Matcher matcher3 = regPat3.matcher(sentence);
		Matcher matcher4 = regPat4.matcher(sentence);
		StringBuffer sb = new StringBuffer();
		 String st = "";
		Boolean m = matcher.find(), m2 = matcher2.find(), m3 = matcher3.find(), m4 = matcher4.find();
		while (m || m2 || m3 || m4) {// match only one date in a sentence!!!
			if (m) {
				st=st+matcher.group().trim()+"%";//separate multiple candidate answers by this symbol
				m = matcher.find();
			}
			if (m2) {
				st=st+matcher2.group().trim()+"%";
				m2 = matcher2.find();
			}
			if (m3) {
				st=st+matcher3.group().trim()+"%";
				m3 = matcher3.find();
			}
			if (m4) {
				st=st+matcher4.group().trim()+"%";
				m4 = matcher4.find();
			}
		}
		HashMap<String, String> answersentence = new HashMap<String, String>();
		if (st.equals(null)||st.toString().equals("") || st.toString().equals(null))
		{	answersentence.put("NoAnswer", sentence);
			return answersentence;
		} else {
			StringTokenizer stt =new StringTokenizer(st,"%");
			while(stt.hasMoreElements())
			{
				String x=stt.nextToken().toString();
				if(AQAMain.Queryword.contains(x))
						{
					st=st.replace(x, "");
						}
			}
			
			answersentence.put(st, sentence);

			return answersentence;			
		}
	}
	public static void main(String[] x) {
		DatePatternRule dp = new DatePatternRule();
		System.out.println(dp.matchdate("Qormaata Biyyoolessaa 12ffaa kan bara barnootaa 2013 kennuuf qophiin xumuramuu Ejensiin Madaallii Barnootaa fi Qormaata Biyyaalessaa beeksise"));
	}
}
