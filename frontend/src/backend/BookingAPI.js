const axios = require('axios');

const baseURL = "http://localhost:8080/api/booking/"

function registerTicket(ticket) {
    console.log(ticket)
    const {
        firstName,
        secondName,
        lastName,
        secondLastName,
        ci,
        flights,
        type
    } = ticket;

    axios({
        method: "POST",
        url: baseURL,
        data: {
            "user":
                {
                    "id": ci,
                    "firstName": firstName,
                    "secondName": secondName,
                    "firstLastName": lastName,
                    "secondLastName": secondLastName
                },
            "flightId": flights,
            "category": type
        },
        responseType: "json",
    }).catch((error) => {
        console.error(error.message)
    })
}

module.exports = {
    registerTicket: registerTicket
}