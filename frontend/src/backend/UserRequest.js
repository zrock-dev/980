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
	console.log({
		oldData: oldData,
		newData: {
			...newData, id: parseInt(newData.id)
		}
	})
	return await axios.put(`${CLIENT_DOMAIN}/api/users/`, {
		oldData: oldData,
		newData: {
			...newData, id: parseInt(newData.id)
		}
	});
};
