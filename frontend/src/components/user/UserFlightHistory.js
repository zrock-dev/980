'use client';

import { useEffect, useState } from 'react';
import Loader from '../Loader';
import { getUserTickets } from '@/backend/UserRequest';
import UserFlight from './UserFlight';
import { FlightHistoryContainer } from '@/elements/UserFlightHistory';

const UserFlightHistory = ({ ticketIds = [] }) => {
	const [flightHistory, setFlightHistory] = useState(null);

	const renderFlihtHistory = () => {
		return flightHistory.length > 0 ? (
			<FlightHistoryContainer>
				{flightHistory.map((ticket) => (
					<UserFlight ticket={ticket} />
				))}
			</FlightHistoryContainer>
		) : (
			<span>empty history</span>
		);
	};

	useEffect(() => {
		getUserTickets(ticketIds)
			.then((response) => {
				setFlightHistory(response.data);
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
