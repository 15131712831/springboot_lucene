package com.zzy.controller;

import com.zzy.entity.Product;
import com.zzy.service.ProductService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductConteroller {

    @Resource
    private ProductService productService;

    @RequestMapping("addProduct")
    public String addProduct(Product product, MultipartFile img, HttpSession session) throws IOException {
        //2获取上传位置的目标路径（绝对路径）
        ServletContext servletContext = session.getServletContext();
        String path = servletContext.getRealPath("/image");
        //在headimage文件夹下 创建文件名与上传的文件名相同的文件
        File file = new File(path+"/"+img.getOriginalFilename());
        img.transferTo(file);
        product.setId(1);
        product.setP_image(img.getOriginalFilename());
        System.out.println("这是添加user的方法对象"+product);
        try {
            productService.add(product);
            return "ok";
        } catch (Exception e) {
            System.out.println("添加失败");
            e.printStackTrace();
            return "addProduct";
        }
    }

    @RequestMapping("queryContent")
    @ResponseBody
    public List<Product> queryContent(String content){
        List<Product> list = productService.query(content);
        for (Product product : list) {
            System.out.println(product);
        }
        return list;
    }

    @RequestMapping("addjsp")
    public String addjsp(){

        return "addProduct";
    }

    @RequestMapping("indexjsp")
    public String indexjsp(){

        return "index";
    }
}
