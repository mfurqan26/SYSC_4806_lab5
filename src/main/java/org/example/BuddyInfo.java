package org.example;
//Name: Muhammad Furqan, 101162068

import jakarta.persistence.*;

@Entity
@Table(name="BuddyInfo")
public class BuddyInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "address_book_id")
    private AddressBook addressBook;

    public AddressBook getAddressBook() {
        return addressBook;
    }

    public void setAddressBook(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    public BuddyInfo(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public BuddyInfo(){
        this("","","");
    }

    public BuddyInfo(String name){
        this(name,"","");
    }

    public BuddyInfo(String name, String address){
        this(name, address,"");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {this.name = name;}

    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getPhoneNumber(){return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BuddyInfo other = (BuddyInfo) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Buddy id = " + id + ", name = " + name + ", address = " + address + ", phone = " + phoneNumber;
    }

}
