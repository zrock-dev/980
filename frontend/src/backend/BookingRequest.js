import axios from 'axios';
import { CLIENT_DOMAIN } from './Requester';

export const getFlights = async (page) => {
	return await axios.get(`${CLIENT_DOMAIN}/api/booking/flights?page=${page}`);
};

export const booking = async (formData) => {
	return await axios.post(`${CLIENT_DOMAIN}/api/booking`, {
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

export const editBooking = async (ticketId, newCategory) => {
	return await axios.put(`${CLIENT_DOMAIN}/api/booking/${ticketId}`, {
		newCategory
	});
};

export const deleteBooking = async (user, ticketId) => {
	return await axios.delete(
		`${CLIENT_DOMAIN}/api/booking/${ticketId}/${user.id}?fn=${user.firstName}&sn=${user.secondName}&lfn=${user.firstLastName}&lsn=${user.secondLastName}`
	);
};
