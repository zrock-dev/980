'use client';

import { QueryClientProvider, QueryClient } from 'react-query';
import Navbar from './navbar/Navbar';

const queryClient = new QueryClient();

const MainContainer = ({ children }) => {
	return (
		<QueryClientProvider client={queryClient}>
			<Navbar />
			{children}
		</QueryClientProvider>
	);
};

export default MainContainer;
