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
 *@ Sentence Indexing, separated by .
 *@param bb
 */
public class SentenceIndexing {

	// the directory that stores files to be indexed
	private final String dataDir ="datadir\\textdata";
        //C:\training\textdata;

	// the directory that is used to store lucene index
	private final String indexDir ="UNSEIndexes";

        //@SuppressWarnings({"deprecation", "deprecation"})
	public boolean createIndex() throws Exception {
		File dir = new File(dataDir);
		if (!dir.exists()) {
			return false;
		}
		File[] files = dir.listFiles();
           //     @SuppressWarnings("deprecation")
		Directory fsDirectory;
            fsDirectory = FSDirectory.getDirectory(indexDir, true);
		AfaanoromoAnalyzer analyzer = new AfaanoromoAnalyzer();
		IndexWriter indexWriter = new IndexWriter(fsDirectory, analyzer,
				MaxFieldLength.UNLIMITED);
		DocumentNormalization dn = new DocumentNormalization();
            for (File file : files) {
                String filePath = file.getAbsolutePath();
                IndexSentence(filePath, indexWriter);
            }
		indexWriter.optimize();
		indexWriter.close();
		return true;
	}

	public void IndexSentence(String file, IndexWriter Writerindex)
			throws IOException {
		int count = 1;
		File f = new File(file);
		String content = FileUtils.readFileToString(f);
		int start = 0;
		int aratnetib = content.indexOf(".");
		while (aratnetib > 0) {
			String arefteneger = content.substring(start, aratnetib).trim();
			Document document = new Document();
			document.add(new Field("content", arefteneger, Field.Store.YES,
					Field.Index.ANALYZED));
			try {
				Writerindex.addDocument(document);
			} catch (IOException e) {
				e.printStackTrace();
			}
			start = aratnetib + 1;
			count++;
			aratnetib = content.indexOf(".",aratnetib + 1);
                        aratnetib=aratnetib +1;
                        
		}
	}
	public  static void main (String[] args) throws Exception
	{
		SentenceIndexing si = new SentenceIndexing();
		
	}

}
