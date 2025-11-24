package by.losik;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void testCategoryValues() {
        Category[] categories = Category.values();
        assertEquals(6, categories.length);
        assertArrayEquals(new Category[]{
                Category.ELECTRONICS,
                Category.CLOTHING,
                Category.BOOKS,
                Category.HOME,
                Category.BEAUTY,
                Category.TOYS
        }, categories);
    }

    @Test
    void testCategoryValueOf() {
        assertEquals(Category.ELECTRONICS, Category.valueOf("ELECTRONICS"));
        assertEquals(Category.BOOKS, Category.valueOf("BOOKS"));
        assertEquals(Category.TOYS, Category.valueOf("TOYS"));
    }

    @Test
    void testCategoryOrdinal() {
        assertEquals(0, Category.ELECTRONICS.ordinal());
        assertEquals(2, Category.BOOKS.ordinal());
    }
}