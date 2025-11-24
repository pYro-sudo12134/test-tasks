package org.example;

import java.util.List;

public class App {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.addFirst(10);
        list.addLast(30);
        list.add(1, 20);

        System.out.println("List: " + list);
        System.out.println("Size: " + list.size());

        System.out.println("First: " + list.getFirst());
        System.out.println("Last: " + list.getLast());
        System.out.println("Element at index 1: " + list.get(1));

        System.out.println("Removed first: " + list.removeFirst());
        System.out.println("Removed last: " + list.removeLast());
        System.out.println("List after removals: " + list);

        list.addLast(40);
        list.addLast(50);
        System.out.println("Before remove by index: " + list);
        System.out.println("Removed at index 1: " + list.remove(1));
        System.out.println("Final list: " + list);

        MyLinkedList<Integer> list1 = new MyLinkedList<>(List.of(1, 2, 3, 4, 5));
        System.out.println("List from List.of(): " + list1);
        System.out.println("Size: " + list1.size());

        MyLinkedList<String> list2 = new MyLinkedList<>(List.of("A", "B", "C"));
        System.out.println("String list: " + list2);

        MyLinkedList<Double> list3 = new MyLinkedList<>(List.of());
        System.out.println("Empty list: " + list3);

        MyLinkedList<Character> list4 = new MyLinkedList<>(List.of('X'));
        System.out.println("Single element: " + list4);

        System.out.println("First element: " + list1.getFirst());
        System.out.println("Last element: " + list1.getLast());
        System.out.println("Element at index 2: " + list1.get(2));
    }
}
