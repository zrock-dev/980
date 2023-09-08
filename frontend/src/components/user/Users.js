'use client';
import Link from 'next/link';
import UserSearchOption from './UserSeach';
import { useSearch } from '@/contexts/SearchContext'; 

  const Users = () => {

	const userList = [

		{
		  firstName: "Diego",
		  lastName: "Rodriguez",
		  year: "31",
		  category: "PREMIUM",
		  country: "ARGENTINA",
		}];

	const { searchText } = useSearch(); 
	
	return (
	  <div>
		{userList.map((user, index) => (
		  <UserSearchOption
			key={index}
			firstName={searchText}
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
