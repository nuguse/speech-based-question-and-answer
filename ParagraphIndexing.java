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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


/**
 *@ Sentence Indexing, separated by ።
 * 
 * @param bb
 */
public class ParagraphIndexing {
	// the directory that stores files to be indexed
	private final String dataDir = 
                "C:\\training\\textdata";
	// the directory that is used to store lucene index
	private final String indexDir = "UNPAIndexes";

	public boolean createIndex() throws Exception {
		File dir = new File(dataDir);
		if (!dir.exists()) {
			return false;
		}
		File[] files = dir.listFiles();
		Directory fsDirectory = FSDirectory.getDirectory(indexDir, true);
		AfaanoromoAnalyzer analyzer = new AfaanoromoAnalyzer();
		IndexWriter indexWriter = new IndexWriter(fsDirectory, analyzer,
				MaxFieldLength.UNLIMITED);
		DocumentNormalization dn = new DocumentNormalization();
		// dn.readReplace(dataDir, "::", ".");
		
		for (int i = 0; i < files.length; i++) {
			String filePath = files[i].getAbsolutePath();
			
			TextIndexPassage(filePath, indexWriter);
		}
		indexWriter.optimize();
		indexWriter.close();
		return true;
	}
	public void TextIndexPassage(String file, IndexWriter Writerindex)
			throws IOException {
		int count = 1;
		File f = new File(file);
		String content = FileUtils.readFileToString(f);
		int start = 0;
		int numberofsentence = 0;
		int aratnetib = content.indexOf(".");
		String arefteneger = "";
		Document document = new Document();
		if (aratnetib<0)
			{document.add(new Field("content", arefteneger, Field.Store.YES,
					Field.Index.ANALYZED));
			arefteneger = "";
			try {
				Writerindex.addDocument(document);
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		else{
		while (aratnetib > 0) {
			numberofsentence = numberofsentence + 1;
			arefteneger = arefteneger
					+ content.substring(start, aratnetib).trim();
			if (numberofsentence > 2) {
				document.add(new Field("content", arefteneger, Field.Store.YES,
						Field.Index.ANALYZED));
				arefteneger = "";
				try {
					Writerindex.addDocument(document);
				} catch (IOException e) {
					e.printStackTrace();
				}
				document = new Document();
				numberofsentence = 0;
			}
			start = aratnetib + 1;
			count++;
			aratnetib = content.indexOf(".", aratnetib + 1);
		}
		if (!arefteneger.equals("")) {
			document.add(new Field("content", arefteneger, Field.Store.YES,
					Field.Index.ANALYZED));
			arefteneger = "";
			try {
				Writerindex.addDocument(document);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}

	public static void main(String[] args) throws Exception {
		ParagraphIndexing si = new ParagraphIndexing();
		long start = new Date().getTime();
		si.createIndex();
		long end = new Date().getTime();
		NumberFormat form = NumberFormat.getInstance();
		form.setMinimumFractionDigits(3);
		form.setMaximumFractionDigits(3);
		double x = (((double) end - (double) start) / 60000);
		String time = form.format(x);
		JOptionPane.showMessageDialog(null, "It takes:" + time
				+ " minute to answer this question");
	}

}
