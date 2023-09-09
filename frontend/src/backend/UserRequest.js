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
	// replace this for fetch peticion
	return {
		data: {
			id: 12345678,
			firstName: 'Luiggy',
			secondName: '',
			firstLastName: 'Mamani',
			secondLastName: 'Condori',
			country: 'Bolivia',
			birthdate: '1993-10-21T13:28:06.419Z',
			flights: []
		},
		status: 202
	};
};

export const getUserTickets = async (ticketIds) => {
	// replace this for fetch peticion
	return {
		data: [],
		status: 202
	};
};

export const getFlagImage = async (countryName) => {
	const response = await axios.get(
		`https://restcountries.com/v3.1/name/${countryName}?fields=flags`
	);
	const imagePng = response.data[0].flags.png;

	return imagePng;
};
