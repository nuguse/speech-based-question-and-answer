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


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;

/**
 * 
 * @author nuguse N this class matches person name in a document with the help of
 *         title patterns
 * 
 */
public class PatternBasedNameExtractor {
	public HashMap<String/* candidate answers */, String/* document */> Answer(
			String doc) throws IOException {
		// System.out.println("========"+doc+"++++++++++");//test
		String answer="";
		answer=doubletitle(doc);
		String tempdoc=doc;
		tempdoc.replace(answer, "");//remove the double title name from doc
		answer=answer+singletitle(tempdoc);
		HashMap<String, String> answersentence = new HashMap<String, String>();
		if (answer == null) {
			answersentence.put("NoAnswer", doc);
			return answersentence;
		} else {
			answersentence.put(answer, doc);
			// System.out.println("========"+answersentence+"++++++++");
			return answersentence;
		}
	}

	public String patanswer(String document) throws IOException {// PatternBasedNameExtractor
																	// pans=new
																	// PatternBasedNameExtractor();
		String answer = "";
		boolean flag1 = false;
		String prevword = "";
		StringBuffer ans = new StringBuffer();
		File titlefile = new File("C:\\datadir\\Gazetteers\\titles.txt");// list
																			// of
		// titles
		String titles = FileUtils.readFileToString(titlefile);
		String ttltru = "";
		StringTokenizer doc = new StringTokenizer(document);
		int loopcount = 0;
		while (doc.hasMoreElements()) {
			String term = doc.nextToken();
			ttltru = returnterm(term);
			if (loopcount == 0) {
				prevword = term;
				loopcount = loopcount + 1;
				if (!ttltru.equals("")) {
					flag1 = true;// this is a title
					answer = ttltru;
				}
			} else {
				if (ttltru.equals("") && prevword.equals("")) {//perv word was not a title
					ttltru = returnterm(prevword + term);//check for double titles
					if (ttltru.equals("")) {
						prevword = term;
					} else {
						answer = prevword + " " + term;
					}
				}
				else if (ttltru.equals(""))
				{
					answer=prevword+" "+term;
				}
			}
		}
		return answer;
	}

	public String returnterm(String term) throws IOException {
		String newterm = "";
		File titlefile = new File("Gazetteers\\titles.txt");// list
																			// of
		// titles
		String titles = FileUtils.readFileToString(titlefile);
		StringTokenizer title = new StringTokenizer(titles, ",");
		while (title.hasMoreElements()) {
			String ttl = title.nextToken().trim();
			if (term.equals(ttl)
					|| term.substring(1, term.length()).equals(ttl))
				{
				newterm = ttl;
			break;
			}
		}
		return newterm;
	}
	
public String doubletitle(String doc) throws IOException
{
	String answer ="";
	StringBuffer ans = new StringBuffer();
	StringTokenizer words = new StringTokenizer(doc);//for duplicate titles
	while(words.hasMoreElements())
	{String word=words.nextToken();
	if(words.hasMoreElements())
	{
		String word2=words.nextToken();
		String doubletit=returnterm(word+" "+word2);
		if(!doubletit.equals(""))
		{
			if(words.hasMoreElements())
			{
				String checksingtitl=returnterm(words.nextToken());
			if(!checksingtitl.equals(""))
			{
				if (words.hasMoreElements())
					{
					answer = ans.append(doubletit+" " +checksingtitl+" "+words.nextToken()+"$").toString();
					doc.replace(answer, "");
					}
			}
			else{
					answer = ans.append(doubletit+" "+checksingtitl+"$").toString();
					doc.replace(answer, "");
				}
			}
		}
	}
		
	}
	return answer;
}
public String singletitle(String doc) throws IOException
{
	String answer ="";
	StringBuffer ans = new StringBuffer();
	File titlefile = new File("Gazetteers\\titles.txt");// list
																		// of
	// titles
	String titles = FileUtils.readFileToString(titlefile);
	StringTokenizer words = new StringTokenizer(doc);
	Boolean flag = false;
	Boolean flag2 = false;
	Boolean flag3 = false;
	int wordindex = 0;
	int word2index = 0;
	int word3index = 0;
	int word4index = 0;
	int word2length = 0;
	int word3length = 0;
	int indexloc = 0;
	int i = 0;
	while (words.hasMoreElements()) {
		StringTokenizer title = new StringTokenizer(titles, ",");
		StringTokenizer title2 = new StringTokenizer(titles, ",");
		StringTokenizer title3 = new StringTokenizer(titles, ",");
		String word = words.nextToken().trim();

		if (i == 0)// in case the title word appears at first position
		{
			wordindex = doc.indexOf(word, indexloc);
			i = 1;
		} else {
			indexloc = wordindex + 1;
			wordindex = doc.indexOf(word, indexloc);
		}
		while (title.hasMoreElements()) {
			String ttl = title.nextToken().trim();
			String x = word.substring(1, word.length());//
													
			if ((ttl.equals(word)
					|| word.substring(1, word.length()).equals(
							ttl)&& !x.equals("")) ){
				// doublettl=false;
				if (words.hasMoreElements()) {
					String word2 = words.nextToken().trim();
					word2index = doc.indexOf(word2, wordindex);
					word2length = word2.length();
					if (words.hasMoreElements()) {
						String word3 = words.nextToken().trim();
						word3index = doc.indexOf(word3, word2index);
						int tempword3index = word3index;
						word3length = word3.length();
						int tempword3lenegth = word3length;
						while (title2.hasMoreElements()) {
							String ttl2 = title2.nextToken();
							if ((ttl2.equals(word2)
									|| word2.substring(1,
											word2.length()).equals(
											ttl2))
									&& !x.equals("")) {
								flag2 = true;
								while (title3.hasMoreElements())// three
																// con.
																// titles
																// might
																// come tog
								{
									String ttl3 = title3.nextToken();
									if (ttl3.equals(word3)
											|| word3.substring(1,
													word3.length()).equals(
													ttl3)) {
										word3 = words.nextToken().trim();
										word3length = word3.length();
										word3index = doc.indexOf(word3,
												word2index);
										flag3 = true;
										break;
									}
								}
								if (flag3 == false) {
									word3index = tempword3index;
									word3length = tempword3lenegth;
									flag2 = true;
									break;
								}

							}
						}
					}
					if (flag2 == true) {
						ans.append(doc.substring((wordindex), word3index
								+ word3length));
						ans.append("$");
						answer = ans.toString().trim();
						flag = true;
						flag2 = false;
						break;
					} else if (flag == true) {
						ans.append("$");
						ans.append(doc.substring((wordindex), word2index
								+ word2length));
						answer = ans.toString().trim();
						flag = true;
						break;
					} else {
						ans.append(doc.substring((wordindex), word2index
								+ word2length));
						answer = ans.toString().trim();
						ans.append("$");
						flag = true;
						break;
					}
				} else if (flag == true) {
					ans.append("$");
					ans.append(doc.substring((wordindex), word2index
							+ word2length));
					answer = ans.toString().trim();
					flag = true;
				} else {
					ans.append(doc.substring((wordindex), word2index
							+ word2length));
					answer = ans.toString().trim();
					ans.append("$");
					flag = true;
				}
			}
		}
	}
	return answer;
}
	public static void main(String[] s) throws IOException {
		PatternBasedNameExtractor patname = new PatternBasedNameExtractor();
		 System.out.println(patname.Answer("Pirezidaanti Shimallis Abdiisaa Obbo Shimallis Obbo Shimallis Abdiisaa Afrikaa keessa Obbo Shimallis afrikaa gaanfa doktora keenya bitadhu injinarii Pirezidaantii Obbo Shimallis Ameerikaa Obbo Shimallis"));
		//System.out.println(patname.Answer("Pirezidaantii obbo  Shimallis Abdiisaa"));
	}
}
