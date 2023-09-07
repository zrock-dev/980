const axios = require('axios');

axios.defaults.baseURL = 'http://localhost:8080/api'

axios.get('/currency-exchange/dollar', {
    params: {
        amount: Number(99.99)
    }
})
    .then(function (response) {
        console.log("Example of dollar exchange requesting:")
        response = response.data
        console.log(response);
    })
    .catch(function (error) {
        console.log(error)
    });


axios.get('/currency-exchange/euro', {
    params: {
        amount: Number(99.99)
    }
})
    .then(function (response) {
        console.log("Example of euro exchange requesting")
        response = response.data
        console.log(response);
    })
    .catch(function (error) {
        console.log(error)
    });

axios.get('/currency-exchange/bolivian', {
    params: {
        amount: Number(99.99)
    }
})
    .then(function (response) {
        console.log("Example of bolivian exchange requesting")
        response = response.data
        console.log(response);
    })
    .catch(function (error) {
        console.log(error)
    });


axios.get('/users/search', {
    params: {
        name:"Aaron"
    }
})
    .then(function (response) {
        response = response.data
        console.log(response);
    })
    .catch(function (error) {

    });
