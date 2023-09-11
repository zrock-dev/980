'use client';

import { FormSelect } from '@/elements/FormElements';
import {
	GeneralButton,
	SecondaryText,
	Subtitle,
	ButtonsContainer
} from '@/elements/GeneralElements';
import {
	UserFlightAirports,
	UserFlightAirport
} from '@/elements/UserFlightHistory';
import { OptionContainer, OptionTopContainer } from '@/elements/UserOptions';
import Xmark from '@/icons/Xmark';
import Loader from '../Loader';
import { differenceInDays, format } from 'date-fns';
import { useState } from 'react';
import DeleteTicketCheck from './DeleteTicketCheck';
import { editBooking } from '@/backend/BookingRequest';
import Link from 'next/link';
import JetFighterUp from '@/icons/JetFighterUp';

const EditTicketForm = ({
	onClose,
	ticket,
	sourceFlag,
	destinationFlag,
	fetchUserData,
	user
}) => {
	const [category, setCategory] = useState(ticket.priority);

	const updateTicket = async () => {
		editBooking(ticket.id, category)
			.then(() => {
				fetchUserData();
			})
			.catch((error) => {
				alert(error.message);
			});
	};

	return (
		<OptionContainer maxWidth={'500px'}>
			<OptionTopContainer>
				<Subtitle>
					#{ticket.number} - {format(new Date(ticket.date), 'dd-MMM-yyyy')}
					{' - '}
					<Link href={`/flights/${ticket.flightId}`}>
						<JetFighterUp />
					</Link>
				</Subtitle>
				<button onClick={onClose}>
					<Xmark />
				</button>
			</OptionTopContainer>
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
					<SecondaryText>{ticket.source.airportName}</SecondaryText>
					<SecondaryText>
						{ticket.source.country} -{ticket.source.state}
					</SecondaryText>
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
					<SecondaryText>{ticket.destination.airportName}</SecondaryText>
					<SecondaryText>
						{ticket.destination.country} -{ticket.destination.state}
					</SecondaryText>
				</UserFlightAirport>
			</UserFlightAirports>

			{differenceInDays(new Date(ticket.date), new Date()) > 0 ? (
				<>
					<FormSelect
						value={category}
						onChange={(e) => setCategory(e.target.value)}
					>
						<option value="VIP">VIP</option>
						<option value="FREQUENT_PASSENGER">FREQUENT PASSENGER</option>
						<option value="REGULAR_PASSENGER">REGULAR PASSENGER</option>
					</FormSelect>
					<SecondaryText>
						You can still change the category of this flight, the category and
						price will change. Your arrival number will not be affected.
					</SecondaryText>
					<ButtonsContainer>
						<DeleteTicketCheck
							user={user}
							ticket={ticket}
							fetchUserData={fetchUserData}
						/>
						<GeneralButton
							disabled={category === ticket.priority}
							onClick={updateTicket}
						>
							Save
						</GeneralButton>
					</ButtonsContainer>
				</>
			) : (
				<>
					<Subtitle>{ticket.priority} CATEGORY</Subtitle>
					<SecondaryText>
						The flight has already left, thank you for your purchase and trust
						in us.
					</SecondaryText>
				</>
			)}
		</OptionContainer>
	);
};

export default EditTicketForm;
