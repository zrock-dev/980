import axios from 'axios';
import { CLIENT_DOMAIN } from './Requester';

export const getUserInformation = async (
	userId,
	firstName,
	secondName,
	firstLastName,
	secondLastName
) => {
	const response = await axios.get(
		`${CLIENT_DOMAIN}/api/users/${userId}?fn=${firstName}&sn=${secondName}&lfn=${firstLastName}&lsn=${secondLastName}`
	);
	return response;
};

export const deleteUser = async (
	userId,
	firstName,
	secondName,
	firstLastName,
	secondLastName
) => {
	return await axios.delete(
		`${CLIENT_DOMAIN}/api/users/${userId}?fn=${firstName}&sn=${secondName}&lfn=${firstLastName}&lsn=${secondLastName}`
	);
};

export const getUserTickets = async (userId, page) => {
	return await axios.get(
		`${CLIENT_DOMAIN}/api/booking/user-tickets/${userId}?page=${page}`
	);
};

export const getFlagImage = async (countryName) => {
	const response = await axios.get(
		`https://restcountries.com/v3.1/name/${countryName}?fields=flags`
	);
	const imagePng = response.data[0].flags.png;
	return imagePng;
};

export const editUser = async (oldData, newData) => {
	return await axios.put(`${CLIENT_DOMAIN}/api/users`, {
		oldData: {
			id: parseInt(oldData.id),
			firstName: oldData.firstName,
			secondName: oldData.secondName,
			firstLastName: oldData.firstLastName,
			secondLastName: oldData.secondLastName,
			dateBirth: oldData.dateBirth,
			flights: oldData.flights,
			country: oldData.country,
			fullName:
				oldData.firstName +
				oldData.secondName +
				oldData.firstLastName +
				oldData.secondLastName
		},
		newData: {
			id: parseInt(newData.id),
			firstName: newData.firstName,
			secondName: newData.secondName,
			firstLastName: newData.firstLastName,
			secondLastName: newData.secondLastName,
			dateBirth: newData.dateBirth,
			flights: newData.flights,
			country: newData.country,
			fullName:
				newData.firstName +
				newData.secondName +
				newData.firstLastName +
				newData.secondLastName
		}
	});
};
