package by.losik;

import java.util.*;
import java.util.stream.Collectors;

public class OrderMetrics {

    /**
     * 1. List of unique cities where orders came from
     */
    public static Set<String> getUniqueCities(List<Order> orders) {
        return orders.stream()
                .map(Order::getCustomer)
                .filter(Objects::nonNull)
                .map(Customer::getCity)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    /**
     * 2. Total income for all completed orders
     * Assuming "completed" means DELIVERED status
     */
    public static double getTotalIncome(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .flatMap(order -> order.getItems().stream())
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    /**
     * 3. The most popular product by sales
     */
    public static String getMostPopularProduct(List<Order> orders) {
        return orders
                .stream()
                .flatMap(order -> order.getItems().stream())
                .collect(Collectors.groupingBy(
                        OrderItem::getProductName,
                        Collectors.summingInt(OrderItem::getQuantity)
                ))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No products found");
    }

    /**
     * 4. Average check for successfully delivered orders
     */
    public static double getAverageCheckForDeliveredOrders(List<Order> orders) {
        return orders
                .stream()
                .filter(order -> order.getStatus() == OrderStatus.DELIVERED)
                .map(order -> order.getItems().stream()
                        .mapToDouble(item -> item.getPrice() * item.getQuantity())
                        .sum()).toList()
                .stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    /**
     * 5. Customers who have more than 5 orders
     */
    public static List<String> getCustomerIdsWithMoreThan5Orders(List<Order> orders) {
        return orders.stream()
                .filter(order -> order.getCustomer() != null)
                .collect(Collectors.groupingBy(
                        order -> order.getCustomer().getCustomerId(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 5)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
