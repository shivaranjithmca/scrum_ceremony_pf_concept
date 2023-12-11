import {ADD_SCRUM_DATA} from "../constants/action-types";

function ScrumDataReducer(state = {retrospectives: []}, action) {
    if (action.type == ADD_SCRUM_DATA) {
        return {retrospectives: action.payload}
    }

    return state
}

export default ScrumDataReducer
