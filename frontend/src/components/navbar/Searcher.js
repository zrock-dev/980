import {
	SearcherContainer,
	SearchSuggestion,
	SearchSuggestions
} from '@/elements/Navbar';
import MagnifyingGlass from '@/icons/MagnifyingGlass';
import { BLUE, GRAYTWO } from '@/styles/colors';
import { useRef } from 'react';

const Searcher = () => {
	const searchContainer = useRef(null);
	const suggestionsContainer = useRef(null);

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

	return (
		<SearcherContainer ref={searchContainer}>
			<input
				type="text"
				placeholder="Search"
				onFocus={showSuggestions}
				onBlur={hideSuggestions}
			/>
			<button>{<MagnifyingGlass />}</button>

			<SearchSuggestions ref={suggestionsContainer}>
				<SearchSuggestion>Luiggy Mamani</SearchSuggestion>
				<SearchSuggestion>Luiggy Mamani</SearchSuggestion>
				<SearchSuggestion>Luiggy Mamani</SearchSuggestion>
				<SearchSuggestion>Luiggy Mamani</SearchSuggestion>
				<SearchSuggestion>Luiggy Mamani</SearchSuggestion>
				<SearchSuggestion>Luiggy Mamani</SearchSuggestion>
			</SearchSuggestions>
		</SearcherContainer>
	);
};

export default Searcher;
