import axios from 'axios';

export const getFlights = async (page) => {
	return await axios.get(
		`http://localhost:8080/api/booking/flights?page=${page}`
	);
};

export const booking = async (formData) => {
	return await axios.post('http://localhost:8080/api/booking', {
		user: {
			id: formData.ci,
			firstName: formData.firstName,
			secondName: formData.secondName,
			firstLastName: formData.lastName,
			secondLastName: formData.secondLastName,
			dateOfBirth: formData.dateOfBirth,
			country: formData.country
		},
		flightId: formData.flightId,
		category: formData.category
	});
};
