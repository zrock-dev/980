const axios = require('axios');

const baseURL = "http://localhost:8080/api/booking"
async function requestAvailableFlights(pageNumber) {
    try {
        const response = await axios({
            method: "GET",
            url: `${baseURL}/flights?page=${pageNumber}`,
            responseType: "json"
        });

        return response.data.elements;
    } catch (error) {
        throw error;
    }
}

async function requestFlight(flightId){
    const response = await axios({
        method: "GET",
        url: `${baseURL}/flights/${flightId}`,
        responseType: "json"
    })
    return {
        "id": response.data.id,
        "source": response.data.source,
        "destination": response.data.destination,
    }
}

module.exports = {
    requestAvailableFlights: requestAvailableFlights,
    requestFlight: requestFlight,
}