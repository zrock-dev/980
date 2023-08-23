import { BLUE, WHITE } from '@/styles/colors';
import styled from '@emotion/styled';

export const GeneralButton = styled.button`
	font-size: ${(props) => (props.size ? props.size : '1em')};
	font-weight: 700;
	border-radius: 20px;
	padding: 10px 30px 10px 30px;

	color: ${WHITE};
	background-color: ${BLUE};
	text-transform: capitalize;
`;

export const Icon980 = styled.img`
	max-height: 7vh;
`;
