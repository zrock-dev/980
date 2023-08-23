package PassengerManager.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
/**
 * This class represent a User in the aero port context.
 */
public class User {
    private int id;
    private String name;
    private String lastName;
    private Date dateBirth;
    private List<Integer> flights;
    private Category category;
    private String country;

    public User(int id, String name, String lastName, Date dateBirth, Category category, String country) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.category = category;
        this.country = country;
    }
}
