const User = ({ id }) => {
	return id ? <div>Only User id: {id}</div> : <div>loading</div>;
};

export default User;
