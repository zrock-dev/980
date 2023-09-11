import axios from 'axios';

export const getUserSuggestion = async (inputField) => {
	// const {data} = await api.post('/api/users/suggestions', { inputField });

	const data = [
		'Luiggy Mamani',
		'Luiggy Mamani',
		'Luiggy Mamani',
		'Luiggy Mamani',
		'Luiggy Mamani',
		'Luiggy Mamani',
		'Luiggy Mamani',
		'Luiggy Mamani',
		'Luiggy Mamani',
		'Luiggy Mamani'
	];
	inputField.replace(/\s+/g, '').length > 0 && data.push(inputField);

	return data;
};

export const getUserInformation = async (
	userId,
	firstName,
	secondName,
	firstLastName,
	secondLastName
) => {
	const response = await axios.get(
		`http://localhost:8080/api/users/${userId}?fn=${firstName}&sn=${secondName}&lfn=${firstLastName}&lsn=${secondLastName}`
	);
	return response;
	// return {
	// 	data: {
	// 		id: 1234,
	// 		firstName: 'Luiggy',
	// 		secondName: '',
	// 		firstLastName: 'Mamani',
	// 		secondLastName: 'Condori',
	// 		fullName: 'Luiggy Mamani Condori',
	// 		country: 'USA',
	// 		dateBirth: '1993-10-21T13:28:06.419Z',
	// 		flights: []
	// 	},
	// 	status: 202
	// };
};

export const deleteUser = async (
	userId,
	firstName,
	secondName,
	firstLastName,
	secondLastName
) => {
	return await axios.delete(
		`http://localhost:8080/api/users/${userId}?fn=${firstName}&sn=${secondName}&lfn=${firstLastName}&lsn=${secondLastName}`
	);
};

export const getUserTickets = async (userId, page) => {
	// replace this for fetch peticion
	return await axios.get(
		`http://localhost:8080/api/booking/user-tickets/${userId}?page=${page}`
	);
	// console.log(response);
	// return {
	// 	data: [
	// 		{
	// 			id: 'unique-ticket1',
	// 			number: 1,
	// 			priority: 'VIP',
	// 			date: '2023-09-10T13:28:06.419Z',
	// 			flightId: 'flightid1',
	// 			price: 1500,
	// 			source: {
	// 				airportName: 'Source airport',
	// 				country: 'Bolivia',
	// 				state: 'La paz'
	// 			},
	// 			destination: {
	// 				airportName: 'Destination skndvsndvsdvsdv airport',
	// 				country: 'Argentina',
	// 				state: 'Buenos Aires'
	// 			}
	// 		},
	// 		{
	// 			id: 'unique-ticket2',
	// 			number: 2,
	// 			priority: 'Regular passager',
	// 			date: '2023-09-30T13:28:06.419Z',
	// 			flightId: 'flightid1',
	// 			price: 3500,
	// 			source: {
	// 				airportName: 'Source skndvsndvsdvsdv airport',
	// 				country: 'France',
	// 				state: 'Paris'
	// 			},
	// 			destination: {
	// 				airportName: 'Destination ajsnakjsbas airport',
	// 				country: 'Italy',
	// 				state: 'Roma'
	// 			}
	// 		}
	// 	],
	// 	status: 202
	// };
};

export const getFlagImage = async (countryName) => {
	const response = await axios.get(
		`https://restcountries.com/v3.1/name/${countryName}?fields=flags`
	);
	const imagePng = response.data[0].flags.png;
	return imagePng;
};
