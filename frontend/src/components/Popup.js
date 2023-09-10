import { Overlay, PopupContainer } from '@/elements/popup';

const Popup = ({
	isOpen,
	onClose,
	children,
	color = 'black',
	top = 'calc(50% - 40%)',
	right = 'calc(50% - 22.5%)',
	width = 'auto',
	animation = 'appear',
	isCenter = true,
	isCustom = false
}) => {
	const handleCloseModal = (event) => {
		event.target.id === 'overlay' && onClose();
	};

	return (
		isOpen && (
			<Overlay onClick={handleCloseModal} id="overlay" color={color}>
				<PopupContainer
					isOpen={isOpen}
					onRequestClose={onClose}
					overlayClassName="modal-overlay"
					top={top}
					right={right}
					isCenter={isCenter}
					width={width}
					animation={animation}
					isCustom={isCustom}
				>
					{children != undefined && children}
				</PopupContainer>
			</Overlay>
		)
	);
};

export default Popup;
