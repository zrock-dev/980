package com.fake_orgasm.lazy_loading;

import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;
import org.junit.jupiter.api.Test;

public class SearchTest {
    /**
     * This function is a test case for the search functionality.
     */
    @Test
    public void searchTest() {
        BTree<User> bTree = new BTree<>(2, new BTreeRepository());
        UserGenerator userGenerator = new UserGenerator();
        for (int i = 0; i < 1000; i++) {
            bTree.searchKey(userGenerator.make());
        }
        bTree.getRoot().printTree("");
    }
}
