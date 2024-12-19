package com.mcrae.app.entity;

import javax.persistence.*;

@Entity
public class Like {
    private Integer likedBy;
    private Integer likedMessageId;

    public Like(Integer likedBy, Integer likedMessageId){
        this.likedBy = likedBy;
        this.likedMessageId = likedMessageId;
    }
}
