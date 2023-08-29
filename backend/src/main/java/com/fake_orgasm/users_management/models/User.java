package com.fake_orgasm.users_management.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fake_orgasm.flights_management.models.Category;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a user in the system.
 */
@Getter
@Setter
public class User implements Comparable<User> {

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     *
     * <p>The implementor must ensure {@link Integer#signum
     * signum}{@code (x.compareTo(y)) == -signum(y.compareTo(x))} for
     * all {@code x} and {@code y}.  (This implies that {@code
     * x.compareTo(y)} must throw an exception if and only if {@code
     * y.compareTo(x)} throws an exception.)
     *
     * <p>The implementor must also ensure that the relation is transitive:
     * {@code (x.compareTo(y) > 0 && y.compareTo(z) > 0)} implies
     * {@code x.compareTo(z) > 0}.
     *
     * <p>Finally, the implementor must ensure that {@code
     * x.compareTo(y)==0} implies that {@code signum(x.compareTo(z))
     * == signum(y.compareTo(z))}, for all {@code z}.
     *
     * <p> It is strongly recommended, but <i>not</i> strictly required that
     * {@code (x.compareTo(y)==0) == (x.equals(y))}.  Generally speaking, any
     * class that implements the {@code Comparable} interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     * from being compared to this object.
     */
    private int id;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private LocalDate dateBirth;
    private List<FlightHistory> flights;
    private Category category;
    private String country;
    private List<String> tickets;

    /**
     * Constructs a new User object with the provided information.
     *
     * @param id        int receives the id of the user.
     * @param name      String receives the name of the user.
     * @param lastName  String receives the last name of the user.
     * @param birthdate Date receives the date birth of the user.
     * @param ci        Category receives the category of the user.
     */
    public User(int id, String name, String lastName,
                Date birthdate, int ci) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.ci = ci;
        this.tickets = new ArrayList<>();
    }

    /**
     * Constructs a new User object with the provided name information.
     *
     * @param firstName      The first name of the user.
     * @param secondName     The second name of the user.
     * @param firstLastName  The first last name of the user.
     * @param secondLastName The second last name of the user.
     */
    public User(String firstName, String secondName, String firstLastName, String secondLastName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
        this.flights = new ArrayList<>();
    }

    /**
     * <<<<<<< Updated upstream
     * Empty User constructor.
     */
    public User() {}

    /** This adds new flight histories.
     *
     * @param flightHistory
     */
    public void addFlightHistory(FlightHistory flightHistory) {
        flights.add(flightHistory);
    }
    /**
     * Returns the full name of the user.
     *
     * @return The full name of the user.
     */
    public String getFullName() {
        return String.format("%s %s %s %s", firstName, secondName, firstLastName, secondLastName);
    }

    /**
     * Compares this user with another user.
     *
     * @param o The user to compare with.
     * @return A negative integer, zero, or a positive integer as this user is less than, equal to,
     *         or greater than the specified user.
     */
    @Override
    public int compareTo(User o) {
        int resultCompare = 0;
        resultCompare = getFullName().compareTo(o.getFullName()) * (-1);
        if (resultCompare == 0) {
            if (this.id != o.getId()) {
                resultCompare = -1;
            }
        }
        return resultCompare;
    }
}
=======
}
>>>>>>> 3a81eb8 (refactor: models refactored to book flights)
