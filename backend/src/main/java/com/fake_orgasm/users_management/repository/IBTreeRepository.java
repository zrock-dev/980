package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.users_management.libs.btree.Node;
import com.fake_orgasm.users_management.models.User;

public interface IBTreeRepository {
    /**
     * Save a node in the secondary memory.
     *
     * @param node Node to save;
     * @return result of the operation.
     */
    boolean saveNode(Node<User> node);

    /**
     * Update the data of a node in the secondary memory.
     *
     * @param node Node with the new information.
     * @return result of the operation.
     */
    boolean updateNode(Node<User> node);

    /**
     * Delete a node in the secondary memory.
     *
     * @param node Node to delete.
     * @return result of operation.
     */
    boolean deleteNode(Node<User> node);

    /**
     * Get a node by id of the secondary memory.
     *
     * @param id id node to remove.
     * @return Node found.
     */
    Node<User> readNodeById(int id);
}
