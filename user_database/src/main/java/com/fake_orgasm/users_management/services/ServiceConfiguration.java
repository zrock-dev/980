package com.fake_orgasm.users_management.services;

import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.User;

/**
 * Configuration class for initializing beans related to the service layer.
 */
public class ServiceConfiguration {

    /**
     * Initializes and configures a B-tree instance to store User objects.
     *
     * @return A BTree instance with a specified order.
     */
    public BTree<User> initBTree() {
        return new BTree<>(10);
    }
}
