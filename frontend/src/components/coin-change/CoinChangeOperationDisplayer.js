import React from 'react';
import '@/styles/coin-change-result.css'
const CoinChangeOperationDisplayer = () => {
    return (
      <div className="coin-change-operation-main-container">
        <div className='coin-change-operation-results'>
          <h2>Coin Changed</h2>
          <div className="coin-details">
            <p>$1500 USD</p>
            <p>Total Coins 6</p>
          </div>
          <div className="coin-images">
            <div className="coin-image-group">
              <div className="coin-image-result">
                <img src="/coin1.png" alt="Image 1" />
                <p>x1</p>
              </div>
              <div className="coin-image-result">
                <img src="/coin2.png" alt="Image 2" />
                <p>x1</p>
              </div>
              <div className="coin-image-result">
                <img src="/coin3.png" alt="Image 3" />
                <p>x1</p>
              </div>
            </div>
            <div className="coin-image-group">
              <div className="coin-image-result">
                <img src="/coin5.png" alt="Image 4" />
                <p>x1</p>
              </div>
              <div className="coin-image-result">
                <img src="/coin10.png" alt="Image 5" />
                <p>x1</p>
              </div>
              <div className="coin-image-result">
                <img src="/coin100.png" alt="Image 6" />
                <p>x1</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
  
  export default CoinChangeOperationDisplayer;
