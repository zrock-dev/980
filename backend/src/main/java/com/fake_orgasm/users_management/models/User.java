package com.fake_orgasm.users_management.models;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * This is a modal class for a user.
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
     *                              from being compared to this object.
     *
     */
    private int id;

    private String name;
    private String lastName;
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
     * @param id int receives the id of the user.
     * @param name String receives the name of the user.
     * @param lastNam String receives the last name of the user.
     * @param dateBirth Date receives the date birth of the user.
     * @param cat Category receives the category of the user.
     * @param country String receives the country of the user.
     */
    public User(int id, String name, String lastNam, Date dateBirth, Category cat, String country) {
        this.id = id;
        this.name = name;
        this.lastName = lastNam;
        this.dateBirth = dateBirth;
        this.category = cat;
        this.country = country;
    }

    /**
     * @return return the full name of the user.
     */
    public String getFullName() {
        return name + " " + lastName;
    }

    /**
     * This method compare the actual user with other user.
     * This method compares the names of the users to verify that they are in
     * alphabetical order, it returns 1 when the current user is alphabetically
     * higher than the compared user, when the opposite happens it returns -1,
     * in case both users have the same name it compares the user id to verify
     * that they are different users, if they are users with the same name but
     * different id it returns -2 and in case they are equal in all the mentioned
     * aspects it returns 0.
     *
     * @param o the object to be compared.
     * @return int result of the comparative.
     */
    @Override
    public int compareTo(User o) {
        int resultCompare = 0;
        char[] nameUser = getFullName().toCharArray();
        char[] nameUserCompared = o.getFullName().toCharArray();
        for (int i = 0; i < nameUser.length && resultCompare == 0; i++) {
            if (nameUser[i] != ' ') {
                if ((int) nameUser[i] < (int) nameUserCompared[i]) {
                    resultCompare = 1;
                } else if ((int) nameUser[i] > (int) nameUserCompared[i]) {
                    resultCompare = -1;
                }
            }
        }
        if (resultCompare == 0) {
            if (this.id != o.getId()) {
                resultCompare = -2;
            }
        }
        return resultCompare;
    }
}
