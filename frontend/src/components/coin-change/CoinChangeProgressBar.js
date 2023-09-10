const ProgressBar = ({moneyJson}) => {
  return (
    <div className="progress-bar-container">
      <h2 className="progress-title">Progress Bar</h2>
      <div className="progress-bar">
        <div className="progress red" style={{ width: '30%' }}>30%</div>
        <div className="progress yellow" style={{ width: '5%' }}>5%</div>
        <div className="progress purple" style={{ width: '10%' }}>10%</div>
        <div className="progress orange" style={{ width: '25%' }}>25%</div>
        <div className="progress green" style={{ width: '10%' }}>10%</div>
        <div className="progress blue" style={{ width: '20%' }}>20%</div>
      </div>
    </div>
  );
};

export default ProgressBar;
