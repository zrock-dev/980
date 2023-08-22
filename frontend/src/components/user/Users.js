import Link from 'next/link';

const Users = () => {
	return (
		<div>
			<span>users</span>
			<Link href={'/users/userid1'}>only user</Link>
		</div>
	);
};

export default Users;
