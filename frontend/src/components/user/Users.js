'use client'
import { useState, useEffect } from 'react';
import UserSearchOption from './UserSeach';
import { useSearch } from '@/contexts/SearchContext';
const Users = () => {
	const itemsPerPage = 6;
	const { searchResults, inputSearch, currentPage,fetchData, setCurrentPage } = useSearch();
	const [fetchedUsers, setFetchedUsers] = useState([]);
	const [fetchedUsersPage, setFetchedUsersPage] = useState(1);
	const [fetchedUsersPerPage, setFetchedUsersPerPage] = useState([]);
	const [backendUserIndex, setBackendUserIndex] = useState(1);
	const [searchUserIndex, setSearchUserIndex] = useState(1);
	const [charging, isCharging] = useState(false)

	const startIndex = (currentPage - 1) * itemsPerPage;
	const endIndex = startIndex + itemsPerPage;
	const displayedResults = searchResults ? searchResults.slice(startIndex, endIndex) : [];

	useEffect(() => {
		fetchData(inputSearch, currentPage);
	}, []);

	const fetchBackendUsers = async () => {
		try {
		  let allUsers = [];
		  let cPage = 0;
		  let totalPages = 1;
		  isCharging(true)
	  
		  do {
			if(cPage <= totalPages){
				const url = `http://localhost:8080/api/users?page=${cPage}`;
				const response = await fetch(url);
		  
				if (!response.ok) {
				  throw new Error('Failed to fetch backend users');
				}
		  
				const userData = await response.json();
				allUsers = [...allUsers, ...userData.elements];
				totalPages = userData.totalPages;
				cPage++;
			}
			
		  } while (cPage <= totalPages);
	  
		  setFetchedUsers(allUsers);
		  isCharging(false)
		} catch (error) {
		  console.error('Error fetching backend users:', error);
		}
	  };
	useEffect(() => {
		fetchBackendUsers();
	}, []); 

	  useEffect(() => {
		const start = (fetchedUsersPage - 1) * itemsPerPage;
		const end = start + itemsPerPage;
		setFetchedUsersPerPage(fetchedUsers.slice(start, end));
	  }, [fetchedUsers, fetchedUsersPage]);
	
	  const handlePrevFetchedUsersPage = () => {
		if (fetchedUsersPage > 1) {
		  setFetchedUsersPage(fetchedUsersPage - 1);
		  setBackendUserIndex(backendUserIndex-1)
		}
	  };
	  const handleNextFetchedUsersPage = () => {
		const nextPage = fetchedUsersPage + 1;
		setFetchedUsersPage(nextPage);
		fetchBackendUsers(nextPage);
		setBackendUserIndex(backendUserIndex+1)
	  };

	  const spanStyle = {
		border: '2px solid black',
		borderRadius: '10%',
		backgroundColor: '#f0f0f0',
		fontWeight: 'bold',
		margin: '1rem',
		padding: '0.5rem',
	  };
	  
	

	const centerDivStyle = {
    	display: 'flex',
    	flexDirection: 'column',
    	alignItems: 'center',
    	justifyContent: 'flex-start',
    	maxHeight: '100vh',
    	textAlign: 'center',
  	};
	const boldBigTextStyle = {
    	fontWeight: 'bold',
    	fontSize: '2rem',
		marginLeft: '2rem',
  	};
	  const SearchTextStyle = {
    	fontSize: '1.5rem',
		marginLeft: '2rem',
  	};

  	const imgStyle = {
	    maxWidth: '50%',
	    marginTop: '1rem',
  	};
	const buttonStyle = {
		marginLeft: "1rem",
		backgroundColor: "var(--blackone)",
		color: "var(--white)",
		fontWeight: "700",
		border: "none",
		marginTop: "10px",
		marginRight: "1rem",
		marginBottom: "1rem",
		padding: "1.1rem 3rem",
		cursor: "pointer",
		borderRadius: "10px",
		fontSize: "14px"
	};

	const centerDiv = {
    	textAlign: 'center',
  	};

	const fixedPaginationStyle = {
	position: 'fixed',
	bottom: '20px',
	left: '50%',
	transform: 'translateX(-50%)',
	display: 'flex',
	alignItems: 'center',
	justifyContent: 'center',
	zIndex: '1',
	};
	  
	
	const handlePrevPage = () => {
		const prevPage = currentPage - 1;
		setCurrentPage(prevPage);
		setSearchUserIndex(searchUserIndex-1)
	  };
	
	  const handleNextPage = () => {
		const nextPage = currentPage + 1;
		setCurrentPage(nextPage);
		setSearchUserIndex(searchUserIndex+1)
		fetchData(inputSearch, nextPage)
	  };
	

	  return (
		<div>
		  {inputSearch.length >= 1 ? (
			searchResults != null && searchResults.length > 0 ? (
			  <div>
				<p style={SearchTextStyle}>Results found for: {inputSearch}</p>
	  
				{displayedResults.map((user) => (
				<div key={user.id}>
					<UserSearchOption
						key={user.id}
						idNum={user.id}
						firstName={user.firstName}
						secondName={user.secondName}
						lastName={user.firstLastName}
						secondLastName={user.secondLastName}
						year={user.dateBirth}
						category={user.category}
						country={user.country}
					/>
				</div>
				))}
				<div style={centerDiv}>
				  {currentPage > 1 && (
					<button style={buttonStyle} onClick={handlePrevPage}>
					  Previous
					</button>
				  )}
				  <span style={spanStyle}>
					{searchUserIndex}
				  </span>
				  {endIndex < searchResults.length && (
					<button style={buttonStyle} onClick={handleNextPage}>
					  Next
					</button>
				  )}
				</div>
			  </div>
			) : (
			  <div style={centerDivStyle}>
				<p style={boldBigTextStyle}>No search results available for: {inputSearch}.</p>
				<img src='/not-found-img.png' alt='results not found' style={imgStyle} />
			  </div>
			)
		  ) : (

			<div>
			{charging ? (
			  <p>Simple paragraph to be displayed when the condition is not met</p>
			) : (
				fetchedUsersPerPage.map((user) => (
					<div key={user.id}>
					  <UserSearchOption
						key={user.id}
						idNum={user.id}
						firstName={user.firstName}
						secondName={user.secondName}
						lastName={user.firstLastName}
						secondLastName={user.secondLastName}
						year={user.dateBirth}
						category={user.category}
						country={user.country}
					  />
					</div>
				  )
				  )
			)}
			<div style={fixedPaginationStyle}>
			  {fetchedUsersPage > 1 && (
				<button style={buttonStyle} onClick={handlePrevFetchedUsersPage}>
				  Previous
				</button>
			  )}
			  <span style={spanStyle}>{backendUserIndex}</span>
			  {fetchedUsersPage < Math.ceil(fetchedUsers.length / itemsPerPage) && (
				<button style={buttonStyle} onClick={handleNextFetchedUsersPage}>
				  Next
				</button>
			  )}
			</div>
		  </div>
		  
      )}
    </div>
  );
};
export default Users;
