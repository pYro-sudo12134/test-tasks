package org.example;

import java.util.Collection;

public class MyLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;
        Node(E data) {
            this.data = data;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public MyLinkedList(Collection<? extends E> collection) {
        this();
        if (collection != null) {
            for (E element : collection) {
                addLast(element);
            }
        }
    }

    /**
     * Returns the size of the list
     */
    public int size() {
        return size;
    }

    /**
     * Adds the element in the beginning of the list
     */
    public void addFirst(E element) {
        Node<E> newNode = new Node<>(element);
        if (head == null) {
            // Если список пустой
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Adds the element in the end of the list
     */
    public void addLast(E element) {
        Node<E> newNode = new Node<>(element);
        if (tail == null) {
            // Если список пустой
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Adds the element in the list by index
     */
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            addFirst(element);
        } else if (index == size) {
            addLast(element);
        } else {
            Node<E> current = getNode(index);
            Node<E> newNode = new Node<>(element);

            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;

            size++;
        }
    }

    /**
     * Returns the first element of the list
     */
    public E getFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }
        return head.data;
    }

    /**
     * Returns the last element of the list
     */
    public E getLast() {
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }
        return tail.data;
    }

    /**
     * Returns the element by index
     */
    public E get(int index) {
        return getNode(index).data;
    }

    /**
     * Retrieve and remove the first element of the list
     */
    public E removeFirst() {
        if (head == null) {
            throw new IllegalStateException("List is empty");
        }

        E removedData = head.data;
        if (head == tail) {
            // Только один элемент в списке
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return removedData;
    }

    /**
     * Retrieve and remove the last element of the list
     */
    public E removeLast() {
        if (tail == null) {
            throw new IllegalStateException("List is empty");
        }

        E removedData = tail.data;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return removedData;
    }

    /**
     * Retrieve and remove the element of the list by index
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node<E> nodeToRemove = getNode(index);
            E removedData = nodeToRemove.data;

            // Удаляем узел из списка
            nodeToRemove.prev.next = nodeToRemove.next;
            nodeToRemove.next.prev = nodeToRemove.prev;

            size--;
            return removedData;
        }
    }

    /**
     * Helper method to get node by index
     */
    private Node<E> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Node<E> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    /**
     * Returns string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }
}