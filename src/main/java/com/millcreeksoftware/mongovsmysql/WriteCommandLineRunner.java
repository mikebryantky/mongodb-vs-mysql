package com.millcreeksoftware.mongovsmysql;

import com.millcreeksoftware.mongovsmysql.mongo.MongoCustomer;
import com.millcreeksoftware.mongovsmysql.mongo.MongoCustomerRepository;
import com.millcreeksoftware.mongovsmysql.mysql.MySQLCustomer;
import com.millcreeksoftware.mongovsmysql.mysql.MySQLCustomerRepository;
import io.codearte.jfairy.Fairy;
import io.codearte.jfairy.producer.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Order(1)
@Component
public class WriteCommandLineRunner implements CommandLineRunner {
    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Value("${initialNumberRecords}")
    private long initialNumberRecords;

    @Value("${runCount}")
    private long runCount;

    @Value("${lastName}")
    String lastName;

    @Autowired
    private MongoCustomerRepository mongoCustomerRepository;

    @Autowired
    private MySQLCustomerRepository mySQLCustomerRepository;


    @Override
    public void run(String... strings) throws Exception {
        logger.info("WriteCommandLineRunner");

        long numberRecords = initialNumberRecords;
        for(long i=1; i<=runCount; i++) {
            mongoCustomerRepository.deleteAll();
            loadMongoDb(numberRecords);
            searchMongoDb(numberRecords);
            numberRecords *= 10;
        }

        numberRecords = initialNumberRecords;
        for(long i=1; i<=runCount; i++) {
            mySQLCustomerRepository.deleteAll();
            loadMySQL(numberRecords);
            numberRecords *= 10;
        }
    }

    private void searchMongoDb(long numberRecords) {
        // Find to account for any internal caching. The second
        // lookup is the one that we will time.
        mongoCustomerRepository.findByLastName(lastName);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        mongoCustomerRepository.findByLastName(lastName);
        stopWatch.stop();
        logger.info("MongoDB: Finding " + lastName + " in "
                + numberRecords + " took "
                + stopWatch.getLastTaskTimeMillis() + " " + "millis.");
    }

    private void loadMongoDb(long numberRecords) {
        Fairy fairy = Fairy.create();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (long i = 1; i < numberRecords; i++) {
            Person person = fairy.person();
            String uuid = UUID.randomUUID().toString();

            MongoCustomer mongoCustomer = new MongoCustomer();
            mongoCustomer.setId(uuid);
            mongoCustomer.setFirstName(person.firstName());
            mongoCustomer.setLastName(person.lastName());
            mongoCustomer.setBirthDate(person.dateOfBirth().toDate());
            mongoCustomer.setAge(person.age());
            mongoCustomerRepository.save(mongoCustomer);
        }

        MongoCustomer mongoCustomer = new MongoCustomer();
        mongoCustomer.setId(UUID.randomUUID().toString());
        mongoCustomer.setFirstName("AAA");
        mongoCustomer.setLastName(lastName);
        mongoCustomer.setBirthDate(Date.from(Instant.now().minus(Duration.ofDays(365))));
        mongoCustomer.setAge(1);
        mongoCustomerRepository.save(mongoCustomer);
        stopWatch.stop();
        logger.info("MongoDB: Added " + numberRecords + " customers in " + stopWatch.getLastTaskTimeMillis() + " millis.");
    }

    private void searchMySQL(long numberRecords) {
        // Find to account for any internal caching. The second
        // lookup is the one that we will time.
        mySQLCustomerRepository.findByLastName(lastName);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        mySQLCustomerRepository.findByLastName(lastName);
        stopWatch.stop();
        logger.info("MySQL: Finding " + lastName + " in "
                + numberRecords + " took "
                + stopWatch.getLastTaskTimeMillis() + " " + "millis.");
    }

    private void loadMySQL(long numberRecords) {
        Fairy fairy = Fairy.create();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (long i = 1; i < numberRecords; i++) {
            Person person = fairy.person();
            String uuid = UUID.randomUUID().toString();

            MySQLCustomer mySQLCustomer = new MySQLCustomer();
            mySQLCustomer.setId(uuid);
            mySQLCustomer.setFirstName(person.firstName());
            mySQLCustomer.setLastName(person.lastName());
            mySQLCustomer.setBirthDate(person.dateOfBirth().toDate());
            mySQLCustomer.setAge(person.age());
            mySQLCustomerRepository.save(mySQLCustomer);
        }

        MySQLCustomer mySQLCustomer = new MySQLCustomer();
        mySQLCustomer.setId(UUID.randomUUID().toString());
        mySQLCustomer.setFirstName("AAA");
        mySQLCustomer.setLastName(lastName);
        mySQLCustomer.setBirthDate(Date.from(Instant.now().minus(Duration.ofDays(365))));
        mySQLCustomer.setAge(1);
        mySQLCustomerRepository.save(mySQLCustomer);

        stopWatch.stop();
        logger.info("MySQL: Added " + numberRecords + " customers in " + stopWatch.getLastTaskTimeMillis() + " millis.");
    }

}
