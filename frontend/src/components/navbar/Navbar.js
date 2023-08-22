import Link from 'next/link';

const Navbar = () => {
	return (
		<div>
			<img src="/icon-980.png" alt="980" />
			<Link href={'/'}>Home</Link>
			<Link href={'/users'}>Users</Link>
			<Link href={'/booking'}>Booking</Link>
			<Link href={'/money-exchange'}>Money exchange</Link>
		</div>
	);
};

export default Navbar;
