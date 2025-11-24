package by.losik;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OrderMetricsTest {
    private List<Order> orders;

    @BeforeEach
    void setUp() {
        Customer customer1 = new Customer();
        customer1.setCustomerId("CUST001");
        customer1.setName("John Doe");
        customer1.setCity("New York");

        Customer customer2 = new Customer();
        customer2.setCustomerId("CUST002");
        customer2.setName("Jane Smith");
        customer2.setCity("Los Angeles");

        Customer customer3 = new Customer();
        customer3.setCustomerId("CUST003");
        customer3.setName("Bob Johnson");
        customer3.setCity("New York");

        OrderItem laptop = new OrderItem();
        laptop.setProductName("Laptop");
        laptop.setQuantity(1);
        laptop.setPrice(999.99);

        OrderItem book = new OrderItem();
        book.setProductName("Book");
        book.setQuantity(3);
        book.setPrice(29.99);

        OrderItem phone = new OrderItem();
        phone.setProductName("Phone");
        phone.setQuantity(2);
        phone.setPrice(499.99);

        Order order1 = new Order();
        order1.setOrderId("ORD001");
        order1.setCustomer(customer1);
        order1.setStatus(OrderStatus.DELIVERED);
        order1.setItems(Arrays.asList(laptop, book));

        Order order2 = new Order();
        order2.setOrderId("ORD002");
        order2.setCustomer(customer2);
        order2.setStatus(OrderStatus.DELIVERED);
        order2.setItems(List.of(phone));

        Order order3 = new Order();
        order3.setOrderId("ORD003");
        order3.setCustomer(customer3);
        order3.setStatus(OrderStatus.PROCESSING);
        order3.setItems(List.of(book));

        Order order4 = new Order();
        order4.setOrderId("ORD004");
        order4.setCustomer(customer1);
        order4.setStatus(OrderStatus.DELIVERED);
        order4.setItems(List.of(phone));

        orders = Arrays.asList(order1, order2, order3, order4);
    }

    @Test
    void testGetUniqueCities() {
        Set<String> cities = OrderMetrics.getUniqueCities(orders);

        assertEquals(2, cities.size());
        assertTrue(cities.contains("New York"));
        assertTrue(cities.contains("Los Angeles"));
        assertFalse(cities.contains("Chicago"));
    }

    @Test
    void testGetUniqueCitiesWithNullCustomer() {
        Order orderWithNullCustomer = new Order();
        orderWithNullCustomer.setCustomer(null);

        List<Order> ordersWithNull = List.of(orderWithNullCustomer);
        Set<String> cities = OrderMetrics.getUniqueCities(ordersWithNull);

        assertTrue(cities.isEmpty());
    }

    @Test
    void testGetTotalIncome() {
        double totalIncome = OrderMetrics.getTotalIncome(orders);

        // Order1: 999.99 * 1 + 29.99 * 3 = 999.99 + 89.97 = 1089.96
        // Order2: 499.99 * 2 = 999.98
        // Order4: 499.99 * 2 = 999.98
        // Total: 1089.96 + 999.98 + 999.98 = 3089.92
        assertEquals(3089.92, totalIncome, 0.01);
    }

    @Test
    void testGetTotalIncomeWithNoDeliveredOrders() {
        Order cancelledOrder = new Order();
        cancelledOrder.setStatus(OrderStatus.CANCELLED);
        cancelledOrder.setItems(List.of(new OrderItem()));

        double totalIncome = OrderMetrics.getTotalIncome(List.of(cancelledOrder));

        assertEquals(0.0, totalIncome, 0.001);
    }

    @Test
    void testGetMostPopularProduct() {
        String mostPopular = OrderMetrics.getMostPopularProduct(orders);

        // Book: 3 + 3 = 6 quantities
        // Phone: 2 + 2 = 4 quantities
        // Laptop: 1 quantity
        assertEquals("Book", mostPopular);
    }

    @Test
    void testGetMostPopularProductWithEmptyOrders() {
        String mostPopular = OrderMetrics.getMostPopularProduct(List.of());

        assertEquals("No products found", mostPopular);
    }

    @Test
    void testGetAverageCheckForDeliveredOrders() {
        double averageCheck = OrderMetrics.getAverageCheckForDeliveredOrders(orders);

        // Order1 total: 1089.96
        // Order2 total: 999.98
        // Order4 total: 999.98
        // Average: (1089.96 + 999.98 + 999.98) / 3 = 3089.92 / 3 = 1029.973...
        assertEquals(1029.97, averageCheck, 0.01);
    }

    @Test
    void testGetAverageCheckForDeliveredOrdersWithNoDeliveredOrders() {
        Order processingOrder = new Order();
        processingOrder.setStatus(OrderStatus.PROCESSING);

        double averageCheck = OrderMetrics.getAverageCheckForDeliveredOrders(List.of(processingOrder));

        assertEquals(0.0, averageCheck, 0.001);
    }

    @Test
    void testGetCustomerIdsWithMoreThan5Orders() {
        // Create a customer with 6 orders
        Customer frequentCustomer = new Customer();
        frequentCustomer.setCustomerId("CUST999");

        List<Order> manyOrders = Arrays.asList(
                createOrder(frequentCustomer), createOrder(frequentCustomer),
                createOrder(frequentCustomer), createOrder(frequentCustomer),
                createOrder(frequentCustomer), createOrder(frequentCustomer)
        );

        List<String> frequentCustomers = OrderMetrics.getCustomerIdsWithMoreThan5Orders(manyOrders);

        assertEquals(1, frequentCustomers.size());
        assertEquals("CUST999", frequentCustomers.get(0));
    }

    @Test
    void testGetCustomerIdsWithMoreThan5OrdersWithFewOrders() {
        List<String> frequentCustomers = OrderMetrics.getCustomerIdsWithMoreThan5Orders(orders);

        assertTrue(frequentCustomers.isEmpty());
    }

    @Test
    void testGetCustomerIdsWithMoreThan5OrdersWithNullCustomer() {
        Order orderWithNullCustomer = new Order();
        orderWithNullCustomer.setCustomer(null);

        List<String> frequentCustomers = OrderMetrics.getCustomerIdsWithMoreThan5Orders(List.of(orderWithNullCustomer));

        assertTrue(frequentCustomers.isEmpty());
    }

    private Order createOrder(Customer customer) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.DELIVERED);
        return order;
    }
}