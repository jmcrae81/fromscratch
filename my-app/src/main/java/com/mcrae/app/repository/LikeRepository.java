package com.mcrae.app.repository;

import com.mcrae.app.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long>{
    public List<Like> findAllBylikedMessageId(int likedMessageId);
}
