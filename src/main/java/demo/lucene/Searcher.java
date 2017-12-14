package demo.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.spans.SpanBoostQuery;
import org.apache.lucene.search.spans.SpanOrQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by dongsj on 17/3/6.
 */
public class Searcher {

    public static void search(String indexDir, String q) throws IOException, ParseException {
        Directory dir = FSDirectory.open(Paths.get(indexDir));
//        DirectoryReader reader = DirectoryReader.open(dir);
        IndexReader indexReader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(indexReader);
        Analyzer analyzer = new StandardAnalyzer();

        QueryParser parser = new QueryParser("url", analyzer);
        Query query = parser.parse(q);

        long start = System.currentTimeMillis();
        TopDocs hits = searcher.search(query, 10);
        long end = System.currentTimeMillis();
        System.err.println("Found " + hits.totalHits + " document(s) (in " + (end-start) + " milliseconds) that matched query '" + q + "':");

        for (ScoreDoc scoreDoc : hits.scoreDocs) {
            Document doc = searcher.doc(scoreDoc.doc);
            System.out.println(doc.toString());
        }

    }


    public static void main(String[] args) throws IOException, ParseException {
        String indexDir = "/Users/dongsj/workspace/dsj/javaSpace/javaDemo/src/main/resources/lucene/example/domain.index";
        String q = "google";

        search(indexDir, q);
    }


}
