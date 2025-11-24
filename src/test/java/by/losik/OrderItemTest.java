package by.losik;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {
    private OrderItem orderItem;

    @BeforeEach
    void setUp() {
        orderItem = new OrderItem();
    }

    @Test
    void testSetAndGetProductName() {
        String productName = "Test Product";
        orderItem.setProductName(productName);
        assertEquals(productName, orderItem.getProductName());
    }

    @Test
    void testSetAndGetQuantity() {
        int quantity = 5;
        orderItem.setQuantity(quantity);
        assertEquals(quantity, orderItem.getQuantity());
    }

    @Test
    void testSetAndGetPrice() {
        double price = 99.99;
        orderItem.setPrice(price);
        assertEquals(price, orderItem.getPrice(), 0.001);
    }

    @Test
    void testSetAndGetCategory() {
        Category category = Category.ELECTRONICS;
        orderItem.setCategory(category);
        assertEquals(category, orderItem.getCategory());
    }

    @Test
    void testSetNegativeQuantity() {
        orderItem.setQuantity(-1);
        assertEquals(-1, orderItem.getQuantity());
    }

    @Test
    void testSetZeroPrice() {
        orderItem.setPrice(0.0);
        assertEquals(0.0, orderItem.getPrice(), 0.001);
    }
}
