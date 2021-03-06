package groentjes.onzeMoestuin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.*;

/**
 * @author Wim Kruizinga and Gjalt Wybenga
 */
@Entity (name = "User")
@JsonIgnoreProperties({"joinedGardens", "password", "role"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true)
    @Size(min = 3, message = "Gebruikersnaam moet minimaal 3 tekens lang zijn")
    private String username;

    @Size(min = 3, message = "Wachtwoord moet minimaal 3 tekens lang zijn")
    private String password;

    @Column(unique = true)
    @Email(message = "Geen geldig e-mailadres")
    private String email;

    private String firstName;

    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            mappedBy = "gardenMembers")
    private Set<Garden> joinedGardens = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.DETACH,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_role",
            joinColumns = { @JoinColumn(name = "user_Id") },
            inverseJoinColumns = { @JoinColumn(name = "role_Id") })
    private Set<Role> role = new HashSet<>();

    public User() {
    }

    public User(String username, String password, String email) {
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Garden> getJoinedGardens() {
        return joinedGardens;
    }

    public void setJoinedGardens(Set<Garden> joinedGardens) {
        this.joinedGardens = joinedGardens;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }
}