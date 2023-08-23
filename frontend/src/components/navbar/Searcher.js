import { SearcherContainer } from '@/elements/Navbar';
import MagnifyingGlass from '@/icons/MagnifyingGlass';

const Searcher = () => {
	return (
		<SearcherContainer>
			<input type="text" placeholder="Search" />
			<button>{<MagnifyingGlass />}</button>
		</SearcherContainer>
	);
};

export default Searcher;
