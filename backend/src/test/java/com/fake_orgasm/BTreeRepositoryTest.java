package com.fake_orgasm;

import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.Category;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;
import com.fake_orgasm.users_management.repository.IBTreeRepository;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class BTreeRepositoryTest {
    @Test
    public void insertNodeTest(){
        Node<User> nodeTest = new Node<>(4);
        User user = new User(12, "Jorge", "Oropeza",new Date(), Category.FREQUENT_PASSENGER, "Bolivia");
        nodeTest.setSize(1);
        nodeTest.setKey(0, user);
        IBTreeRepository<User> bTreeRepository = new BTreeRepository();

        bTreeRepository.saveNode(nodeTest);
    }
    @Test
    public void getNodeByIdTest(){
        IBTreeRepository<User> userIBTreeRepository = new BTreeRepository();
        userIBTreeRepository.readNodeById(661119548);
    }
}
