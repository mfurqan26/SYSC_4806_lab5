package org.example;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<AddressBook, Integer> {
    AddressBook findById(long id);
    AddressBook findAddressBooksById(int id);
}
