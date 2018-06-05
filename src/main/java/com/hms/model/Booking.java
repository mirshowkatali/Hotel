package com.hms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "BOOKING")
@Proxy(lazy = false)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"user", "comment"})
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

   
    @OneToOne(fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    @JoinColumn(name = "ROOM_ID", nullable = false)
    private Room room;

    
    @Column(name = "PEOPLE", nullable = false)
    private Integer people;

    
    @Column(name = "ARRIVAL_TIME", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date arrivalTime;

   
    @Column(name = "DEPARTURE_TIME", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private Date departureTime;

    @SafeHtml
    @Column(name = "COMMENT", nullable = true)
    private String comment;

    @NotEmpty
    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "ROOM_BOOKED")
    private String roomBooked;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public String getRoomBooked() {
        return roomBooked;
    }

    public void setRoomBooked(String roomBooked) {
        this.roomBooked = roomBooked;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Booking))
            return false;
        Booking other = (Booking) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (room == null) {
            if (other.room != null)
                return false;
        } else if (!room.equals(other.room))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Booking [id=" + id + ", user=" + user + ", room=" + room + ", people=" + people +
                ", arrivalTime=" + arrivalTime + ", departureTime=" + departureTime + ", comment=" + comment +
                ", status=" + status + ", roomBooked=" + roomBooked + "]";
    }

}
