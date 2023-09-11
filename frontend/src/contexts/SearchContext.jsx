'use client'
import { createContext, useContext, useState } from 'react';

const SearchContext = createContext();

export const SearchProvider = ({ children }) => {
  const [searchResults, setSearchResults] = useState([]);
  const [inputSearch, setInputSearch] = useState('');
  const [currentPage, setCurrentPage] = useState(0);
  const updateInputSearch = (text) => {
    setInputSearch(text);
  };
  
  const fetchData = async (searchText, page) => {
    try {
      const url = `http://localhost:8080/api/users/search?name=${searchText}&page=${page}`;
      const response = await fetch(url);
      if (!response.ok) {
        throw new Error('Failed to fetch data');
      }
      const data = await response.json();
      if (data.total != 0 && (data.totalPages * data.amountPerPage) > searchResults.length) {
        setSearchResults([...searchResults, ...data.elements]);
        console.log("count")
      }else{
        setSearchResults([]);
      }
    } catch (error) {
      console.error('Error fetching data:', error);
    } finally {
    }
  };

  return (
  <SearchContext.Provider 
  value={{ searchResults, fetchData, inputSearch, updateInputSearch, currentPage, setCurrentPage }
  }>
    {children}
  </SearchContext.Provider>


  );
};

export const useSearch = () => {
  return useContext(SearchContext);
};