import { BLACKONE, BLUE, WHITE } from '@/styles/colors';
import styled from '@emotion/styled';
import { css } from '@emotion/react';
import Link from 'next/link';

export const NavbarContainer = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: center;

	width: 100%;
	padding: 0 30px 0 30px;

	background-color: ${WHITE};
`;

export const NavbarOptionContainer = styled.div`
	display: flex;
`;

export const NavOptionRoute = styled(Link)`
	position: relative;
	overflow: hidden;

	padding: 40px 5px 20px 5px;
	font-size: 17px;
	font-weight: 700;

	color: ${BLACKONE};
	transition: all ease 200ms;

	&::before {
		content: '   ';
		position: absolute;
		top: -100%;
		left: 50%;

		height: 5vh;
		width: 3px;

		background-color: ${BLUE};
		transition: all ease 200ms;
	}

	&:hover {
		color: ${BLUE};
		&::before {
			top: -12%;
		}
	}

	${(props) =>
		props.selected === true &&
		css`
			color: ${BLUE};
			&::before {
				top: -12%;
			}
		`}
`;
