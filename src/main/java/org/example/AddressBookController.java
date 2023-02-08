package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/addressBook")
public class AddressBookController {

    private final AddressBookRepository addressBookRepository;
    private AddressBook currentAddressBook;

    public AddressBookController(AddressBookRepository addressBookRepository){
        this.addressBookRepository = addressBookRepository;
        currentAddressBook = new AddressBook();
    }

    /** try http://localhost:8080/addressBook */
    @GetMapping("/")
    public String initial(Model model) {
        currentAddressBook = new AddressBook(0);
        model.addAttribute("id", currentAddressBook.getId());
        model.addAttribute("buddies",currentAddressBook.getBuddyCollection());
        return "addressBook";
    }

    /** try http://localhost:8080/addressBook/0 */
    @GetMapping("/{id}")
    public String home(@PathVariable int id, Model model) {
        AddressBook AddressBook = addressBookRepository.findAddressBooksById(id);

        if(AddressBook == null){
            AddressBook = new AddressBook(id);
            addressBookRepository.save(AddressBook);
        }
        model.addAttribute("id", id);
        model.addAttribute("buddies",AddressBook.getBuddyCollection());
        currentAddressBook = AddressBook;
        return "addressBook";
    }

    @PostMapping("/{id}")
    public String addBuddy(@PathVariable int id, @RequestParam(name="name", required=false, defaultValue="") String name, @RequestParam(name="address", required=false, defaultValue="") String address, @RequestParam(name="phoneNumber", required=false, defaultValue="") String phoneNumber, Model model) {

        AddressBook AddressBook = addressBookRepository.findAddressBooksById(id);
        if(AddressBook == null){AddressBook = currentAddressBook;}

        BuddyInfo newBuddy = new BuddyInfo(name,address,phoneNumber);
        AddressBook.addBuddy(newBuddy);
        model.addAttribute("id", id);
        model.addAttribute("buddies",AddressBook.getBuddyCollection());
        currentAddressBook = AddressBook;
        return "addressBook";
    }

    @DeleteMapping("/{id}/{buddyID}")
    public String deleteEmployee(@PathVariable int id, @PathVariable int buddyID, Model model) {
        AddressBook AddressBook = addressBookRepository.findAddressBooksById(id);
        if(AddressBook == null){AddressBook = currentAddressBook;}

        AddressBook.removeBuddy(buddyID);
        model.addAttribute("id", id);
        model.addAttribute("buddies",AddressBook.getBuddyCollection());
        currentAddressBook = AddressBook;
        return "addressBook";
    }
}
