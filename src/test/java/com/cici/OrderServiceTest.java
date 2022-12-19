package com.cici;

import com.cici.domain.Order;
import com.cici.exception.ResourceNotFoundException;
import com.cici.repositories.OrderRepository;
import com.cici.services.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order1;
    private Order order2;

    @BeforeEach
    public void setup() {
        order1 = new Order();
        order1.setId(Long.valueOf("1"));
        order1.setName("testCustomer1");
        order1.setDispached("NO");
        order1.setQuantity(100);
        order1.setPrice(BigDecimal.valueOf(100.99));
        order1.setCustIdFk(Long.valueOf(1));

        order2 = new Order();
        order2.setId(Long.valueOf("1"));
        order2.setName("testCustomer1");
        order2.setDispached("NO");
        order2.setQuantity(200);
        order1.setPrice(BigDecimal.valueOf(200.99));
        order2.setCustIdFk(Long.valueOf(2));
    }

    @DisplayName("Test Create Order method")
    @Test
    public void testCreateOrder() {
        // given - precondition or setup
        given(orderRepository.save(order1)).willReturn(order1);
        Order savedOrder = orderService.createOrder(order1);
        // then - verify the output
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull();
        //Order reference is returned
    }

    @DisplayName("Test Retrieve Orders")
    @Transactional
    @Test
    void getOrderById() {
        Optional<Order> optOrder = Optional.of(order1);
        given(orderRepository.findById(order1.getId())).willReturn(optOrder);
        Order testOrder = orderService.getOrderById(order1.getId());
        assertThat(order1.getId()).isEqualTo(testOrder.getId());
    }

    @DisplayName("Test Retrieve All Orders")
    @Transactional
    @Test
    void getAllOrders() {
        ArrayList<Order> differentCustomers = new ArrayList();
        differentCustomers.add(order1);
        differentCustomers.add(order2);
        given(orderRepository.findAll()).willReturn(differentCustomers);
        List<Order> ordersLst = orderService.getAllOrders();
        assertThat(ordersLst).isNotNull();
        assertThat(ordersLst.get(0).getId()).isEqualTo(1);
        assertThat(ordersLst.get(1).getQuantity()).isEqualTo(200);
    }

    @DisplayName("Test Update Order")
    @Transactional
    @Test
    void updateOrder() {
        order1.setQuantity(300);
        Optional<Order> optOrder = Optional.of(order1);
        given(orderRepository.findById(order1.getId())).willReturn(optOrder);
        given(orderRepository.save(order1)).willReturn(order1);
        Order updated = orderService.updateOrder(order1);
        assertThat(updated).isNotNull();
        assertThat(updated.getQuantity()).isEqualTo(300);
    }

    @DisplayName("Test Fulfill Order")
    @Transactional
    @Test
    void fulfilOrder() {
        order1.setDispached("NO");
        Optional<Order> optOrder = Optional.of(order1);
        given(orderRepository.findById(order1.getId())).willReturn(optOrder);
        given(orderRepository.save(order1)).willReturn(order1);
        Order fulfilled = orderService.fulfilOrder(order1);
        assertThat(fulfilled).isNotNull();
        assertThat(fulfilled.getDispached()).isEqualTo("YES");
    }

    @DisplayName("Test Update Fulfil Order is impossible ")
    @Transactional
        //@Test
    void updateDispachedOrder() throws ResourceNotFoundException {
        order1.setDispached("YES");
        Optional<Order> optOrder = Optional.of(order1);
        given(orderRepository.findById(order1.getId())).willReturn(optOrder);
        given(orderRepository.save(order1)).willReturn(order1);
        Order fulfilled = orderService.updateOrder(order1);
    }

}
