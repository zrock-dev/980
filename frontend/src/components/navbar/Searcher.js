import MagnifyingGlass from '@/icons/MagnifyingGlass';

const Searcher = () => {
	return (
		<div>
			<input type="text" placeholder="Search" />
			<button>{<MagnifyingGlass />}</button>
		</div>
	);
};

export default Searcher;
