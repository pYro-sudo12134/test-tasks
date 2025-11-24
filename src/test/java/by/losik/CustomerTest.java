package by.losik;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer();
    }

    @Test
    void testSetAndGetCustomerId() {
        String customerId = "CUST123";
        customer.setCustomerId(customerId);
        assertEquals(customerId, customer.getCustomerId());
    }

    @Test
    void testSetAndGetName() {
        String name = "John Doe";
        customer.setName(name);
        assertEquals(name, customer.getName());
    }

    @Test
    void testSetAndGetEmail() {
        String email = "john.doe@example.com";
        customer.setEmail(email);
        assertEquals(email, customer.getEmail());
    }

    @Test
    void testSetAndGetRegisteredAt() {
        LocalDateTime registeredAt = LocalDateTime.now();
        customer.setRegisteredAt(registeredAt);
        assertEquals(registeredAt, customer.getRegisteredAt());
    }

    @Test
    void testSetAndGetAge() {
        int age = 30;
        customer.setAge(age);
        assertEquals(age, customer.getAge());
    }

    @Test
    void testSetAndGetCity() {
        String city = "New York";
        customer.setCity(city);
        assertEquals(city, customer.getCity());
    }

    @Test
    void testSetNegativeAge() {
        customer.setAge(-5);
        assertEquals(-5, customer.getAge());
    }

    @Test
    void testSetZeroAge() {
        customer.setAge(0);
        assertEquals(0, customer.getAge());
    }
}