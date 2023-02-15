package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addressRestBook")
public class AddressBookRestController {

    @Autowired AddressBookRepository addressBookRestRepository;

    public AddressBookRestController(AddressBookRepository addressBookRepository){
        this.addressBookRestRepository = addressBookRepository;
    }

    /** try http://localhost:8080/addressRestBook/ */
    @GetMapping("/")
    public List<AddressBook> home() {
        List<AddressBook> books = addressBookRestRepository.findAll();
        return books;
    }

    /** try http://localhost:8080/addressRestBook/1 */
    @GetMapping("/{id}")
    public AddressBook book(@PathVariable int id) {
        AddressBook book = addressBookRestRepository.findAddressBooksById(id);

        if(book == null){return null;}
        return book;
    }

    @PostMapping(value = "/{id}", params = "AddBuddy")
    public AddressBook addBuddy(@PathVariable int id, @RequestParam(name="name", required=false, defaultValue="") String name, @RequestParam(name="address", required=false, defaultValue="") String address, @RequestParam(name="phoneNumber", required=false, defaultValue="") String phoneNumber) {

        AddressBook book = addressBookRestRepository.findAddressBooksById(id);
        if(book == null){return null;}

        BuddyInfo newBuddy = new BuddyInfo(name,address,phoneNumber);
        book.addBuddy(newBuddy);
        addressBookRestRepository.save(book);
        return book;
    }

    @PostMapping(value = "/{id}", params = "DeleteBuddy")
    public AddressBook deleteBuddy(@PathVariable int id, @RequestParam(name="buddyID") String buddyID) {
        AddressBook book = addressBookRestRepository.findAddressBooksById(id);
        Integer buddy_ID = Integer.parseInt(buddyID);
        if(book == null){return null;}

        BuddyInfo buddy = book.getBuddy(buddy_ID);
        if(buddy == null){return null;}

        book.removeBuddy(buddy_ID);
        addressBookRestRepository.save(book);
        return book;
    }
}
