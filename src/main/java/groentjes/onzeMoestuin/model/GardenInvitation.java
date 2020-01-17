package groentjes.onzeMoestuin.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * @author Wim Kruizinga
 */
@Entity
public class GardenInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invitationId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "invitedById", referencedColumnName = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "invitedUserId", referencedColumnName = "userId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User invitedUser;

    @Email
    private String emailAddress;

    private Boolean accepted;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gardenId", referencedColumnName = "gardenId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Garden garden;

    public GardenInvitation() {}

    public Integer getInvitationId() {
        return invitationId;
    }

    public void setInvitationId(Integer invitationId) {
        this.invitationId = invitationId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getInvitedUser() {
        return invitedUser;
    }

    public void setInvitedUser(User invitedUser) {
        this.invitedUser = invitedUser;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

}
