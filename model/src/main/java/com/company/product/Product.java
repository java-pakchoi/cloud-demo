package com.company.product;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("business_product")
public class Product {

    @TableId(type =IdType.ASSIGN_ID)
    private Long id;

    /**
     * 商品价格
     */
    private BigDecimal price;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 当前商品购买的数量
     */
    private int num;

    /**
     * 商品总数
     */
    private int total;

}
