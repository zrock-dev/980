'use client';

import { SecondaryText, Separator } from '@/elements/GeneralElements';
import {
	HomepageContainer,
	HomepageImage,
	HomepageOptionContainer
} from '@/elements/Homepage';

const Home = () => {
	return (
		<HomepageContainer>
			<HomepageImage src="/plane-main-page.png" alt="plane-980" />
			<HomepageOptionContainer href={'/users'} r>
				<b>01</b>
				<span>user seeker</span>
				<SecondaryText>
					This is a microservice that runs on its own database managed by a
					B-Tree.
				</SecondaryText>
			</HomepageOptionContainer>
			<Separator></Separator>
			<HomepageOptionContainer href={'/booking'}>
				<b>02</b>
				<span>flight booking</span>
				<SecondaryText>
					This is a microservice it also has its own database and works together
					with the user service to manage the tickets created. In addition, it
					works with a Heap-Tree to manage the priority of tickets on a flight.
				</SecondaryText>
			</HomepageOptionContainer>
			<Separator></Separator>
			<HomepageOptionContainer href={'/coin-change'}>
				<b>03</b>
				<span>coin exchange</span>
				<SecondaryText>
					This service works independently with a Heap-Tree to calculate the
					change based on an input.
				</SecondaryText>
			</HomepageOptionContainer>
		</HomepageContainer>
	);
};

export default Home;
