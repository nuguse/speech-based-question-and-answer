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
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

/**
 * a rule that is used to match a given question to return the question type.
 */
public class RuleBasedQuestionTypes {
	public String Qtypes(String question) throws IOException {
		String qtypes =null;
		AfaanoromoStemmer Stem = new AfaanoromoStemmer();
		String qwords = FileUtils
				.readFileToString(new File(
						"C:\\Users\\Administrator\\Documents\\NetBeansProjects\\finalASQA\\src\\qa\\all\\afaanoromoo\\numberrelated.txt"));
		  String[]QfocuseTypes = StringUtils.split(qwords, "\n");					
			StringTokenizer st=new StringTokenizer(QfocuseTypes[0].toString(),",[]?");
                       // StringTokenizer st=new StringTokenizer(QfocuseTypes[0],",[]?");
                        
			StringTokenizer st2=new StringTokenizer(QfocuseTypes[0],",[]?");
			while (st.hasMoreElements()) {
				String x = st.nextToken();
				int ind=question.indexOf(x);
				if (ind >= 0) {
					while(st2.hasMoreElements())
					{
                                            String y=st2.nextToken();
					if (question.contains(y))
					{qtypes = "numeric";// stem.Stem(question);
					break;
					}
				}
				}
			}
			
		qwords = FileUtils//Question Words to classify a question
				.readFileToString(new File(
						"C:\\Users\\Administrator\\Documents\\NetBeansProjects\\finalASQA\\src\\qa\\all\\afaanoromoo\\personrelated.txt"));
		 QfocuseTypes= StringUtils.split(qwords, "\n");					
		 st=new StringTokenizer(QfocuseTypes[0],",[]?");
		 st2=new StringTokenizer(QfocuseTypes[1].toString(),",[]?");
		while (st.hasMoreElements()) {
			String x = st.nextToken();
			int ind=question.indexOf(x);
			if (ind>=0) {
				while(st2.hasMoreElements())
				{
					String y=st2.nextToken();
				if (    question.contains(y))
				{qtypes = "person";// stem.Stem(question);
				break;
				}
			}
			}
		}
		
		qwords = FileUtils
		.readFileToString(new File(
						"C:\\Users\\Administrator\\Documents\\NetBeansProjects\\finalASQA\\src\\qa\\all\\afaanoromoo\\placerelated.txt"));
		 QfocuseTypes = StringUtils.split(qwords, "\n");					
		st=new StringTokenizer(QfocuseTypes[0],",[]?");
		 st2=new StringTokenizer(QfocuseTypes[1],",[]?");
		while (st.hasMoreElements()) {
			String x = st.nextToken();
			int ind=question.indexOf(x);
			if (ind>=0) {
				while(st2.hasMoreElements())
				{
					String y=st2.nextToken();
				if (question.indexOf(y)>=0)
				{qtypes = "place";// stem.Stem(question);
				break;
				}
			}
			}
		}
		qwords = FileUtils
		.readFileToString(new File("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\finalASQA\\src\\qa\\all\\afaanoromoo\\timerelated.txt"));
		 QfocuseTypes = StringUtils.split(qwords, "\n");					
		st=new StringTokenizer(QfocuseTypes[0].toString(),",[]?");
		 st2=new StringTokenizer(QfocuseTypes[1].toString(),",[]?");
		while (st.hasMoreElements()) {
			String x = st.nextToken();
			int ind=question.indexOf(x);
			if (ind>=0) {
				while(st2.hasMoreElements())
				{
					String y=st2.nextToken();
				if (question.indexOf(y)>=0)
				{qtypes = "time";// stem.Stem(question);
				break;
				}
			}
			}
		}
		
		return qtypes;
	}

	public static void main(String[] arg) {
		RuleBasedQuestionTypes qt = new RuleBasedQuestionTypes();
                
	}
}
  /*class  PersonHierarchy
{
	public String 
}

*/