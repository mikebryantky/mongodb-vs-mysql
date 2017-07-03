# MongoDB vs. MySQL Write Times

## Results

### Settings
* **runCount** = 4 
* **initialNumberRecords** = 1000

### Runs
* First run: 1,000 random records were added.
* Second run: 10,000 random records were added.
* Third run: 100,000 random records were added.
* Fourth run: 1,000,000 random records were added.

### Run Times
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
* application.yaml: _initialNumberRecords_ is the, er, initial number of records to add.
* application.yaml: A loop will run _runCount_ times, each time the value of _initialNumberRecords_ is multipled by 10. 
* Must have MongoDB instance running on localhost.
* Must have MySQL isntance running on localhost, and _perftest_ schema created.
* Set user/pass for MongoDB and/or MySQL as needed.



## Notes
* There is duplicated code in this repo, which is by design.  Typically, I would 
  make this code more generic with Interfaces, etc. but the idea here is simplicity in 
  readability. Futher, this is, at best, v0.0.0.1.
* This app uses Java 1.8, [Spring Boot](https://projects.spring.io/spring-boot/), and Maven.




