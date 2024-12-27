package com.mcrae.app.repository;

import com.mcrae.app.entity.AuthDto;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepository extends MongoRepository<AuthDto, ObjectId>{

}
