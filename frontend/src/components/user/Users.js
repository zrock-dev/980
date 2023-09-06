'use client';
import Link from 'next/link';
import UserSearchOption from './UserSeach';

const Users = () => {
	return (
		<div>
			<UserSearchOption
			firstName= "Maria Natalia"
			lastName="Montalvo Mamani" 
			year ="19"
			category="NORMAL"
			country = "BOLIVIA"
			/>
		</div>
	);
};

export default Users;
