'use client';
import React, { useEffect, useState } from 'react';
import PlainTextInput from './PlainTextInput';
import OptionInput from './OptionInput';
import DateInput from './DateInput';
import '@/styles/booking.css';
import { useRouter, useSearchParams } from 'next/navigation';
import Flights from './Flights';
import { booking } from '@/backend/BookingRequest';

const categoryList = [
	{ label: 'VIP', value: 'VIP' },
	{ label: 'REGULAR PASSENGER', value: 'REGULAR_PASSENGER' },
	{ label: 'FRECUENT PASSENGER', value: 'FRECUENT_PASSENGER' }
];

const BookingForm = () => {
	const router = useRouter();
	const params = useSearchParams();
	const [formData, setFormData] = useState({
		firstName: '',
		secondName: '',
		lastName: '',
		secondLastName: '',
		ci: '',
		dateOfBirth: '',
		country: '',
		flightId: '',
		category: categoryList[0].value
	});

	const [pageData, setPageData] = useState({
		currentPage: -1,
		elements: [],
		totalPages: 1,
		isLoading: true
	});

	const onChange = (event) => {
		const { name, value } = event.target;
		setFormData({
			...formData,
			[name]: value
		});
	};

	const handleSubmit = (e) => {
		e.preventDefault();
		booking(formData)
			.then(() => {
				router.push(
					`/users/${formData.ci}?firstName=${formData.firstName}&secondName=${formData.secondName}&firstLastName=${formData.lastName}&secondLastName=${formData.secondLastName}`
				);
			})
			.catch((e) => {
				alert(e.message);
			});
	};

	const loadParams = () => {
		const firstName = params.get('firstName');
		let secondName = params.get('secondName');
		secondName = secondName ? secondName : '';
		const lastName = params.get('firstLastName');
		const secondLastName = params.get('secondLastName');
		const ci = params.get('id');
		const dateOfBirth = params.get('birthdate');
		const country = params.get('country');
		setFormData({
			...formData,
			firstName,
			secondName,
			lastName,
			secondLastName,
			ci,
			dateOfBirth,
			country
		});
	};

	useEffect(() => {
		loadParams();
	}, []);

	return (
		<div className="main-container">
			<form className="booking-form-container" onSubmit={handleSubmit}>
				<h2 className="title">Booking Flight Form</h2>
				<div className="two-line">
					<div className="two-inputs">
						<PlainTextInput
							className="text-input"
							name={'firstName'}
							label="First Name:"
							value={formData.firstName}
							onChange={onChange}
							placeholder="Enter your first name"
						/>
					</div>
					<div className="two-inputs">
						<PlainTextInput
							className="text-input"
							name={'secondName'}
							label="Second Name:"
							value={formData.secondName}
							onChange={onChange}
							placeholder="Enter your second name"
						/>
					</div>
				</div>
				<div className="two-line">
					<div className="two-inputs">
						<PlainTextInput
							className="text-input"
							name={'lastName'}
							label="Last Name:"
							value={formData.lastName}
							onChange={onChange}
							placeholder="Enter your last name"
						/>
					</div>
					<div className="two-inputs">
						<PlainTextInput
							className="text-input"
							name={'secondLastName'}
							label="Second Last Name:"
							value={formData.secondLastName}
							onChange={onChange}
							placeholder="Enter your second last name"
						/>
					</div>
				</div>
				<div className="three-line">
					<div className="three-input">
						<PlainTextInput
							className="text-input"
							name={'ci'}
							label="CI:"
							value={formData.ci}
							onChange={onChange}
							placeholder="Enter your CI number"
						/>
					</div>
					<div className="three-input">
						<DateInput
							className="text-input"
							name={'dateOfBirth'}
							label="Date of Birth:"
							value={formData.dateOfBirth}
							onChange={onChange}
						/>
					</div>
					<div className="three-input">
						<PlainTextInput
							className="text-input"
							name={'country'}
							label="Country:"
							value={formData.country}
							onChange={onChange}
							placeholder="Enter your country"
						/>
					</div>
				</div>
				<div className="one-line">
					<div className="one-input">
						<Flights
							value={formData.flightId}
							name={'flightId'}
							onChange={onChange}
						/>
					</div>
				</div>
				<div className="one-line">
					<div className="one-input">
						<OptionInput
							className="one-input"
							name={'category'}
							label="Type:"
							value={formData.category}
							stringlist={categoryList}
							onChange={onChange}
						/>
					</div>
				</div>
				<p className="paragraph">
					"Your privacy matters. We want you to know that the data you share
					here is used exclusively for booking flights. We handle your
					information with care and respect. Accuracy helps us provide you with
					the best service possible. Thank you for choosing us for your travel
					needs."
				</p>
				<button type="submit" className="submit-button">
					Book
				</button>
			</form>
			<div className="img-container">
				<img src="/plane-booking.svg" alt="Plane" />
			</div>
		</div>
	);
};

export default BookingForm;
