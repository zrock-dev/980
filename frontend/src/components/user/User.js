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
	UserOptionContainer
} from '@/elements/User';
import { Subtitle, GeneralButton } from '@/elements/GeneralElements';
import { getUserInformation, getUserTickets } from '@/backend/UserRequest';
import DeleteUserCheck from './DeleteUserCheck';
import EditUserForm from './EditUserForm';

const User = ({ id }) => {
	const router = useRouter();
	const params = useSearchParams();
	const [user, setUser] = useState(null);
	const [flightHistory, setFlightHistory] = useState(null);

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

	const redirectToMainPage = () => {
		router.push('/');
	};

	useEffect(() => {
		if (user) {
			getUserTickets(user.flights)
				.then((response) => {
					setFlightHistory(response.data);
				})
				.catch(() => {
					setFlightHistory([]);
				});
		}
	}, [user]);

	const renderFlihtHistory = () => {
		return flightHistory.length > 0 ? (
			<div>flights</div>
		) : (
			<span>empty history</span>
		);
	};

	const renderUserInformation = () => {
		return (
			<UserContainer>
				<UserInfoContainer>
					<UserTopContainer>
						<div>
							{user.country}
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
							<b>Age:</b> {user.birthdate} years old
						</span>
						<span>
							<b>C.I.:</b> {user.id}
						</span>
					</UserMainInfoContainer>
				</UserInfoContainer>
				<UserInfoContainer>
					<Subtitle>Flight history</Subtitle>
					{flightHistory ? (
						renderFlihtHistory()
					) : (
						<Loader
							withAbsolute={false}
							overallSize={'200px'}
							iconSize="50px"
						/>
					)}
				</UserInfoContainer>
			</UserContainer>
		);
	};

	return user ? renderUserInformation() : <Loader iconSize="80px" />;
};

export default User;
