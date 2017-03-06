package demo.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by dongsj on 17/3/6.
 */
public class Searcher {

    public static void search(String indexDir, String q) throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        DirectoryReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        Query query = new TermQuery(new Term("contents", q));
        new StandardAnalyzer();

        long start = System.currentTimeMillis();
        TopDocs hits = searcher.search(query, 10);
        long end = System.currentTimeMillis();
        System.err.println("Found " + hits.totalHits + " document(s) (in " + (end-start) + " milliseconds) that matched query '" + q + "':");

        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println(doc.get("fullpath"));
        }

    }


    public static void main(String[] args) throws IOException {
        String indexDir = "/Users/dongsj/workspace/dsj/javaSpace/javaDemo/src/main/resources/lucene/demo/index";
        String q = "lucene";

        search(indexDir, q);
    }


}
