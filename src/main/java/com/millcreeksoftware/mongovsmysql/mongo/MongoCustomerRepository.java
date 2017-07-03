package com.millcreeksoftware.mongovsmysql.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MongoCustomerRepository extends MongoRepository<MongoCustomer, String> {

    List<MongoCustomer> findByLastName(String lastName);
}
