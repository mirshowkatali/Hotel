package com.hms.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ROOM")
@Proxy(lazy = false)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"bath", "bed", "internet", "status", "booking", "images", "type"})
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @SafeHtml
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

  
    @Column(name = "PRICE", nullable = false)
    private Integer price;

   
    @Column(name = "CAPACITY", nullable = false)
    private Integer capacity;

    @SafeHtml
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;

    
    @Column(name = "BATH", nullable = false)
    private Boolean bath;

   
    @Column(name = "BED", nullable = false)
    private Integer bed;

   
    @Column(name = "INTERNET", nullable = false)
    private Boolean internet;

    @SafeHtml
    @Column(name = "STATUS")
    private String status;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TYPE", nullable = false)
    private RoomType type;

    @OneToOne(mappedBy = "room", fetch = FetchType.EAGER)
    private Booking booking;

    @OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<RoomImages> images = new ArrayList<RoomImages>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getBath() {
        return bath;
    }

    public void setBath(Boolean bath) {
        this.bath = bath;
    }

    public Integer getBed() {
        return bed;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    public Boolean getInternet() {
        return internet;
    }

    public void setInternet(Boolean internet) {
        this.internet = internet;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<RoomImages> getImages() {
        return images;
    }

    public void setImages(List<RoomImages> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Room))
            return false;
        Room other = (Room) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Room [id=" + id + ", name=" + name +
                ", price=" + price + ", capacity=" + capacity +
                ", description=" + description + ", bath=" + bath +
                ", bed=" + bed + ", internet=" + internet + ", type=" + type +
                ", status=" + status + "]";
    }

}