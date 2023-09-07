import { BLACK, WHITE } from '@/styles/colors';
import ReactModal from 'react-modal';
import styled from '@emotion/styled';
import { css } from '@emotion/react';

export const Overlay = styled.div`
	position: fixed;
	top: 0;
	left: 0;
	z-index: 900;

	width: 100vw;
	height: 100vh;

	background-color: ${(props) => props.color};
	opacity: 0.4;
`;

export const PopupContainer = styled(ReactModal)`
	position: absolute;
	top: ${(props) => props.top};
	right: ${(props) => props.right};
	z-index: 1000;

	width: ${(props) => props.width};
	max-height: 90vh;
	${(props) =>
		props.isCenter === true
			? css`
					border-radius: 5px;
			  `
			: css`
					border-bottom-left-radius: 5px;
					border-bottom-right-radius: 5px;
			  `}

	background-color: ${WHITE};
	animation: ${(props) => props.animation} 200ms linear;
`;
