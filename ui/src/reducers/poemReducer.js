import { GET_POEMS } from "../actions/types";
import { GET_COMMENTS } from "../actions/types";
import { ADD_POEM } from "../actions/types";
import { ADD_COMMENT } from "../actions/types";
import { UPDATE_POEM } from "../actions/types";
const initialState = {

  Poems: [],
  Comments: []

}


export default function (state = initialState, action) {
  switch (action.type) {
    case GET_POEMS:
      return {
        ...state,
        Poems: action.payload,


      };
    case ADD_POEM:
      return {
        ...state,
        Poems: [...state.Poems, action.payload]
      }

      case UPDATE_POEM:
        return {
          ...state,
         
          Poems: [...state.Poems.filter( poem => poem.id !== action.payload.id ), action.payload]
        }

      case ADD_COMMENT:
        return {
          ...state,
          Comments: [...state.Comments, action.payload]
        }

      case GET_COMMENTS:
        return {
          ...state,
          Comments: action.payload
        }

    default:
      return state;
  }
}

