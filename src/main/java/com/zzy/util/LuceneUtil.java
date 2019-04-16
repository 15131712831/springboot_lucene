package com.zzy.util;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class LuceneUtil {
    private static Directory dir;
    private static StandardAnalyzer analyzer;
    private static IndexWriterConfig conf;
    private static Version version;
    private static IndexSearcher indexSearcher;

    static{
        try {
            version = Version.LUCENE_44;
            //声明一个文件加为索引库
            dir = FSDirectory.open(new File("D:/笔记JAVA/第三阶段笔记/后期项目/lucene/索引"));
            //创建标准分词器对象
            analyzer = new StandardAnalyzer(version);
            //索引写入器的相关配置
            conf = new IndexWriterConfig(version,analyzer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static IndexSearcher getIndexSearcher(){
        IndexReader reader = null;
        try {
            reader = DirectoryReader.open(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //创建索引搜索器
        indexSearcher = new IndexSearcher(reader);
        return indexSearcher;
    }

    public static IndexWriter getIndexWriter() {
        IndexWriter indexWriter = null;
        try {
            //参数1：索引库的目录 参数2：索引写入器的相关配置
            indexWriter = new IndexWriter(dir,conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return indexWriter;
    }

    public static void commit(IndexWriter indexWriter){
        try {
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void rollback(IndexWriter indexWriter){
        try {
            indexWriter.rollback();
            indexWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
