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

export const Title980 = styled.span`
	font-size: 30px;
	font-weight: 700;
	font-style: italic;

	color: ${BLUE};
`;

export const Loader = styled.span`
	transform: rotateZ(45deg);
	perspective: 1000px;
	border-radius: 50%;
	width: 48px;
	height: 48px;
	color: ${BLUE};

	&::before,
	&::after {
		content: '';
		display: block;
		position: absolute;
		top: 0;
		left: 0;
		width: inherit;
		height: inherit;
		border-radius: 50%;
		transform: rotateX(70deg);
		animation: 1s spin linear infinite;
	}
	&::after {
		color: #ff3d00;
		transform: rotateY(70deg);
		animation-delay: 0.4s;
	}
`;
