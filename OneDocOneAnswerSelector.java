/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa.all.afaanoromoo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

/**
 * this module is used to select one best answer(if any) from a matched document
 * @author user
 * 
 */
public class OneDocOneAnswerSelector {
	 // hashmap<sentence, hashmap<answer, number>>
	public HashMap<String, HashMap<String, Integer>> OneNameOneDoc(
			HashMap<String/* answer */, String/* Sentence */> OneNameinDoc,
			String Qwords) throws IOException {
		System.out.println("====candidate answers + a document"+OneNameinDoc+"======");
		AfaanoromoStemmer r = new AfaanoromoStemmer();// stem document and query
		StopStemmer stwr = new StopStemmer();
		String bb = OneNameinDoc.values().toString();// document
		// Normalise the question words
		if (Qwords.contains("?"))
			Qwords = Qwords.replace("?", "");// question mark not to be searched
		int QueryTerms = 0;
		StringTokenizer qwrd = new StringTokenizer(Qwords);
		String q = "";
		while (qwrd.hasMoreElements()) {
			QueryTerms = QueryTerms + 1;
			String tk = qwrd.nextToken();
			if (!stwr.isStop(tk));
				//;
			q = q + r.Stem(tk) + " ";
		}
		// Normalise the answer document
		StringTokenizer ss = new StringTokenizer(bb, "[] ");
		String s = "";
		while (ss.hasMoreElements()) {
			String tk = ss.nextToken();
			if (!stwr.isStop(tk))
				s = s + r.Stem(tk) + " ";
		}
		
		HashMap<String/* Answer */, Integer/* Distance */> Selectedanswer = new HashMap<>();
		Boolean notrelevant=false;//checks word proximity
		StringTokenizer  orignialnames=new StringTokenizer(OneNameinDoc.keySet().toString(), "$[]");
		StringTokenizer names = new StringTokenizer(OneNameinDoc.keySet()
				.toString(), "$[]");
		int qwordcount = 0;
		StringTokenizer qq = new StringTokenizer(q);
		String uniqueword="";// number of unique matched query terms
		String prevword="";
		while(qq.hasMoreElements())
		{
			StringTokenizer senttok = new StringTokenizer(s);
			String qqq=qq.nextToken();
			
			while(senttok.hasMoreElements())
			{int between=0;
				String sentok=senttok.nextToken();
				if(sentok.equals(qqq)){
					if(!prevword.equals(""))
						{ between=Math.abs((s.indexOf(prevword)-s.indexOf(qqq)));}
					if(prevword.equals("")){
						if(uniqueword.contains(sentok)){}
						else
						{
						qwordcount = qwordcount + 1;
						uniqueword=uniqueword+qqq;
						prevword=qqq;
						break;
						}
					}
					else if(between>500){
						notrelevant=true;
						break;
					}
					
					else if(uniqueword.contains(sentok)){break;}
			else
			{
			qwordcount = qwordcount + 1;
			uniqueword=uniqueword+qqq;
			prevword=qqq;
			break;
			}
			}
		}
			}
		JOptionPane.showMessageDialog(null, "Number of query term is " +QueryTerms);
		JOptionPane.showMessageDialog(null, "Number of querycount term is " +qwordcount);
		Boolean relevantdoc=false;
		if(qwordcount==QueryTerms)
			relevantdoc=true;
		else if(QueryTerms<3&&qwordcount==QueryTerms)
			relevantdoc=true;
		else if (QueryTerms<3&&qwordcount!=QueryTerms)
			relevantdoc=false;
		else if(QueryTerms<=6&&qwordcount>=QueryTerms/3)
			relevantdoc=true;
		else if(QueryTerms>=7&&qwordcount>=QueryTerms/4)
			relevantdoc=true;
		if(!notrelevant&&relevantdoc)
		{
			String origname="";
			String Orignames="";
			while(orignialnames.hasMoreElements())
			{
				String nm=orignialnames.nextToken();
				Orignames=Orignames+nm+"$";
				StringTokenizer checkdblnames=new StringTokenizer(nm," ");
				String newnam="";
				while(checkdblnames.hasMoreElements())
				{
					newnam=newnam.trim()+" "+r.Stem(checkdblnames.nextToken())+" ";
				}
				origname=origname+newnam.trim()+"$";
			}
			names = new StringTokenizer(origname, "$");
			orignialnames=new StringTokenizer(Orignames,"$");
			while (names.hasMoreElements()) {//for every answer particles in a document
			String name = names.nextToken();
                        if(!name.startsWith(" ")||!name.startsWith(""))
                         {
			int dist1 = 0, compare;// distance b/n query term and candidate answer
			 qq = new StringTokenizer(q);
			while (qq.hasMoreElements()) {//for every question terms
				String qwrds = qq.nextToken();
				StringTokenizer senttok = new StringTokenizer(s);
			int updateindexloc=0;//may be query terms repeated in a document
				while (senttok.hasMoreElements()) {//for every term in a document
					String sentok = senttok.nextToken();
					if (sentok.equals(qwrds) || qwrds.contains(sentok)
							|| sentok.contains(qwrds))// document containing query terms
					{
						int x=s.indexOf(sentok,updateindexloc);
						//x=s.indexOf(sentok,0)+s.indexOf(sentok,updateindexloc);
						int y=s.indexOf(name);
						//int treshhold=Math.abs((x-y));
						if (x > y) {
							int namelen=name.length();
							compare = x-(y + namelen);
						} else
							compare = y-(x+sentok.length());
						dist1 = dist1 + compare;
					}
					updateindexloc=updateindexloc+sentok.length();
				}
			}
			Selectedanswer.put(orignialnames.nextToken(), dist1);
		}
		}
	}
		System.out.println("****answer distance*****"+Selectedanswer+"************");// test
		if ( qwordcount < QueryTerms / 3) {
			qwordcount = 0;
		}
		if (qwordcount > (3 * (QueryTerms) / 4))
			qwordcount = qwordcount * 10;
		else if (qwordcount > (2 * (QueryTerms) / 3))
			qwordcount = qwordcount * 8;
		else if (qwordcount > ((QueryTerms) / 2))
			qwordcount = qwordcount * 4;
		else {
		}
		// names
		Iterator it = Selectedanswer.keySet().iterator();
		int min = 0;// minimum distance from answer particle to query term
		String correctanswer = "";
		// select the best answer which has little distance with query words in
		// a single sentence
		while (it.hasNext()) {
			String key = it.next().toString();
			if (min == 0) {
				min = Selectedanswer.get(key);
				correctanswer = key;
			} else if (min > Selectedanswer.get(key)) {
				min = Selectedanswer.get(key);
				correctanswer = key;
			}
		}
		// return the selected best answer
		HashMap<String/* Sentence */, HashMap<String/* answer */, Integer/*
																	 * number of
																	 * query
																	 * terms
																	 */>> sel;
            sel = new HashMap<>();
		HashMap<String, Integer> Selectedanswers = new HashMap<>();
		if (qwordcount > 0&&!notrelevant) {
			Selectedanswers.put(correctanswer, qwordcount);
			sel.put(bb, Selectedanswers);// replaced s--->Reminder
		} else {
			Selectedanswers.put("NoAnswer", qwordcount);
			sel.put(bb, Selectedanswers);// replaced s--->Reminder
		}
	System.out.println("++++Best Answer + Document"+sel+"++++");
		return sel;
	}
	public void Distance(String x, String y) throws IOException {
		AfaanoromoStemmer r = new AfaanoromoStemmer();
		StringTokenizer st = new StringTokenizer(y);
		while (st.hasMoreElements()) {
			System.out.print(r.Stem(st.nextToken()) + " ");
		}
		if (y.contains(x)) {
			System.out.println(y.indexOf(x));
		}
	}
	public static void main(String[] xx) throws IOException {
		OneDocOneAnswerSelector one = new OneDocOneAnswerSelector();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("8 qabxii 2 kilo$ birrii miiliyoona 115$ m$ 25",
"Daandiin ijaaramuu qabu dheerinni isaa kiiloo meetira 8.2 fi bal'inni isaa meetira 25 qaba."+
"Birrii miiliyoona 115." );
		
one.OneNameOneDoc(map, "bal'inni daandii ijaaramuu qabu meeqa?");
	}
}
