package com.fake_orgasm.users_management.repository;

import com.fake_orgasm.users_management.libs.btree.Node;

public interface IBTreeRepository<T extends Comparable<T>> {
    /**
     * Save a node in the secondary memory.
     *
     * @param node Node to save;
     * @return result of the operation.
     */
    boolean save(Node<T> node);

    /**
     * Delete a node in the secondary memory.
     *
     * @param node Node to delete.
     * @return result of operation.
     */
    boolean delete(Node<T> node);

    /**
     * Get a node by id of the secondary memory.
     *
     * @param id id node to read.
     * @return Node found.
     */
    Node<T> readNodeById(String id);
}
