package groentjes.onzeMoestuin.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Eric van Dalen, Wim Kruizinga
 * The Garden class concerns a garden of a user(users).
 */
@Entity
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gardenId;

    @Size(min = 3, message = "De naam van de tuin moet minstens drie tekens lang zijn")
    private String gardenName;

    @NotNull
    @Min(value = 1, message = "Geef een heel getal in meters op (minimaal 1)")
    private Integer length;

    @NotNull
    @Min(value = 1, message = "Geef een heel getal in meters op (minimaal 1)")
    private Integer width;

    @OneToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "userId")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST,
                    CascadeType.MERGE })
    @JoinTable(
            name = "garden_members",
            joinColumns = @JoinColumn(name = "gardenId"),
            inverseJoinColumns = @JoinColumn(name = "userId"))
    private Set<User> gardenMembers = new HashSet<>();


    public Garden() {
    }

    public boolean isGardenMember(User user) {
        Set<User> users = this.getGardenMembers();
        for (User gardenUser : users) {
            if (user.getUserId().equals(gardenUser.getUserId())) {
                return true;
            }
        }
        return false;
    }

    // getters and setters
    public Integer getGardenId() {
        return gardenId;
    }

    public void setGardenId(Integer gardenId) {
        this.gardenId = gardenId;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getGardenName() {
        return gardenName;
    }

    public void setGardenName(String gardenName) {
        this.gardenName = gardenName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Set<User> getGardenMembers() {
        return gardenMembers;
    }

    public void setGardenMembers(Set<User> gardenMembers) {
        this.gardenMembers = gardenMembers;
    }

    public void addGardenMember(User member) {
        this.gardenMembers.add(member);
    }
}
