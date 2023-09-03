const PlainTextInput = ({ label, text, width, height, onChangeText }) => {
  const containerStyle = {
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'flex-start',
    position: 'relative',
    padding: '10px',
  };

  const inputStyle = {
    width: width,
    height: height,
    border: '1px solid #111',
    padding: '5px',
    position: 'relative',
    marginTop: '20px',
    borderRadius: '5px',
  };

  const labelStyle = {
    color:'#111',
    fontSize: '14px',
    position: 'relative',
    top: '-50px',
    left: '10px',
    background: 'white',
    padding: '5px',
  };

  return (
    <div style={containerStyle}>
      <input
        type="text"
        value={text}
        style={inputStyle}
        onChange={(e) => {
          onChangeText(e.target.value);
        }}
      />
      <label style={labelStyle}>{label}</label>
    </div>
  );
};

export default PlainTextInput;
