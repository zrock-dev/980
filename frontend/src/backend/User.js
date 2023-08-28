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
