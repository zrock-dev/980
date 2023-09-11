'user client';

import PenToSquare from '@/icons/PenToSquare';
import Popup from '../Popup';
import { useEffect, useState } from 'react';
import { GeneralButton, Subtitle } from '@/elements/GeneralElements';
import { OptionContainer, OptionTopContainer } from '@/elements/UserOptions';
import Xmark from '@/icons/Xmark';
import { FormContainer, FormSubContainer } from '@/elements/FormElements';
import FieldForm from './FieldForm';
import { useRouter } from 'next/navigation';
import { editUser } from '@/backend/UserRequest';

const EditUserForm = ({ user, fetchUserData }) => {
	const router = useRouter();
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
		editUser(user, userEditing)
			.then(() => {
				const secondName =
					userEditing.secondName.length == 0
						? ''
						: `secondName=${userEditing.secondName}&`;
				const url = `/users/${user.id}?firstName=${userEditing.firstName}
			&${secondName}firstLastName=${userEditing.firstLastName}
			&secondLastName=${userEditing.secondLastName}`;
				router.push(url);
				fetchUserData();
			})
			.catch((error) => {
				alert(error.message);
			});
	};

	useEffect(() => {
		if (
			user.id.toString() === userEditing.id.toString() &&
			user.firstName === userEditing.firstName &&
			user.secondName === userEditing.secondName &&
			user.firstLastName === userEditing.firstLastName &&
			user.secondLastName === userEditing.secondLastName &&
			user.dateBirth.toString() === userEditing.dateBirth.toString() &&
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
								value={userEditing.dateBirth}
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
