package com.company.order.service;

import com.company.order.Order;

public interface OrderService {

    void startBusiness(Long productId, String userId);

    Order createOrder(Long productId,String userId);

    void updateProductTotalAmount(Long productId,int totalAmount);
}
