package com.cici.services;

import com.cici.domain.Order;
import com.cici.exception.OrderDispachedException;
import com.cici.exception.ResourceNotFoundException;
import com.cici.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Order order) {
        Optional<Order> orderObj = this.orderRepository.findById(order.getId());
        // order is present and not yet dispatched then save
        if (orderObj.isPresent() && !"YES".equals(order.getDispached())) {
            Order orderUpdate = orderObj.get();
            orderUpdate.setId(order.getId());
            orderUpdate.setName(order.getName());
            orderUpdate.setQuantity(order.getQuantity());
            orderUpdate.setPrice(order.getPrice());
            orderUpdate.setDispached(order.getDispached());
            this.orderRepository.save(orderUpdate);
            return orderUpdate;
        } else {
            throw new ResourceNotFoundException("Not found or order dispatched id: " + order.getId());
        }
    }

    public Order fulfilOrder(Order order) throws ResourceNotFoundException {
        Optional<Order> orderObj = this.orderRepository.findById(order.getId());
        if (orderObj.isPresent()) {
            Order orderUpdate = orderObj.get();
            orderUpdate.setId(order.getId());
            orderUpdate.setName(order.getName());
            orderUpdate.setQuantity(order.getQuantity());
            orderUpdate.setPrice(order.getPrice());
            orderUpdate.setDispached("YES");
            this.orderRepository.save(orderUpdate);
            return orderUpdate;
        } else {
            throw new ResourceNotFoundException("Not found id: " + order.getId());
        }
    }

    public List<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    public Order getOrderById(long orderId) {
        Optional<Order> orderObj = this.orderRepository.findById(orderId);
        if (orderObj.isPresent()) {
            return orderObj.get();
        } else {
            throw new ResourceNotFoundException("Not found id: " + orderId);
        }
    }

    public void deleteOrder(long orderId) {
        Optional<Order> orderObj = this.orderRepository.findById(orderId);
        if (orderObj.isPresent()) {
            this.orderRepository.delete(orderObj.get());
        } else {
            throw new ResourceNotFoundException("Not found id: " + orderId);
        }
    }
}
