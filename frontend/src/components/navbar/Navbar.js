'use client';
import { Icon980, Title980 } from '@/elements/GeneralElements';
import {
	NavTitle,
	NavbarContainer,
	NavbarOptionContainer
} from '@/elements/Navbar';
import { usePathname } from 'next/navigation';

import NavOption from './NavOption';
import Searcher from './Searcher';

const Navbar = () => {
	const pathname = usePathname();

	return (
		<NavbarContainer>
			<NavTitle href={'/'}>
				<Icon980 src="/icon-980.png" alt="980" />
				<Title980>980</Title980>
			</NavTitle>
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
					route={'/coin-change'}
					title={'Coin change'}
					selected={pathname === '/coin-change'}
				/>
				<NavOption
					route={'/flights'}
					title={'Flights'}
					selected={pathname === '/flights'}
				/>
				<NavOption
					route={'/queue'}
					title={'Queue'}
					selected={pathname === '/queue'}
				/>
			</NavbarOptionContainer>
			<Searcher />
		</NavbarContainer>
	);
};

export default Navbar;
