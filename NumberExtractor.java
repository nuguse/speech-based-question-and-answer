/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa.all.afaanoromoo;

import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.regex.*;
/**
 *
 * @author Nuguse
 */
public class NumberExtractor {

    public HashMap<String, String>  Extractanynumber(String Sentence) {
		String numpat2 = "\\b(([lakk])|tokko|lama|sadii|afur|shan|ja'a|torba"
+ "|saddeet|sagal|kudhan|kudha|walakkaa|digdamii|soddoma|afurtam|shantama|shantama|jaatama"
+ "|Jaatama|Torbaatama|Saddeettamii|Sagaltamii|Dhibba|kumaa|Mil[y]n|Miliyoona|Miliyoona|Biliyoona|Biliyoona|Biliyoona|Triiliyoona|"
+ "Tokkoffaa|Lammaffaa|sadaffaa|Afraffaa|Shaffaa|Jahaffaa|Torbaffaa|Saddeettaffaa|Sagalffaa|Kurnaffaa|"
+ "\\d|\\.|qabxii|\\s)+(\\s+)(olii|gadi|naannoo|daqiiqaa|sa'aatii|ji'a|" +
"waggaa|sa'aatii|waggaa|ji'oota|meetii|doolaara|iskuweer kiiloo meetira|kiilomeetira|iskuweer|kiilomeetira|meetira|yuuroo|meetira|km|km|" +
")*\\b";
                
		Pattern letternumpat = Pattern.compile(numpat2);
		Matcher letternummatch = letternumpat.matcher(Sentence);
		StringBuffer numbuf = new StringBuffer();
		String st = "";
		Boolean b = letternummatch.find();
		while (b) {
			if (b) {
				numbuf.append(letternummatch.group().trim());
				numbuf.append("$");
				st = numbuf.toString();
			}
			b = letternummatch.find();
		}
		HashMap<String, String> answersentence = new HashMap<String, String>();
		if (st == null||st.toString().equals("") || st.equals(null))
		{	answersentence.put("NoAnswer", Sentence);
			return answersentence;
		} else {
			StringTokenizer stt =new StringTokenizer(st,"$");
			while(stt.hasMoreElements())
			{
				String x=stt.nextToken().toString();
				if(AQAMain.Queryword.contains(x))
						{
					st=st.replace(x, "");
						}
			}
			
			answersentence.put(st, Sentence);
		System.out.println("========"+answersentence+"++++++++");//test
			return answersentence;			
		}
	}
	public static void main(String[] arg) {
		NumberExtractor numext = new NumberExtractor();
		System.out.print(numext.Extractanynumber("Daandiin ijaaramuuf jiru dheerina kiiloo meetira 8.2 fi bal’inni meetira 25 akka ta’u ibsaniiru. Karaan mirgaa daandii kanaa kontiraaktara bara kana ji'a jalqabaatti waliigaltee kana mallatteesseen Birrii miiliyoona 115n xumuramuu isaa yaadachiisaniiru"));// 345
    }
    
}
