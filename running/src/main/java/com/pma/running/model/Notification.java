package com.pma.running.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class Notification implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Date timestamp;
    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;
    private String description;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Notification() {
    }

    public Notification(Date timestamp, NotificationType notificationType, String description, User user) {
        this.timestamp = timestamp;
        this.notificationType = notificationType;
        this.description = description;
        this.user = user;
    }

    public Notification(Date timestamp, NotificationType notificationType, String description) {
        this.timestamp = timestamp;
        this.notificationType = notificationType;
        this.description = description;
    }
    public Notification(Long id, Date timestamp, NotificationType notificationType, String description) {
        this.id = id;
        this.timestamp = timestamp;
        this.notificationType = notificationType;
        this.description = description;
    }

}