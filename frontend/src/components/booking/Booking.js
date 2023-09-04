'use client'
import React, { useState } from 'react';
import PlainTextInput from './PlainTextInput';
import OptionInput from './OptionInput';
import DateInput from './DateInput';
import '@/styles/booking.css'
const BookingForm = () => {
const [firstName, setFirstName] = useState('');
const [secondName, setSecondName] = useState('');
const [lastName, setLastName] = useState('');
const [secondLastName, setSecondLastName] = useState('');
const [ci, setCi] = useState('');
const [dateOfBirth, setDateOfBirth] = useState('');
const [country, setCountry] = useState('');
const [flights, setFlights] = useState('');
const [type, setType] = useState('');

const stringlist = [
{ label: 'Option 1', value: 'option1' },
{ label: 'Option 2', value: 'option2' },
{ label: 'Option 3', value: 'option3' },
];
const handleFirstNameChange = (newText) => {
	setFirstName(newText);
};

const handleSecondNameChange = (newText) => {
	setSecondName(newText);
};

const handleLastNameChange = (newText) => {
	setLastName(newText);
};

const handleSecondLastNameChange = (newText) => {
	setSecondLastName(newText);
};

const handleCiChange = (newText) => {
	setCi(newText);
};

const handleDateOfBirthChange = (newText) => {
	setDateOfBirth(newText);
};

const handleCountryChange = (newOption) => {
	setCountry(newOption);
};

const handleFlightsChange = (newOption) => {
	setFlights(newOption);
};

const handleTypeChange = (newOption) => {
	setType(newOption);
};

const handleParagraphChange = (newText) => {
	setParagraph(newText);
};

const handleSubmit = (e) => {
	e.preventDefault();
};

return (
	<div className='main-container'>
		<div className="booking-form-container">
		<h2 className="title">Booking Flight Form</h2>
		<div className="two-line">
			<div className='two-inputs'>
				<PlainTextInput
				className="text-input"
				label="First Name:"
				text={firstName}
				width="100%"
				height="40px"
				onChangeText={handleFirstNameChange}
				/>
			</div>
			<div className='two-inputs'>
				<PlainTextInput
				className="text-input"
				label="Second Name:"
				text={secondName}
				width="100%"
				height="40px"
				onChangeText={handleSecondNameChange}
				/>
			</div>
		</div>
		<div className="two-line">
			<div className='two-inputs'>
			<PlainTextInput
				className="text-input"
				label="Last Name:"
				text={lastName}
				width="100%"
				height="40px"
				onChangeText={handleLastNameChange}
				/>
			</div>
			<div className='two-inputs'>
			<PlainTextInput
			className="text-input"
			label="Second Last Name:"
			text={secondLastName}
			width="100%"
			height="40px"
			onChangeText={handleSecondLastNameChange}
			/>
			</div>
		</div>
		<div className="three-line">
			<div className='three-input'>
			<PlainTextInput
			className="text-input"
			label="CI:"
			text={ci}
			width="100%"
			height="40px"
			onChangeText={handleCiChange}
			/>
			</div>
			<div className='three-input'>
			<DateInput
			className="text-input"
			label="Date of Birth:"
			text={dateOfBirth}
			width="100%"
			height="40px"
			onChangeText={handleDateOfBirthChange}
			/>
			</div>
			<div className='three-input'>
			<OptionInput
			label="Country:"
			selectedOption={country}
			stringlist={stringlist}
			width="100%"
			height="40px"
			onSelectChange={handleCountryChange}
			/>
			</div>
		</div>
		<div className="one-line">
			<div className='one-input'>
			<OptionInput
			className="select-input"
			label="Flights:"
			selectedOption={flights}
			stringlist={stringlist}
			width="100%"
			height="40px"
			onSelectChange={handleFlightsChange}
			/>
			</div>
		</div>
		<div className="one-line">
			<div className='one-input'>
			<OptionInput
				className="one-input"
				label="Type:"
				selectedOption={type}
				stringlist={stringlist}
				width="100%"
				height="40px"
				onSelectChange={handleTypeChange}
				/>
			</div>
		</div>
		<p className="paragraph">"Your privacy matters. We want you to know that the data you share here is used exclusively for booking flights. We handle your information with care and respect. Accuracy helps us provide you with the best service possible. Thank you for choosing us for your travel needs."</p>
		<button type="submit" className="submit-button" onClick={handleSubmit}>
			Book
		</button>
		</div>
		<div className='img-container'>
			<img src='/plane-booking.svg'/>
		</div>
	</div>

);
};

export default BookingForm;
