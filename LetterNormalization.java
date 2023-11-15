/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package qa.all.afaanoromoo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.apache.commons.io.FileUtils;
/**
 *
 * @author Nuguse
 */
public class LetterNormalization {
    
public void Normalize(String directory) throws IOException {
		File dir = new File(directory);
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			String fname = files[i].getAbsolutePath();
			String qwords = FileUtils.readFileToString(new File(fname));
			qwords = qwords.replace("!", "");
                      qwords = qwords.replace("A", "a");
			qwords = qwords.replace("B", "b");
			qwords = qwords.replace("C", "c");
			qwords = qwords.replace("D", "d");
                        qwords = qwords.replace("e", "e");
			qwords = qwords.replace("F", "f");
			qwords = qwords.replace("G", "g");
			qwords = qwords.replace("H", "h");
			qwords = qwords.replace("I", "i");
			qwords = qwords.replace("J", "j");
			qwords = qwords.replace("K", "k");
			qwords = qwords.replace("L", "l");
			qwords = qwords.replace("M", "m");
			qwords = qwords.replace("N", "n");
			qwords = qwords.replace("O", "o");
			qwords = qwords.replace("P", "p");
			qwords = qwords.replace("Q", "q");
			qwords = qwords.replace("R", "r");
			qwords = qwords.replace("S", "s");
			qwords = qwords.replace("T", "t");
			qwords = qwords.replace("U", "u");
			qwords = qwords.replace("V", "v");
			qwords = qwords.replace("W", "w");
			qwords = qwords.replace("X", "x");
			qwords = qwords.replace("Y", "y");
			qwords = qwords.replace("Z", "z");
			
			System.out.println(qwords);
			BufferedWriter out = new BufferedWriter(new FileWriter(fname));
			out.write(qwords);
			out.close();

		}
	}

	public void SpaceNormalize(String file) throws IOException {
		String lines = FileUtils.readFileToString(new File(file));
		if(lines.contains("(1)")||lines.contains("(2)")||
				lines.contains("(3)")||lines.contains("(4)"));
		{
		lines=lines.replace("(1)", "");
		lines=lines.replace("(2)", "");
		lines=lines.replace("(3)", "");
		lines=lines.replace("(4)", "");
		}
		StringTokenizer st = new StringTokenizer(lines, "\r\n");
		BufferedWriter out = new BufferedWriter(new FileWriter(
				"C:\\park.txt"));
		String nondp="";
		while (st.hasMoreElements()) {
			String line = st.nextToken();
			if (line.contains("Paarkii"))
			//if (line.contains("ትምህርት ቤት")||line.contains("ትምህርትቤት")
				//	||line.contains("ት/ቤት")||line.contains("ትምርት ቤት")||line.contains("ትምርትቤት"))
				//if (line.contains("ተራራ"))//if (nondp.contains(line)){}else
			{//nondp=nondp+line.trim();
				out.write(line.trim() + "\r\n");}

		}
		out.close();
	}

	public void count(String directory) throws IOException
	{
		File dir = new File(directory);
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			String fname = files[i].getAbsolutePath();
			String content = FileUtils.readFileToString(new File(fname));
			StringTokenizer countit=new StringTokenizer(content,"\r\n");
			//int count=0;
			//System.out.println(countit.countTokens());
			BufferedWriter out = new BufferedWriter(new FileWriter(fname));
			out.write(countit.countTokens() + "\r\n");
			while(countit.hasMoreElements())
			{String line=countit.nextToken();
			out.write(line.trim() + "\r\n");
			}
			out.close();
		}
		
	}
	public static void main(String[] str) throws IOException {
		LetterNormalization qn = new LetterNormalization();
		// qn.Normalize("C:\\new");
		//qn.SpaceNormalize("C:\\placename3.txt");
		qn.count("C:\\Gazetteers");
	}
}