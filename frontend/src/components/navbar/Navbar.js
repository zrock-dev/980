'use client';
import { Icon980 } from '@/elements/GeneralElements';
import {
	NavbarContainer,
	NavbarOptionContainer
} from '@/elements/navbar/Navbar';
import { usePathname } from 'next/navigation';

import NavOption from './NavOption';

const Navbar = () => {
	const pathname = usePathname();

	return (
		<NavbarContainer>
			<Icon980 src="/icon-980.png" alt="980" />
			<NavbarOptionContainer>
				<NavOption route={'/'} title={'Home'} selected={pathname === '/'} />
				<NavOption
					route={'/users'}
					title={'Users'}
					selected={pathname.includes('/users')}
				/>
				<NavOption
					route={'/booking'}
					title={'Booking'}
					selected={pathname.includes('/booking')}
				/>
				<NavOption
					route={'/money-exchange'}
					title={'Money exchange'}
					selected={pathname === '/money-exchange'}
				/>
			</NavbarOptionContainer>
		</NavbarContainer>
	);
};

export default Navbar;
