import { GRAYONE, GRAYTWO } from '@/styles/colors';
import styled from '@emotion/styled';

export const FlightHistoryContainer = styled.div`
	display: flex;
	gap: 35px;
	flex-wrap: wrap;

	padding-top: 25px;
`;

export const UserFLightContainer = styled.button`
	display: flex;
	flex-direction: column;
	gap: 30px;

	padding: 20px;

	border-radius: 20px;
	box-shadow: 0 4px 5px ${GRAYONE};
	transition: all ease 250ms;

	&:hover {
		box-shadow: 0 6px 10px ${GRAYONE};
	}
`;

export const UserFlightAirports = styled.div`
	display: flex;
	gap: 20px;

	& span {
		text-align: left;
		font-size: 20px;
	}
`;

export const UserFlightAirport = styled.div`
	display: flex;
	flex-direction: column;
	gap: 10px;

	& img {
		width: 130px;
		height: 80px;
	}
`;

export const UserFlightInformation = styled.div`
	display: flex;
	justify-content: space-between;
	align-items: end;

	padding-top: 10px;
	border-top: 1px solid ${GRAYTWO};
`;

export const UserFlightDate = styled.div`
	display: flex;
	flex-direction: column;
	align-items: start;

	& b {
		text-transform: uppercase;
		font-size: 18px;
	}
`;

export const FlightPrice = styled.b`
	font-size: 22px;
`;
