import { GET_USER } from './types';
import { NotificationManager } from 'react-notifications';
import axios from 'axios';

export const getUser = () => async (dispatch) => {
	//const res = await axios.get(`http://localhost:3001/poems`);

	try {
		const res = await axios.get(`/users/user`);
		console.log(res.data);

		dispatch({
			type: GET_USER,
			payload: res.data,
		});
	} catch (err) {
		if (err.response) {
			console.log(err.response.data.message);
			NotificationManager.error('Error message', err.response.data.message, 3000);
		}
	}
};

export const updateUserImage = (uploadImageData) => async (dispatch) => {
	try {
		const res = await axios.post(`/users/upload`, uploadImageData);
		dispatch({
			type: GET_USER,
			payload: res.data,
		});
	} catch (err) {
		console.log(err.response.data.message);
		NotificationManager.error('Error message', err.response.data.message, 3000);
	}
};
