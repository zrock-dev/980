'use client';

import { getFlights } from '@/backend/BookingRequest';
import {
	ContainerStyle,
	SelectStyle,
	LabelStyle
} from '@/elements/FormElements';
import { LoaderIcon } from '@/elements/GeneralElements';
import Rotate from '@/icons/Rotate';
import { useEffect, useState } from 'react';

const Flights = ({ value, name, onChange }) => {
	const [pageData, setPageData] = useState({
		currentPage: -1,
		elements: [],
		totalPages: 1,
		isLoading: true
	});

	const loadData = () => {
		getFlights(pageData.currentPage + 1)
			.then((response) => {
				const { currentPage, elements, totalPages } = response.data;

				setPageData({
					currentPage,
					elements: pageData.elements.concat(elements),
					totalPages,
					isLoading: false
				});
			})
			.catch(() => {
				setPageData({
					currentPage: 2,
					elements: [],
					totalPages: 1,
					isLoading: false
				});
			});
	};

	useEffect(() => {
		if (pageData.isLoading && pageData.currentPage + 1 <= pageData.totalPages) {
			loadData();
		}
	}, [pageData.isLoading]);

	useEffect(() => {
		loadData();
	}, []);

	return (
		<ContainerStyle>
			<SelectStyle
				value={value}
				name={name}
				onChange={(event) => {
					onChange(event);
				}}
				required
			>
				<option value="">Select your flight</option>
				{pageData.elements?.map((flight, index) => (
					<option key={flight.id} value={flight.id}>
						#{index + 1}
						{' - '}
						{flight.source.country} {flight.source.state}
						{' - '}
						{flight.destination.country} {flight.destination.state}
						{' | '}
						{flight.date}
					</option>
				))}
			</SelectStyle>
			<LabelStyle>Flights</LabelStyle>

			{pageData.currentPage < pageData.totalPages && (
				<button onClick={() => setPageData({ ...pageData, isLoading: true })}>
					{pageData.isLoading ? (
						<LoaderIcon></LoaderIcon>
					) : (
						<>
							<Rotate /> Load more flights
						</>
					)}
				</button>
			)}
		</ContainerStyle>
	);
};

export default Flights;
