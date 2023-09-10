const DateInput = ({
	label,
	value,
	name,
	width = '100%',
	height = '40px',
	onChange
}) => {
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
		border: '2px solid #0014ff',
		padding: '5px',
		position: 'relative',
		marginTop: '20px',
		borderRadius: '5px',
		background: 'transparent',
		color: 'white',
	};

	const labelStyle = {
		color: '#111',
		fontSize: '14px',
		position: 'relative',
		top: '-50px',
		left: '10px',
		background: '#4eb1ff',
		padding: '0 5px',
		color: 'white',
		fontWeight: 'bold'
	};

	return (
		<div style={containerStyle}>
			<input
				type="date"
				name={name}
				value={value}
				style={inputStyle}
				onChange={(e) => {
					onChange(e);
				}}
				required
			/>
			<label style={labelStyle}>{label}</label>
		</div>
	);
};

export default DateInput;
