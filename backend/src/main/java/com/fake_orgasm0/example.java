package com.fake_orgasm0;

import org.apache.hyracks.storage.am.btree.impls.BTree;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class example {
    @GetMapping("/api/user")
    public User getUser() {

        return new User("daniel");
    }

        record User(String name) { }
}
