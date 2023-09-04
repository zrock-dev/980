import Navbar from '@/components/navbar/Navbar';
import CoinChangeForm from '@/components/coin-change/CoinChangeForm';
import CoinChangeOperationDisplayer from '@/components/coin-change/CoinChangeOperationDisplayer';
import '../styles/globals.css';
import { Quattrocento_Sans } from 'next/font/google';
import CoinChangeOperationDisplayer from '@/components/coin-change/CoinChangeOperationDisplayer';

const quattrocentoSans = Quattrocento_Sans({
	weight: ['400', '700'],
	subsets: ['latin']
});

export default function RootLayout({ children }) {
	return (
		<html lang="en">
			<body className={quattrocentoSans.className}>
				<CoinChangeOperationDisplayer/>
				<CoinChangeForm/>
				{children}
			</body>
		</html>
	);
}
