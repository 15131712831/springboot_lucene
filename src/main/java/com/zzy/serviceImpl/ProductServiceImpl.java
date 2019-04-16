package com.zzy.serviceImpl;

import com.zzy.dao.ProductDAO;
import com.zzy.entity.Product;
import com.zzy.lucenedao.LuceneDAO;
import com.zzy.service.ProductService;
import com.zzy.util.LuceneUtil;
import org.apache.lucene.index.IndexWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductDAO productDAO;
    @Resource
    private LuceneDAO luceneDAO;

    @Override
    public void add(Product product) {
        productDAO.insert(product);
        luceneDAO.addLucene(product);
    }

    @Override
    public List<Product> query(String content) {
        return luceneDAO.queryContent(content);
    }


}
