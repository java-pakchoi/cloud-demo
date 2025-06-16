package com.company.product.service.impl;

import com.company.product.Product;
import com.company.product.mapper.ProductMapper;
import com.company.product.service.ProductService;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductServiceImpl implements ProductService {

    @Resource
    ProductMapper productMapper;

    @Override
    public Product getProductById(Long id) {
        return productMapper.selectById(id);
    }

    @Transactional
    @Override
    public void updateProduct(Long id, int total) {
        Product productById = getProductById(id);
        productById.setTotal(productById.getTotal() - total);
        productMapper.updateById(productById);
    }
}
