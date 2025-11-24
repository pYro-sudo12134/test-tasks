package by.losik;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderStatusTest {

    @Test
    void testOrderStatusValues() {
        OrderStatus[] statuses = OrderStatus.values();
        assertEquals(5, statuses.length);
        assertArrayEquals(new OrderStatus[]{
                OrderStatus.NEW,
                OrderStatus.PROCESSING,
                OrderStatus.SHIPPED,
                OrderStatus.DELIVERED,
                OrderStatus.CANCELLED
        }, statuses);
    }

    @Test
    void testOrderStatusValueOf() {
        assertEquals(OrderStatus.NEW, OrderStatus.valueOf("NEW"));
        assertEquals(OrderStatus.DELIVERED, OrderStatus.valueOf("DELIVERED"));
        assertEquals(OrderStatus.CANCELLED, OrderStatus.valueOf("CANCELLED"));
    }

    @Test
    void testOrderStatusOrdinal() {
        assertEquals(0, OrderStatus.NEW.ordinal());
        assertEquals(3, OrderStatus.DELIVERED.ordinal());
    }
}
