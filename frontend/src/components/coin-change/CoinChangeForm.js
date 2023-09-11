const CoinChangeForm = ({
  moneyAmount,
  moneyType,
  setMoneyAmountValue,
  setMoneyTypeValue,
  requestMoneyExchange,
}) => {
  const handleMoneyTypeChange = (event) => {
    setMoneyTypeValue(event.target.value);
  };

  const handleMoneyAmountChange = (event) => {
    let value = event.target.value;
    let lengthValue = value.length;
    let index = value.indexOf(".");
    let validation = 2;

    if (index != -1 && lengthValue - index > validation) {
      return;
    }

    if (value.length > 8) {
      return;
    }

    if (value === "") {
      setMoneyAmountValue("");
    } else if (/^\d*\.?\d*$/.test(value)) {
      if (parseFloat(value) >= 1 && parseFloat(value) < 100000) {
        setMoneyAmountValue(value);
      }
    }
  };

  return (
    <div className="form-container">
      <h2 className="form-title">Coin Changer</h2>
      <label htmlFor="currency" className="label">
        What currency?
      </label>
      <select
        id="currency"
        value={moneyType}
        onChange={handleMoneyTypeChange}
        className="select_input"
      >
        <option value="$">Dollar</option>
        <option value="Bs">Bolivian</option>
        <option value="€">Euro</option>
      </select>
      <label htmlFor="amount" className="label">
        How much?
      </label>
      <input
        type="text"
        id="amount"
        min={0}
        value={moneyAmount}
        className="text_input"
        onChange={handleMoneyAmountChange}
      />
      <img src="/coin_img.png" alt="Coin Changer" className="coin-image" />
      <button onClick={requestMoneyExchange} className="main-button">
        Change Coin
      </button>
    </div>
  );
};

export default CoinChangeForm;
