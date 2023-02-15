$(document).ready(function() {
    $.ajax({
        type : 'GET',
        url: "http://localhost:8080/addressRestBook/"
    }).then(function (data) {
        for (let i = 0; i < data.length; i++) {
            let id = data[i].id;
            let buddies = data[i].buddyCollection;
            let numBuddies = buddies.length;
            $('.tbody').append("Address Book ID: "+id+", Number of Buddies "+numBuddies).append("<br>");

            for (let j = 0; j < numBuddies; j++) {
                let buddyID = buddies[j].id;
                let name = buddies[j].name;
                let address = buddies[j].address;
                let phoneNumber = buddies[j].phoneNumber;

                $('.tbody2').append("Buddy ID: "+buddyID+", Name: "+name+", Address: "+address+", Phone Number: "+phoneNumber).append("<br>");
            }
        }
    });
});