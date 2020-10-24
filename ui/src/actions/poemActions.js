import { GET_POEMS } from './types';
import { GET_COMMENTS } from './types';
import { UPDATE_POEM } from './types';
import { ADD_COMMENT } from './types';
import axios from 'axios';
import { NotificationManager } from 'react-notifications';

export const getBacklog = () => async (dispatch) => {
	try {
		const res = await axios.get('/poems');
		dispatch({
			type: GET_POEMS,
			payload: res.data,
		});
	} catch (err) {
		if (err.response) {
			console.log(err.response.data.message);
			NotificationManager.error('Error message', err.response.data.message, 3000);
		}
	}
};

export const updatePoem = (updatedPoem) => async (dispatch) => {
	try {
		const res = await axios.put(`/poems/${updatedPoem.id}`, updatedPoem);
		NotificationManager.success('Success message', 'poem updated successfully', 3000);
		dispatch({
			type: UPDATE_POEM,
			payload: res.data,
		});
	} catch (err) {
		console.log(err.response.data.message);
		NotificationManager.error('Error message', err.response.data.message, 3000);
	}
};

export const getComments = (id) => async (dispatch) => {
	try {
		const res = await axios.get(`/poems/${id}/comments`);

		console.log(res.data);

		let commentsList = res.data.map((comment, index) => {
			let commentinfo = {
				email: comment.email,
				content: comment.content,
				userImage: comment.userImage ? 'data:image/jpeg;base64,' + comment.userImage : null,
				metadata: '1 day ago ',
			};

			return commentinfo;
		});

		dispatch({
			type: GET_COMMENTS,
			payload: commentsList,
		});
	} catch (err) {
		console.log(err.response.data.message);
		NotificationManager.error('Error message', err.response.data.message, 3000);
	}
};

export const addPoem = (formData, history) => async (dispatch) => {
	try {
		const res = await axios.post('/poems', formData);
		history.push('/');
		NotificationManager.success('Success message', 'poem added successfully', 3000);
	} catch (err) {
		console.log(err.response.data.message);
		NotificationManager.error('Error message', err.response.data.message, 3000);
	}
};

export const addComment = (newComment, id) => async (dispatch) => {
	try {
		const res = await axios.post(`/poems/${id}/comments`, newComment);

		let commentinfo = {
			email: res.data.email,
			content: res.data.content,
			userImage: res.data.userImage ? 'data:image/jpeg;base64,' + res.data.userImage : null,
			metadata: '1 day ago ',
		};

		dispatch({
			type: ADD_COMMENT,
			payload: commentinfo,
		});
	} catch (err) {
		console.log(err.response.data.message);
		NotificationManager.error('Error message', err.response.data.message, 3000);
	}
};
