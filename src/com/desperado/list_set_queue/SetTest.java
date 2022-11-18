package com.desperado.list_set_queue;

import java.util.HashSet;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest {
    public static void main(String[] args) {
        HashSet<Object> objects = new HashSet<>();
        CopyOnWriteArraySet<Object> objects1 = new CopyOnWriteArraySet<>();
        ConcurrentSkipListSet<Object> objects2 = new ConcurrentSkipListSet<>();
    }
}
