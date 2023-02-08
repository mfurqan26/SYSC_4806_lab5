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

    private AddressBookRepository addressBookRepository;
    private AddressBook currentAddressBook;

    @Autowired
    public AddressBookController(AddressBookRepository addressBookRepository){
        this.addressBookRepository = addressBookRepository;
    }

    /** try http://localhost:8080/addressBook/0 */
    @GetMapping("/{id}")
    public String home(@PathVariable int id, Model model) {
        AddressBook AddressBook = addressBookRepository.findAddressBooksById(id);

        if(AddressBook == null){
            AddressBook = new AddressBook(id);
            BuddyInfo buddy1 =  new BuddyInfo("Tom","Carleton","613");
            BuddyInfo buddy2 =  new BuddyInfo("Frank","Carleton","618");
            BuddyInfo buddy3 =  new BuddyInfo("Bob","Carleton","813");

            //Add buddies to address book
            AddressBook.addBuddy(buddy1);AddressBook.addBuddy(buddy2);AddressBook.addBuddy(buddy3);
            addressBookRepository.save(AddressBook);
        }
        model.addAttribute("id", id);
        model.addAttribute("buddies",AddressBook.getbuddyCollection());
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
        model.addAttribute("buddies",AddressBook.getbuddyCollection());
        currentAddressBook = AddressBook;
        return "addressBook";
    }
}
