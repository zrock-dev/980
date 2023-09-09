'user client';

import PenToSquare from '@/icons/PenToSquare';
import Popup from '../Popup';
import { useEffect, useState } from 'react';
import { GeneralButton, Subtitle } from '@/elements/GeneralElements';
import { OptionContainer, OptionTopContainer } from '@/elements/UserOptions';
import Xmark from '@/icons/Xmark';
import { FormContainer, FormSubContainer } from '@/elements/FormElements';
import FieldForm from './FieldForm';

const EditUserForm = ({ user }) => {
	const [isEditing, setEditing] = useState(false);
	const [userEditing, setUserEditing] = useState(user);
	const [userChanged, setUserChanged] = useState(false);

	const changeField = (event) => {
		const { name, value } = event.target;
		setUserEditing({
			...userEditing,
			[name]: value
		});
	};

	const summit = () => {
		alert(
			userEditing.firstName +
				userEditing.secondName +
				userEditing.firstLastName +
				userEditing.secondLastName
		);
	};

	useEffect(() => {
		if (
			user.id.toString() === userEditing.id.toString() &&
			user.firstName === userEditing.firstName &&
			user.secondName === userEditing.secondName &&
			user.firstLastName === userEditing.firstLastName &&
			user.secondLastName === userEditing.secondLastName &&
			user.birthdate.toString() === userEditing.birthdate.toString() &&
			user.country === userEditing.country
		) {
			setUserChanged(false);
		} else {
			setUserChanged(true);
		}
	}, [userEditing]);

	return (
		<>
			<button disabled={!user} onClick={() => setEditing(true)}>
				<PenToSquare />
			</button>
			<Popup
				isOpen={isEditing}
				onClose={() => setEditing(false)}
				width="25%"
				right="730px"
				children={
					<OptionContainer>
						<OptionTopContainer>
							<Subtitle>Edit user information</Subtitle>
							<button onClick={() => setEditing(false)}>
								<Xmark />
							</button>
						</OptionTopContainer>
						<FormContainer>
							<FieldForm
								type={'text'}
								value={userEditing.id}
								title={'C.I.'}
								name={'id'}
								changeField={changeField}
							/>
							<FormSubContainer>
								<FieldForm
									type={'text'}
									value={userEditing.firstName}
									title={'First Name'}
									name={'firstName'}
									changeField={changeField}
								/>
								<FieldForm
									type={'text'}
									value={userEditing.secondName}
									title={'Second Name'}
									name={'secondName'}
									changeField={changeField}
								/>
							</FormSubContainer>
							<FormSubContainer>
								<FieldForm
									type={'text'}
									value={userEditing.firstLastName}
									title={'First Last Name'}
									name={'firstLastName'}
									changeField={changeField}
								/>
								<FieldForm
									type={'text'}
									value={userEditing.secondLastName}
									title={'Second Last Name'}
									name={'secondLastName'}
									changeField={changeField}
								/>
							</FormSubContainer>
							<FieldForm
								type={'date'}
								value={userEditing.birthdate}
								title={'Birthdate'}
								name={'birthdate'}
								changeField={changeField}
							/>
							<FieldForm
								type={'text'}
								value={userEditing.country}
								title={'Country'}
								name={'country'}
								changeField={changeField}
							/>
						</FormContainer>

						<GeneralButton disabled={!userChanged} onClick={summit}>
							Save
						</GeneralButton>
					</OptionContainer>
				}
			/>
		</>
	);
};

export default EditUserForm;
