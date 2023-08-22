package com.fake_orgasm0.usersmanagement.services;

import com.fake_orgasm0.usersmanagement.models.User;

import java.util.List;

public interface IUserManagement {
    List<User> search(String name);

    User search(int ci);
    boolean createUser(User user);

    boolean deleteUser(int ci);

    boolean updateUser(int id,User user);

    List<User> getUsers(int page);
}
