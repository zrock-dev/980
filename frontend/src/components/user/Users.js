'use client'
import UserSearchOption from './UserSeach';
import { useSearch } from '@/contexts/SearchContext';

const Users = () => {
  const { searchResults } = useSearch();
  const  firstResults = searchResults.slice(0,20)
  return (
    <div>
      {firstResults.map((user) => (
		<div>
			<UserSearchOption
			key={user.id}
			firstName={user.firstName}
			lastName={user.secondName}
			year={user.dateBirth}
			category={user.category}
			country={user.country}
			/>
		</div>		
      ))}
    </div>
  );
};

export default Users;
