package demo.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by dongsj on 17/3/5.
 */
public class Indexer {

    private IndexWriter writer;

    public Indexer(String indexDir) throws IOException {
        writer = new IndexWriter(
                FSDirectory.open(Paths.get(indexDir)),
                new IndexWriterConfig(new StandardAnalyzer()));
    }

    public void close() throws IOException {
        writer.close();
    }

    public int index(String dataDir, FileFilter filter) throws Exception {
        File[] files = new File(dataDir).listFiles();

        for (File file : files) {
            if (!file.isDirectory() && !file.isHidden() && file.exists() && file.canRead()
                    && (null == filter || filter.accept(file))) {
                indexFile(file);
            }
        }
        return writer.numDocs();
    }

    private void indexFile(File file) throws Exception {
        System.out.println("Indexing " + file.getCanonicalPath());
        Document doc = getDocument(file);
        writer.addDocument(doc);
    }

    protected Document getDocument(File file) throws Exception {
        Document doc = new Document();
        doc.add(new Field("contents", new FileReader(file), new FieldType()));
        FieldType fileNameType = new FieldType();
        fileNameType.setStored(true);
        //fileNameType.setIndexOptions(IndexOptions.);
        doc.add(new Field("filename", file.getName(), fileNameType));

        FieldType filePathType = new FieldType();
        filePathType.setStored(true);
        doc.add(new Field("fullpath", file.getCanonicalPath(), filePathType));

        return doc;
    }

    private static class TextFilter implements FileFilter {

        @Override
        public boolean accept(File pathname) {
            return pathname.getName().toLowerCase().endsWith(".txt");
        }
    }


    public static void main(String[] args) throws IOException {
        /*if (2 != args.length) {
            throw new IllegalArgumentException("Usage: java " + Indexer.class.getName() + " <index dir> <data dir>");
        }*/

        String dataDir = "/Users/dongsj/workspace/dsj/javaSpace/javaDemo/src/main/resources/lucene/demo/data";
        String indexDir = "/Users/dongsj/workspace/dsj/javaSpace/javaDemo/src/main/resources/lucene/demo/index";

        long start = System.currentTimeMillis();
        Indexer indexer = new Indexer(indexDir);
        int numIndexed = 0;
        try {
            numIndexed = indexer.index(dataDir, new TextFilter());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            indexer.close();
        }
        long end = System.currentTimeMillis();

        System.out.println("Indxing " + numIndexed + " files took." + (end - start) + " milliseconds");
    }

}
