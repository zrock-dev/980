package com.fake_orgasm0.usersmanagement;

import com.fake_orgasm0.usersmanagement.libs.btree.BTree;
import com.fake_orgasm0.usersmanagement.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conf {
    String country  = "bOlivia";

    @Bean
    public BTree<User> initUser(){
        return new BTree(10);
    }
}
