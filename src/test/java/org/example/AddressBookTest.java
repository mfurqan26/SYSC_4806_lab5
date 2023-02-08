package org.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddressBookTest {

    @Test
    public void addBuddy() {
        BuddyInfo buddy =  new BuddyInfo("Furqan","Ottawa","987");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        assertEquals("Furqan",addressBook.getBuddyCollection().get(0).getName());
    }

    @Test
    public void removeBuddy() {
        BuddyInfo buddy =  new BuddyInfo("Furqan","Ottawa","987");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(0);
        assertEquals(0,addressBook.getBuddyCollection().size());
    }
}