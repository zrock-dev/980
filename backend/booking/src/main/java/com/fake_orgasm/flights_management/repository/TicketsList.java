package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.models.TicketJoined;

import java.util.List;

/**
 * Represents a paginated list of tickets with additional pagination information.
 *
 * This record class provides a structured representation of a list of tickets, including details about pagination.
 *
 * @param totalTickets The total number of tickets in the dataset.
 * @param ticketsPerPage The number of tickets displayed per page.
 * @param tickets The list of tickets for the current page.
 * @param currentPage The current page number.
 * @param totalPages The total number of pages in the pagination.
 */
public record TicketsList(int totalTickets, int ticketsPerPage, List<TicketJoined> tickets, int currentPage,
                          int totalPages) {
}
