package demo.lucene;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Bennett Dong <br>
 * Date : 2017/12/9 <br>
 * Mail: dongshujin.beans@gmail.com <br> <br>
 * Desc:
 */
public class Example01 {

    public static void main(String[] args) {
        try {
            // 索引
            File file = new File("/Users/dongsj/workspace/dsj/javaSpace/javaDemo/src/main/resources/lucene/example/domain.index");
            IndexWriterConfig conf = new IndexWriterConfig(new StandardAnalyzer());
//            conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            IndexWriter indexWriter = new IndexWriter(FSDirectory.open(file.toPath()), conf);
            indexDocs(indexWriter);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // 查询

    }


    // IndexWriter
    private static void indexDocs(IndexWriter indexWriter) throws IOException {
        File file = new File("/Users/dongsj/workspace/dsj/javaSpace/javaDemo/src/main/resources/lucene/example/domain.data");
        FileReader in = new FileReader(file);
        BufferedReader reader = new BufferedReader(in);
        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
            if (StringUtils.isEmpty(line) || StringUtils.startsWith(line, "#"))
                continue;
            System.out.println("adding " + line);
            String[] parts = line.split("\\|");

            Document document = new Document();
            // domain
//            FieldType domainField = new FieldType();
//            domainField.setStored(true);
//            document.add(new Field("domain", parts[0], domainField));
            document.add(new TextField("domain", parts[0], Field.Store.YES));
            // url
//            FieldType urlField = new FieldType();
//            urlField.setStored(true);
//            document.add(new Field("url", parts[1], urlField));
            document.add(new TextField("url", parts[1], Field.Store.YES));
            // name
//            FieldType nameField = new FieldType();
//            nameField.setStored(true);
//            document.add(new Field("name", parts[2], nameField));
            document.add(new TextField("name", parts[2], Field.Store.YES));
            // keywords
//            FieldType keywordsField = new FieldType();
//            keywordsField.setStored(true);
//            document.add(new Field("keywords", parts[3], keywordsField));
            document.add(new TextField("keywords", parts[3], Field.Store.YES));

            indexWriter.addDocument(document);

        }
        reader.close();
        in.close();

        System.out.println("nums: " + indexWriter.numDocs());
        indexWriter.close();

//        indexWriter.addDocument();
    }

}
