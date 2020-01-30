package groentjes.onzeMoestuin.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "senderId", referencedColumnName = "userId")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User sender;

    //many-to-one relatie (moet ook in garden worden gezet!)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gardenId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Garden garden;

    private String messageBody;

    // TODO: is dit de beste optie?
    private LocalDateTime dateTime;

    // reply to another message
    // many-to-many to Message
    //@ManyToMany
    Message rootMessage;


    public Message() { }





}
