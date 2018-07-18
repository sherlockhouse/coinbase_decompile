package com.fasterxml.jackson.databind.util;

public final class LinkedNode<T> {
    private LinkedNode<T> next;
    private final T value;

    public LinkedNode(T value, LinkedNode<T> next) {
        this.value = value;
        this.next = next;
    }

    public void linkNext(LinkedNode<T> n) {
        if (this.next != null) {
            throw new IllegalStateException();
        }
        this.next = n;
    }

    public LinkedNode<T> next() {
        return this.next;
    }

    public T value() {
        return this.value;
    }
}
