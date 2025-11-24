package by.losik;

import java.util.Arrays;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Customer customer1 = new Customer();
        customer1.setCustomerId("CUST001");
        customer1.setName("John Doe");
        customer1.setCity("New York");

        Customer customer2 = new Customer();
        customer2.setCustomerId("CUST002");
        customer2.setName("Jane Smith");
        customer2.setCity("Los Angeles");

        OrderItem item1 = new OrderItem();
        item1.setProductName("Laptop");
        item1.setQuantity(1);
        item1.setPrice(999.99);
        item1.setCategory(Category.ELECTRONICS);

        OrderItem item2 = new OrderItem();
        item2.setProductName("Book");
        item2.setQuantity(2);
        item2.setPrice(29.99);
        item2.setCategory(Category.BOOKS);

        Order order1 = new Order();
        order1.setOrderId("ORD001");
        order1.setCustomer(customer1);
        order1.setStatus(OrderStatus.DELIVERED);
        order1.setItems(Arrays.asList(item1, item2));

        Order order2 = new Order();
        order2.setOrderId("ORD002");
        order2.setCustomer(customer2);
        order2.setStatus(OrderStatus.DELIVERED);
        order2.setItems(List.of(item1));

        List<Order> orders = Arrays.asList(order1, order2);

        System.out.println("Unique cities: " + OrderMetrics.getUniqueCities(orders));
        System.out.println("Total income: $" + OrderMetrics.getTotalIncome(orders));
        System.out.println("Most popular product: " + OrderMetrics.getMostPopularProduct(orders));
        System.out.println("Average check: $" + OrderMetrics.getAverageCheckForDeliveredOrders(orders));
        System.out.println("Customers with >5 orders: " + OrderMetrics.getCustomerIdsWithMoreThan5Orders(orders));
    }
}
