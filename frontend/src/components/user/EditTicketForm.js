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
import { RED } from '@/styles/colors';
import DeleteTicketCheck from './DeleteTicketCheck';

const EditTicketForm = ({
	onClose,
	ticket,
	sourceFlag,
	destinationFlag,
	fetchUserData
}) => {
	const [category, setCategory] = useState(ticket.category);

	const deleteTicket = async () => {
		// request to delete the ticket
		fetchUserData();
	};

	const updateTicket = async () => {
		// request to edit the ticket
		fetchUserData();
	};

	return (
		<OptionContainer>
			<OptionTopContainer>
				<Subtitle>
					#{ticket.number} - {format(new Date(ticket.date), 'dd-MMM-yyyy')}
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
						<option value="Vip">VIP</option>
						<option value="Frecuent passager">FRECUENT PASSAGER</option>
						<option value="Regular passager">REGULAR PASSAGER</option>
					</FormSelect>
					<SecondaryText maxWidth={'280px'}>
						You can still change the category of this flight, the category and
						price will change. Your arrival number will not be affected.
					</SecondaryText>
					<ButtonsContainer>
						<DeleteTicketCheck
							ticket={ticket}
							fetchUserData={fetchUserData}
						/>
						<GeneralButton
							disabled={category === ticket.category}
							onClick={updateTicket}
						>
							Save
						</GeneralButton>
					</ButtonsContainer>
				</>
			) : (
				<>
					<Subtitle>{ticket.category} category</Subtitle>
					<SecondaryText maxWidth={'300px'}>
						The flight has already left, thank you for your purchase and trust
						in us.
					</SecondaryText>
				</>
			)}
		</OptionContainer>
	);
};

export default EditTicketForm;
