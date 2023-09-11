import {
	BLACKONE,
	BLACKTWO,
	BLUE,
	GRAYONE,
	GRAYTWO,
	LIGHTBLUE,
	WHITE
} from '@/styles/colors';
import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const GeneralButton = styled.button`
	font-size: ${(props) => (props.size ? props.size : '1em')};
	font-weight: 700;
	border-radius: 20px;
	padding: 10px 30px 10px 30px;

	max-width: ${(props) => (props.width ? props.width : 'auto')};

	color: ${WHITE};
	background-color: ${(props) => (props.color ? props.color : BLUE)};
	text-transform: capitalize;

	${(props) =>
		props.disabled === true &&
		css`
			opacity: 0.3;
		`}
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
	text-align: left;

	width: 100%;
	font-size: ${(props) => (props.size ? props.size : '20px')};
	font-weight: 700;
`;

export const SecondaryText = styled.p`
	font-size: 14px;

	color: ${GRAYONE};
	& b {
		color: ${BLACKTWO};
	}
`;

export const ButtonsContainer = styled.div`
	display: flex;
	align-items: end;
	gap: 10px;
`;

export const PaginationContainer = styled.div`
	display: flex;
	background-color: #e9e9e9;
	gap: 10px;

	border-radius: 20px;
	padding: 0 25px 0 25px;

	transition: all ease 250ms;
`;

export const PaginationButton = styled.button`
	padding: 10px 15px 10px 15px;
	border-radius: 10px;

	font-size: 16px;
	font-weight: 700;
	background-color: transparent;
	color: ${BLACKONE};
	transition: all ease 250ms;

	${(props) =>
		props.selected === true &&
		css`
			color: ${WHITE};
			background-color: ${BLUE};
			box-shadow: 0 0 20px ${LIGHTBLUE};
		`}

	&:hover {
		color: ${WHITE};
		background-color: ${BLUE};
		box-shadow: 0 0 20px ${LIGHTBLUE};
	}
`;
