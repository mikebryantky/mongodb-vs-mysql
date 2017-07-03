package com.millcreeksoftware.mongovsmysql.mysql;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MySQLCustomerRepository extends CrudRepository<MySQLCustomer, String> {
    List<MySQLCustomer> findByLastName(String lastName);
}
