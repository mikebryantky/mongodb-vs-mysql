# MongoDB vs. MySQL Write Times

## Results


|              | 1,000   | 10,000 | 100,000 | 1,000,000 |
|-------------:|--------:|-------:|--------:|----------:|
| **MongoDB**  | 0.6     | 3.1    | 33.5    | 414.3     |
| **MySQL**    | 1.4     | 8.5    | 81.1    | 886.2     |

Times are in seconds.

_Example: MongoDB inserted 10,000 random records in 3.1 seconds._


## Test System
* MacBook Pro (Mid 2014)
* 2.5 GHz Intel i7, 16 GB RAM, SSD

### MySQL
* Server version: v5.6.30
* InnoDB table
* Unique index on id
* Non-unique index on last_name, birth_date, age

### MongoDB
* Server version: v2.6.7
* Unique index on id
* Non-unique index on lastName, birthDate, age



## Usage
* Must have MongoDB instance running on localhost.
* Must hace MySQL isntance running on localhost, and _perftest_ schema created.
* Set user/pass for MongoDB and/or MySQL as needed.
* This app uses Java 1.8 and [Spring Boot](https://projects.spring.io/spring-boot/).







