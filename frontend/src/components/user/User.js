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
import { getFlagImage, getUserInformation } from '@/backend/UserRequest';
import DeleteUserCheck from './DeleteUserCheck';
import EditUserForm from './EditUserForm';
import { differenceInYears } from 'date-fns';
import UserFlightHistory from './UserFlightHistory';

const User = ({ id }) => {
	const router = useRouter();
	const params = useSearchParams();
	const [isLoading, setLoading] = useState(true);
	const [user, setUser] = useState(null);
	const [flagImage, setFlagImage] = useState(null);

	const fetchUserData = () => {
		setLoading(true);
	};

	useEffect(() => {
		if (isLoading) {
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
					.then(() => {
						setLoading(false);
					})
					.catch(() => {
						redirectToMainPage();
					});
			} else {
				redirectToMainPage();
			}
		}
	}, [isLoading]);

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

	const redirectToBooking = () => {
		const secondName =
			user.secondName.length == 0 ? '' : `secondName=${user.secondName}&`;
		const url = `/booking?firstName=${user.firstName}
		&${secondName}firstLastName=${user.firstLastName}
		&secondLastName=${user.secondLastName}&country=${user.country}
		&id=${user.id}&birthdate=${user.dateBirth}`;
		router.push(url);
	};

	const renderUserInformation = () => {
		return (
			<UserContainer>
				<UserInfoContainer>
					<UserTopContainer>
						<div>
							{flagImage && <UserFlagImage src={flagImage} alt="" />}
							<UserName>{user.fullName}</UserName>
						</div>
						<UserOptionContainer>
							<EditUserForm user={user} fetchUserData={fetchUserData} />
							<DeleteUserCheck user={user} />
							<GeneralButton onClick={redirectToBooking}>
								Book flight
							</GeneralButton>
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
							{differenceInYears(new Date(), new Date(user.dateBirth))} years
							old
						</span>
						<span>
							<b>C.I.:</b> {user.id}
						</span>
					</UserMainInfoContainer>
				</UserInfoContainer>
				<UserInfoContainer>
					<Subtitle size={'24px'}>Flight history</Subtitle>
					<UserFlightHistory user={user} fetchUserData={fetchUserData} />
				</UserInfoContainer>
			</UserContainer>
		);
	};

	return isLoading ? <Loader iconSize="80px" /> : renderUserInformation();
};

export default User;
