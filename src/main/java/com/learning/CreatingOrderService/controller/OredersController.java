package com.learning.CreatingOrderService.controller;


import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OredersController {

    @Autowired
    private MongoTemplate mongoTemplate;
    @GetMapping("/getAllOrders")
    public List<Document>getOrders(){
        List<Document>Orders=mongoTemplate.findAll(Document.class,"Orders");
        return Orders;
    }
}
