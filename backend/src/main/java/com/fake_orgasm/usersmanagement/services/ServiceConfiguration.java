package com.fake_orgasm.usersmanagement.services;

import com.fake_orgasm.usersmanagement.libs.btree.BTree;
import com.fake_orgasm.usersmanagement.models.User;
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
}
