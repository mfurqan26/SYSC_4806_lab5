$(document).ready(function() {
    $.ajax({
        type : 'GET',
        url: "http://localhost:8080/addressRestBook/"
    }).then(function (data) {
        for (let i = 0; i < data.length; i++) {
            let id = data[i].id;
            let buddies = data[i].buddyCollection;
            let numBuddies = buddies.length;
            $('.tbody').append("<tr><td>"+id+"</td> <td>"+numBuddies+"</td></tr><br>");

            for (let j = 0; j < numBuddies; j++) {
                let buddyID = buddies[j].id;
                let name = buddies[j].name;
                let address = buddies[j].address;
                let phoneNumber = buddies[j].phoneNumber;

                $('.tbody2').append("<tr><td>"+buddyID+"</td> <td>"+name+"</td> <td>"+address+"</td> <td>"+phoneNumber+"</td></tr>" +
                    "<br>");
            }
        }
    });
    $.ajax({
        type : 'POST',
        params: 'newBook',
        url: "http://localhost:8080/addressRestBook/"
    }).then(function (data) {
        let id = data.id;
        let numBuddies = data.buddyCollection.length;
        $('.tbody').append("<tr><td>"+id+"</td> <td>"+numBuddies+"</td></tr><br>");
    });
});