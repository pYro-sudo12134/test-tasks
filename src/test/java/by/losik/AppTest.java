package by.losik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void testMainMethodRunsWithoutErrors() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    @Test
    void testSampleDataCreation() {
        Customer customer1 = new Customer();
        customer1.setCustomerId("CUST001");
        customer1.setName("John Doe");
        customer1.setCity("New York");

        assertEquals("CUST001", customer1.getCustomerId());
        assertEquals("John Doe", customer1.getName());
        assertEquals("New York", customer1.getCity());
    }
}