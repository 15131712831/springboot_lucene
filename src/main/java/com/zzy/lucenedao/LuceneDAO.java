package com.zzy.lucenedao;

import com.zzy.entity.Product;

import java.util.List;

public interface LuceneDAO {
    void addLucene(Product product);
    List<Product> queryContent(String content);
}
