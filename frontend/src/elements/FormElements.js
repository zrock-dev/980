import { GRAYONE, GRAYTWO, WHITE } from '@/styles/colors';
import styled from '@emotion/styled';

export const FormContainer = styled.div`
	display: flex;
	flex-direction: column;
	gap: 25px;

	margin: 20px 0 40px 0;
`;

export const FormSubContainer = styled.div`
	display: flex;
	gap: 20px;
`;

export const FormInputContainer = styled.div`
	position: relative;

	width: 100%;
`;

export const FormInputTitle = styled.label`
	position: absolute;
	top: -7px;
	left: 10px;

	padding: 0 10px 0 10px;
	font-weight: 700;
	font-size: 14px;

	background-color: ${WHITE};
`;

export const FormInput = styled.input`
	width: 100%;
	padding: 12px 20px 12px 20px;
	border-radius: 10px;

	border: 1px solid ${GRAYTWO};
`;

export const FormSelect = styled.select`
	margin-top: 35px;
	width: 100%;
	padding: 10px 15px 10px 15px;
	border-radius: 20px;
	border: none;

	box-shadow: 0 5px 10px ${GRAYONE};
	background-color: ${WHITE};
`;