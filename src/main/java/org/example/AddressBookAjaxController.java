package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/index.html")
public class AddressBookAjaxController {
    @Autowired AddressBookRepository addressBookRepository;

    /** try http://localhost:8080/addressBook/index */
    @GetMapping("/")
    public String index() {
        return "redirect:/index.html";
    }

    @PostMapping(value = "/", params = "newBook")
    public String addBook() {
        AddressBook newBook = new AddressBook();
        addressBookRepository.save(newBook);
        return "redirect:/index.html";
    }

}
