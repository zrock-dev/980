'use client'
import React, { useState } from 'react';
import PlainTextInput from './PlainTextInput';
import OptionInput from './OptionInput';

const Booking = () => {
  const [inputText, setInputText] = useState('');
  const [selectedOption, setSelectedOption] = useState('');

  const handleTextChange = (newText) => {
    setInputText(newText);
  };
  const handleSelectChange = (newOption) => {
    setSelectedOption(newOption);
  };

  const stringlist = [
    { label: 'Option 1', value: 'option1' },
    { label: 'Option 2', value: 'option2' },
    { label: 'Option 3', value: 'option3' },
  ];


  return (
    <div>
      <PlainTextInput
        label="Enter Text:"
        text={inputText}
        width="200px"
        height="40px"
        onChangeText={handleTextChange}
      />
	  <hr/>
	    <OptionInput
        label="Select an Option:"
        selectedOption={selectedOption}
        stringlist={stringlist}
        width="200px"
        height="40px"
        onSelectChange={handleSelectChange}
      />
    </div>
  );
};

export default Booking;