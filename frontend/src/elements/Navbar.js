import { BLACKONE, BLUE, GRAYONE, GRAYTWO, WHITE } from '@/styles/colors';
import styled from '@emotion/styled';
import { css } from '@emotion/react';
import Link from 'next/link';

export const NavbarContainer = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;
	overflow: hidden;

	width: 100%;
	padding: 15px 40px 15px 40px;

	background-color: ${WHITE};
`;

export const NavTitle = styled(Link)`
	display: flex;
	align-items: center;
	justify-content: center;
`;

export const NavbarOptionContainer = styled.div`
	display: flex;
	margin-left: 200px;
`;

export const NavOptionRoute = styled(Link)`
	position: relative;

	font-size: 17px;
	font-weight: 700;
	padding: 0 5px 0 5px;

	color: ${BLACKONE};
	transition: all ease 200ms;

	&::before {
		content: '   ';
		position: absolute;
		top: -500%;
		left: 50%;

		height: 5vh;
		width: 3px;

		background-color: ${BLUE};
		transition: all ease 200ms;
	}

	&:hover {
		color: ${BLUE};
		&::before {
			top: -240%;
		}
	}

	${(props) =>
		props.selected === true &&
		css`
			color: ${BLUE};
			&::before {
				top: -240%;
			}
		`}
`;

export const SearcherContainer = styled.div`
	display: flex;
	justify-content: space-between;
	overflow: hidden;
	gap: 10px;

	min-width: 300px;
	padding: 6px 10px 6px 10px;

	border: 2px solid ${GRAYTWO};
	border-radius: 20px;

	& input {
		width: 100%;
		border: none;
		font-size: 15px;
	}

	& svg {
		fill: ${GRAYONE};
	}
`;
