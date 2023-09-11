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
import Popup from '../Popup';
import EditTicketForm from './EditTicketForm';

const UserFlight = ({ ticket, fetchUserData, user }) => {
	const [isEditing, setEditing] = useState(false);
	const [sourceFlag, setSourceFlag] = useState(null);
	const [destinationFlag, setDestinationFlag] = useState(null);

	useEffect(() => {
		getFlagImage(ticket.source.country).then((image) => {
			setSourceFlag(image);
		});
		getFlagImage(ticket.destination.country).then((image) => {
			setDestinationFlag(image);
		});
	}, [ticket]);

	return (
		ticket && (
			<>
				<UserFLightContainer onClick={() => setEditing(true)}>
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
							<b>{ticket.priority}</b>
							<span>{format(new Date(ticket.date), 'dd-MMM-yyyy')}</span>
						</UserFlightDate>
						{ticket.price && <FlightPrice>{ticket.price} Bs</FlightPrice>}
					</UserFlightInformation>
				</UserFLightContainer>
				<Popup
					isOpen={isEditing}
					onClose={() => setEditing(false)}
					right="765px"
					children={
						<EditTicketForm
							user={user}
							ticket={ticket}
							onClose={() => setEditing(false)}
							sourceFlag={sourceFlag}
							destinationFlag={destinationFlag}
							fetchUserData={fetchUserData}
						/>
					}
				/>
			</>
		)
	);
};

export default UserFlight;
