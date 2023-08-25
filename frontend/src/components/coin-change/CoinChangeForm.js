'use client'
import React, { useState } from 'react';
import '@/styles/coin-change.css'

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
    <div className='form-container'>
      <h2 className='form-title'>Coin Changer</h2>
      <label htmlFor="currency" className='label'>What currency?</label>
      <select id="currency" value={currency} onChange={handleCurrencyChange} className='select_input'>
        <option value="USD">Dollar</option>
        <option value="BOL">Bolivian</option>
        <option value="EUR">Euro</option>
      </select>
      <label htmlFor="amount" className='label'>How much?</label>
      <input
        type="text"
        id="amount"
        value={amount}
        className='text_input'
        onChange={handleAmountChange}
        placeholder={`$1500 ${currency}`}
      />
      <div className='option-buttons'>
        <button onClick={() => handleButtonClick(150)}>$150</button>
        <button onClick={() => handleButtonClick(10)}>$10</button>
        <button onClick={() => handleButtonClick(50)}>$50</button>
        <button onClick={() => handleButtonClick(250)}>$250</button>
      </div>
      <img src="/coin_img.png" alt="Coin Changer" className='coin-image'/>
      <button onClick={() => handleButtonClick(amount)} className='main-button'>
        Chage Coin
      </button>
    </div>
  );
};

export default CoinChangeForm;
