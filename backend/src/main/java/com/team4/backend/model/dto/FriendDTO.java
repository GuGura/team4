package com.team4.backend.model.dto;


import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class  FriendDTO {

    private int FRIEND_UID;
    private int FRIEND_SENDER;
    private int FRIEND_RECEIVER;
    private boolean FRIEND_CHECKED;
    private Timestamp FRIEND_DATE;

    private String USERNAME;

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public int getFRIEND_UID() {
        return FRIEND_UID;
    }

    public void setFRIEND_UID(int FRIEND_UID) {
        this.FRIEND_UID = FRIEND_UID;
    }

    public int getFRIEND_SENDER() {
        return FRIEND_SENDER;
    }

    public void setFRIEND_SENDER(int FRIEND_SENDER) {
        this.FRIEND_SENDER = FRIEND_SENDER;
    }

    public int getFRIEND_RECEIVER() {
        return FRIEND_RECEIVER;
    }

    public void setFRIEND_RECEIVER(int FRIEND_RECEIVER) {
        this.FRIEND_RECEIVER = FRIEND_RECEIVER;
    }

    public boolean isFRIEND_CHECKED() {

        return FRIEND_CHECKED;
    }

    public void setFRIEND_CHECKED(boolean FRIEND_CHECKED) {
        this.FRIEND_CHECKED = FRIEND_CHECKED;
    }

    public Timestamp getFRIEND_DATE() {
        return FRIEND_DATE;
    }


    public void setFRIEND_DATE(Timestamp FRIEND_DATE) {
        this.FRIEND_DATE = FRIEND_DATE;
    }


}
