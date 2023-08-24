package com.fake_orgasm.usersmanagement.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
/**
 * This class is used to represent the data structure of a user.
 * <p>
 * This class is implemented to be able to represent the data of a user within
 * the program and to be able to manage this information.
 */
public class User {
    /**
     * Id of the user.
     */
    private int id;
    /**
     * The name of the user.
     */
    private String name;
    /**
     * The last name of the user.
     */
    private String lastName;
    /**
     * Date birth of the user.
     */
    private Date dateBirth;
    /**
     * Id list of the flights.
     */
    private List<Integer> flights;
    /**
     * The category of the user.
     */
    private Category category;
    /**
     * The country of the user.
     */
    private String country;

    /**
     * This is the constructor of the class.
     * <p>
     * The constructor receives all the necessary parameters to correctly
     * represent the User data structure within the program.
     *
     * @param id int receives the id of the user.
     * @param name String receives the name of the user.
     * @param lastName String receives the last name of the user.
     * @param dateBirth Date receives the date birth of the user.
     * @param category Category receives the category of the user.
     * @param country String receives the country of the user.
     */
    public User(int id, String name, String lastName, Date dateBirth, Category category, String country) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.category = category;
        this.country = country;
    }
}
