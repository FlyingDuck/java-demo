package demo.lucene.test;

import junit.framework.TestCase;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import java.io.IOException;

/**
 * Created by dongsj on 17/3/8.
 */
public class IndexTest extends TestCase {

    protected String[] ids = new String[] {
            "1", "2"
    };
    protected String[] unindexed = new String[] {
            "Netherlands", "Italy"
    };
    protected String[] unstored = new String[] {
            "Amsterdam has lots of bridges",
            "Venice has lots of canals"
    };
    protected String[] text = new String[] {
            "Amsterdam", "Venice"
    };


    private Directory directory;

    protected void setUp() {
        directory = new RAMDirectory();
    }

    private IndexWriter getWriter() throws IOException {
        return new IndexWriter(directory, new IndexWriterConfig(new StandardAnalyzer()));
    }

    protected long getHitCount(String fieldname, String searchString) throws IOException {
        IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(directory));
        Term term = new Term(fieldname, searchString);
        Query query = new TermQuery(term);
        long hitCount = searcher.search(query, 1).totalHits;
        return hitCount;
    }

    public void testIndexWriter() throws IOException {
        IndexWriter writer = getWriter();
        for (int i=0; i<ids.length; i++) {
            Document document = new Document();
            FieldType idField = new FieldType();
            idField.setStored(true);
            idField.setIndexOptions(IndexOptions.NONE);
            document.add(new Field("id", ids[i], idField));

            FieldType countryField = new FieldType();
            countryField.setStored(true);
            countryField.setIndexOptions(IndexOptions.NONE);
            document.add(new Field("country", unindexed[i], countryField));

            FieldType contentField = new FieldType();
            contentField.setStored(false);
            contentField.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            document.add(new Field("content", unstored[i], contentField));

            FieldType cityField = new FieldType();
            cityField.setStored(true);
            cityField.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            document.add(new Field("city", text[i], cityField));

            writer.addDocument(document);
        }

        assertEquals(ids.length, writer.numDocs());

        writer.close();
    }

    public void testDeleteBeforeOptimize() throws IOException {
        index();

        IndexWriter writer = getWriter();

        assertEquals(2, writer.numDocs());
        writer.deleteDocuments(new Term("id", "1"));
        writer.commit();
//        assertTrue(writer.hasDeletions());
        assertEquals(2, writer.maxDoc());
        assertEquals(1, writer.numDocs());
        writer.close();
    }



    private void index() throws IOException {
        IndexWriter writer = getWriter();
        for (int i=0; i<ids.length; i++) {
            Document document = new Document();
            FieldType idField = new FieldType();
            idField.setStored(true);
            idField.setIndexOptions(IndexOptions.NONE);
            document.add(new Field("id", ids[i], idField));

            FieldType countryField = new FieldType();
            countryField.setStored(true);
            countryField.setIndexOptions(IndexOptions.NONE);
            document.add(new Field("country", unindexed[i], countryField));

            FieldType contentField = new FieldType();
            contentField.setStored(false);
            contentField.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            document.add(new Field("content", unstored[i], contentField));

            FieldType cityField = new FieldType();
            cityField.setStored(true);
            cityField.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
            document.add(new Field("city", text[i], cityField));

            writer.addDocument(document);
        }

        assertEquals(ids.length, writer.numDocs());

        writer.close();
    }







}
