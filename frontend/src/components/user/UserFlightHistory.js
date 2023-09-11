'use client';

import { useEffect, useState } from 'react';
import Loader from '../Loader';
import { getUserTickets } from '@/backend/UserRequest';
import UserFlight from './UserFlight';

import {
	FlightHistoryMainContainer,
	FlightHistoryContainer,
	EmptyFlightHistory
} from '@/elements/UserFlightHistory';
import {
	SecondaryText,
	PaginationContainer,
	PaginationButton
} from '@/elements/GeneralElements';
import ChevronLeft from '@/icons/ChevronLeft';
import ChevronRight from '@/icons/ChevronRight';

const UserFlightHistory = ({ user, fetchUserData }) => {
	const [pageData, setPageData] = useState({
		elements: null,
		currentPage: 0,
		totalPages: 0
	});
	const [pages, setPages] = useState([]);

	const fetchTickets = () => {
		getUserTickets(user.id, pageData.currentPage)
			.then((response) => {
				const { currentPage, totalPages, elements } = response.data;
				setPageData({
					currentPage,
					totalPages : totalPages + 1,
					elements
				});
			})
			.catch(() => {
				setPageData({
					currentPage: 0,
					totalPages: 0,
					elements: []
				});
			});
	};

	useEffect(() => {
		fetchTickets();
	}, [pageData.currentPage]);

	useEffect(() => {
		if (pages.length <= 0) {
			const pages = [];
			for (let index = 0; index < pageData.totalPages; index++) {
				pages.push(index);
			}
			setPages(pages);
		}
	}, [pageData]);

	const renderFlihtHistory = () => {
		return pageData.elements.length > 0 ? (
			<FlightHistoryMainContainer>
				<FlightHistoryContainer>
					{pageData.elements.map((ticket) => (
						<UserFlight ticket={ticket} fetchUserData={fetchUserData} />
					))}
				</FlightHistoryContainer>
				{pageData.totalPages > 0 && (
					<PaginationContainer>
						{pageData.currentPage - 1 >= 0 && (
							<button
								onClick={() => {
									setPageData({
										...pageData,
										currentPage: pageData.currentPage - 1
									});
								}}
							>
								<ChevronLeft />
							</button>
						)}
						{pages.map((page) => (
							<PaginationButton
								selected={page === pageData.currentPage}
								onClick={() => {
									setPageData({
										...pageData,
										currentPage: page
									});
								}}
							>
								{page + 1}
							</PaginationButton>
						))}
						{pageData.currentPage + 1 < pageData.totalPages && (
							<button
								onClick={() => {
									setPageData({
										...pageData,
										currentPage: pageData.currentPage + 1
									});
								}}
							>
								<ChevronRight />
							</button>
						)}
					</PaginationContainer>
				)}
			</FlightHistoryMainContainer>
		) : (
			<EmptyFlightHistory>
				<img src="/airplane_flying.gif" alt="" />
				<span>empty flight history</span>
				<SecondaryText>
					You can book flights from the "Book Flight" option and have a flight
					history stored in our database.
				</SecondaryText>
			</EmptyFlightHistory>
		);
	};

	return pageData.elements ? (
		renderFlihtHistory()
	) : (
		<Loader withAbsolute={false} overallSize={'200px'} iconSize="50px" />
	);
};

export default UserFlightHistory;
