package com.cici.controller;

import com.cici.domain.Customer;
import com.cici.domain.Order;
import com.cici.services.CustomerService;
import com.cici.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequestMapping("api/customer")
@RestController
public class CustomerRestController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> createOrder(@RequestBody Customer customer) {
        // http://localhost/api/customer/create
        return ResponseEntity.ok().body(this.customerService.createCustomer(customer));
    }

}
