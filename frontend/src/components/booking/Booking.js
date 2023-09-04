'use client'
import React, { useState } from 'react';
import PlainTextInput from './PlainTextInput';
import OptionInput from './OptionInput';
import DateInput from './DateInput';
import '@/styles/booking.css';

const BookingForm = () => {
  const [formData, setFormData] = useState({
    firstName: '',
    secondName: '',
    lastName: '',
    secondLastName: '',
    ci: '',
    dateOfBirth: '',
    country: '',
    flights: '',
    type: '',
  });

  const stringlist = [
    { label: 'Option 1', value: 'option1' },
    { label: 'Option 2', value: 'option2' },
    { label: 'Option 3', value: 'option3' },
  ];

  const handleChange = (fieldName, newValue) => {
    setFormData({
      ...formData,
      [fieldName]: newValue,
    });
  };

  const handleSubmit = (e) => {
	e.preventDefault();
	const formDataString = `
    First Name: ${formData.firstName}
    Second Name: ${formData.secondName}
    Last Name: ${formData.lastName}
    Second Last Name: ${formData.secondLastName}
    CI: ${formData.ci}
    Date of Birth: ${formData.dateOfBirth}
    Country: ${formData.country}
    Flights: ${formData.flights}
    Type: ${formData.type}
  `;
  const formDataJson = {
    firstName: formData.firstName,
    secondName: formData.secondName,
    lastName: formData.lastName,
    secondLastName: formData.secondLastName,
    ci: formData.ci,
    dateOfBirth: formData.dateOfBirth,
    country: formData.country,
    flights: formData.flights,
    type: formData.type,
  };
  const formDataStringJson = JSON.stringify(formDataJson);
  console.log(formDataString);
	setFormData({
	  firstName: '',
	  secondName: '',
	  lastName: '',
	  secondLastName: '',
	  ci: '',
	  dateOfBirth: '',
	  country: '',
	  flights: '',
	  type: '',
	});
  };

  return (
    <div className="main-container">
      <div className="booking-form-container">
        <h2 className="title">Booking Flight Form</h2>
        <div className="two-line">
          <div className='two-inputs'>
            <PlainTextInput
              className="text-input"
              label="First Name:"
              text={formData.firstName}
              width="100%"
              height="40px"
              onChangeText={(newText) => handleChange('firstName', newText)}
            />
          </div>
          <div className='two-inputs'>
            <PlainTextInput
              className="text-input"
              label="Second Name:"
              text={formData.secondName}
              width="100%"
              height="40px"
              onChangeText={(newText) => handleChange('secondName', newText)}
            />
          </div>
        </div>
        <div className="two-line">
          <div className='two-inputs'>
            <PlainTextInput
              className="text-input"
              label="Last Name:"
              text={formData.lastName}
              width="100%"
              height="40px"
              onChangeText={(newText) => handleChange('lastName', newText)}
            />
          </div>
          <div className='two-inputs'>
            <PlainTextInput
              className="text-input"
              label="Second Last Name:"
              text={formData.secondLastName}
              width="100%"
              height="40px"
              onChangeText={(newText) => handleChange('secondLastName', newText)}
            />
          </div>
        </div>
        <div className="three-line">
          <div className='three-input'>
            <PlainTextInput
              className="text-input"
              label="CI:"
              text={formData.ci}
              width="100%"
              height="40px"
              onChangeText={(newText) => handleChange('ci', newText)}
            />
          </div>
          <div className='three-input'>
            <DateInput
              className="text-input"
              label="Date of Birth:"
              text={formData.dateOfBirth}
              width="100%"
              height="40px"
              onChangeText={(newText) => handleChange('dateOfBirth', newText)}
            />
          </div>
          <div className='three-input'>
            <OptionInput
              label="Country:"
              selectedOption={formData.country}
              stringlist={stringlist}
              width="100%"
              height="40px"
              onSelectChange={(newOption) => handleChange('country', newOption)}
            />
          </div>
        </div>
        <div className="one-line">
          <div className='one-input'>
            <OptionInput
              className="select-input"
              label="Flights:"
              selectedOption={formData.flights}
              stringlist={stringlist}
              width="100%"
              height="40px"
              onSelectChange={(newOption) => handleChange('flights', newOption)}
            />
          </div>
        </div>
        <div className="one-line">
          <div className='one-input'>
            <OptionInput
              className="one-input"
              label="Type:"
              selectedOption={formData.type}
              stringlist={stringlist}
              width="100%"
              height="40px"
              onSelectChange={(newOption) => handleChange('type', newOption)}
            />
          </div>
        </div>
        <p className="paragraph">"Your privacy matters. We want you to know that the data you share here is used exclusively for booking flights. We handle your information with care and respect. Accuracy helps us provide you with the best service possible. Thank you for choosing us for your travel needs."</p>
        <button type="submit" className="submit-button" onClick={handleSubmit}>
          Book
        </button>
      </div>
      <div className='img-container'>
        <img src='/plane-booking.svg' alt="Plane" />
      </div>
    </div>
  );
};

export default BookingForm;
