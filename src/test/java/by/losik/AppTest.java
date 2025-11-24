package by.losik;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AppTest {

    @Test
    public void shouldAnswerWithTrue() {
        assertDoesNotThrow(() -> App.main(new String[]{}), String.valueOf(Exception.class));
    }
}
