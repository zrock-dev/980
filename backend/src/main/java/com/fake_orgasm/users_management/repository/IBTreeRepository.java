package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.users_management.libs.btree.Node;

/**
 * This interface collects the fundamental methods for saving and reading nodes.
 *
 * @param <T> The type of data to be stored and read in the nodes.
 */
public interface IBTreeRepository<T extends Comparable<T>> {
    /**
     * Save a node in the secondary memory.
     *
     * @param node Node to save;
     * @return result of the operation.
     */
    boolean saveNode(Node<T> node);

    /**
     * Update the data of a node in the secondary memory.
     *
     * @param node Node with the new information.
     * @return result of the operation.
     */
    boolean updateNode(Node<T> node);

    /**
     * Delete a node in the secondary memory.
     *
     * @param node Node to delete.
     * @return result of operation.
     */
    boolean deleteNode(Node<T> node);

    /**
     * Get a node by id of the secondary memory.
     *
     * @param id id node to remove.
     * @return Node found.
     */
    Node<T> readNodeById(String id);
}
