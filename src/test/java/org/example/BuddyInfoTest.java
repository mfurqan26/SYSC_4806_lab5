package org.example;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BuddyInfoTest {

    @Test
    public void getName() {
        BuddyInfo buddy1 = new BuddyInfo("Muhammad");
        assertEquals("Muhammad",buddy1.getName());
    }

    @Test
    public void getAddress() {
        BuddyInfo buddy1 = new BuddyInfo("","Ottawa");
        assertEquals("Ottawa",buddy1.getAddress());
    }

    @Test
    public void getPhone_number() {
        BuddyInfo buddy1 = new BuddyInfo("","","123");
        assertEquals("123",buddy1.getPhoneNumber());
    }
}