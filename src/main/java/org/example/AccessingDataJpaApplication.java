package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccessingDataJpaApplication {

    private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(AccessingDataJpaApplication.class);
    }

    @Bean
    public CommandLineRunner demo(AddressBookRepository repository1) {
        return (args) -> {

            AddressBook addressBook = new AddressBook();

            BuddyInfo buddy1 =  new BuddyInfo("Tom","Carleton","613");
            BuddyInfo buddy2 =  new BuddyInfo("Frank","Carleton","618");
            BuddyInfo buddy3 =  new BuddyInfo("Bob","Carleton","813");
            BuddyInfo buddy4 =  new BuddyInfo("Tom","Carleton","913");

            //Add buddies to address book
            addressBook.addBuddy(buddy1);
            addressBook.addBuddy(buddy2);
            addressBook.addBuddy(buddy3);
            addressBook.addBuddy(buddy4);

            repository1.save(addressBook);

            //fetch all AddressBook
            log.info("AddressBook found with findAll():");
            log.info("-------------------------------");
            for (AddressBook AddressBook : repository1.findAll()) {
                log.info(AddressBook.toString());
            }
            log.info("");
        };
    }
}