package org.example;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="AddressBook")
public class AddressBook {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddyCollection;

    /** Creates a new instance of AddressBook */
    public AddressBook() {buddyCollection = new ArrayList<>();}

    public AddressBook(int id) {
        buddyCollection = new ArrayList<>();
        this.id = id;
        BuddyInfo buddy1 =  new BuddyInfo("Tom","Carleton","613");
        BuddyInfo buddy2 =  new BuddyInfo("Frank","Carleton","618");
        BuddyInfo buddy3 =  new BuddyInfo("Bob","Carleton","813");
        //Add buddies to address book
        buddyCollection.add(buddy1);
        buddyCollection.add(buddy2);
        buddyCollection.add(buddy3);
    }

    public void setbuddyCollection(List<BuddyInfo> buddyCollection) {this.buddyCollection = buddyCollection;}

    public void addBuddy(BuddyInfo buddy){
        buddyCollection.add(buddy);
    }
    public void removeBuddy(Integer buddyID){
        for (int i = 0; i<buddyCollection.size(); i++){
            BuddyInfo buddy = buddyCollection.get(i);
            if(buddy.getId() == buddyID){
                buddyCollection.remove(buddy);
            }
        }
    }

    public BuddyInfo getBuddy(Integer buddyID){
        for (int i = 0; i<buddyCollection.size(); i++){
            BuddyInfo buddy = buddyCollection.get(i);
            if(buddy.getId() == buddyID){
                return buddy;
            }
        }
        return null;
    }

    public List<BuddyInfo> getBuddyCollection(){return buddyCollection;}

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        AddressBook other = (AddressBook) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        String returnString = "Address Book id: "+id+"\n";
        for (int i = 0; i < buddyCollection.size(); i++) {
            returnString += "Buddy id = " + buddyCollection.get(i).getId() + ", name = " + buddyCollection.get(i).getName() + ", address = " + buddyCollection.get(i).getAddress() + ", phone = " + buddyCollection.get(i).getPhoneNumber()+"\n";
        }
        return returnString;
    }


    public static void main(String[] args) {
        BuddyInfo buddy =  new BuddyInfo("Tom","Carleton","613");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(buddy);
        addressBook.removeBuddy(0);
        BuddyInfo buddy2 =  new BuddyInfo("Frank","Carleton","618");
        addressBook.addBuddy(buddy2);
        BuddyInfo buddy3 =  new BuddyInfo("Bob","Carleton","813");
        addressBook.addBuddy(buddy3);
    }
}