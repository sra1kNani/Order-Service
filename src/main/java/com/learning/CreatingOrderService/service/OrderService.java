package com.learning.CreatingOrderService.service;

import com.learning.CreatingOrderService.domain.Orders;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Orders CreateOrders(Orders orders);

     List<Orders> getAllOrdersByMobileNumber(String mobileNumber);

    List<Orders>getAllOrdersByEmailId(String emailId);

    Map<String,Integer>getOrdersWhichHasMaxQuantity();

     Map<String,Double>getOrdersWhichHasMaxOrderAmount();
}
