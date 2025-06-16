package com.company.product.service;

import com.company.product.Product;

public interface ProductService {

    Product getProductById(Long id);

    void updateProduct(Long id, int total);

}
