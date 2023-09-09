'use client';
import { useRouter, useSearchParams } from 'next/navigation';
import { useEffect, useState } from 'react';
import Loader from '../Loader';
import {
	UserContainer,
	UserInfoContainer,
	UserTopContainer,
	UserName,
	UserMainInfoContainer,
	UserOptionContainer,
	UserFlagImage
} from '@/elements/User';
import { Subtitle, GeneralButton } from '@/elements/GeneralElements';
import {
	getFlagImage,
	getUserInformation,
	getUserTickets
} from '@/backend/UserRequest';
import DeleteUserCheck from './DeleteUserCheck';
import EditUserForm from './EditUserForm';
import { differenceInYears } from 'date-fns';
import UserFlightHistory from './UserFlightHistory';

const User = ({ id }) => {
	const router = useRouter();
	const params = useSearchParams();
	const [user, setUser] = useState(null);
	const [flagImage, setFlagImage] = useState(null);

	useEffect(() => {
		const firstName = params.get('firstName');
		let secondName = params.get('secondName');
		secondName = secondName ? secondName : '';
		const firstLastName = params.get('firstLastName');
		const secondLastName = params.get('secondLastName');

		if (firstName && firstLastName && secondLastName) {
			getUserInformation(
				id,
				firstName,
				secondName,
				firstLastName,
				secondLastName
			)
				.then((response) => {
					setUser(response.data);
				})
				.catch(() => {
					redirectToMainPage();
				});
		} else {
			redirectToMainPage();
		}
	}, []);

	useEffect(() => {
		if (user) {
			getFlagImage(user.country)
				.then((imagePng) => {
					setFlagImage(imagePng);
				})
				.catch(() => {
					setFlagImage(null);
				});
		}
	}, [user]);

	const redirectToMainPage = () => {
		router.push('/');
	};

	const renderUserInformation = () => {
		return (
			<UserContainer>
				<UserInfoContainer>
					<UserTopContainer>
						<div>
							{flagImage && <UserFlagImage src={flagImage} alt="" />}
							<UserName>
								{user.firstName} {user.secondName} {user.firstLastName}{' '}
								{user.secondLastName}
							</UserName>
						</div>
						<UserOptionContainer>
							<EditUserForm user={user} />
							<DeleteUserCheck user={user} />
							<GeneralButton>Book flight</GeneralButton>
						</UserOptionContainer>
					</UserTopContainer>
					<Subtitle>
						{user.flights.length < 15 ? 'REGULAR' : 'FREQUENT'}
					</Subtitle>
					<UserMainInfoContainer>
						<span>
							<b>Country:</b> {user.country}
						</span>
						<span>
							<b>Age:</b>{' '}
							{differenceInYears(new Date(), new Date(user.birthdate))} years
							old
						</span>
						<span>
							<b>C.I.:</b> {user.id}
						</span>
					</UserMainInfoContainer>
				</UserInfoContainer>
				<UserInfoContainer>
					<Subtitle size={'24px'}>Flight history</Subtitle>
					<UserFlightHistory ticketIds={user.flights} />
				</UserInfoContainer>
			</UserContainer>
		);
	};

	return user ? renderUserInformation() : <Loader iconSize="80px" />;
};

export default User;
