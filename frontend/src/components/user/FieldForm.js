'use client';

import {
	FormInputContainer,
	FormInput,
	FormInputTitle
} from '@/elements/FormElements';

const FieldForm = ({ type, value, title, name, changeField }) => {
	const getValue = () => {
		return type === 'date'
			? new Date(value).toISOString().split('T')[0]
			: value;
	};

	return (
		<FormInputContainer>
			<FormInputTitle>{title}</FormInputTitle>
			<FormInput
				type={type}
				value={getValue()}
				name={name}
				onChange={(event) => changeField(event)}
			/>
		</FormInputContainer>
	);
};

export default FieldForm;
