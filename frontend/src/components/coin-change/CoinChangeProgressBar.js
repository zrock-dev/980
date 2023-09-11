const colors = [
  "#cc0000",
  "#cc3300",
  "#cc6600",
  "#cc9900",
  "#cccc00",
  "#99cc00",
  "#66cc00",
  "#33cc00",
  "#00cc00",
  "#00cc33",
  "#00cc66",
  "#00cc99",
  "#00cccc",
  "#0099cc",
  "#0066cc",
  "#0033cc",
  "#0000cc",
  "#3300cc",
];

const ProgressBar = ({ coinContext }) => {
  return (
    <div className="progress-bar-container">
      <h2 className="progress-title">Progress Bar</h2>
      <div className="progress-bar">
        {console.log(coinContext)}
        {coinContext  &&
          coinContext.map((coin, index) => (
            <div
              className="progress"
              key={index}
              style={{
                width: `${coin.percentage}%`,
                backgroundColor: colors[index],
              }}
            >
              {coin.percentage.toFixed(2)}%
            </div>
          ))}
      </div>
    </div>
  );
};
export default ProgressBar;
