package by.losik;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;
    private Customer customer;
    private List<OrderItem> items;

    @BeforeEach
    void setUp() {
        order = new Order();
        customer = new Customer();
        customer.setCustomerId("CUST001");
        customer.setName("Test Customer");

        OrderItem item1 = new OrderItem();
        item1.setProductName("Product 1");
        item1.setQuantity(2);
        item1.setPrice(10.0);

        OrderItem item2 = new OrderItem();
        item2.setProductName("Product 2");
        item2.setQuantity(1);
        item2.setPrice(20.0);

        items = Arrays.asList(item1, item2);
    }

    @Test
    void testSetAndGetOrderId() {
        String orderId = "ORD001";
        order.setOrderId(orderId);
        assertEquals(orderId, order.getOrderId());
    }

    @Test
    void testSetAndGetOrderDate() {
        LocalDateTime orderDate = LocalDateTime.now();
        order.setOrderDate(orderDate);
        assertEquals(orderDate, order.getOrderDate());
    }

    @Test
    void testSetAndGetCustomer() {
        order.setCustomer(customer);
        assertEquals(customer, order.getCustomer());
        assertEquals("CUST001", order.getCustomer().getCustomerId());
    }

    @Test
    void testSetAndGetItems() {
        order.setItems(items);
        assertEquals(items, order.getItems());
        assertEquals(2, order.getItems().size());
        assertEquals("Product 1", order.getItems().get(0).getProductName());
    }

    @Test
    void testSetAndGetStatus() {
        order.setStatus(OrderStatus.DELIVERED);
        assertEquals(OrderStatus.DELIVERED, order.getStatus());
    }

    @Test
    void testSetNullCustomer() {
        order.setCustomer(null);
        assertNull(order.getCustomer());
    }

    @Test
    void testSetEmptyItems() {
        order.setItems(List.of());
        assertTrue(order.getItems().isEmpty());
    }
}