const CoinChangeForm = ({
                            moneyAmount,
                            moneyType,
                            setMoneyAmountValue,
                            setMoneyTypeValue,
                            requestMoneyExchange}) => {

  const handleMoneyTypeChange = (event) => {
      setMoneyTypeValue(event.target.value)
  };


  const handleMoneyAmountChange = (event) =>{
      setMoneyAmountValue(event.target.value)
  };


  return (
    <div className='form-container'>
      <h2 className='form-title'>Coin Changer</h2>
      <label htmlFor="currency" className='label'>What currency?</label>
      <select id="currency" value={moneyType} onChange={handleMoneyTypeChange} className='select_input'>
        <option value="dollar">Dollar</option>
        <option value="bolivian">Bolivian</option>
        <option value="euro">Euro</option>
      </select>
      <label htmlFor="amount" className='label'>How much?</label>
      <input
        type="number"
        id="amount"
        min={0}
        value={moneyAmount}
        className='text_input'
        onChange={handleMoneyAmountChange}
      />
      <img src="/coin_img.png" alt="Coin Changer" className='coin-image'/>
      <button onClick={requestMoneyExchange} className='main-button'>
        Change Coin
      </button>
    </div>
  );
};

export default CoinChangeForm;
