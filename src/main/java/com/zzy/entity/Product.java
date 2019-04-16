package com.zzy.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private Integer id;
    private String name;
    private String content;
    private double price;
    private String p_image;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date reg_date;
    private String address;


}
