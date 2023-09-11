import { BLACKONE, GRAYTWO } from '@/styles/colors';
import styled from '@emotion/styled';
import Link from 'next/link';

export const HomepageContainer = styled.div`
	position: relative;
	display: flex;
	align-items: flex-end;
	justify-content: center;

	height: 85vh;
    padding-bottom: 30px;
`;

export const HomepageImage = styled.img`
	position: absolute;
	top: 37%;
	left: 50%;
	transform: translate(-50%, -50%);

	height: 60vh;
`;

export const HomepageOptionContainer = styled(Link)`
	display: flex;
	flex-direction: column;
	gap: 2px;

	max-width: 380px;
	padding: 30px;
	cursor: pointer;

	& b {
		font-size: 60px;
		color: ${GRAYTWO};
		transition: all ease 350ms;
	}

	& span {
		text-transform: uppercase;
		font-size: 30px;
		font-weight: 700;
		transition: all ease 350ms;
	}

	&:hover {
		& b {
			font-size: 80px;
			color: ${BLACKONE};
		}
		& span {
			font-size: 31px;
		}
	}
`;
