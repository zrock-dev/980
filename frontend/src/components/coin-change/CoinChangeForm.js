'use client'
import React, { useState } from 'react';

const CoinChangeForm = () => {
  const [currency, setCurrency] = useState('');
  const [amount, setAmount] = useState('');

  const handleCurrencyChange = (event) => {
    setCurrency(event.target.value);
  };

  const handleAmountChange = (event) => {
    setAmount(event.target.value);
  };

  const handleButtonClick = (selectedAmount) => {
    console.log("click")
  };

  return (
    <div>
      <h2>Coin Changer</h2>
      <label htmlFor="currency">What currency?</label>
      <select id="currency" value={currency} onChange={handleCurrencyChange}>
        <option value="USD">Dollar</option>
        <option value="BOL">Bolivian</option>
        <option value="EUR">Euro</option>
      </select>
      <label htmlFor="amount">How much?</label>
      <input
        type="text"
        id="amount"
        value={amount}
        onChange={handleAmountChange}
        placeholder={`$1500 ${currency}`}
      />
      <div>
        <button onClick={() => handleButtonClick(150)}>$150</button>
        <button onClick={() => handleButtonClick(10)}>$10</button>
        <button onClick={() => handleButtonClick(50)}>$50</button>
        <button onClick={() => handleButtonClick(250)}>$250</button>
      </div>
      <img src="/coin_img.png" alt="Coin Changer" />
      <button onClick={() => handleButtonClick(amount)}>
        Call Action
      </button>
    </div>
  );
};

export default CoinChangeForm;
