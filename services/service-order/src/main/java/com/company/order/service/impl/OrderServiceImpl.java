package com.company.order.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.company.order.Order;
import com.company.order.feign.ProductFeignService;
import com.company.order.mapper.OrderMapper;
import com.company.order.service.OrderService;
import com.company.product.Product;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    RestTemplate restTemplate;

    @Resource
    ProductFeignService productFeignService;

    @Resource
    OrderMapper orderMapper;

    @GlobalTransactional
    @Override
    public void startBusiness(Long productId, String userId) {
        log.info("开始下单业务，productId:{},userId:{}", productId, userId);
        // 扣减库存
        updateProductTotalAmount(productId, 1);
        log.info("扣减库存成功，productId:{},userId:{}", productId, userId);
        // 创建订单
        createOrder(productId, userId);
        log.info("创建订单成功，productId:{},userId:{}", productId, userId);
    }

    @Override
    public Order createOrder(Long productId, String userId) {
        Order order = new Order();
        Product productFormRemoteWithFeign = getProductFormRemoteWithFeign(productId);
        order.setTotalAmount(calculatePrice(productFormRemoteWithFeign));
        order.setUserId(userId);
        order.setNickName("测试用户");
        order.setAddress("测试地址");
        order.setProductList(Collections.singletonList(productFormRemoteWithFeign));
        orderMapper.insert(order);
        return order;
    }

    @Override
    public void updateProductTotalAmount(Long productId, int totalAmount) {
        productFeignService.updateProduct(productId,totalAmount);
    }

    private BigDecimal calculatePrice(Product productFormRemoteWithFeign) {
        if(productFormRemoteWithFeign == null){
            return new BigDecimal("0");
        }
        return productFormRemoteWithFeign.getPrice()
                .multiply(new BigDecimal(productFormRemoteWithFeign.getNum()))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public Order createOrderBlockHandler(String productId, String userId, BlockException e) {
        Order order = new Order();
        order.setId(1L);
        order.setTotalAmount(new BigDecimal("0"));
        order.setUserId("1");
        order.setNickName("限流了");
        order.setAddress("限流了");
        order.setProductList(Collections.singletonList(new Product()));
        return order;
    }

    public Product getProductFormRemoteWithFeign(Long productId) {
        return productFeignService.getProductById(productId);
    }

    public Product getProductFormRemote(String productId) {
        String url = "http://service-product/product/getProduct?id=" + productId;
        return restTemplate.getForObject(url, Product.class);
    }
}
