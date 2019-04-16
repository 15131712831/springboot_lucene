package com.zzy.lunceneTest;

import com.zzy.util.LuceneUtil;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

public class suosou {

    @Resource
    private LuceneUtil luceneUtil;

    public static void main(String[] args) throws IOException {

    }
}
