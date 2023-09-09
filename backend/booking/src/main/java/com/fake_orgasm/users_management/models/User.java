package com.fake_orgasm.users_management.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    private List<String> flights;
    private String country;

    /**
     * Constructs a new User object with the provided information.
     *
     * @param id        The unique identifier for the user.
     * @param firstName The first name of the user.
     * @param lastNam   The last name of the user.
     * @param dateBirth The date of birth of the user.
     * @param country   The country of the user.
     */
    public User(int id, String firstName, String lastNam, LocalDate dateBirth, String country) {
        this.id = id;
        this.firstName = firstName;
        this.firstLastName = lastNam;
        this.dateBirth = dateBirth;
        this.country = country;
        this.flights = new ArrayList<>();
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
    }

    /**
     * Constructs a new User object with the provided name information.
     *
     * @param id             The unique identifier for the user.
     * @param firstName      The first name of the user.
     * @param secondName     The second name of the user.
     * @param firstLastName  The first last name of the user.
     * @param secondLastName The second last name of the user.
     */
    public User(int id, String firstName, String secondName, String firstLastName, String secondLastName) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
    }

    /**
     * Empty User constructor.
     */
    public User() {}

    /**
     * Returns the full name of the user.
     *
     * @return The full name of the user.
     */
    public String getFullName() {
        return String.format("%s %s %s %s", firstName, secondName, firstLastName, secondLastName);
    }

    /**
     * This method adds a flight to the list of flights associated with the user.
     * <p>
     * This method adds the specified flight ID to the list of flights associated
     * with the user. The user is now considered to be booked on this flight.
     *
     * @param flightId The ID of the flight to be added to the user's list of flights.
     */
    public void addFlight(String flightId) {
        flights.add(flightId);
    }

    /**
     * This method removes a flight from the list of flights associated with the user.
     * <p>
     * This method removes the specified flight ID from the list of flights
     * associated with the user. The user is no longer considered to be booked on
     * this flight.
     *
     * @param flightId The ID of the flight to be removed from the user's list of flights.
     */
    public void removeFlight(String flightId) {
        flights.remove(flightId);
    }

    /**
     * Compares this user with another user.
     *
     * @param o The user to compare with.
     * @return A negative integer, zero, or a positive integer as this user is less than, equal to,
     * or greater than the specified user.
     */
    @Override
    public int compareTo(User o) {
        int resultCompare = 0;
        resultCompare = getFullName().compareTo(o.getFullName());
        if (resultCompare == 0) {
            if (this.id != o.getId()) {
                resultCompare = -1;
            }
        }
        return resultCompare;
    }

    /**
     * Compares this user to another object for equality.
     *
     * @param object The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        System.out.println("I'm diferent");

        User userToCompare = (User) object;
        return this.id == userToCompare.getId();
    }

    /**
     * This method returns a hash code value for this User object. This method overrides
     * the hashCode method inherited from the Object class.
     *
     * @return A hash code value for this User object.
     */
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
