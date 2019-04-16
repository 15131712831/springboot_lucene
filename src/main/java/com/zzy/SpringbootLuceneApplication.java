package com.zzy;

import com.zzy.lucenedao.LuceneDAO;
import com.zzy.lucenedao.LuceneDaoImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.zzy.dao")
public class SpringbootLuceneApplication {;;

    public static void main(String[] args) {

        SpringApplication.run(SpringbootLuceneApplication.class, args);
    }

    @Bean
    public LuceneDAO getLuceneDAO(){
        return new LuceneDaoImpl();
    }

}
