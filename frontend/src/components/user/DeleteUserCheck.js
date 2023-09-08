'user client';

import Trash from '@/icons/Trash';
import Popup from '../Popup';
import { useState } from 'react';
import {
	GeneralButton,
	SecondaryText,
	Subtitle
} from '@/elements/GeneralElements';
import { OptionContainer, OptionTopContainer } from '@/elements/UserOptions';
import { RED } from '@/styles/colors';
import Xmark from '@/icons/Xmark';

const DeleteUserCheck = ({ user }) => {
	const [isDeleting, setDeleting] = useState(false);

	const deleteUser = () => {
		alert(user.firstName);
	};

	return (
		<>
			<button disabled={!user} onClick={() => setDeleting(true)}>
				<Trash />
			</button>
			<Popup
				isOpen={isDeleting}
				onClose={() => setDeleting(false)}
				width="35%"
				right="630px"
				children={
					<OptionContainer>
						<OptionTopContainer>
							<Subtitle>Are you sure you want to delete this user?</Subtitle>
							<button onClick={() => setDeleting(false)}>
								<Xmark />
							</button>
						</OptionTopContainer>
						<SecondaryText>
							The action you want to perform does not have a reverb, that is,
							the user's account{' '}
							<b>
								{user.firstName} {user.secondName} {user.firstLastName}{' '}
								{user.secondLastName}
							</b>{' '}
							will be permanently deleted. In addition, your flights will be
							permanently canceled and eliminated.
						</SecondaryText>
						<GeneralButton onClick={deleteUser} color={RED} width={'150px'}>
							Delete
						</GeneralButton>
					</OptionContainer>
				}
			/>
		</>
	);
};

export default DeleteUserCheck;
