package groentjes.onzeMoestuin.model;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @Author Patricia Orriens-Spuij
 * Model to send and show messages in a message board of a garden
 */
@Entity
@JsonIgnoreProperties({"garden"})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "senderId", referencedColumnName = "userId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gardenId", referencedColumnName = "gardenId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Garden garden;

    private String messageBody;

    private LocalDateTime dateTime;

    // reply to another message
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "replyToMessage")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Message> replies = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "replyToMessage", referencedColumnName = "messageId")
    private Message replyToMessage;


    public Message() {
    }

    public Message(Integer messageId, User sender, Garden garden, String messageBody, LocalDateTime dateTime) {
        this.messageId = messageId;
        this.sender = sender;
        this.garden = garden;
        this.messageBody = messageBody;
        this.dateTime = dateTime;
    }

    public Message(User sender, Garden garden, String messageBody, LocalDateTime dateTime) {
        this(null, sender, garden, messageBody, dateTime);
    }

    // getters and setters
   public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    // Additional getter to add readable datatime format to JSON
    @JsonFormat(pattern="dd-MM-yyyy (HH:mm)")
    public LocalDateTime getFormattedDateTime() {
        return getDateTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Set<Message> getReplies() {
        return replies;
    }

    public void setReplies(Set<Message> replies) {
        this.replies = replies;
    }

    public Message getReplyToMessage() {
        return replyToMessage;
    }

    public void setReplyToMessage(Message replyToMessage) {
        this.replyToMessage = replyToMessage;
    }


}
