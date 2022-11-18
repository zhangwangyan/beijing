package com.desperado.hashmap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Test {
    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Desperado","Desperado是张王岩");
        map.get("Desperado");

        ConcurrentHashMap<Object, Object> map1 = new ConcurrentHashMap<>();
        map1.put("","");
    }
}
