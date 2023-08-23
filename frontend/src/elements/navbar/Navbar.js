import { WHITE } from '@/styles/colors';
import styled from '@emotion/styled';

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
    gap: 10px;
`;
