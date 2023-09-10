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

	const startIndex = (currentPage - 1) * itemsPerPage;
	const endIndex = startIndex + itemsPerPage;
	const displayedResults = searchResults ? searchResults.slice(startIndex, endIndex) : [];

	useEffect(() => {
		fetchData(inputSearch, currentPage);
	}, [currentPage]);
	
	useEffect(() => {
		const fetchBackendUsers = async (page) => {
		  try {
			const url = `http://localhost:8080/api/users?page=${page}`;
			const response = await fetch(url);
			if (!response.ok) {
			  throw new Error('Failed to fetch backend users');
			}
			const users = await response.json();
			setFetchedUsers(users.elements);
		  } catch (error) {
			console.error('Error fetching backend users:', error);
		  }
		};
	
		fetchBackendUsers(0);
	  }, []);

	  useEffect(() => {
		const start = (fetchedUsersPage - 1) * itemsPerPage;
		const end = start + itemsPerPage;
		setFetchedUsersPerPage(fetchedUsers.slice(start, end));
	  }, [fetchedUsers, fetchedUsersPage]);
	
	  const handlePrevFetchedUsersPage = () => {
		if (fetchedUsersPage > 1) {
		  setFetchedUsersPage(fetchedUsersPage - 1);
		}
	  };
	  const handleNextFetchedUsersPage = () => {
		const maxPage = Math.ceil(fetchedUsers.length / itemsPerPage);
		if (fetchedUsersPage < maxPage) {
		  setFetchedUsersPage(fetchedUsersPage + 1);
		}
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
		marginLeft: "auto",
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
	const handlePrevPage = () => {
		const prevPage = currentPage - 1;
		setCurrentPage(prevPage);
	  };
	
	  const handleNextPage = () => {
		const nextPage = currentPage + 1;
		setCurrentPage(nextPage);
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
          {fetchedUsersPerPage.map((user) => (
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
            {fetchedUsersPage > 1 && (
              <button style={buttonStyle} onClick={handlePrevFetchedUsersPage}>Previous</button>
            )}
            {fetchedUsersPage < Math.ceil(fetchedUsers.length / itemsPerPage) && (
              <button style={buttonStyle} onClick={handleNextFetchedUsersPage}>Next</button>
            )}
          </div>
        </div>
      )}
    </div>
  );
};
export default Users;
