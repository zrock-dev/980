package com.fake_orgasm.users_management.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * This is a modal class for a user.
 */
@Getter
@Setter
public class User implements Comparable<User> {

    private int id;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private Date dateBirth;
    private List<Integer> flights;
    private Category category;
    private String country;

    /**
     * This is the constructor of the class.
     * <p>
     * The constructor receives all the necessary parameters to correctly
     * represent the User data structure within the program.
     *
     * @param id        int receives the id of the user.
     * @param firstName String receives the name of the user.
     * @param lastNam   String receives the last name of the user.
     * @param dateBirth Date receives the date birth of the user.
     * @param cat       Category receives the category of the user.
     * @param country   String receives the country of the user.
     */
    public User(int id, String firstName, String lastNam, Date dateBirth, Category cat, String country) {
        this.id = id;
        this.firstName = firstName;
        this.firstLastName = lastNam;
        this.dateBirth = dateBirth;
        this.category = cat;
        this.country = country;
    }

    public User(String firstName, String secondName,String firstLastName, String secondLastName) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.firstLastName = firstLastName;
        this.secondLastName = secondLastName;
    }

    /**
     * @return return the full name of the user.
     */
    public String getFullName() {
        return String.format("%s %s %s %s", firstName, secondName, firstLastName, secondLastName);
    }

    /**
     * This method compare the actual user with other user.
     * This method compares the names of the users to verify that they are in
     * alphabetical order, it returns a positive value when the current user is alphabetically
     * higher than the compared user, when the opposite happens it returns the negative value,
     * in case both users have the same name it compares the user id to verify
     * that they are different users and return the negative value if are different.
     *
     * @param o the object to be compared.
     * @return int result of the comparative.
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
}
