'use client';

import { LoaderContainer, LoaderIcon } from '@/elements/GeneralElements';

const Loader = ({
	overallSize = '100vh',
	withAbsolute = true,
	iconSize = '100px'
}) => {
	return (
		<LoaderContainer overallSize={overallSize} withAbsolute={withAbsolute}>
			<LoaderIcon iconSize={iconSize}></LoaderIcon>
		</LoaderContainer>
	);
};

export default Loader;
