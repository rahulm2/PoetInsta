import { combineReducers } from "redux";
import errorsReducer from "./errorsReducer";
import poemReducer from "./poemReducer";
import userReducer from "./userReducer";

export default combineReducers({
  //
  errors: errorsReducer,
  poems: poemReducer,
  user: userReducer
});