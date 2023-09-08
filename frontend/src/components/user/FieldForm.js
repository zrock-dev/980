'use client';

import {
	FormInputContainer,
	FormInput,
	FormInputTitle
} from '@/elements/FormElements';

const FieldForm = ({ type, value, title }) => {
	return (
		<FormInputContainer>
			<FormInputTitle>{title}</FormInputTitle>
			<FormInput type={type} value={value} />
		</FormInputContainer>
	);
};

export default FieldForm;
