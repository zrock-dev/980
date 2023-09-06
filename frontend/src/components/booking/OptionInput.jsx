const OptionInput = ({ label, selectedOption, stringlist, width, height, onSelectChange }) => {
  const containerStyle = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'flex-start',
    position: 'relative',
    padding:'10px',
  };

  const selectStyle = {
    width: width,
    height: height,
    border: '2px solid #0014ff',
    padding: '5px',
    position: 'relative',
    marginTop: '20px',
    borderRadius: '5px',
    background: 'transparent',
    zIndex:'1',
    outline: 'none',
  };

  const labelStyle = {
    fontSize: '18px',
    position: 'relative',
    top: '-50px',
    left: '10px',
    background: '#4eb1ff',
    padding: '0 5px',
    zIndex:'2',
    color: 'white',
    fontWeight: 'bold',
  };

  return (
    <div style={containerStyle}>
      <select
        value={selectedOption}
        onChange={(e) => {
          onSelectChange(e.target.value);
        }}
        style={selectStyle}
      >
        {stringlist.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
      <label style={labelStyle}>{label}</label>
    </div>
  );
};

export default OptionInput;