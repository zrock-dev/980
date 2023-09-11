import { SearcherContainer } from '@/elements/Navbar';
import MagnifyingGlass from '@/icons/MagnifyingGlass';
import Xmark from '@/icons/Xmark';
import { BLUE, GRAYTWO } from '@/styles/colors';
import { useRef, useState } from 'react';
import { useSearch, setSearchResults } from '@/contexts/SearchContext';
import { useRouter } from 'next/navigation';

const Searcher = () => {
	const router = useRouter();
	const searchContainer = useRef(null);
	const [inputSearch, setInputSearch] = useState('');
	const {
		fetchData,
		isFetching,
		updateInputSearch,
		currentPage,
		setCurrentPage
	} = useSearch();

	const showSuggestions = () => {
		if (searchContainer.current) {
			searchContainer.current.style.borderColor = BLUE;
		}
	};

	const hideSuggestions = () => {
		if (searchContainer.current) {
			searchContainer.current.style.borderColor = GRAYTWO;
		}
	};

	const handleSearch = () => {
		updateInputSearch(inputSearch);
		fetchData(inputSearch, 0);
		setCurrentPage(1);
		router.push('/users');
	};

	return (
		<SearcherContainer ref={searchContainer}>
			<input
				type="text"
				placeholder="Search"
				value={inputSearch}
				onChange={(event) => setInputSearch(event.target.value)}
				onFocus={showSuggestions}
				onBlur={hideSuggestions}
				onKeyDown={(event) => {
					event.key === 'Enter' && inputSearch.length > 0 && handleSearch();
				}}
			/>
			<button onClick={() => setInputSearch('')} onFocus={showSuggestions}>
				{<Xmark />}
			</button>
			<button onClick={handleSearch}>{<MagnifyingGlass />}</button>
		</SearcherContainer>
	);
};

export default Searcher;
