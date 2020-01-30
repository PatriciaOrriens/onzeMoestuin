package groentjes.onzeMoestuin.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author Patricia Orriens-Spuij
 * Model to send and show messages in a message board of a garden
 */
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "senderId", referencedColumnName = "userId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gardenId")
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


    public Message() { }





}
