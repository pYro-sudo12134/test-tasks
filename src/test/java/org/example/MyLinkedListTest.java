package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyLinkedListTest {

    private MyLinkedList<Integer> list;
    private MyLinkedList<String> stringList;

    @BeforeEach
    void setUp() {
        list = new MyLinkedList<>();
        stringList = new MyLinkedList<>();
    }

    @Test
    void emptyListSize() {
        assertEquals(0, list.size());
        assertEquals(0, list.size());
    }

    @Test
    void addFirst() {
        list.addFirst(10);
        assertEquals(1, list.size());
        assertEquals(10, list.getFirst());

        list.addFirst(20);
        assertEquals(2, list.size());
        assertEquals(20, list.getFirst());
        assertEquals(10, list.getLast());
    }

    @Test
    void addLast() {
        list.addLast(10);
        assertEquals(1, list.size());
        assertEquals(10, list.getLast());

        list.addLast(20);
        assertEquals(2, list.size());
        assertEquals(10, list.getFirst());
        assertEquals(20, list.getLast());
    }

    @Test
    void addAtIndex() {
        list.add(0, 10);
        list.add(1, 30);
        list.add(1, 20);

        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void addAtInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(1, 10));

        list.addFirst(5);
        assertThrows(IndexOutOfBoundsException.class, () -> list.add(2, 10));
    }

    @Test
    void getFirst() {
        list.addFirst(10);
        list.addLast(20);
        assertEquals(10, list.getFirst());
    }

    @Test
    void getFirstEmptyList() {
        assertThrows(IllegalStateException.class, () -> list.getFirst());
    }

    @Test
    void getLast() {
        list.addFirst(10);
        list.addLast(20);
        assertEquals(20, list.getLast());
    }

    @Test
    void getLastEmptyList() {
        assertThrows(IllegalStateException.class, () -> list.getLast());
    }

    @Test
    void get() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertEquals(10, list.get(0));
        assertEquals(20, list.get(1));
        assertEquals(30, list.get(2));
    }

    @Test
    void getInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));

        list.addFirst(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(1));
    }

    @Test
    void removeFirst() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertEquals(10, list.removeFirst());
        assertEquals(2, list.size());
        assertEquals(20, list.getFirst());

        assertEquals(20, list.removeFirst());
        assertEquals(1, list.size());
        assertEquals(30, list.getFirst());
    }

    @Test
    void removeFirstEmptyList() {
        assertThrows(IllegalStateException.class, () -> list.removeFirst());
    }

    @Test
    void removeFirstSingleElement() {
        list.addFirst(10);
        assertEquals(10, list.removeFirst());
        assertEquals(0, list.size());
        assertThrows(IllegalStateException.class, () -> list.getFirst());
    }

    @Test
    void removeLast() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);

        assertEquals(30, list.removeLast());
        assertEquals(2, list.size());
        assertEquals(20, list.getLast());

        assertEquals(20, list.removeLast());
        assertEquals(1, list.size());
        assertEquals(10, list.getLast());
    }

    @Test
    void removeLastEmptyList() {
        assertThrows(IllegalStateException.class, () -> list.removeLast());
    }

    @Test
    void removeLastSingleElement() {
        list.addFirst(10);
        assertEquals(10, list.removeLast());
        assertEquals(0, list.size());
        assertThrows(IllegalStateException.class, () -> list.getLast());
    }

    @Test
    void removeByIndex() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);

        assertEquals(20, list.remove(1));
        assertEquals(3, list.size());
        assertEquals(10, list.get(0));
        assertEquals(30, list.get(1));
        assertEquals(40, list.get(2));

        assertEquals(30, list.remove(1));
        assertEquals(2, list.size());
        assertEquals(10, list.get(0));
        assertEquals(40, list.get(1));
    }

    @Test
    void removeFirstByIndex() {
        list.addLast(10);
        list.addLast(20);

        assertEquals(10, list.remove(0));
        assertEquals(1, list.size());
        assertEquals(20, list.getFirst());
    }

    @Test
    void removeLastByIndex() {
        list.addLast(10);
        list.addLast(20);

        assertEquals(20, list.remove(1));
        assertEquals(1, list.size());
        assertEquals(10, list.getLast());
    }

    @Test
    void removeInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));

        list.addFirst(10);
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(1));
    }

    @Test
    void constructorWithCollection() {
        List<Integer> initialList = List.of(1, 2, 3, 4, 5);
        MyLinkedList<Integer> newList = new MyLinkedList<>(initialList);

        assertEquals(5, newList.size());
        assertEquals(1, newList.getFirst());
        assertEquals(5, newList.getLast());
        assertEquals(3, newList.get(2));
    }

    @Test
    void constructorWithEmptyCollection() {
        MyLinkedList<Integer> newList = new MyLinkedList<>(List.of());
        assertEquals(0, newList.size());
        assertThrows(IllegalStateException.class, newList::getFirst);
    }

    @Test
    void constructorWithSingleElementCollection() {
        MyLinkedList<Integer> newList = new MyLinkedList<>(List.of(42));
        assertEquals(1, newList.size());
        assertEquals(42, newList.getFirst());
        assertEquals(42, newList.getLast());
    }

    @Test
    void stringListOperations() {
        stringList.addFirst("Hello");
        stringList.addLast("World");
        stringList.add(1, "Beautiful");

        assertEquals(3, stringList.size());
        assertEquals("Hello", stringList.getFirst());
        assertEquals("World", stringList.getLast());
        assertEquals("Beautiful", stringList.get(1));

        assertEquals("Hello", stringList.removeFirst());
        assertEquals("World", stringList.removeLast());
        assertEquals(1, stringList.size());
    }

    @Test
    void complexScenario() {
        assertEquals(0, list.size());

        list.addFirst(1);
        list.addLast(3);
        list.add(1, 2);
        list.addLast(4);

        assertEquals(4, list.size());
        assertEquals(1, list.getFirst());
        assertEquals(4, list.getLast());
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));

        assertEquals(2, list.remove(1));
        assertEquals(3, list.size());
        assertEquals(1, list.get(0));
        assertEquals(3, list.get(1));
        assertEquals(4, list.get(2));

        assertEquals(1, list.removeFirst());
        assertEquals(4, list.removeLast());
        assertEquals(1, list.size());
        assertEquals(3, list.getFirst());
        assertEquals(3, list.getLast());

        assertEquals(3, list.removeLast());
        assertEquals(0, list.size());
    }

    @Test
    void toStringTest() {
        assertEquals("[]", list.toString());

        list.addFirst(1);
        assertEquals("[1]", list.toString());

        list.addLast(2);
        list.addLast(3);
        assertEquals("[1, 2, 3]", list.toString());
    }

    @Test
    void nullValues() {
        MyLinkedList<String> listWithNulls = new MyLinkedList<>();

        listWithNulls.addFirst(null);
        listWithNulls.addLast("not null");
        listWithNulls.add(1, null);

        assertEquals(3, listWithNulls.size());
        assertNull(listWithNulls.getFirst());
        assertNull(listWithNulls.get(1));
        assertEquals("not null", listWithNulls.getLast());

        assertNull(listWithNulls.removeFirst());
        assertNull(listWithNulls.remove(0));
        assertEquals("not null", listWithNulls.getFirst());
    }
}