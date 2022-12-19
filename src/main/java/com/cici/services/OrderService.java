package com.cici.services;

import com.cici.domain.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrder(Order order);

    Order fulfilOrder(Order order);

    List<Order> getAllOrders();

    Order getOrderById(long orderId);

    void deleteOrder(long orderId);
}
