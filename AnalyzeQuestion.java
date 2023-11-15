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
import java.util.StringTokenizer;
import org.apache.lucene.search.QueryTermVector;

public class AnalyzeQuestion {
	/*
	 * @q the question already asked by the user
	 */
	private  String QType = null;
	//public String[]
	public  String[] PlaceQuestinParticles = { "eessa","eessati","eessatti","eessarra","gara eessatti","kam","kamin","kamirra","kamif",
			"kan eessa", "eessaa irraa", "essaf", "hamma essaa", "eessan","echati","echaan" };
													 
	public  String[] PersonQuestionParticles = { "eenyu", "eenyuf", "eenyufa","eenyutu",
			"eenyuf eenyu", "eenyun", "kan eenyu", "eenyu irraa", "eenyura", "eenyuni" };
	public  String[] TimeQuestionParticles = { "yoom", "yoomi", "hamma",
			"yoomiraa", "yoomif", "hamma yoomit","yomitti","yoom","yoom fa","yoom fa'i" };
	public  String[] NumberQuestionParticles = { "meeqa","hammam","meeqan",
			"meeqaf", "meeqatu", "meeqatti","hammamitti","hammamiif", "hammamin","meeqotaa"};
	public  String[] PlaceQuestionTypes = { "Magaalaa", "Biyya", "Gaara", "Laga","nannoo",
			"haroo", "hoteela", "ardii", "Magaalaa guddicha" ,"Magaalaa gudda","Magaalaa gudditti" };
	public String[] PersonQuestionTypes = { "Pirezidaantii", "Muummicha ministeeraa","Minister Deta", 
			"Bulchaa","Atileet", "Kantibaa","Kantibaan","itti gafataaman","kan biraa" ,"Dura taa'aa"};
	public  String[] NumericQuestionTypes = { "gatii","gatiin", "fageenya", "fagenyi","baay'ina","baay'ini","dherinni","dherinaa" };
        
        public  String[]  TimeQuestionTypes = {"baraa","barri","guyya","guyyan","yeroo"};

	public String AnalyzedQuery(String q) {
		while (true) {
			//if(q.contains("eessa jedhama?")&&q.contains("magaalaa guddicha"))
			//	{QType="Place";return QType;}
			///if(q.contains("eenyu jedhama")&&q.contains("Pirezidaantii"))
			//{	QType = "Person";return QType;}
			// check if the question seeks Place Name
                    
			for (int i = 0; i < PlaceQuestinParticles.length; i++) {
				if (q.contains(PlaceQuestinParticles[i])) {
					QType = "Place";
					break;
				}
			}
			// check if the question seeks Person Name
			for (int i = 0; i < PersonQuestionParticles.length; i++) {
				if (q.contains(PersonQuestionParticles[i])) {
					QType = "Person";
					break;
				}
			}
			// check if the question seeks Time and Date Value
			for (int i = 0; i < TimeQuestionParticles.length; i++) {
				if (q.contains(TimeQuestionParticles[i])) {
					QType = "Time";
					break;
				}
			}
			// check if the question seeks Numerical Value
			for (int i = 0; i < NumberQuestionParticles.length; i++) {
				String x=NumberQuestionParticles[i];
				if (q.contains(NumberQuestionParticles[i])) {
					QType = "Number";
					break;
				}
			}
			return QType;
		}
		
	}
	public static void main(String[] args) {
		AnalyzeQuestion qtype = new AnalyzeQuestion();
		String Question = "Dorgommii seekaafaa umrii waggaa digdamii sadii gadii qopheessummaa eessatti gaggeeffame?";
		System.out.println(qtype.AnalyzedQuery(Question));
	}
}