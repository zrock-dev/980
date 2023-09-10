'use client'
import UserSearchOption from './UserSeach';
import { useSearch } from '@/contexts/SearchContext';
const Users = () => {
	const { searchResults } = useSearch();
	const firstResults = searchResults.slice(0, 20);

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
  	};

  	const imgStyle = {
	    maxWidth: '50%',
	    marginTop: '1rem',
  	};

  	return (
	    <div>
      	{searchResults.length > 0 ? (
	        firstResults.map((user) => (
          	<div key={user.id}>
	            <UserSearchOption
              	key={user.id}
              	firstName={user.firstName}
				secondName={user.secondName}
              	lastName={user.firstLastName}
				secondLastName={user.secondLastName}
              	year={user.dateBirth}
              	category={user.category}
              	country={user.country}
            	/>
          	</div>
        	))
      	) : (
		<div style={centerDivStyle}>
			<p style={boldBigTextStyle}>No search results available.</p>
			<img src='/not-found-img.png' alt='results not found' style={imgStyle} />
		</div>
    	  )}
    </div>
  );
};

export default Users;
