package com.fake_orgasm.users_management.models;

import java.util.List;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a user in the system.
 */
@Getter
@Setter
public class User implements Comparable<User> {

    private int id;

    private long citizenId;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private String dateBirth;
    private List<Integer> flights;
    private Category category;
    private String country;

    /**
     * Constructs a new User object with the provided information.
     *
     * @param id        The unique identifier for the user.
     * @param firstName The first name of the user.
     * @param lastNam   The last name of the user.
     * @param dateBirth The date of birth of the user.
     * @param cat       The category of the user.
     * @param country   The country of the user.
     */
    public User(int id, String firstName, String lastNam, String dateBirth, Category cat, String country) {
        this.id = id;
        this.firstName = firstName;
        this.firstLastName = lastNam;
        this.dateBirth = dateBirth;
        this.category = cat;
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

    /**
     * Compares this user to another object for equality.
     *
     * @param obj The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        User comparison = (User) obj;

        boolean nameComparison = Objects.equals(firstName, comparison.firstName);
        boolean firstLastNameComparison = Objects.equals(firstLastName, comparison.firstLastName);
        boolean secondLastNameComparison = Objects.equals(secondLastName, comparison.secondLastName);

        return nameComparison && firstLastNameComparison && secondLastNameComparison;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
