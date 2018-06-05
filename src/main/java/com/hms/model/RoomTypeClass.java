package com.hms.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public enum RoomTypeClass implements Serializable {
    FAMILY("FAMILY"),
    DELUXE("DELUXE"),
    EXECUTIVE("EXECUTIVE");

    String roomTypeClass;

    private RoomTypeClass(String roomTypeClass) {
        this.roomTypeClass = roomTypeClass;
    }

    public String getRoomTypeClass() {
        return roomTypeClass;
    }
}