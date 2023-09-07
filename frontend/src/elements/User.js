import { BLACKONE, GRAYONE, GRAYTWO } from '@/styles/colors';
import styled from '@emotion/styled';

export const UserContainer = styled.div`
	display: flex;
	flex-direction: column;

	max-width: 85%;
	margin: auto;
`;

export const UserInfoContainer = styled.div`
	display: flex;
	flex-direction: column;

	margin-bottom: 40px;
`;

export const UserTopContainer = styled.div`
	display: flex;
	justify-content: space-between;
`;

export const UserOptionContainer = styled.div`
	display: flex;
	gap: 15px;

	svg {
		height: 23px;
		fill: ${GRAYONE};
		transition: all ease 250ms;
	}

	svg:hover {
		fill: ${BLACKONE};
	}
`;

export const UserMainInfoContainer = styled.div`
	display: flex;
	flex-direction: column;
	margin-top: 30px;
`;

export const UserName = styled.span`
	text-transform: uppercase;
	font-size: 35px;
	font-weight: 700;
`;

export const UserInformationContainer = styled.div``;

export const FlightHistoryContainer = styled.div``;
