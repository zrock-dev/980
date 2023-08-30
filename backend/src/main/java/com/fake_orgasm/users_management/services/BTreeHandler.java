package com.fake_orgasm.users_management.services;

import com.fake_orgasm.users_management.libs.btree.BTree;
import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.springframework.stereotype.Service;

/**
 * A utility class for handling operations on a B-tree data structure containing User objects.
 */
@Service
public class BTreeHandler {

    /**
     * Performs a Breadth-First Search (BFS) traversal on the B-tree starting from the root node,
     * searching for users whose names contain the given name fragment.
     *
     * @param name The name fragment to search for.
     * @param root The root node of the B-tree.
     * @return A list of User objects whose names contain the given name fragment.
     */
    public static List<User> bfs(String name, Node<User> root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<User> foundUsers = new ArrayList<>();
        Queue<Node<User>> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node<User> current = queue.poll();

            if (current != null) {
                for (int i = 0; i < current.getSize(); i++) {
                    User user = current.getKey(i);
                    if (user.getName().contains(name)) {
                        foundUsers.add(user);
                    }
                }

                Node<User>[] currentChildren = current.getChildren();
                for (Node<User> child : currentChildren) {
                    if (child != null) {
                        queue.offer(child);
                    }
                }
            }
        }

        return foundUsers;
    }

    /**
     * Retrieves the first twenty User objects from a specific page number of the B-tree.
     *
     * @param pageNumber The page number from which to retrieve User objects.
     * @param bTree      The B-tree containing the User objects.
     * @return A list of the first twenty User objects from the specified page number.
     */
    public static List<User> getKeysFromPage(int pageNumber, BTree<User> bTree) {
        List<User> result = new ArrayList<>();
        int count = 0;
        getFirstTwentyFromIndex(bTree.getRoot(), pageNumber, result, count);
        return result;
    }

    /**
     * Recursively retrieves up to twenty User objects from a specific index within the B-tree
     * node and its children.
     *
     * @param node   The node from which to start retrieving User objects.
     * @param index  The index within the node indicating the starting position.
     * @param result The list to store the retrieved User objects.
     * @param count  The running count of retrieved User objects.
     * @return The updated count of retrieved User objects.
     */
    private static int getFirstTwentyFromIndex(Node<User> node, int index, List<User> result, int count) {
        if (node == null || count >= 20) {
            return count;
        }

        int i = 0;
        while (i < node.getSize() && count < 20) {
            if (index <= count) {
                result.add(node.getKey(i));
            }
            count++;
            i++;
        }

        if (!node.isLeaf()) {
            for (int j = 0; j < node.getSize() + 1 && count < 20; j++) {
                count = getFirstTwentyFromIndex(node.getChild(j), index, result, count);
            }
        }

        return count;
    }
}
