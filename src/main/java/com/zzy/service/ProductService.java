package com.zzy.service;

import com.zzy.entity.Product;

import java.util.List;

public interface ProductService {
    void add(Product product);
    List<Product> query(String content);
}
