package com.millcreeksoftware.mongovsmysql.mysql;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(
        name = "customer",
        indexes = {
                @Index(name = "idx_last_name", columnList = "last_name", unique = false),
                @Index(name = "idx_birth_date", columnList = "birth_date", unique = false),
                @Index(name = "idx_age", columnList = "age", unique = false)
        }
)
public class MySQLCustomer {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private Date birthDate;

    @Column(name = "age")
    private Integer age;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
