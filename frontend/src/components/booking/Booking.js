'use client'
import React, { useState } from 'react';
import PlainTextInput from './PlainTextInput';
import OptionInput from './OptionInput';

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
	<div className="booking-form-container">
	<h2 className="title">Title:</h2>
	<div className="form-line">
		<label className="form-label">First Name:</label>
		<PlainTextInput
		className="text-input"
		label="First Name:"
		text={firstName}
		width="200px"
		height="40px"
		onChangeText={handleFirstNameChange}
		/>
		<PlainTextInput
		className="text-input"
		label="Second Name:"
		text={secondName}
		width="200px"
		height="40px"
		onChangeText={handleSecondNameChange}
		/>
	</div>
	<div className="form-line">
		<label className="form-label">Last Name:</label>
		<PlainTextInput
		className="text-input"
		label="Last Name:"
		text={lastName}
		width="200px"
		height="40px"
		onChangeText={handleLastNameChange}
		/>
		<PlainTextInput
		className="text-input"
		label="Second Last Name:"
		text={secondLastName}
		width="200px"
		height="40px"
		onChangeText={handleSecondLastNameChange}
		/>
	</div>
	<div className="form-line">
		<label className="form-label">CI:</label>
		<PlainTextInput
		className="text-input"
		label="CI:"
		text={ci}
		width="200px"
		height="40px"
		onChangeText={handleCiChange}
		/>
		<PlainTextInput
		className="text-input"
		label="Date of Birth:"
		text={dateOfBirth}
		width="200px"
		height="40px"
		onChangeText={handleDateOfBirthChange}
		/>
		<OptionInput
		className="select-input"
		label="Country:"
		selectedOption={country}
		stringlist={stringlist}
		width="200px"
		height="40px"
		onSelectChange={handleCountryChange}
		/>
	</div>
	<div className="form-line">
		<OptionInput
		className="select-input"
		label="Flights:"
		selectedOption={flights}
		stringlist={stringlist}
		width="200px"
		height="40px"
		onSelectChange={handleFlightsChange}
		/>
	</div>
	<div className="form-line">
		<OptionInput
		className="select-input"
		label="Type:"
		selectedOption={type}
		stringlist={stringlist}
		width="200px"
		height="40px"
		onSelectChange={handleTypeChange}
		/>
	</div>
	<p className="paragraph">DKSAJÑDAWFJÑAKDFJSKLADFSLJADSFLÑKADJS FDKALD FÑAKSDF LÑAD FKAD FKL FASDJ FKLD FJÑKA FDFKL ADF</p>
	<button type="submit" className="submit-button" onClick={handleSubmit}>
		Book
	</button>
	</div>
);
};

export default BookingForm;
