package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired AddressBookRepository addressBookRepository;

    public AddressBookController(AddressBookRepository addressBookRepository){
        this.addressBookRepository = addressBookRepository;
    }

    /** try http://localhost:8080/addressBook/ */
    @GetMapping("/")
    public String home(Model model) {
        List<AddressBook> books = addressBookRepository.findAll();
        model.addAttribute("addressBook", books);
        return "home";
    }

    /** try http://localhost:8080/addressBook/ */
    @GetMapping("/index")
    public String index(Model model) {

        return "redirect:/index.html";
    }

    @PostMapping(value = "/", params = "newBook")
    public String addBook(Model model) {
        AddressBook newBook = new AddressBook();
        addressBookRepository.save(newBook);
        List<AddressBook> books = addressBookRepository.findAll();
        model.addAttribute("addressBook", books);
        return "home";
    }


    /** try http://localhost:8080/addressBook/1 */
    @GetMapping("/{id}")
    public String book(@PathVariable int id, Model model) {
        AddressBook AddressBook = addressBookRepository.findAddressBooksById(id);

        if(AddressBook == null){
            model.addAttribute("text","Can not find an AddressBook for this id:"+id+"\nReturn back to home page.");
            return "error";
        }
        model.addAttribute("id", id);
        model.addAttribute("buddies",AddressBook.getBuddyCollection());
        return "addressBook";
    }

    @PostMapping(value = "/{id}", params = "AddBuddy")
    public String addBuddy(@PathVariable int id, @RequestParam(name="name", required=false, defaultValue="") String name, @RequestParam(name="address", required=false, defaultValue="") String address, @RequestParam(name="phoneNumber", required=false, defaultValue="") String phoneNumber, Model model) {

        AddressBook book = addressBookRepository.findAddressBooksById(id);
        if(book == null){
            model.addAttribute("text","Can not find an AddressBook for this id:"+id+"\nReturn back to home page.");
            return "error";
        }

        BuddyInfo newBuddy = new BuddyInfo(name,address,phoneNumber);
        book.addBuddy(newBuddy);
        addressBookRepository.save(book);

        model.addAttribute("id", id);
        model.addAttribute("buddies",book.getBuddyCollection());
        return "addressBook";
    }

    @PostMapping(value = "/{id}", params = "DeleteBuddy")
    public String deleteBuddy(@PathVariable int id, @RequestParam(name="buddyID") String buddyID,Model model) {
        AddressBook book = addressBookRepository.findAddressBooksById(id);
        Integer buddy_ID = Integer.parseInt(buddyID);
        if(book == null){
            model.addAttribute("text","Can not find an AddressBook for this id:"+id+"\nReturn back to home page.");
            return "error";
        }

        BuddyInfo buddy = book.getBuddy(buddy_ID);
        if(buddy == null){
            model.addAttribute("text","Can not find a Buddy for this id:"+buddy_ID+"\nReturn back to home page.");
            return "error";
        }

        book.removeBuddy(buddy_ID);
        addressBookRepository.save(book);

        model.addAttribute("id", id);
        model.addAttribute("buddies",book.getBuddyCollection());
        return "addressBook";
    }
}
