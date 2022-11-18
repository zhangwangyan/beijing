package com.desperado.list_set_queue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
      list.add("1");

        CopyOnWriteArrayList<Object> objects = new CopyOnWriteArrayList<>();
        objects.add("");

    }
}
