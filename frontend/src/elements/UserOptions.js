import styled from '@emotion/styled';

export const OptionContainer = styled.div`
	display: flex;
	flex-direction: column;
	align-items: flex-end;
	gap: 15px;

	padding: 30px;
	max-width: ${(props) => (props.maxWidth ? props.maxWidth : 'auto')};

	border-radius: 20px;
	transition: all ease 250ms;
	animation: modalComeIn 250ms linear;
`;

export const OptionTopContainer = styled.div`
	display: flex;
	justify-content: space-between;

    width: 100%;
`;
