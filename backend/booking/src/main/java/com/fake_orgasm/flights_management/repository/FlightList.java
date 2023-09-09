package com.fake_orgasm.flights_management.repository;

import com.fake_orgasm.flights_management.models.FlightJoined;

import java.util.List;

/**
 * Represents a paginated list of flight records with additional pagination information.
 * <p>
 * This record class provides a structured representation of a list of flight records, including details about pagination.
 *
 * @param totalTickets   The total number of flight records in the dataset.
 * @param ticketsPerPage The number of flight records displayed per page.
 * @param tickets        The list of flight records for the current page.
 * @param currentPage    The current page number.
 * @param totalPages     The total number of pages in the pagination.
 */
public record FlightList(int totalTickets, int ticketsPerPage, List<FlightJoined> tickets, int currentPage,
                         int totalPages) {
}
