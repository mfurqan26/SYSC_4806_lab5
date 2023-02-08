package org.example;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

import jakarta.persistence.*;

public class JPATest {
    @Test
    public void JPATest1() {
        BuddyInfo buddy1 = new BuddyInfo();
        buddy1.setName("Bob"); buddy1.setAddress("Ottawa"); buddy1.setPhoneNumber("123");

        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Persisting the BuddyInfo entity objects
        em.persist(buddy1);
        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT p FROM BuddyInfo p");

        @SuppressWarnings("unchecked")
        List<BuddyInfo> results = q.getResultList();

        Boolean condition1 = buddy1.toString().equals(results.get(0).toString());
        System.out.println("Test 1: "+results.get(0).toString());

        assertTrue(condition1);

        // Closing connection
        em.close();
        emf.close();
    }

    @Test
    public void JPATest2() {
        AddressBook addressBook = new AddressBook();

        BuddyInfo buddy1 =  new BuddyInfo("Tom","Carleton","613");
        BuddyInfo buddy2 =  new BuddyInfo("Frank","Carleton","618");
        BuddyInfo buddy3 =  new BuddyInfo("Bob","Carleton","813");

        //Add buddies to address book
        addressBook.addBuddy(buddy1);
        addressBook.addBuddy(buddy2);
        addressBook.addBuddy(buddy3);

        // Connecting to the database through EntityManagerFactory
        // connection details loaded from persistence.xml
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");
        EntityManager em = emf.createEntityManager();

        // Creating a new transaction
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        // Persisting the AddressBook and BuddyInfo entity objects
        em.persist(addressBook);

        buddy1.setAddressBook(addressBook);
        em.persist(buddy1);

        buddy2.setAddressBook(addressBook);
        em.persist(buddy2);

        buddy3.setAddressBook(addressBook);
        em.persist(buddy3);

        tx.commit();

        // Querying the contents of the database using JPQL query
        Query q = em.createQuery("SELECT p FROM AddressBook p");

        @SuppressWarnings("unchecked")
        List<AddressBook> results = q.getResultList();

        Boolean condition1 = addressBook.toString().equals(results.get(0).toString());
        System.out.println("Test 2: "+results.get(0).toString());

        assertTrue(condition1);

        // Closing connection
        em.close();
        emf.close();
    }
}
