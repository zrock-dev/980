import { NavOptionRoute } from '@/elements/Navbar';

const NavOption = ({ route, title, selected }) => {
	return (
		<NavOptionRoute href={route} selected={selected}>
			{title}
		</NavOptionRoute>
	);
};

export default NavOption;
