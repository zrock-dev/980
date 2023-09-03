'use client'
import React, { useState } from 'react';
import PlainTextInput from './PlainTextInput';

const Booking = () => {
  const [inputText, setInputText] = useState('');

  const handleTextChange = (newText) => {
    setInputText(newText);
  };

  return (
    <div>
      <PlainTextInput
        label="Enter Text:"
        text={inputText}
        width="200px"
        height="40px"
        onChangeText={handleTextChange}
      />
    </div>
  );
};

export default Booking;