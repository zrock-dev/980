package com.fake_orgasm.lazy_loading;

import com.fake_orgasm.generator.flight_history_generator.FlightHistory;
import com.fake_orgasm.generator.flight_history_generator.FlightHistoryGenerator;
import com.fake_orgasm.generator.user_generator.UserGenerator;
import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.models.User;
import com.fake_orgasm.users_management.repository.BTreeRepository;
import org.junit.jupiter.api.Test;

public class DeleteTest {


    private User makeUser(){
        FlightHistoryGenerator flightHistoryGenerator = FlightHistoryGenerator.getInstance();
        UserGenerator userGenerator = new UserGenerator();

        User user = userGenerator.make();
        FlightHistory history = flightHistoryGenerator.generateRandomFlightHistory();
        user.addFlightHistory(history);
        return user;
    }
    @Test
    public void deleteTest() {
       BTree bTree = new BTree(2,new BTreeRepository());
        User user1 = makeUser();
        User user2 = makeUser();
        User user3 = makeUser();
        User user4 = makeUser();
        User user5 = makeUser();

        bTree.insert(user1);
        bTree.insert(user2);
        bTree.insert(user3);
        bTree.insert(user4);
        bTree.insert(user5);

        long start = System.nanoTime();

        bTree.remove(user1);
        bTree.remove(user2);
        bTree.remove(user3);
        bTree.remove(user4);
        bTree.remove(user5);

        long end = System.nanoTime();
        System.out.println((end - start) / 1e+9);

        // 100k users = 12s
        // 1M users = 120s = 2 min
        // 10M users = 1200s = 20 min
    }

}
