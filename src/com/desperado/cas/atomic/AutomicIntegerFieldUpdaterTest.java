package com.desperado.cas.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AutomicIntegerFieldUpdaterTest {
    private static AtomicIntegerFieldUpdater<User> atom=AtomicIntegerFieldUpdater
            .newUpdater(User.class,"id");

    public static void main(String[] args) {
        User user = new User(100, 100, "Desperado");
        atom.addAndGet(user,50);
        System.out.println(user);
    }
}


class User{
    volatile int id;
    volatile int age;

    private String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
