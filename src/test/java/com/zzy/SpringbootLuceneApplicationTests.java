package com.zzy;

import com.zzy.entity.Product;
import com.zzy.lucenedao.LuceneDAO;
import com.zzy.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootLuceneApplicationTests {

    @Resource
    private LuceneUtil luceneUtil;
    @Resource
    private LuceneDAO luceneDAO;
    @Test
    public void addDocument()  {
        IndexWriter indexWriter = luceneUtil.getIndexWriter();
        Document document = new Document();
        document.add(new StringField("id","2", Field.Store.YES));
        document.add(new StringField("title","背影2",Field.Store.YES));
        document.add(new StringField("author","朱自清",Field.Store.YES));
        document.add(new StringField("content","2你在此地不要动,我去买几个橘子",Field.Store.YES));
        document.add(new StringField("date","2019-04-11",Field.Store.YES));

        try {
            indexWriter.addDocument(document);
            indexWriter.commit();
        } catch (IOException e) {
            try {
                indexWriter.rollback();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Test
    public void testgetIndexSearcher() throws IOException {
        //创建索引搜索器
        IndexSearcher indexSearcher = luceneUtil.getIndexSearcher();
        //指定查询条件 和 内容
        Query query = new TermQuery(new Term("author", "朱自清"));
        TopDocs topDocs = indexSearcher.search(query, 100);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            System.out.println("id:" + document.get("id"));
            System.out.println("title:" + document.get("title"));
            System.out.println("content:" + document.get("content"));
            System.out.println("author:" + document.get("author"));
            System.out.println("date:" + document.get("date"));

        }
    }

    @Test
    public void testQueryAll(){
        List<Product> list = luceneDAO.queryContent("般");
        for (Product product : list) {
            System.out.println(product);
        }
    }

}
