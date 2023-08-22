import Navbar from '@/components/navbar/Navbar';
import '../styles/globals.css';
import { Quattrocento_Sans } from 'next/font/google';

const quattrocentoSans = Quattrocento_Sans({
	weight: ['400', '700'],
	subsets: ['latin']
});

export default function RootLayout({ children }) {
	return (
		<html lang="en">
			<body className={quattrocentoSans.className}>
				<Navbar />
				{children}
			</body>
		</html>
	);
}
