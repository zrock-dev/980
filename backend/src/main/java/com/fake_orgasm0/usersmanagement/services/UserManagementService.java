package com.fake_orgasm0.usersmanagement.services;

import com.fake_orgasm0.usersmanagement.libs.btree.BTree;
import com.fake_orgasm0.usersmanagement.models.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserManagementService implements IUserManagement{

    private final BTree<User> users;

    public UserManagementService(BTree<User> users) {
        this.users = users;
    }


    @Override
    public List<User> search(String name) {

        return null;
    }

    @Override
    public User search(int ci) {
        return null;
    }

    @Override
    public boolean createUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(int ci) {
        return false;
    }
    @Override
    public boolean updateUser(int id, User user) {
        return false;
    }

    @Override
    public List<User> getUsers(int page) {
        return null;
    }
}
