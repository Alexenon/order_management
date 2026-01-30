package com.example.xenon.order.controller;

import com.example.xenon.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;



}
