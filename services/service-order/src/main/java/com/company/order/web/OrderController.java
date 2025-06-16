package com.company.order.web;

import com.company.order.Order;
import com.company.order.properties.OrderProperties;
import com.company.order.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @Resource
    OrderProperties orderProperties;

    @GetMapping("/config")
    public OrderProperties getConfig(){
        return orderProperties;
    }

    @GetMapping("/create")
    public Order createOrder(@RequestParam("productId") Long productId,
                              @RequestParam("userId") String userId){
        orderService.startBusiness(productId,userId);
        return null;
    }
}
