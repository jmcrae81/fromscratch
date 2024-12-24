package com.mcrae.app.entity;

import javax.persistence.*;

@Entity
@Table(name="likes")
public class Like {
    private Integer likedBy;

    @Id
    private Integer likedMessageId;

    public Like(Integer likedBy, Integer likedMessageId){
        this.likedBy = likedBy;
        this.likedMessageId = likedMessageId;
    }


}
