package com.fake_orgasm0.usersmanagement.libs.btree;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/**
 * Utility class for providing methods to work with collections.
 *
 * @param <T> The type of elements in the collection.
 */
public class Utils<T extends Comparable<? super T>> {

    /**
     * Returns the index where the specified element should be inserted into the sorted list.
     *
     * @param nodes The sorted list of elements.
     * @param t     The element to be inserted.
     * @return The index where the element should be inserted.
     */
    public static <T extends Comparable<? super T>> int getIndex(List<T> nodes, T t) {
        int left = 0, mid, right = nodes.size() - 1, comparison;
        while (left <= right) {
            mid = left + (right - left) / 2;
            comparison = t.compareTo(nodes.get(mid));
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static <T> List<T> splice(List<T> list, int from, int to) {
        List<T> newList = new ArrayList<>(to - from);
        ListIterator<T> iterator = list.listIterator(from);

        for (int i = from; i < to; i++) {
            newList.add(iterator.next());
            iterator.remove();
        }

        return newList;
    }

    public static <T> List<T> splice(List<T> list, int from) {
        List<T> newList = new ArrayList<>(list.size() - from);
        ListIterator<T> iterator = list.listIterator(from);

        while (iterator.hasNext()) {
            newList.add(iterator.next());
            iterator.remove();
        }

        return newList;
    }
}