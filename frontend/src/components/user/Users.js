'use client';
import Link from 'next/link';
import UserSearchOption from './UserSeach';
import { useSearch } from '@/contexts/SearchContext'; 

  const Users = () => {
	const { searchResults } = useSearch();
	
	return (
	  <div>
		{searchResults.map((user, index) => (
        <UserSearchOption
		key={user.id}
		firstName={user.firstName}
		lastName={user.lastName}
		year={user.year}
		category={user.category}
		country={user.country}
	  />
		))}
	  </div>
	);
  };

export default Users;
