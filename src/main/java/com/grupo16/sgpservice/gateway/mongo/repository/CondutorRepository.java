package com.grupo16.sgpservice.gateway.mongo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.grupo16.sgpservice.gateway.mongo.document.CondutorDocument;

public interface CondutorRepository extends MongoRepository<CondutorDocument, String>{

}