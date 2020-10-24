import { GET_USER } from '../actions/types';

const initialState = {
	User: {
		activities: [],
		poems: [
			{
				id: 14,
				author: 'mehra9@gmail.com',
				title: 'New Poem',
				rating: 2,
				content: 'New Poems coming shortlu',
				comments: [],
			},
			{ id: 8, author: 'mehrar1@gmail.com', title: 'kbh', rating: 3, content: 'bjkj', comments: [] },
		],
	},
};

export default function (state = initialState, action) {
	switch (action.type) {
		case GET_USER:
			return {
				...state,
				User: action.payload,
			};

		default:
			return state;
	}
}
