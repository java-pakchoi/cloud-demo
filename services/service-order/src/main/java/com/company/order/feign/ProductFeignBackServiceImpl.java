package com.company.order.feign;

import com.company.product.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 结合sentinel实现熔断，降级，限流，兜底等功能
 * 如果在sentinel中对该feign进行流控，那么也会执行这个fullback的处理逻辑
 */
@Component
public class ProductFeignBackServiceImpl implements  ProductFeignService {

    @Override
    public Product getProductById(Long productId) {
        Product product = new Product();
        product.setId(1L);
        product.setProductName("<UNK>");
        product.setPrice(new BigDecimal(100));
        product.setNum(10);
        return product;
    }

    @Override
    public void updateProduct(Long id, int total) {
        System.out.println("updateProduct");
    }
}
