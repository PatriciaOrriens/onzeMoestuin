package groentjes.onzeMoestuin.model;

import javax.persistence.*;

@Entity
public class Garden {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer gardenId;

    private String gardenName;
    private Integer length;
    private Integer width;

    //TODO Change later into @ManyToOne, for several users can make use of one garden
    @OneToOne
    @JoinColumn(name = "ownerId", referencedColumnName = "userId")
    private User user;

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
}
