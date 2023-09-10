'use client'
import { createContext, useContext, useState } from 'react';

const SearchContext = createContext();

export const SearchProvider = ({ children }) => {
  const [searchResults, setSearchResults] = useState([]);
  const [isFetching, setIsFetching] = useState(false);

  const fetchData = async (searchText) => {
    try {
      setIsFetching(true);
      const url = `http://localhost:8080/api/users/search?name=${searchText}`;
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }
      const data = await response.json();
      setSearchResults(data);
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
      setIsFetching(false);
    }
  };

  return (
    <SearchContext.Provider value={{ searchResults, fetchData, isFetching }}>
      {children}
    </SearchContext.Provider>
  );
};

export const useSearch = () => {
  return useContext(SearchContext);
};