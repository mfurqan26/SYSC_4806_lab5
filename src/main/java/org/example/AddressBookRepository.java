package org.example;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {
    AddressBook findById(long id);
    AddressBook findAddressBooksById(int id);
}
