'use client';
import Link from 'next/link';
import UserSearchOption from './UserSeach';
const userList = [

	{
	  firstName: "Diego",
	  lastName: "Rodriguez",
	  year: "31",
	  category: "PREMIUM",
	  country: "ARGENTINA",
	},
	{
	  firstName: "Hiroshi",
	  lastName: "Sato",
	  year: "28",
	  category: "NORMAL",
	  country: "JAPAN",
	},
	{
	  firstName: "Isabel",
	  lastName: "Fernandez",
	  year: "22",
	  category: "PREMIUM",
	  country: "SPAIN",
	},
	{
	  firstName: "William",
	  lastName: "Davis",
	  year: "25",
	  category: "NORMAL",
	  country: "UNITED KINGDOM",
	},
	{
	  firstName: "Maria",
	  lastName: "Gonzalez",
	  year: "29",
	  category: "NORMAL",
	  country: "MEXICO",
	},
	{
	  firstName: "Hans",
	  lastName: "Müller",
	  year: "33",
	  category: "VIP",
	  country: "GERMANY",
	},
  ];

  const Users = () => {
	return (
	  <div>
		{userList.map((user, index) => (
		  <UserSearchOption
			key={index}
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
