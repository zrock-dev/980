'use client';

import { useEffect, useState } from 'react';
import Loader from '../Loader';
import { getUserTickets } from '@/backend/UserRequest';
import UserFlight from './UserFlight';

import {
	FlightHistoryContainer,
	EmptyFlightHistory
} from '@/elements/UserFlightHistory';
import { SecondaryText } from '@/elements/GeneralElements';

const UserFlightHistory = ({ user, fetchUserData }) => {
	const [page, setPage] = useState(0);
	const [flightHistory, setFlightHistory] = useState(null);

	const renderFlihtHistory = () => {
		return flightHistory.length > 0 ? (
			<FlightHistoryContainer>
				{flightHistory.map((ticket) => (
					<UserFlight ticket={ticket} fetchUserData={fetchUserData} />
				))}
			</FlightHistoryContainer>
		) : (
			<EmptyFlightHistory>
				<img src="/airplane_flying.gif" alt="" />
				<span>empty flight history</span>
				<SecondaryText>
					You can book flights from the "Book Flight" option and have a flight
					history stored in our database.
				</SecondaryText>
			</EmptyFlightHistory>
		);
	};

	useEffect(() => {
		getUserTickets(user.id, page)
			.then((response) => {
				setFlightHistory(response.data.elements);
			})
			.catch(() => {
				setFlightHistory([]);
			});
	}, []);

	return flightHistory ? (
		renderFlihtHistory()
	) : (
		<Loader withAbsolute={false} overallSize={'200px'} iconSize="50px" />
	);
};

export default UserFlightHistory;
