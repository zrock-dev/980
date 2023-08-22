package com.fake_orgasm0.usersmanagement.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Comparable<User> {

    private int ci;
    private String name;
    private  String lastName;

    @Override
    public int compareTo(User o) {
        return name.compareTo(o.getName());
    }
}
