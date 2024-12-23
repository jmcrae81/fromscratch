package com.mcrae.app.service;

import com.mcrae.app.entity.Like;
import com.mcrae.app.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    LikeRepository likeRepository;

    @Autowired
    public LikeService(LikeRepository likeRepo){ this.likeRepository = likeRepo; }

    public Like likeMessage(int likedBy, int messageId){
        Like newLike = (Like) this.likeRepository.save( new Like( likedBy, messageId));

        return newLike;
    }

    public void unlikeMessage(Like like){ this.likeRepository.delete(like); }
}
