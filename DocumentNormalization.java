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
import java.io.*;
import java.text.NumberFormat;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class DocumentNormalization {
	public void readReplace(String directory, String oldPattern,
			String replPattern) {
		File dir = new File(directory);
		File[] files = dir.listFiles();
            for (File file : files) {
                String fname = file.getAbsolutePath();
                String line;
                StringBuilder sb = new StringBuilder();
                try {
                    FileInputStream fis = new FileInputStream(fname);
                    try (BufferedReader reader = new BufferedReader(
                            new InputStreamReader(fis))) {
                        while ((line = reader.readLine()) != null) {
                            line = line.replaceAll(oldPattern, replPattern);
                            StringBuilder append = sb.append(line).append("\n");
                        }
                    }
                    try (BufferedWriter out = new BufferedWriter(new FileWriter(fname))) {
                        out.write(sb.toString());
                    }
                } catch (IOException e) {
                    System.err.println("*** exception ***");
                }
            }

	}

	public String NormalizeQuery(String norm) {
		norm = norm.replace("!", " ");
		norm = norm.replace("A", " a");
                norm = norm.replace("B" , "b");
		norm = norm.replace("C", "c");
		norm = norm.replace("D", "d");
		norm = norm.replace("E", "e");
		norm = norm.replace("F", "f");
		norm = norm.replace("G", "g");
		norm = norm.replace("H", "h");
		norm = norm.replace("I", "i");
		norm = norm.replace("J", "j");
		norm = norm.replace("K", "k");
		norm = norm.replace("L", "l");
		norm = norm.replace("M", "m");
		norm = norm.replace("N", "n");
		norm = norm.replace("O", "o");
		norm = norm.replace("P", "p");
		norm = norm.replace("Q", "q");
		norm = norm.replace("R", "r");
		norm = norm.replace("S", "s");
		norm = norm.replace("T", "t");
		norm = norm.replace("U", "u");
		norm = norm.replace("V", "v");
		norm = norm.replace("W", "w");
		norm = norm.replace("X", "x");
		norm = norm.replace("Y", "y");
		norm = norm.replace("Z", "z");
                //norm =norm.replace("", "");
                
		
		System.out.println(norm);
		return norm;
	}

	public static void main(String[] args) throws IOException {
		DocumentNormalization norm = new DocumentNormalization();
		 
		double start = new Date().getTime();
		//norm.readReplace("datadir\\texxtdata", "::", ".");//.
		String dataDir = "C:\\Users\\Administrator\\Documents\\NetBeansProjects\\finalASQA\\datadir\\textdata";
		File dir = new File(dataDir);
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File filePath = new File(files[i].getAbsolutePath());
				String content = FileUtils.readFileToString(filePath);
				content = norm.NormalizeQuery(content);
				FileUtils.writeStringToFile(filePath, content);
			}}
			//NumberFormat form = NumberFormat.getInstance();
			//form.setMinimumFractionDigits(3);
			//form.setMaximumFractionDigits(3);
			//double end = new Date().getTime();
			//double x = (((double) end - (double) start) / 60000);
			//String time = form.format(x);
			//JOptionPane.showMessageDialog(null, "It takes:" + time   + " minute to answer this question");
       		


        }
}