package com.cici.controller;

import com.cici.domain.Order;
import com.cici.exception.ResourceNotFoundException;
import com.cici.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequestMapping("api/order")
@RestController
public class OrderRestController {

    @Autowired
    Environment environment;

    @Autowired
    OrderService orderService;

    @RequestMapping("/environment")
    public String getEnvironment() throws UnknownHostException {
        // http://localhost/api/order/environment
        String datePattern ="MM/dd/YYYY HH:mm:ss";
        DateTimeFormatter dtf= DateTimeFormatter.ofPattern(datePattern);
        LocalDateTime now= LocalDateTime.now();
        String hostName= InetAddress.getLocalHost().getHostName();
        String port= environment.getProperty("local.server.port");
        return dtf.format(now)+" Host: "+ hostName +"<span style\"color:red;\">:</span> " + port;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok().body(orderService.getAllOrders());
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok().body((this.orderService.createOrder(order)));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        // localhost/api/order/get/{2}
        return ResponseEntity.ok().body(orderService.getOrderById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable long id, @RequestBody Order order) {
        order.setId(id);
        return ResponseEntity.ok().body(this.orderService.updateOrder(order));
    }

    @PutMapping("/fulfil/{id}")
    public ResponseEntity<Order> fulfilOrder(@PathVariable long id, @RequestBody Order order) {
        return ResponseEntity.ok().body(this.orderService.fulfilOrder(order));
    }

    @DeleteMapping("/delete/{id}")
    public HttpStatus deleteOrder(@PathVariable long id) {
        this.orderService.deleteOrder(id);
        return HttpStatus.OK;
    }

}
