'use client';
import { useSearchParams } from 'next/navigation';
import { useEffect, useState } from 'react';
import Loader from '../Loader';

const User = ({ id }) => {
	const params = useSearchParams();
	const [user, setUser] = useState({
		id: 12345678,
		firstName: 'Luiggy',
		secondName: '',
		firstLastName: 'Mamani',
		secondLastName: 'Condori',
		country: 'Bolivia',
		age: 19,
		flights: []
	});
	const [flightHistory, setFlightHistory] = useState(null);

	useEffect(() => {
		const firstName = params.get('firstName');
		const secondName = params.get('secondName');
		const firstLastName = params.get('firstLastName');
		const secondLastName = params.get('secondLastName');

		// fetch to get user information
	}, []);

	useEffect(() => {
		// fetch to get flight history using tickets ids
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
			<div>
				<div>
					<div>
						<div>
							{user.country}
							<span>
								{user.fisrtName} {user.secondName} {user.firstLastName}{' '}
								{user.secondLastName}
							</span>
						</div>
						<div>
							<button>edit</button>
							<button>delete</button>
							<button>Book flight</button>
						</div>
					</div>
					<span>{user.flights.length < 15 ? 'REGULAR' : 'FREQUENT'}</span>
					<div>
						<span>Country: {user.country}</span>
						<span>Age: {user.age} years old</span>
						<span>C.I.: {user.id}</span>
					</div>
				</div>
				<span>Flight history</span>
				{flightHistory ? (
					renderFlihtHistory()
				) : (
					<Loader withAbsolute={false} overallSize={'200px'} iconSize="70px" />
				)}
			</div>
		);
	};

	return user ? renderUserInformation() : <Loader iconSize="100px" />;
};

export default User;
