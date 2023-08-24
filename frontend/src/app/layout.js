import Navbar from '@/components/navbar/Navbar';
import CoinChangeForm from '@/components/coin-change/CoinChangeForm';
import '../styles/globals.css';
import '../styles/coin-change.css'
import { Quattrocento_Sans } from 'next/font/google';

const quattrocentoSans = Quattrocento_Sans({
	weight: ['400', '700'],
	subsets: ['latin']
});

export default function RootLayout({ children }) {
	return (
		<html lang="en">
			<body className={quattrocentoSans.className}>
				<CoinChangeForm/>
				{children}
			</body>
		</html>
	);
}
