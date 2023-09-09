'use client';

import { useEffect, useState } from 'react';
import Loader from '../Loader';
import { format } from 'date-fns';
import { getFlagImage } from '@/backend/UserRequest';
import {
	UserFLightContainer,
	UserFlightAirports,
	UserFlightAirport,
	UserFlightInformation,
	UserFlightDate,
	FlightPrice
} from '@/elements/UserFlightHistory';

const UserFlight = ({ ticket }) => {
	const [sourceFlag, setSourceFlag] = useState(null);
	const [destinationFlag, setDestinationFlag] = useState(null);

	useEffect(() => {
		if (ticket) {
			getFlagImage(ticket.source.country).then((image) => {
				setSourceFlag(image);
			});
			getFlagImage(ticket.destination.country).then((image) => {
				setDestinationFlag(image);
			});
		}
	}, []);

	return (
		<UserFLightContainer>
			<UserFlightAirports>
				<UserFlightAirport>
					<span>
						<b>From</b> {ticket.source.country}
					</span>
					{sourceFlag ? (
						<img src={sourceFlag} alt="" />
					) : (
						<Loader
							withAbsolute={false}
							overallSize={'200px'}
							iconSize="50px"
						/>
					)}
				</UserFlightAirport>
				<UserFlightAirport>
					<span>
						<b>To</b> {ticket.destination.country}
					</span>
					{destinationFlag ? (
						<img src={destinationFlag} alt="" />
					) : (
						<Loader
							withAbsolute={false}
							overallSize={'200px'}
							iconSize="50px"
						/>
					)}
				</UserFlightAirport>
			</UserFlightAirports>
			<UserFlightInformation>
				<UserFlightDate>
					<b>{ticket.category}</b>
					<span>{format(new Date(ticket.date), 'dd-MMM-yyyy')}</span>
				</UserFlightDate>
				{ticket.price && <FlightPrice>{ticket.price} Bs</FlightPrice>}
			</UserFlightInformation>
		</UserFLightContainer>
	);
};

export default UserFlight;
