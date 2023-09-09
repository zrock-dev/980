package com.fake_orgasm.flights_management.repository;

import java.util.List;

/**
 * Represents a paginated list of elements with additional pagination information.
 * <p>
 * This record class provides a structured representation of a list of tickets, including details about pagination.
 *
 * @param total         The total number of tickets in the dataset.
 * @param amountPerPage The number of tickets displayed per page.
 * @param elements      The list of tickets for the current page.
 * @param currentPage   The current page number.
 * @param totalPages    The total number of pages in the pagination.
 */
public record Pagination(int total, int amountPerPage, List<?> elements, int currentPage,
                         int totalPages) {
}
