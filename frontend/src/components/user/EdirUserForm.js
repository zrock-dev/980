'user client';

import PenToSquare from '@/icons/PenToSquare';
import Popup from '../Popup';
import { useState } from 'react';
import { GeneralButton, Subtitle } from '@/elements/GeneralElements';
import { OptionContainer, OptionTopContainer } from '@/elements/UserOptions';
import Xmark from '@/icons/Xmark';
import { FormContainer, FormSubContainer } from '@/elements/FormElements';
import FieldForm from './FieldForm';

const EditUserForm = ({ user }) => {
	const [isEditing, setEditing] = useState(false);
	const [userEditing, setUserEditing] = useState(user);

	const changeField = (key, value) => {
		setUserEditing((prevState) => ({
			...prevState,
			key: value
		}));
	};

	const verifyChanged = () => {
		return JSON.stringify(user) === JSON.stringify(userEditing);
	};

	const summit = () => {
		alert(userEditing.firstLastName);
	};

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
							<FieldForm type={'text'} value={userEditing.id} title={'C.I.'} />
							<FormSubContainer>
								<FieldForm
									type={'text'}
									value={userEditing.firstName}
									title={'First Name'}
								/>
								<FieldForm
									type={'text'}
									value={userEditing.secondName}
									title={'Second Name'}
								/>
							</FormSubContainer>
							<FormSubContainer>
								<FieldForm
									type={'text'}
									value={userEditing.firstLastName}
									title={'First Last Name'}
								/>
								<FieldForm
									type={'text'}
									value={userEditing.secondLastName}
									title={'Second Last Name'}
								/>
							</FormSubContainer>

							<FieldForm
								type={'date'}
								value={userEditing.birthdate}
								title={'Birthdate'}
							/>
							<FieldForm
								type={'text'}
								value={userEditing.country}
								title={'Country'}
							/>
						</FormContainer>

						<GeneralButton disabled={verifyChanged} onClick={summit}>
							Save
						</GeneralButton>
					</OptionContainer>
				}
			/>
		</>
	);
};

export default EditUserForm;
