import {
	SearcherContainer,
	SearchSuggestion,
	SearchSuggestions
} from '@/elements/Navbar';
import MagnifyingGlass from '@/icons/MagnifyingGlass';

const Searcher = () => {
	return (
		<SearcherContainer>
			<input type="text" placeholder="Search" />
			<button>{<MagnifyingGlass />}</button>

			<SearchSuggestions>
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
