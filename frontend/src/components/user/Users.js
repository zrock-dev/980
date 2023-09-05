'use client';
import Link from 'next/link';
import UserSearchOption from './UserSeach';

const Users = () => {
	return (
		<div>
			<span>users</span>
			<UserSearchOption
			firstName= "Victor Leon"
			lastName="Villca Silva" 
			year ="19"
			category="NORMAL"
			country = "BOLIVIA"
			/>
		</div>
	);
};

export default Users;
