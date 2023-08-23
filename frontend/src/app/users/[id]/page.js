import User from '@/components/user/User';

const UserPage = ({ params }) => {
	return <User id={params.id} />;
};

export default UserPage;
