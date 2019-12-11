package groentjes.onzeMoestuin.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity (name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @Column(unique = true)
    private String userName;

    private String password;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
