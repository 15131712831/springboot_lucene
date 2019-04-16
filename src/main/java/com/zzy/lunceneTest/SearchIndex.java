package com.zzy.lunceneTest;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;

//标准分词器：八种基本类型 和 String类型不分词
//标准分词器单个分词
public class SearchIndex {
    public static void main(String[] args) throws IOException {
       //指定索引搜索中索引库的位置
        Directory dir = FSDirectory.open(new File("D:/笔记JAVA/第三阶段笔记/后期项目/lucene/索引"));
        IndexReader reader = DirectoryReader.open(dir);
        //创建索引搜索器
        IndexSearcher indexSearcher = new IndexSearcher(reader);
        //指定查询条件 和 内容
        Query query = new TermQuery(new Term("author","朱自清"));
        TopDocs topDocs = indexSearcher.search(query, 100);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            int doc = scoreDoc.doc;
            Document document = indexSearcher.doc(doc);
            System.out.println("id:"+document.get("id"));
            System.out.println("title:"+document.get("title"));
            System.out.println("content:"+document.get("content"));
            System.out.println("author:"+document.get("author"));
            System.out.println("date:"+document.get("date"));

        }

    }
}
