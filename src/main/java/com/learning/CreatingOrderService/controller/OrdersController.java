package com.learning.CreatingOrderService.controller;


import com.learning.CreatingOrderService.domain.Orders;
import com.learning.CreatingOrderService.service.OrderService;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrdersController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private OrderService orderService;

    @GetMapping("/getAllOrders")
    public List<Document> getOrders() {
        List<Document> Orders = mongoTemplate.findAll(Document.class, "Orders");
        return Orders;
    }

    @PostMapping("/create-Orders")
    public Orders createOrders(@RequestBody Orders orders) {
        Orders ordersCreated = orderService.CreateOrders(orders);
        return ordersCreated;

    }

    @GetMapping("/getAllOrdersByMobilenumber")
    public List<Orders> getAllOrdersByMobileNumber(@RequestParam String mobileNumber) {
        List<Orders> OrdersbyMobileNumber = orderService.getAllOrdersByMobileNumber(mobileNumber);
        return OrdersbyMobileNumber;
    }

    @GetMapping("/getAllOrdersByEmailid")
    public List<Orders> getAllOrdersByEmailId(@RequestParam String emailId) {
        List<Orders> OrdersbyEmailId = orderService.getAllOrdersByEmailId(emailId);
        return OrdersbyEmailId;
    }

    @GetMapping("/getOrderwithMaxQuantity")
    public Map<String, Integer> getOrdersWhichHasMaxQuantity() {
        Map<String, Integer> OrdersWhichHasMaxQuantity = orderService.getOrdersWhichHasMaxQuantity();
        return OrdersWhichHasMaxQuantity;

    }

    @GetMapping("/getOrderwithMaxAmount")
    public Map<String, Double> getOrdersWhichHasMaxAmount() {
        Map<String, Double> OrdersWhichHasMaxAmount = orderService.getOrdersWhichHasMaxOrderAmount();
        return OrdersWhichHasMaxAmount;
    }
}