package com.company.order.feign;

import com.company.product.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 这里的fallback的兜底操作，在sentinel对该openfeign请求进行流控时，也会调用它进行流控处理
 */
@FeignClient(value = "service-product",fallback = ProductFeignBackServiceImpl.class)
public interface ProductFeignService {

    @GetMapping("/product/getProduct")
    Product getProductById(@RequestParam("id") Long productId);

    @GetMapping("/product/updateProduct")
    void updateProduct(@RequestParam("id") Long id,@RequestParam("total") int total);
}
