const OptionInput = ({ label, selectedOption, stringlist, width, height, onSelectChange }) => {
    const containerStyle = {
      display: 'flex',
      flexDirection: 'column',
      alignItems: 'flex-start',
      position: 'relative',
      padding: '10px',
    };
  
    const selectStyle = {
      width: width,
      height: height,
      border: '1px solid #ccc',
      padding: '5px',
      position: 'relative',
      marginTop: '20px',
      borderRadius: '5px',
      background: 'transparent',
      zIndex:'1',
    };
  
    const labelStyle = {
      fontSize: '14px',
      position: 'relative',
      top: '35px',
      left: '10px',
      background: 'white',
      padding: '5px',
      zIndex:'2',
    };
  
    return (
      <div style={containerStyle}>
        <label style={labelStyle}>{label}</label>
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
      </div>
    );
  };
  
  export default OptionInput;