import { getUserSuggestion } from '@/backend/User';
import {
	SearcherContainer,
	SearchSuggestion,
	SearchSuggestions
} from '@/elements/Navbar';
import MagnifyingGlass from '@/icons/MagnifyingGlass';
import Xmark from '@/icons/Xmark';
import { BLUE, GRAYTWO } from '@/styles/colors';
import { useEffect, useRef, useState } from 'react';

const Searcher = () => {
	const searchContainer = useRef(null);
	const suggestionsContainer = useRef(null);
	const [inputSearch, setInputSearch] = useState('');
	const [suggestions, setSuggestions] = useState([]);

	const showSuggestions = () => {
		if (searchContainer.current && suggestionsContainer.current) {
			searchContainer.current.style.borderColor = BLUE;
			suggestionsContainer.current.style.display = 'flex';
		}
	};

	const hideSuggestions = () => {
		if (searchContainer.current && suggestionsContainer.current) {
			searchContainer.current.style.borderColor = GRAYTWO;
			suggestionsContainer.current.style.display = 'none';
		}
	};

	const renderSuggestions = () => {
		return (
			<SearchSuggestions ref={suggestionsContainer}>
				{suggestions.map((suggestion) => (
					<SearchSuggestion
						onFocus={showSuggestions}
						onClick={() => setInputSearch(suggestion)}
					>
						<MagnifyingGlass />
						<span>{suggestion}</span>
					</SearchSuggestion>
				))}
			</SearchSuggestions>
		);
	};

	useEffect(() => {
		getUserSuggestion(inputSearch).then((data) => {
			setSuggestions(data);
		});
	}, [inputSearch]);

	return (
		<SearcherContainer ref={searchContainer}>
			<input
				type="text"
				placeholder="Search"
				value={inputSearch}
				onChange={(event) => setInputSearch(event.target.value)}
				onFocus={showSuggestions}
				onBlur={hideSuggestions}
			/>
			<button onClick={() => setInputSearch('')} onFocus={showSuggestions}>
				{<Xmark />}
			</button>
			<button>{<MagnifyingGlass />}</button>

			{renderSuggestions()}
		</SearcherContainer>
	);
};

export default Searcher;
