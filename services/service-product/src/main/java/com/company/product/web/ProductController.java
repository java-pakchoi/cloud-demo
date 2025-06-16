package com.company.product.web;

import com.company.product.Product;
import com.company.product.service.ProductService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/getProduct")
    public Product getProduct(@RequestParam("id") Long id) {
        Product productById = productService.getProductById(id);
        return productById;
    }

    /**
     * 更新产品库存
     * @param id 产品id
     * @param total 产品库存
     */
    @GetMapping("/updateProduct")
    public void updateProduct(@RequestParam("id") Long id, @RequestParam("total") int total) {
        productService.updateProduct(id, total);
    }
}
