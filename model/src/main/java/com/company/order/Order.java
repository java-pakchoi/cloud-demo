package com.company.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.company.product.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@TableName("business_order")
public class Order {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户昵称
     */
    private String nickName;

    /**
     * 用户地址
     */
    private String address;

    /**
     * 当前订单的商品列表
     */
    @TableField(exist = false)
    private List<Product> productList;
}
