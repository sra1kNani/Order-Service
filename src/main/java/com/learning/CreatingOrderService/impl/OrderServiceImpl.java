package com.learning.CreatingOrderService.impl;

import com.learning.CreatingOrderService.domain.Orders;
import com.learning.CreatingOrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Orders CreateOrders(Orders orders) {
        Orders CreateOrders = mongoTemplate.insert(orders,"Orders");
        return CreateOrders;
    }

    @Override
    public List<Orders> getAllOrdersByMobileNumber(@RequestParam String mobileNumber) {
        Criteria criteria =Criteria.where("mobileNumber").is(mobileNumber);
        Query query =new Query(criteria);
        query.fields().exclude("-emailid");

        List<Orders> AllOrders = mongoTemplate.find(query,Orders.class,"Orders");
        return AllOrders;

    }

    @Override
    public List<Orders> getAllOrdersByEmailId(@RequestParam String emailId) {
        Criteria criteria =Criteria.where("emailId").is(emailId);
        Query query =new Query(criteria);
        query.fields().exclude("-mobileNumber");

        List<Orders> AllOrders = mongoTemplate.find(query,Orders.class,"Orders");
        return AllOrders;

    }

    @Override
    public Map<String, Integer> getOrdersWhichHasMaxQuantity() {
        List<Orders>AllOrders = mongoTemplate.findAll(Orders.class,"Orders");

        Orders orders = AllOrders.stream().max(Comparator.comparing(Orders::getQuantity)).get();

        Map<String,Integer>ordersWhichHasMaxQuantity = new HashMap<>();
        ordersWhichHasMaxQuantity.put(orders.getEmailId(),orders.getQuantity());
        return ordersWhichHasMaxQuantity;
    }

    @Override
    public Map<String, Double> getOrdersWhichHasMaxOrderAmount() {
        List<Orders>AllOrders = mongoTemplate.findAll(Orders.class,"Orders");

        Orders orders = AllOrders.stream().max(Comparator.comparing(Orders::getAmount)).get();

        Map<String,Double>ordersWhichHasMaxAmount = new HashMap<>();
        ordersWhichHasMaxAmount.put(orders.getEmailId(),orders.getAmount());
        return ordersWhichHasMaxAmount;

    }
}
