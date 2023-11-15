package qa.all.afaanoromoo;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader; //import java.io.Reader;
import java.io.BufferedReader;
import java.util.Properties;
import java.util.StringTokenizer;

//import org.apache.lucene.analysis.Analyzer;
//import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;


/**
 * This class is used to create index for html files
 * 
 */
public class IndexManager {

	// the directory that stores files to be indexed
	private final String dataDir = "C:\\n\\textdata";

	// the directory that is used to store lucene index
	private final String indexDir = "UNSEIndexes";
	File file = null;

		/**
	 * create index
	 */
	public boolean createIndex() throws Exception {
		File dir = new File(dataDir);
		if (!dir.exists()) {

			return false;
		}
		File[] files = dir.listFiles();
		Directory fsDirectory = FSDirectory.getDirectory(indexDir, true);
		AfaanoromoAnalyzer analyzer = new AfaanoromoAnalyzer();
		IndexWriter indexWriter = new IndexWriter (fsDirectory, analyzer, MaxFieldLength.UNLIMITED);
		for (int i = 0; i < files.length; i++) {
			String filePath = files[i].getAbsolutePath();
						addTextDocument(filePath, indexWriter);
		}
		indexWriter.optimize();
		indexWriter.close();
		return true;
	}

	/**
	 * Add one document to the lucene index
	 */
	
	public void addTextDocument(String filePath, IndexWriter Writerindex)
			throws Exception {
		File file = new File(filePath);
		FileInputStream fin = new FileInputStream(file);
		InputStreamReader rd = new InputStreamReader(fin);
		BufferedReader reader = new BufferedReader(rd);
		StringBuffer sb = new StringBuffer();
		String line = null;
		String content = null;

		while ((line = reader.readLine()) != null) {
			int sentenceStartIndex = 0;
			int fullStopIndex = line.indexOf(".");
			int lineLength = line.length();

			if (fullStopIndex >= 0) {
				while (fullStopIndex >= 0) {
					Document document = new Document();
					String beforeFullStop = line.substring(sentenceStartIndex,
							fullStopIndex);
					sb.append(beforeFullStop);
					content = sb.toString();
					sb = new StringBuffer();
					document.add(new Field("content", content, Field.Store.YES,Field.Index.ANALYZED));
					try {
						Writerindex.addDocument(document);
					} catch (IOException e) {
						e.printStackTrace();
					}
					sentenceStartIndex = fullStopIndex + 1;

					fullStopIndex = line.indexOf(".", fullStopIndex + 1);
				}
				if (!line.substring(sentenceStartIndex).isEmpty()) {
					sb.append(line.substring(sentenceStartIndex + 1));
				}
			} else {
				sb.append(line);
			}
		}

		// If anything is left in the StringBuffer, index it
		if (sb.length() > 0) {
			Document document = new Document();
			content = sb.toString();
			sb.delete(0, sb.length());
			document.add(new Field("content", content,Field.Store.YES,Field.Index.ANALYZED ));
			try {
				Writerindex.addDocument(document);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

		/**
	 * judge if the index is already exist
	 */
	public boolean ifIndexExist() {
		File directory = new File(indexDir);
		if (0 < directory.listFiles().length) {
			return true;
		} else {
			return false;
		}
	}

	public String getDataDir() {
		return this.dataDir;
	}

	public String getIndexDir() {
		return this.indexDir;
	}

	public static void main(String[] arg) throws Exception {
		IndexManager man = new IndexManager();

		if (man.createIndex())
			System.out.println("sucess");
		else
			System.out.println("failed");
	}

}