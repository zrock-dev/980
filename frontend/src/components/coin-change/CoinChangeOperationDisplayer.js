import { useState } from "react";
import ProgressBar from "./CoinChangeProgressBar";
import Coin from "./Coin";
const coinTypes = new Map();

coinTypes["Bs"] = [
  0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0,
];
coinTypes["$"] = [0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0];
coinTypes["€"] = [
  0.1, 0.2, 0.5, 1.0, 2.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0, 500.0,
];

const CoinChangeOperationDisplayer = ({
  moneyAmount,
  moneyType,
  moneyJson,
}) => {
  const handleTotalCoins = () => {
    if (!moneyJson) {
      return coinTypes[moneyType].length;
    } else {
      let amount = 0;
      for (let money of moneyJson) {
        amount += money.quantity;
      }
      return amount;
    }
  };

  return (
    <div className="coin-change-operation-main-container">
      <div className="coin-change-operation-results">
        <h2 className="coin-change-title">Coin Changed</h2>
        <div className="coin-details">
          <div className="coin-amount">
            <span className="dollar-amount">{moneyAmount}</span>
            <span className="currency">{moneyType}</span>
          </div>
          <div className="total-coins">
            <span className="coins">Total Coins</span>
            <span className="total">{handleTotalCoins()}</span>
          </div>
        </div>
        <div className="coin-images">
          <div className="coin-image-group">
            {console.log(moneyJson)}
            {!moneyJson &&
              coinTypes[moneyType].map((coin, index) => (
                <Coin
                  value={coin.toFixed(1)}
                  key={index}
                  type={moneyType}
                  amount={coin.quanrity}
                />
              ))}
            {moneyJson &&
              moneyJson.map((coin, index) => {
                return (
                  <Coin
                    value={coin.value}
                    key={index + 1200}
                    type={moneyType}
                    amount={coin.quantity}
                  />
                );
              })}
          </div>
        </div>
        {moneyJson && <ProgressBar coinContext={moneyJson} />}
      </div>
    </div>
  );
};

export default CoinChangeOperationDisplayer;
