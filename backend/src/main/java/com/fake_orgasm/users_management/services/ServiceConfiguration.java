package com.fake_orgasm.users_management.services;

import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for initializing beans related to the service layer.
 */
@Configuration
public class ServiceConfiguration {

    /**
     * Initializes and configures a B-tree instance to store User objects.
     *
     * @return A BTree instance with a specified order.
     */
    @Bean
    public BTree<User> initBTree() {
        return new BTree<>(10);
    }

    @Bean
    public IUserManagement getUserManagement() {
        return null;
    }
}