"use client";
import CoinChangeOperationDisplayer from "./CoinChangeOperationDisplayer";
import CoinChangeForm from "./CoinChangeForm";
import "@/styles/coin-change.css";
import React, { useState } from "react";

const initialMoneyAmount = "0";
const initialMoneyType = "$";
const initialMoneyJson = null;
const coinTypes = new Map();

coinTypes["Bs"] = "bolivian";
coinTypes["$"] = "dollar";
coinTypes["€"] = "euro";

const CoinChange = () => {
  const [moneyAmount, setMoneyAmount] = useState(initialMoneyAmount);
  const [moneyType, setMoneyType] = useState(initialMoneyType);
  const [moneyJson, setMoneyJson] = useState(initialMoneyJson);

  const setMoneyAmountValue = (moneyValue) => {
    setMoneyAmount(moneyValue);
  };

  const setMoneyTypeValue = (moneyTypeValue) => {
    setMoneyType(moneyTypeValue);
  };

  const requestMoneyExchange = async (e) => {
    const url = `http://localhost:8080/api/currency-exchange/${coinTypes[moneyType]}?amount=${moneyAmount}`;

	await fetch(url).then((result) => {
      result.json().then((data) => {
        data.sort((a, b) => b.quantity - a.quantity);
        setMoneyJson(data);
      });
    });
  };

  return (
    <div className="coin-change-container">
      <CoinChangeOperationDisplayer
        moneyAmount={moneyAmount}
        moneyType={moneyType}
        moneyJson={moneyJson}
      />
      <CoinChangeForm
        moneyAmount={moneyAmount}
        moneyType={moneyType}
        setMoneyAmountValue={setMoneyAmountValue}
        setMoneyTypeValue={setMoneyTypeValue}
        requestMoneyExchange={requestMoneyExchange}
      />
    </div>
  );
};

export default CoinChange;
