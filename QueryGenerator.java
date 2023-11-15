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

public class QueryGenerator {
	public String QueryExpand(String q) {
		AQAMain aqamain;
                  aqamain = new AQAMain();
	   //    q=AQAMain.jTextField1.getText();
      //  aqamain = new AQAMain();
		
		while (true)
		{
		AnalyzeQuestion anq = new AnalyzeQuestion();
		
		for (int i = 0; i < anq.PlaceQuestinParticles.length - 1; i++)
			if (q.contains(anq.PlaceQuestinParticles[i])) {
				StringBuffer x = new StringBuffer();
				x.append(q
						.substring(0, q.indexOf(anq.PlaceQuestinParticles[i])));
				int xx = anq.PlaceQuestinParticles[i].length();
				int xxx = q.indexOf(anq.PlaceQuestinParticles[i]);
				int zz = xx + xxx;
				x.append(q.substring(zz));
				q = x.toString();					
				break;
				
			}
		
		for (int i = 0; i < anq.PersonQuestionParticles.length - 1; i++)
			if (q.contains(anq.PersonQuestionParticles[i])) {
				StringBuffer x = new StringBuffer();
				x.append(q
						.substring(0, q.indexOf(anq.PersonQuestionParticles[i])));
				int xx = anq.PersonQuestionParticles[i].length();
				int xxx = q.indexOf(anq.PersonQuestionParticles[i]);
				int zz = xx + xxx;
				x.append(q.substring(zz));
				q = x.toString();// + "obbo Aaddee doctor injinar";
				
				break;
			}
		//remove question particles
		for (int i = 0; i < anq.NumberQuestionParticles.length - 1; i++)
			if (q.contains(anq.NumberQuestionParticles[i])) {
				StringBuffer x = new StringBuffer();
				x.append(q
						.substring(0, q.indexOf(anq.NumberQuestionParticles[i])));
				int xx = anq.NumberQuestionParticles[i].length();
				int xxx = q.indexOf(anq.NumberQuestionParticles[i]);
				int zz = xx + xxx;
				x.append(q.substring(zz));
				q = x.toString();
				break;
			}
		//remove question particles
		for (int i = 0; i < anq.TimeQuestionParticles.length - 1; i++)
			if (q.contains(anq.TimeQuestionParticles[i])) {
				StringBuilder x = new StringBuilder();
				x.append(q
						.substring(0, q.indexOf(anq.TimeQuestionParticles[i])));
				int xx = anq.TimeQuestionParticles[i].length();
				int xxx = q.indexOf(anq.TimeQuestionParticles[i]);
				int zz = xx + xxx;
				x.append(q.substring(zz));
				q = x.toString();
				
				break;
			}
		if( q.contains(" jedhamu"))
		    q=q.replace(" jedhamu", "");
		if (q.contains(" jedhama"))
		    q=q.replace(" jedhama", "");
		if (q.contains(" jedhamtii"))
		    q=q.replace(" jedhamtii", "");
                 if( q.contains(" gaggeeffame"))
                      q=q.replace(" gaggeeffame", "");
                 if(q.contains(" dawwatan"))
                     q=q.replace(" dawwatan", "");
                 if(q.contains(" laatee"))
                     q=q.replace(" laatee", "");
                  if(q.contains(" argamu"))
                     q=q.replace(" argamu", "");
                  if( q.contains(" dha"))
                      q=q.replace(" dha", "");
                  if( q.contains(" galan"))
                       q=q.replace(" galan", "");
                  if( q.contains(" mudee"))
                       q=q.replace(" mudee", "");
                  if( q.contains(" saamee"))
                        q=q.replace(" saamee", "");
                  if( q.contains(" erguf"))
                        q=q.replace(" erguf", "");
                  if( q.contains(" injifate"))
                        q=q.replace(" injifate", "");
		   if( q.contains("?"))
		          q=q.replace("?","");
                
	 	q=q.trim();
		return q;
		}
	}

	public static void main(String[] args) {
		QueryGenerator qg = new QueryGenerator();
		String quetion = "Ministirri Ministeera Maallaqaa eenyu jedhamu?";
		System.out.println(qg.QueryExpand(quetion));
	}

}

