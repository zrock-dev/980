'use client'
import Navbar from '@/components/navbar/Navbar';
import '../styles/globals.css';
import { Quattrocento_Sans } from 'next/font/google';
import { SearchProvider } from '@/contexts/SearchContext'; 

const quattrocentoSans = Quattrocento_Sans({
	weight: ['400', '700'],
	subsets: ['latin']
});

export default function RootLayout({ children }) {
	return (
		<SearchProvider>
			<html lang="en" className={quattrocentoSans.className}>
			<body>
				<Navbar/>
				{children}
			</body>
			</html>
		</SearchProvider>
	);
}
