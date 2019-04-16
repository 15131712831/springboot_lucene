package com.zzy.lucenedao;

import com.zzy.entity.Product;
import com.zzy.util.LuceneUtil;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LuceneDaoImpl implements LuceneDAO {
    @Override
    public void addLucene(Product product) {
        IndexWriter indexWriter = LuceneUtil.getIndexWriter();
        Document document = new Document();
        document.add(new IntField("id",product.getId(),Field.Store.YES));
        document.add(new StringField("name",product.getName(),Field.Store.YES));
        document.add(new TextField("content",product.getContent(),Field.Store.YES));
        document.add(new DoubleField("price",product.getPrice(),Field.Store.YES));
        document.add(new StringField("p_image",product.getP_image(),Field.Store.YES));
        document.add(new StringField("reg_date",product.getReg_date()+"",Field.Store.YES));
        document.add(new StringField("address",product.getAddress(),Field.Store.YES));
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

    @Override
    public List<Product> queryContent(String content) {
        IndexSearcher indexSearcher = LuceneUtil.getIndexSearcher();
        List<Product> list = new ArrayList<Product>();
        try {
            TopDocs topDocs = indexSearcher.search(new TermQuery(new Term("content", content)), 100);
            ScoreDoc[] scoreDocs = topDocs.scoreDocs;
            for (ScoreDoc scoreDoc : scoreDocs) {
                int doc = scoreDoc.doc;
                Document document = indexSearcher.doc(doc);
                Product product = new Product();
                product.setId(Integer.valueOf(document.get("id")));
                product.setName(document.get("name"));
                product.setContent(document.get("content"));
                product.setPrice(Double.valueOf(document.get("price")));
                product.setP_image(document.get("p_image"));
                product.setReg_date(new Date());
                product.setAddress(document.get("address"));
                list.add(product);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
