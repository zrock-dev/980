import { NavOptionRoute } from '@/elements/navbar/NavOption';

const NavOption = ({ route, title, selected }) => {
	return (
		<NavOptionRoute href={route} selected={selected}>
			{title}
		</NavOptionRoute>
	);
};

export default NavOption;
