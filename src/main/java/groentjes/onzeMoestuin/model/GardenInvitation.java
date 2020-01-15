package groentjes.onzeMoestuin.model;

import javax.persistence.*;

/**
 * @author Wim Kruizinga
 */
@Entity
public class GardenInvitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer invitationId;

    @OneToOne
    @JoinColumn(name = "invitedById", referencedColumnName = "userId")
    private User user;

    @OneToOne
    @JoinColumn(name = "invitedUserId", referencedColumnName = "userId")
    private User invitedUser;

    private String emailAddress;

    private boolean accepted;

    @OneToOne
    @JoinColumn(name = "gardenId", referencedColumnName = "gardenId")
    private Garden garden;

    public GardenInvitation() {
    }

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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }
}
