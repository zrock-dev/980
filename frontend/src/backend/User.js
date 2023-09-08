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
			birthdate: '2013-10-21T13:28:06.419Z',
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
