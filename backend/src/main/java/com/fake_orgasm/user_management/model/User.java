package com.fake_orgasm.user_management.model;

import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is a data structure that represent a user.
 */
@Getter
@Setter
public class User {

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
}
