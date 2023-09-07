import { BLUE, GRAYTWO, WHITE } from '@/styles/colors';
import styled from '@emotion/styled';
import { css } from '@emotion/react';

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

export const LoaderContainer = styled.span`
	display: flex;
	justify-content: center;
	align-items: center;

	width: 100%;
	height: ${(props) => props.overallSize};

	opacity: 0.8;
	background-color: transparent;

	${(props) =>
		props.withAbsolute === true &&
		css`
			position: fixed;
			top: 0;
			left: 0;

			background-color: ${GRAYTWO};
		`}
`;

export const LoaderIcon = styled.span`
	display: inline-block;
	box-sizing: border-box;

	width: ${(props) => props.iconSize};
	height: ${(props) => props.iconSize};
	border-radius: 50%;

	border-top: 5px solid ${BLUE};
	border-right: 5px solid transparent;
	animation: rotation 750ms linear infinite;
`;

export const Subtitle = styled.span`
	font-size: 20px;
	font-weight: 700;
`;
