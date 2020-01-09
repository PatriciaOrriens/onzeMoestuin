package groentjes.onzeMoestuin.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gardenId;

    private String gardenName;
    private Integer length;
    private Integer width;

//    //TODO Change later into @ManyToOne, for several users can make use of one garden
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
