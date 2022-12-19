package com.cici;

import com.cici.domain.Customer;
import com.cici.domain.Order;
import com.cici.repositories.CustomerRepository;
import com.cici.services.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.LinkedList;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    private Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        customer.setId(Long.valueOf("1"));
        customer.setName("testCustomer");
        customer.setOrders(new LinkedList<Order>());
    }

    @DisplayName("Test Create Customer method")
    @Test
    public void testCreateCustomer() {
        // given - precondition or setup
        given(customerRepository.save(customer)).willReturn(customer);

        Customer savedCustomer = customerService.createCustomer(customer);

        // then - verify the output
        assertThat(savedCustomer).isNotNull();
    }
}


