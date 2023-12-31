'use client';
import CoinChangeOperationDisplayer from "./CoinChangeOperationDisplayer";
import CoinChangeForm from "./CoinChangeForm";
import '@/styles/coin-change.css'
const CoinChange = () => {
    return (
		<div className="coin-change-container">
		<CoinChangeOperationDisplayer />
		<CoinChangeForm />
	  </div>
	  );
};

export default CoinChange;
