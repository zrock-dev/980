'user client';

import Trash from '@/icons/Trash';
import Popup from '../Popup';
import { useEffect, useState } from 'react';
import {
	GeneralButton,
	SecondaryText,
	Subtitle
} from '@/elements/GeneralElements';
import { OptionContainer, OptionTopContainer } from '@/elements/UserOptions';
import { RED } from '@/styles/colors';
import Xmark from '@/icons/Xmark';
import { deleteBooking } from '@/backend/BookingRequest';

const DeleteTicketCheck = ({ user, ticket, fetchUserData }) => {
	const [isDeleting, setDeleting] = useState(false);

	const deleteTicket = () => {
		deleteBooking(user, ticket.id)
			.then(() => {
				fetchUserData();
			})
			.catch((error) => {
				alert(error.message);
			});
	};

	return (
		<>
			<GeneralButton
				color={RED}
				onClick={() => {
					setDeleting(true);
				}}
			>
				Delete
			</GeneralButton>
			<Popup
				isOpen={isDeleting}
				onClose={() => setDeleting(false)}
				width="35%"
				right="630px"
				children={
					<OptionContainer>
						<OptionTopContainer>
							<Subtitle>Are you sure you want to delete this ticket?</Subtitle>
							<button onClick={() => setDeleting(false)}>
								<Xmark />
							</button>
						</OptionTopContainer>
						<SecondaryText>
							The action you want to perform has no reverberation, the ticket
							will be deleted from the flight to which it belongs, you will be
							able to buy a new ticket for that flight but you will not be able
							to recover your arrival number.
						</SecondaryText>
						<GeneralButton onClick={deleteTicket} color={RED} width={'150px'}>
							Delete
						</GeneralButton>
					</OptionContainer>
				}
			/>
		</>
	);
};

export default DeleteTicketCheck;
